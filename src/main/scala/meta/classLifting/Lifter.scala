package meta.classLifting

import meta.deep.IR
import meta.deep.IR.Predef._
import meta.deep.IR.Predef.base.MethodApplication
import meta.deep.IR.TopLevel._
import meta.deep.algo._
import meta.deep.member._
import meta.deep.runtime.{Actor, RequestMessage}

/** Code lifter
  *
  * lifts the code into the deep representation [[meta.deep]]
  */
class Lifter {

  /** Maps method symbols to their IDs
    *
    */
  var methodsIdMap: Map[IR.MtdSymbol, Int] = Map()

  /** Maps method symbols to their methods' information, [[meta.classLifting.MethodInfo]]
    *
    */
  var methodsMap: Map[IR.MtdSymbol, MethodInfo[_]] = Map()

  /** Lifts the classes and object initialization
    *
    * @param startClasses        - classes that need to be lifted, in form of [[Clasz]]
    * @param initializationClass - contains only one method, which has to return a list of [[Actor]]
    * @return deep embedding of the classes
    */

  def apply(startClasses: List[Clasz[_ <: Actor]],
            initializationClass: Clasz[_])
    : (List[ActorType[_]], OpenCode[List[Actor]]) = {
    val actorsInit: OpenCode[List[Actor]] = liftInitCode(initializationClass)
    //Collecting method symbols and info to generate methodsIdMap and methodsMap
    var counter = 0

    startClasses
      .map(c => c.methods)
      .flatten
      .foreach(method => {
        import method.A
        methodsIdMap = methodsIdMap + (method.symbol -> counter)
        //the method is only nonblocking if its return type is a subtype of NBUnit
        var blocking = true
        if (method.A <:< codeTypeOf[NBUnit]) blocking = false
        methodsMap = methodsMap + (method.symbol -> new MethodInfo[method.A](
          method.symbol,
          method.tparams,
          method.vparamss,
          blocking))
        counter += 1
        Method.getNextMethodId
      })
    //lifting types
    val endTypes = startClasses.map(c => {
      liftActor(c)
    })

    (endTypes, actorsInit)
  }

  /** Lifts a specific [[Actor]] class into an ActorType
    *
    * @param clasz representation of class which extends [[Actor]]
    * @tparam T - type of actor
    * @return an [[ActorType]] - deep embedding of an [[Actor]] class
    */
  private def liftActor[T <: Actor](clasz: Clasz[T]) = {
    val parentNames: List[String] = clasz.parents.map(parent => parent.rep.toString())

    import clasz.C
    val actorSelfVariable: Variable[_ <: Actor] =
      clasz.self.asInstanceOf[Variable[T]]
    //lifting states - class attributes
    val endStates = clasz.fields.map {
      case field =>
        val fi = field.init.getOrElse(
          squid.utils.lastWords("Cannot handle no init value yet")
        )
        State[field.A](field.symbol, field.A, fi)
    }
    var endMethods: List[LiftedMethod[_]] = List()
    var mainAlgo: Algo[_] = DoWhile(code"true", Wait())
    //lifting methods - with main method as special case
    clasz.methods.foreach({
      case method: clasz.Method[a, b] =>
        import method.A
        val cde: OpenCode[method.A] = method.body.asOpenCode
        val mtdBody = liftCode[method.A](cde, actorSelfVariable, clasz)

        endMethods = new LiftedMethod[method.A](
          clasz,
          mtdBody,
          methodsMap(method.symbol).blocking,
          methodsIdMap(method.symbol)) {
          override val mtd: cls.Method[method.A, cls.Scp] =
            method.asInstanceOf[this.cls.Method[method.A, cls.Scp]]
        } :: endMethods

        if (method.symbol.asMethodSymbol.name.toString == "main") {
          mainAlgo = CallMethod[Unit](methodsIdMap(method.symbol), List(List()))
        }
    })
    ActorType[T](clasz.name,
                 parentNames,
                 endStates,
                 endMethods,
                 mainAlgo,
                 clasz.self.asInstanceOf[Variable[T]])
  }

