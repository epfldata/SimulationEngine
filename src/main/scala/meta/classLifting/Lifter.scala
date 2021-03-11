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

  /** Lifts the classes and object initialization
    *
    * @param startClasses        - classes that need to be lifted, in form of [[Clasz]]
    * @param initializationClass - contains only one method, which has to return a list of [[Actor]]
    * @return deep embedding of the classes
    */

  /**
   * We separate between leafActors and branchActors syntactically 
   * An actor is leaf iff it has a main method that defines its behaviour; otherwise it is a branch 
   */ 
  val leafActors: ListBuffer[Clasz[_ <: Actor]] = new ListBuffer[Clasz[_ <: Actor]]()

  var branchActors: Map[String, Clasz[_ <: Actor]] = Map() 
      
  /** 
   * Initialize methodsIdMap, methodsMap, and MMap
   * We also separate agents into leaf agent or branch agent. A branch agent may inherit from another branch agent  
   */ 
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
            cde))
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

  /**
   * If a leaf agent inherits from a branch agent, then we add methods defined in the branch agent 
   * to the leaf agent; for methods defined in both, we keep the leaf agent version. 
   */ 

   // If a branch agent doesn't have any branch parent, it's completed  
   // 
  def addRedirectMethods(): Unit = {

    val branchActorNames: Set[String] = branchActors.keySet 

    // the buffer contains the names of the branch agents which are yet to find its parents 
    val unplaced = branchActorNames.toBuffer
    
    // a branch agent may inherit from another branch agent 
    for (c <- branchActors.values) {
      val parents: Set[String] = c.parents.map(p => p.rep.toString.split("\\.").last).toSet[String]
      val agentParents: Set[String] = branchActorNames.intersect(parents) 

      if (agentParents.nonEmpty){
        inheritance = inheritance.updated(c.name, agentParents)
      } else {
        unplaced -= c.name 
      }
    }
    
    while (unplaced.nonEmpty) {
      unplaced.foreach(a => {
        val nextLayer: Set[String] = inheritance(a) 
        if (!nextLayer.exists(x => unplaced.contains(x))) {
          inheritance = inheritance.updated(a, nextLayer.flatMap(x => inheritance.getOrElse(x, Set())).union(nextLayer))
          unplaced -= a
        }
      })
    }

    for (c <- leafActors) {
      val parents: Set[String] = c.parents.map(p => p.rep.toString.split("\\.").last).toSet[String]

      val cMethods: List[String] = MMap(c.name).toList.map(x => x.split("\\.").last)

      // parents who are also agents 
      val directAgentParents: Set[String] = branchActorNames.intersect(parents) 

      // include dependencies from its parents
      val agentParents: Set[String] = directAgentParents
        .union(directAgentParents.flatMap(x => inheritance.getOrElse(x, Set())))
      
      inheritance = inheritance.updated(c.name, agentParents)

      agentParents.foreach(p => { 
        MMap(p).foreach(n => {
          val nStr: String = n.split("\\.").last 
          if (!cMethods.contains(nStr)) {
            val mtdName: String = s"${c.name}.${nStr}"
            methodsIdMap = methodsIdMap + (mtdName -> Method.getNextMethodId)
            val origMtdInfo = methodsMap.get(n.toString).get 
            methodsMap = methodsMap + (mtdName -> origMtdInfo.replica(mtdName))
            redirectMap = redirectMap.updated(n, mtdName :: redirectMap.getOrElse(n, List()))
            MMap = MMap.updated(c.name, mtdName :: MMap(c.name))
          } 
        })
      })
    }
  }

  def apply(startClasses: List[Clasz[_ <: Actor]])
    : List[ActorType[_]] = {

    init(startClasses) 
    addRedirectMethods()

    val endTypes = leafActors.toList.map(c => {
      liftActor(c)
    })

    endTypes
  }

  /** Lifts a specific [[Actor]] class into an ActorType
    *
    * @param clasz representation of class which extends [[Actor]]
    * @tparam M - type of actor
    * @return an [[ActorType]] - deep embedding of an [[Actor]] class
    */
  def liftActor[M <: Actor](clasz: Clasz[M]): ActorType[M] = {
    import clasz.C 

    val actorName: String = clasz.name 

    // the parents whom we need to extend, thus excluding any lifted parents 
    val discoveredParents: List[String] = clasz.parents.map(parent => 
      parent.rep.toString())
    val agentParents: List[String] = inheritance.getOrElse(actorName, Set()).toList.map(p => branchActors(p).self.Typ.rep.toString())
    val parentNames: List[String] = discoveredParents.diff(agentParents)

    // Add parameters and state variables
    val fields: List[Field] = 
      (clasz :: inheritance.getOrElse(actorName, Set()).toList.map(x => branchActors(x))).flatMap(c => c.fields.map(x => {
        val varName: String = x.symbol.asTerm.toString().split(" ").last
        if (x.init.isDefined) {   // state variable
          Field(varName, 
            x.A.rep.tpe.toString, 
            IR.showScala(x.init.get.rep), 
            x.set.nonEmpty, 
            false)
        } else {                  // parameter
          Field(varName, 
            x.A.rep.tpe.toString, 
          "",
            x.set.nonEmpty, 
            true)
        }}))

    val actorSelfVariable: Variable[_ <: Actor] = clasz.self.asInstanceOf[Variable[M]]

    /** Lifts an [[OpenCode]](expression) into its deep representation [[Algo]]
      *
      * @param cde               - an [[OpenCode]] that will be lifted
      * @tparam T - return type of the expression
      * @return [[Algo]] - deep representation of the expression
      */
  
    def liftCode[T: CodeType](cde: OpenCode[T]): Algo[T] = {

      cde match {
        case code"val $x: squid.lib.MutVar[$xt] = squid.lib.MutVar.apply[xt]($v); $rest: T  " =>
          val f = LetBinding(
            Some(x.asInstanceOf[Variable[Any]]),
            liftCode(v.asInstanceOf[OpenCode[Any]]),
            liftCode(rest),
            mutVar = true,
            xt)
          f.asInstanceOf[Algo[T]]
        case code"val $x: $xt = $v; $rest: T" =>
          LetBinding(Some(x),
                    liftCode(v),
                    liftCode[T](rest))
                    .asInstanceOf[Algo[T]]
          // f.asInstanceOf[Algo[T]]
        case code"$e; $rest: T  " =>
          val f = LetBinding(None,
                            liftCode(e),
                            liftCode(rest))
          f.asInstanceOf[Algo[T]]

        case code"($x: List[$tb]).foreach[$ta](($y: tb) => $foreachbody)" =>
          val f: Foreach[tb.Typ, Unit] = Foreach(
            x,
            y,
            liftCode(code"$foreachbody; ()"))
          f.asInstanceOf[Algo[T]]

        case code"while($cond) $body" =>
          val f = IfThenElse(
            cond,
            DoWhile(cond, liftCode(body)),
            NoOp[Unit]())
          f.asInstanceOf[Algo[T]]
        case code"if($cond: Boolean) $ifBody:T else $elseBody: T  " =>
          val f = IfThenElse(cond,
                            liftCode(ifBody),
                            liftCode(elseBody))
          f.asInstanceOf[Algo[T]]
        case code"SpecialInstructions.handleMessages()" =>
          //generates an IfThenElse for each of this class' methods, which checks if the called method id is the same
          //as any of this class' methods, and calls the method if it is
          val resultMessageCall = Variable[Any]

          val p1 = Variable[RequestMessage]

          //Default, add back, if message is not for my message handler, it its for a merged actor
          val reqAlgo: Algo[Any] = ScalaCode(
            code"$actorSelfVariable.addReceiveMessages(List($p1))")

          // main is not callable 
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
          // println(handleMessage)
          handleMessage.asInstanceOf[Algo[T]]

        case code"SpecialInstructions.waitLabel($x: SpecialInstructions.waitMode, $y: Double)" =>
          WaitLabel[T](code"$x.toString()", y)

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
              methodsIdMap(msg.symbol.toString()),
              argss)
          } else {
            var recipientActorVariable: OpenCode[Actor] = msg.args.last.head.asInstanceOf[OpenCode[Actor]]
            val argss: ListBuffer[OpenCode[_]] = ListBuffer[OpenCode[_]]() // in the reverse order
            var mtd = msg.symbol.toString()
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

            val recipientActorVariable =
              ma.args.head.head.asInstanceOf[OpenCode[Actor]]

            if (redirectMap.contains(methodName)) {
              val redirectedLocalMtdName: String = s"${actorName}.${methodName.split("\\.").last}"
              val redirectedChildMtdName: String = s"${recipientActorVariable.Typ.rep.toString.split("\\.").last}.${methodName.split("\\.").last}"

              if (redirectMap(methodName).contains(redirectedLocalMtdName)) {
                println(s"Redirect ${methodName} to local call ${redirectedLocalMtdName}")
                CallMethod[T](methodsIdMap(redirectedLocalMtdName), argss)
              } else if (redirectMap(methodName).contains(redirectedChildMtdName)) {
                println(s"Redirect ${methodName} to child ${redirectedChildMtdName}")
                Send[T](actorSelfVariable.toCode,
                  recipientActorVariable,
                  methodsIdMap(redirectedChildMtdName),
                  argss, true)
              } else {
                throw new Exception(s"Redirected method ${methodName} to no known dest! ${redirectedLocalMtdName}, ${redirectedChildMtdName}")
              }
            } else { 
              if (actorSelfVariable.toCode == recipientActorVariable) {
                CallMethod[T](methodsIdMap(methodName), argss)
              } else {
              Send[T](actorSelfVariable.toCode,
                  recipientActorVariable,
                  methodsIdMap(methodName),
                  argss, true)
              }
            }

        case _ =>
          //here there is space for some more code patterns to be lifted, by using the liftCodeOther method which can be overriden
          val liftedCode = liftCodeOther(cde)
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
              val c = liftCode(d)
              c match {
                case scalacode: ScalaCode[_] =>
                case _                       =>
                  //  println(Console.RED + s"Lifter warning: possible unsupported code: $cde" + Console.RESET)
                  // throw new Exception("Unsupported code inside " + cde)

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
    def liftCodeOther[T: CodeType](cde: OpenCode[T]): Option[Algo[T]] = {
      cde match {
        case code"()" =>
          val f = NoOp[T]()
          Some(f.asInstanceOf[Algo[T]])

        case code"($x: List[$tb]).map[$a1, List[a1]](($y: tb) => $body: a1)($z): List[a1] " =>
          val f = FlatMap[tb.Typ, a1.Typ](x, y,
            liftCode(code"List($body)"))
          Some(f.asInstanceOf[Algo[T]])

        case code"($x: List[$tb]).flatMap[$a1, List[a1]](($y: tb) => $body: List[a1])($z): List[a1] " =>
          val f = FlatMap[tb.Typ, a1.Typ](x, y,
            liftCode(code"$body"))
          Some(f.asInstanceOf[Algo[T]])

        case code"($x: List[$tb]).forall(($y: tb) => $body): Boolean" =>
          val res = Variable[Boolean]
          val f = LetBinding(Some(res),
            Exists[tb.Typ](x, y,
              liftCode(code"!$body")),
            IfThenElse(code"$res",
              ScalaCode(code"false"),
              ScalaCode(code"true")
            )
          )
          Some(f.asInstanceOf[Algo[T]])

        case code"($x: List[$tb]).exists(($y: tb) => $body): Boolean" =>
          val f = Exists[tb.Typ](x, y,
            liftCode(code"$body"))
          Some(f.asInstanceOf[Algo[T]])

          // Comment out filter. Use default Scala for better performance. Currently filter doesn't involve DSLs
  //      case code"($x: List[$tb]).filter(($y: tb) => $body: Boolean): List[tb] " =>
  //        val el = Variable[Boolean]
  //
  //        val f = FlatMap[tb.Typ, tb.Typ](x, y,
  //          LetBinding(Some(el),
  //            liftCode(code"$body", clasz),
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
            liftCode(y),
            ScalaCode(code"false"))
          Some(f.asInstanceOf[Algo[T]])

        case code"($v: Boolean).|| $y" =>
          val f = IfThenElse(v,
            ScalaCode(code"true"),
            liftCode(y))
          Some(f.asInstanceOf[Algo[T]])
        case _ => None
      }
    }

    var mainAlgo: Algo[_] = DoWhile(code"true", Wait())

    //lifting methods - with main method as special case

    val callableMethods: List[LiftedMethod[_]] = MMap(actorName).map(actorMtd => {

      methodsMap(actorMtd) match {
        case m: MethodInfo[a] => {
    
          val mtdBody = liftCode[m.A](m.body)

          if (m.symbol == s"${actorName}.main") {
            mainAlgo = mtdBody 
            None 
          } else {
            Some(new LiftedMethod[m.A](m.symbol, mtdBody, m.tparams, m.vparams, methodsIdMap(m.symbol)))
          }
        }
      }
    }).filter(x => x!=None).toList.map(x => x.get)
    
    ActorType[M](actorName,
                 parentNames,
                 fields,
                 callableMethods,
                 mainAlgo,
                 clasz.self.asInstanceOf[Variable[M]])
  }

  /** Lifts the code for actor initialization
    *
    * @param clasz - initialization class representation - must contain only 1 method 
    * @return - extracted initialization method body
    */
  def liftInitCode(clasz: Clasz[_]): OpenCode[_] = {
    clasz.methods.head.body 
  }
}
