package meta.classLifting

import meta.deep.IR
import meta.deep.IR.Predef._
import meta.deep.IR.Predef.base.MethodApplication
import meta.deep.IR.TopLevel._
import meta.deep.algo._
import meta.deep.member._
import meta.runtime.{Actor, Message, RequestMessage}

import scala.collection.mutable.ListBuffer

/** Code lifter
  *
  * lifts the code into the deep representation [[meta.deep]]
  */
object Lifter {

  /** Maps method symbols to their IDs
    *
    */
  var methodsIdMap: Map[String, Int] = Map()

  /** Maps method symbols to their methods' information, [[meta.classLifting.MethodInfo]]
    *
    */
  var methodsMap: Map[String, MethodInfo[_]] = Map()

  /* Map actor name and symbol names (owner.method) of its methods
    * store in String because we would like to add new methods 
    */

  var MMap: Map[String, List[String]] = Map()

  /**
   * Map the original method symbol and the method symbol it re-directs to 
   */
  var redirectMap: Map[String, List[String]] = Map()
  
  /**
   * Map each agent (both branch and leaf) with its branch agent parents 
   */
  var inheritance: Map[String, Set[String]] = Map() 

  /**
   * We separate between leafActors and branchActors syntactically 
   * An actor is leaf iff it has a main method that defines its behaviour; otherwise it is a branch 
   */ 
  val leafActors: ListBuffer[Clasz[_ <: Actor]] = new ListBuffer[Clasz[_ <: Actor]]()

  var branchActors: Map[String, Clasz[_ <: Actor]] = Map() 


  def init(initClasses: List[Clasz[_ <: Actor]]): Unit = {
    for (c <- initClasses) {
      var mnamess: List[String] = List[String]()  // array of all the symbol names of this class's methods (owner.method) 
      c.methods.foreach({  
        case m: c.Method[a, b] => 
          import m.A 

          val mtdName: String = m.symbol.toString()
          mnamess = mtdName :: mnamess 
          methodsIdMap = methodsIdMap + (mtdName -> Method.getNextMethodId)
          val cde: OpenCode[m.A] = m.body.asOpenCode
          methodsMap = methodsMap + (mtdName -> new MethodInfo[m.A](
            mtdName, 
            m.tparams, 
            m.vparamss, 
            cde)
          )
      })

      if (mnamess.contains(s"${c.name}.main")) {
        leafActors.append(c) 
        // println(s"Leaf actor: ${c.name}")
      } else {
        branchActors = branchActors + (c.name -> c)
        // println(s"Branch actor: ${c.name}")
      }
      MMap = MMap + (c.name -> mnamess) 
    }
  }

  /** Lifts the classes and object initialization
    *
    * @param startClasses        - classes that need to be lifted, in form of [[Clasz]]
    * @param initializationClass - contains only one method, which has to return a list of [[Actor]]
    * @return deep embedding of the classes
    */

  def apply(startClasses: List[Clasz[_ <: Actor]])
    : List[ActorType[_]] = {

    init(startClasses)

    val endTypes = startClasses.map(c => {
      liftActor(c)
    })

    endTypes
  }