  /** Lifts the code for actor initialization
    *
    * @param clasz - initialization class representation - must contain only 1 method, which returns an [[OpenCode]] of list of [[Actor]]s
    * @return - extracted initialization method body
    */
  private def liftInitCode(clasz: Clasz[_]): OpenCode[List[Actor]] = {
    //it's expected that this class' first method initializes actors
    val initMethod = clasz.methods.head
    val initCode = clasz.methods.head.body
    //check if the method returns a list of actors
    if (initMethod.A <:< codeTypeOf[List[Actor]])
      initCode.asInstanceOf[OpenCode[List[Actor]]]
    else {
      throw new Exception("The main method does not return a list of actors")
    }
  }

  /** Lifts an [[OpenCode]](expression) into its deep representation [[Algo]]
    *
    * @param cde               - an [[OpenCode]] that will be lifted
    * @param actorSelfVariable - a self [[Variable]] of this actor, used to create messages
    * @param clasz             - representation of the [[Actor]] type, used to create a message handler for his methods
    * @tparam T - return type of the expression
    * @return [[Algo]] - deep representation of the expression
    */
  private def liftCode[T: CodeType](cde: OpenCode[T],
                                    actorSelfVariable: Variable[_ <: Actor],
                                    clasz: Clasz[_ <: Actor]): Algo[T] = {
    cde match {
      case code"val $x: squid.lib.MutVar[$xt] = squid.lib.MutVar.apply[xt]($v); $rest: T  " =>
        val f = LetBinding(
          Some(x.asInstanceOf[Variable[Any]]),
          liftCode(v.asInstanceOf[OpenCode[Any]], actorSelfVariable, clasz),
          liftCode(rest, actorSelfVariable, clasz),
          mutVar = true,
          xt
        )
        f.asInstanceOf[Algo[T]]
      case code"val $x: $xt = $v; $rest: T  " =>
        val f = LetBinding(Some(x),
                           liftCode(v, actorSelfVariable, clasz),
                           liftCode(rest, actorSelfVariable, clasz))
        f.asInstanceOf[Algo[T]]
      case code"$e; $rest: T  " =>
        val f = LetBinding(None,
                           liftCode(e, actorSelfVariable, clasz),
                           liftCode(rest, actorSelfVariable, clasz))
        f.asInstanceOf[Algo[T]]
      case code"()  " =>
        val f = NoOp()
        f.asInstanceOf[Algo[T]]
      case code"($x: List[$tb]).foreach[$ta](($y: tb) => $foreachbody)  " =>
        val f: Foreach[tb.Typ, Unit] = Foreach(
          x,
          y,
          liftCode(code"$foreachbody; ()", actorSelfVariable, clasz))
        f.asInstanceOf[Algo[T]]
      case code"while($cond) $body" =>
        val f = IfThenElse(
          cond,
          DoWhile(cond, liftCode(body, actorSelfVariable, clasz)),
          NoOp[Unit]())
        f.asInstanceOf[Algo[T]]
      case code"if($cond: Boolean) $ifBody:T else $elseBody: T  " =>
        val f = IfThenElse(cond,
                           liftCode(ifBody, actorSelfVariable, clasz),
                           liftCode(elseBody, actorSelfVariable, clasz))
        f.asInstanceOf[Algo[T]]
      case code"SpecialInstructions.waitTurns(${Const(turns: Int)}) " =>
        val f = LetBinding(None,
          liftWait(turns).asInstanceOf[Algo[T]],
          handleMsg(actorSelfVariable, clasz).asInstanceOf[Algo[T]])
        f.asInstanceOf[Algo[T]]
      case code"${MethodApplication(ma)}:Any  "
          if methodsIdMap.get(ma.symbol).isDefined =>
        //extracting arguments and formatting them
        val argss =
          ma.args.tail.map(args => args.toList.map(arg => code"$arg")).toList
        //method is local - method recipient is this(self)
        val recipientActorVariable =
          ma.args.head.head.asInstanceOf[OpenCode[Actor]]
        if (actorSelfVariable.toCode == recipientActorVariable) {
          val f = CallMethod(methodsIdMap(ma.symbol), argss)
          f.asInstanceOf[Algo[T]]
        }
        //method recipient is another actor - a message has to be sent
        else {
          val f = Send(actorSelfVariable.toCode,
                       recipientActorVariable,
                       methodsIdMap(ma.symbol),
                       argss,
//                        false)
                       methodsMap(ma.symbol).blocking)
          f.asInstanceOf[Algo[T]]
        }
      case _ =>
        //here there is space for some more code patterns to be lifted, by using the liftCodeOther method which can be overriden
        val liftedCode = liftCodeOther(cde, actorSelfVariable, clasz)
        //if liftCodeOther returns something, return that
        if (liftedCode.isDefined) {
          liftedCode.get
        }
        //otherwise, analyze if the cde is legitimate ScalaCode (does not contain any other recognizable code pattern
        // somewhere inside (e.g. an unsupported code pattern could contain a Foreach somewhere inside of it and that
        // would cause problems if it was lifted as ScalaCode)
        else {
          cde analyse {
            case d if d != cde =>
              val c = liftCode(d, actorSelfVariable, clasz)
              c match {
                case scalacode: ScalaCode[_] =>
                case _                       => throw new Exception("Unsupported code inside " + cde)
              }
          }
          val f = ScalaCode(cde)
          f.asInstanceOf[Algo[T]]
        }
    }
  }

  /** Used for operations that were not covered in [[liftCode]]. Lifts an [[OpenCode]](expression) into its deep representation [[Algo]]
    *
    * @param cde               - an [[OpenCode]] that will be lifted
    * @param actorSelfVariable - a self [[Variable]] of this actor, used to create messages
    * @param clasz             - representation of the [[Actor]] type, used to create a message handler for his methods
    * @tparam T - return type of the expression
    * @return [[Algo]] - deep representation of the expression
    */
  def liftCodeOther[T: CodeType](cde: OpenCode[T],
                                 actorSelfVariable: Variable[_ <: Actor],
                                 clasz: Clasz[_ <: Actor]): Option[Algo[T]] = {
    None
  }

  /** Lifts instruction [[meta.classLifting.SpecialInstructions.waitTurns]] into a [[DoWhile]] where in each iteration there's one [[Wait]]
    *
    * @param turns
    * @return
    */
  private def liftWait(turns: Int): Algo[Unit] = {
    if (turns <= 0)
      throw new Exception("waitTurns takes a positive integer as a parameter")
    else if (turns == 1) {
      Wait()
    } else {
      val waitCounter = Variable[Int]
      LetBinding(
        Some(waitCounter),
        ScalaCode(code"0"),
        DoWhile(code"$waitCounter < ${Const(turns)}",
                LetBinding(Some(waitCounter),
                           ScalaCode(code"$waitCounter + 1"),
                           Wait()))
      )
    }
  }

  /* handle msg automatically at the end of each wait call */
  private def handleMsg(actorSelfVariable: Variable[_ <: Actor],
                        clasz: Clasz[_ <: Actor]): Algo[Unit] = {
    //generates an IfThenElse for each of this class' methods, which checks if the called method id is the same
    //as any of this class' methods, and calls the method if it is
    val resultMessageCall = Variable[Any]
    val p1 = Variable[RequestMessage]
    //Default, add back, if message is not for my message handler, it its for a merged actor
    val algo: Algo[Any] = ScalaCode(
      code"$actorSelfVariable.addReceiveMessages(List($p1))")
    val callCode = clasz.methods.foldRight(algo)((method, rest) => {
      val methodId = methodsIdMap(method.symbol)
      val methodInfo = methodsMap(method.symbol)
      //map method parameters correctly
      val argss: List[List[OpenCode[_]]] =
        methodInfo.vparams.zipWithIndex.map(x => {
          x._1.zipWithIndex.map(y => {
            code"$p1.argss(${Const(x._2)})(${Const(y._2)})"
          })
        })
      IfThenElse(
        code"$p1.methodId==${Const(methodId)}",
        LetBinding(
          Option(resultMessageCall),
          CallMethod[Any](methodId, argss),
          ScalaCode(
            code"""$p1.reply($actorSelfVariable, $resultMessageCall)""")),
        rest
      )
    })

    //for each received message, use callCode
    Foreach(
      code"$actorSelfVariable.popRequestMessages",
      p1,
      callCode
    )
  }
}