  /** Lifts a specific [[Actor]] class into an ActorType
    *
    * @param clasz representation of class which extends [[Actor]]
    * @tparam T - type of actor
    * @return an [[ActorType]] - deep embedding of an [[Actor]] class
    */
  private def liftActor[T <: Actor](clasz: Clasz[T]) = {
    val parentNames: List[String] = clasz.parents.map(parent => parent.rep.toString())

    val parameterList: List[(String, String)] = clasz.fields.filter(field => !field.init.isDefined)
      .map(x => (s"${x.symbol.asMethodSymbol}", s"${x.A.rep}"))

    import clasz.C
    val actorSelfVariable: Variable[_ <: Actor] =
      clasz.self.asInstanceOf[Variable[T]]
    //lifting states - class attributes

    val endStates = clasz.fields.filter(field => field.init.isDefined).map {
      case field =>
        State[field.A](field.symbol, field.A, field.init.get)
    }

    var endMethods: List[LiftedMethod[_]] = List()
    var mainAlgo: Algo[_] = DoWhile(code"true", Wait())

    //lifting methods - with main method as special case
    clasz.methods.foreach({
      case method: clasz.Method[a, b] =>
        import method.A

        val cde: OpenCode[method.A] = method.body.asOpenCode
        val mtdBody = liftCode[method.A](cde, actorSelfVariable, clasz)

        val mName: String = method.symbol.toString() 

        endMethods = new LiftedMethod[method.A](
          clasz,
          mtdBody,
          methodsIdMap(mName)) {
          override val mtd: cls.Method[method.A, cls.Scp] =
            method.asInstanceOf[this.cls.Method[method.A, cls.Scp]]
        } :: endMethods

        if (mName.endsWith(".main")) {
          mainAlgo = CallMethod[Unit](methodsIdMap(mName), List(List()))
        }
    })

    ActorType[T](clasz.name,
                 parentNames,
                 parameterList,
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

  def liftInitCode(clasz: Clasz[_]): OpenCode[_] = {
    clasz.methods.head.body 
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
      case code"SpecialInstructions.handleMessages()" =>
        //generates an IfThenElse for each of this class' methods, which checks if the called method id is the same
        //as any of this class' methods, and calls the method if it is
        val resultMessageCall = Variable[Any]

        val p1 = Variable[RequestMessage]

        //Default, add back, if message is not for my message handler, it its for a merged actor
        val reqAlgo: Algo[Any] = ScalaCode(
          code"$actorSelfVariable.addReceiveMessages(List($p1))")

        // val callRequest = clasz.methods.foldRight(reqAlgo)((method, rest) => {
        //   val methodId = methodsIdMap(method.symbol)
        //   val methodInfo = methodsMap(method.symbol)
        //   //map method parameters correctly

        //   val argss: List[List[OpenCode[_]]] =
        //     methodInfo.vparams.zipWithIndex.map(x => {
        //       x._1.zipWithIndex.map(y => {
        //         code"$p1.argss(${Const(x._2)})(${Const(y._2)})"
        //       })
        //     })
        val actorName: String = clasz.name 
        val callRequest = MMap(actorName).filterNot(x => x.endsWith(".main")).foldRight(reqAlgo)((actorMtd, rest) => {
            val methodId: Int = methodsIdMap(actorMtd)
            val methodInfo: MethodInfo[_] = methodsMap(actorMtd)

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
            rest)})

        //for each received message, use callCode
        val handleMessage =
            Foreach(
              code"$actorSelfVariable.popRequestMessages",
              p1,
              callRequest
            )
        handleMessage.asInstanceOf[Algo[T]]

      case code"SpecialInstructions.waitLabel($x: SpecialInstructions.waitMode, $y: Double)" =>
        WaitLabel(code"$x.toString()", y).asInstanceOf[Algo[T]]

      case code"SpecialInstructions.interrupt($interval: Double, (() => ${MethodApplication(msg)}))" =>
        val argss: ListBuffer[OpenCode[_]] = ListBuffer[OpenCode[_]]() // in the reverse order
        var mtd: String = msg.symbol.toString()
        Interrupt(actorSelfVariable.toCode,
                  interval,
                  methodsIdMap(mtd),
                  List(argss.toList))

      // asynchronously call a remote method
      case code"SpecialInstructions.asyncMessage[$mt]((() => {${MethodApplication(msg)}}: mt))" =>
        if (methodsIdMap.get(msg.symbol.toString()).isDefined){
          val recipientActorVariable: OpenCode[Actor] = msg.args.head.head.asInstanceOf[OpenCode[Actor]]
          val argss: List[List[OpenCode[_]]] = msg.args.tail.map(args => args.toList.map(arg => code"$arg")).toList

          AsyncSend[T, mt.Typ](
            actorSelfVariable.toCode,
            recipientActorVariable,
            methodsIdMap(msg.symbol.toString),
            argss)
        } else {
          var recipientActorVariable: OpenCode[Actor] = msg.args.last.head.asInstanceOf[OpenCode[Actor]]
          val argss: ListBuffer[OpenCode[_]] = ListBuffer[OpenCode[_]]() // in the reverse order
          var mtd: String = msg.symbol.toString 
          var curriedMtd: IR.Predef.base.Code[Any, _] = msg.args.head.head
          argss.append(msg.args.last.head)

          while (!methodsIdMap.get(mtd).isDefined) {
            curriedMtd match {
              case code"($sa: $st) => ${MethodApplication(mtd2)}: Any" => {
                mtd = mtd2.symbol.toString()
                if (methodsIdMap.get(mtd).isDefined){
                  recipientActorVariable = mtd2.args.head.head.asInstanceOf[OpenCode[Actor]]
                  if (mtd2.args.last.length != argss.length){
                    // Due to closure, foreach(c => async(c.abc())) is different from async(c.abc()) the first var is the recipient
                    if (mtd2.args.last.length + 1 == argss.length ) {
                      recipientActorVariable = argss(0).asInstanceOf[OpenCode[Actor]]
                      argss.remove(0)
                    } else {
                      println(s"msg: $msg, argss: $argss, mtd argss: ${mtd2.args.last}")
                      throw new Exception("Async msg does't support local variables yet. Please make it a Sim variable instead")
                    }
                  }
                } else {
                  argss.append(mtd2.args.last.head)
                }
                curriedMtd = mtd2.args.head.head
              }
              case _ => throw new Exception(s"Error state in asyncMessage! $cde $recipientActorVariable $curriedMtd")
            }
          }

          AsyncSend[T, mt.Typ](
            actorSelfVariable.toCode,
            recipientActorVariable,
            methodsIdMap(mtd),
            List(argss.toList))
        }

      case code"${MethodApplication(ma)}:Any "
        if methodsIdMap.get(ma.symbol.toString()).isDefined =>
          //extracting arguments and formatting them
          var argss: List[List[OpenCode[_]]] = ma.args.tail.map(args => args.toList.map(arg => code"$arg")).toList
          val methodName: String = ma.symbol.toString()

          //method is local - method recipient is this(self)
          val recipientActorVariable =
            ma.args.head.head.asInstanceOf[OpenCode[Actor]]
          if (actorSelfVariable.toCode == recipientActorVariable) {
            val f = CallMethod(methodsIdMap(methodName), argss)
            f.asInstanceOf[Algo[T]]
          }
          //method recipient is another actor - a message has to be sent
          else {
            val f = Send(actorSelfVariable.toCode,
                         recipientActorVariable,
                         methodsIdMap(methodName),
                         argss,
                         true)
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
               case _                       =>
                //  println(Console.RED + s"Lifter warning: possible unsupported code: $cde" + Console.RESET)
                 throw new Exception("Unsupported code inside " + cde)
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
    cde match {
      case code"($x: List[$tb]).map[$a1, List[a1]](($y: tb) => $body: a1)($z): List[a1] " =>
        val f = FlatMap[tb.Typ, a1.Typ](x, y,
          liftCode(code"List($body)", actorSelfVariable, clasz))
        Some(f.asInstanceOf[Algo[T]])

      case code"($x: List[$tb]).flatMap[$a1, List[a1]](($y: tb) => $body: List[a1])($z): List[a1] " =>
        val f = FlatMap[tb.Typ, a1.Typ](x, y,
          liftCode(code"$body", actorSelfVariable, clasz))
        Some(f.asInstanceOf[Algo[T]])

      case code"($x: List[$tb]).forall(($y: tb) => $body): Boolean" =>
        val res = Variable[Boolean]
        val f = LetBinding(Some(res),
          Exists[tb.Typ](x, y,
            liftCode(code"!$body", actorSelfVariable, clasz)),
          IfThenElse(code"$res",
            ScalaCode(code"false"),
            ScalaCode(code"true")
          )
        )
        Some(f.asInstanceOf[Algo[T]])

      case code"($x: List[$tb]).exists(($y: tb) => $body): Boolean" =>
        val f = Exists[tb.Typ](x, y,
          liftCode(code"$body", actorSelfVariable, clasz))
        Some(f.asInstanceOf[Algo[T]])

        // Comment out filter. Use default Scala for better performance. Currently filter doesn't involve DSLs
//      case code"($x: List[$tb]).filter(($y: tb) => $body: Boolean): List[tb] " =>
//        val el = Variable[Boolean]
//
//        val f = FlatMap[tb.Typ, tb.Typ](x, y,
//          LetBinding(Some(el),
//            liftCode(code"$body", actorSelfVariable, clasz),
//            IfThenElse(code"$el",
//              ScalaCode(code"List($y)"),
//              ScalaCode(code"List()"),
//            )))
//        Some(f.asInstanceOf[Algo[T]])

      case code"($x: List[$tb]).foldLeft($a: $ta)(($y: ta, $z: tb) => $body): ta" =>
        // todo
        Some(ScalaCode(cde))

      case code"($v: Boolean).&& $y" =>
        val f = IfThenElse(v,
          liftCode(y, actorSelfVariable, clasz),
          ScalaCode(code"false"))
        Some(f.asInstanceOf[Algo[T]])

      case code"($v: Boolean).|| $y" =>
        val f = IfThenElse(v,
          ScalaCode(code"true"),
          liftCode(y, actorSelfVariable, clasz))
        Some(f.asInstanceOf[Algo[T]])
      case _ => None
    }
  }
}
