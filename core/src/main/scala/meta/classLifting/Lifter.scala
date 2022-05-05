package meta.classLifting

import meta.deep.IR
import meta.deep.IR.Predef._
import meta.deep.IR.Predef.base.MethodApplication
import meta.deep.IR.TopLevel._
import meta.deep.algo._
import meta.deep.member._
import meta.runtime.{Actor, Message, RequestMessage}
import scala.collection.mutable.{Map => MutMap, ListBuffer}

/** Code lifter
  *
  * lifts the code into the deep representation [[meta.deep]]
  */
object Lifter {
  val recognized_modifiers: Set[String] = Set("override")
  val modifier_separator: String = "_"
  val className_separator: String = "\\."
  var rootAgents: List[String] = List("Actor")

  // Return (modifier, name)
  def decode_modifiers(name: String): (String, String) = {
    val names: Array[String] = name.split(className_separator)
    assert(names.length==2)
    val class_name = names(0)
    val mtd_name = names(1)
    val mtd_name_components = mtd_name.split(modifier_separator).partition(x => recognized_modifiers.contains(x))
    val modifierStr = mtd_name_components._1.mkString(" ")
    val nameStr = f"${class_name}.${mtd_name_components._2.mkString(modifier_separator)}"
    (modifierStr, nameStr)
  }

  // The dependency graph of the agents as an adjacency matrix
  def buildDependencyGraph(agents: List[Clasz[_ <: Actor]]): Map[String, List[String]] = {
    val graph: MutMap[String, List[String]] = MutMap()
    // Initialize the graph with the dependency at definition time
    for (agent <- agents) {
      val parents = agent.parents.map(p => p.rep.toString.split("\\.").last)
      graph(agent.name) = parents
    }
    // Identify root nodes of the graph. Assume DAG
    val separatedAgents = graph.partition(x => x._2.diff(rootAgents).isEmpty)
    val parentAgents = separatedAgents._1.map(_._1)
    val childAgentsMap = separatedAgents._2
    // Recursively update children with the dependency of parents
    val unplaced: ListBuffer[String] = ListBuffer()
    unplaced++=childAgentsMap.map(x => x._1)
    while (unplaced.nonEmpty) {
      val a = unplaced.remove(0)
      val nextLayer: List[String] = childAgentsMap(a) 
      if (!nextLayer.exists(x => unplaced.contains(x))) {
        try{
          // Preserve the definition order and remove duplicates
          graph(a) = (nextLayer ::: nextLayer.flatMap(a => graph(a))).distinct
        } catch {
          case e: Exception => throw new Exception(f"Exception when building the dependency graph entry for ${a}. ${e}")
        }
      } else {
        unplaced.append(a)
      }
    }
    graph.toMap
  }

  // Return a new graph with parents in rootAgents removed and remove agents w/o parents.
  def dependencyGraphNoRoot(graph: Map[String, List[String]]): Map[String, List[String]] = {
    graph.map(i => (i._1, i._2.filterNot(x => rootAgents.contains(x))))
      .filterNot(_._2.isEmpty)
  }
}

class Lifter {
  import Lifter._
  /** Maps method symbols to their IDs
    *
    */
  var methodsIdMap: Map[String, Int] = Map()

  /** Maps method symbols to their methods' information 
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
   * Map each subclass with its super classes, if exist 
   */
  var dependencyGraph: Map[String, List[String]] = Map() 

  /**
   * Contain the name of methods which are SSO 
   */ 
  var ssoMtds: List[String] = List[String]() 

  var ssoEnabled: Boolean = false

  def init(initClasses: List[Clasz[_ <: Actor]]): Unit = {
    for (c <- initClasses) {
      val classMethodNames = c.methods.flatMap({
        case method: c.Method[_, _] =>
          import method.A 
          // Decode method symbol for possible modifier string
          val raw_mtdName: String = method.symbol.toString()
          val decoded_name = decode_modifiers(raw_mtdName)
          val mtdName = decoded_name._2
          val nextId: Int = Method.getNextMethodId
          
          // If both @override X and override_X are defined, then override_X shadows the other
          if (!(methodsIdMap.get(mtdName).isDefined && mtdName==raw_mtdName)){
            methodsIdMap = methodsIdMap + (mtdName -> nextId)
            // Add a method tag for the raw method name with prefix to simplify matching process
            if (mtdName != raw_mtdName) {
              // Both raw and decoded method names point to the same function
              methodsIdMap = methodsIdMap + (raw_mtdName -> nextId)
            }
            val cde: OpenCode[method.A] = method.body.asOpenCode
            methodsMap = methodsMap + (mtdName -> new MethodInfo(
              decoded_name._1,
              mtdName, 
              method.tparams, 
              method.vparamss, 
              cde, 
              blockingAnalysis(cde, mtdName))(method.A))
            if (!method.body.toString().contains("this@")) {
              ssoMtds = mtdName :: ssoMtds 
            }
          }

          if (mtdName!=raw_mtdName){
            List(mtdName, raw_mtdName)
          } else {
            List(mtdName)
          }
      })
      // assert(classMethodNames.distinct.size == classMethodNames.size)
      // Inject each agent type with method handleMessages to increase code reuse
      methodsIdMap += (c.name +".handleMessages" -> Method.getNextMethodId)
      // Track function definitions of each class
      MMap = MMap + (c.name -> classMethodNames)
    }
  }

  // graph is an adjaceny matrix for children agents, i.e. with dependencies
  def addMethodsToSubclasses(graph: Map[String, List[String]]): Unit = {
      val unplaced: ListBuffer[String] = ListBuffer()
      unplaced++=graph.map(x => x._1)
      // Create a map for class name and methods including only the suffix
      val localMethodNamesMap: Map[String, List[String]] = 
        MMap.map(x => (x._1, x._2.map(i => i.split(className_separator).last)
          .filterNot(j => j=="main" || recognized_modifiers.exists(i => j.startsWith(i)))))

      // Each class in Scala can inherit from at most one class and 
      // Squid does not lift traits, we can evaluate the parents sequentially
      while (unplaced.nonEmpty) {
        val a = unplaced.remove(0)
        val nextLayer: List[String] = graph(a) 
        if (!nextLayer.exists(x => unplaced.contains(x))) {
          try{
            val newMethods = nextLayer.map(i => (i, localMethodNamesMap(i))).foldLeft(localMethodNamesMap(a).map((a, _)))((x, red) => {
              red._2.diff(x.map(_._2)).map((red._1, _)) ::: x
            }).filter(_._1!=a).map(p => {
              val mtdName = s"$a.${p._2}"
              // println(f"Add new method ${mtdName} based on impl of ${p._1}.${p._2}")
              methodsIdMap = methodsIdMap + (mtdName -> Method.getNextMethodId)
              methodsMap = methodsMap + (mtdName -> methodsMap(s"${p._1}.${p._2}").replica(mtdName, true))
              mtdName
            })
            MMap = MMap.updated(a, MMap(a):::newMethods)
          }
          catch {
            case e: Exception => throw new Exception(f"Exception when adding methods to subclass ${a}. ${e}")
          }
        } else {
          unplaced.append(a)
        }
      }
  }

  /** Lifts the classes and object initialization
    *
    * @param startClasses        - classes that need to be lifted, in form of [[Clasz]]
    * @param initializationClass - contains only one method, which has to return a list of [[Actor]]
    * @return deep embedding of the classes
    */

  def apply(startClasses: List[Clasz[_ <: Actor]])
    : (List[ActorType[_]], Map[String, Int], Map[String, MethodInfo[_]]) = {

      // ssoEnabled = Optimization.sso
      init(startClasses)
      val fullDepGraph = buildDependencyGraph(startClasses)
      val childrenDepGraph = dependencyGraphNoRoot(fullDepGraph)
      this.dependencyGraph = childrenDepGraph

      if (childrenDepGraph.size>0){
        addMethodsToSubclasses(childrenDepGraph)
      }

      val endTypes = startClasses.map(c => {
        liftActor(c)
      })

      (endTypes, methodsIdMap, methodsMap)
  }

  /** Lifts a specific [[Actor]] class into an ActorType
    *
    * @param clasz representation of class which extends [[Actor]]
    * @tparam T - type of actor
    * @return an [[ActorType]] - deep embedding of an [[Actor]] class
    */
  def liftActor[T <: Actor](clasz: Clasz[T]) = {
    val actorName: String = clasz.name 
    
    val handleMessageSym: String = actorName + ".handleMessages"
    val handleMessageId: Int = methodsIdMap(handleMessageSym)
    val parentNames: List[String] = clasz.parents.map(parent => 
      parent.rep.toString)
    // val parentNames: List[String] = dependencyGraph.getOrElse(actorName, List("meta.runtime.Actor"))

    // Do not support inheriting values yet
    val fields: List[Field] = 
      clasz.fields.map(x => {
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
        }})

    import clasz.C
    val actorSelfVariable: Variable[_ <: Actor] =
      clasz.self.asInstanceOf[Variable[T]]

    // Cache the translation results for reuse. 
    val cache: MutMap[OpenCode[_], Algo[_]] = MutMap()

    var mainAlgo: Algo[_] = DoWhile(code"true", Wait())

    var addedSSOMtd: List[MethodInfo[_]] = List() 

    // Add handle message of this actor to the method tables as a special method. Populate the methodsIdMap as well as mehodsMap, but not MMap 
    def addHandleMessageMtd(): LiftedMethod[Unit] = {
        //generates an IfThenElse for each of this class' methods, which checks if the called method id is the same
        //as any of this class' methods, and calls the method if it is
        val resultMessageCall = Variable[Any]

        val p1 = Variable[RequestMessage]

        //Default, add back, if message is not for my message handler, it is for a merged actor
        val reqAlgo: Algo[Any] = ScalaCode(
          code"$actorSelfVariable.addReceiveMessages(List($p1))")

        // main is not callable 
        val callRequest = MMap(actorName).filterNot(x => x.endsWith(".main") || methodsMap.get(x).isEmpty).foldRight(reqAlgo)((actorMtd, rest) => {
          val methodId: Int = methodsIdMap(actorMtd)
          val methodInfo: MethodInfo[_] = methodsMap(actorMtd)

          val argss: List[List[OpenCode[_]]] =
            methodInfo.vparams.zipWithIndex.map(x => {
              x._1.zipWithIndex.map(y => {
                code"$p1.argss(${Const(x._2)})(${Const(y._2)})"
              })
            })
           
          IfThenElse(
            code"$p1.methodInfo==Right(${Const(methodId)})",
            IfThenElse(
              code"${Const(methodInfo.blocking)}",
              LetBinding(
              Option(resultMessageCall),
              CallMethod[Any](methodId, argss),
              ScalaCode(
                code"""$p1.reply($actorSelfVariable, $resultMessageCall)""")),
              ScalaCode(code"$actorSelfVariable.handleNonblockingMessage($p1)")
            ),
            rest
          )
        })

        //for each received message, use callCode
        val handleMessage =
            Foreach(
              code"$actorSelfVariable.popRequestMessages",
              p1,
              callRequest)
        
        new LiftedMethod[Unit](handleMessageSym, handleMessage, List(), List(List()), handleMessageId, true)
    }

    // avoid translating same code repeatedly
    def liftCode[T: CodeType](cde: OpenCode[T]): Algo[T] = {
      if (cache.get(cde).isDefined) {
        // println("Save translation! " + cde)
        cache(cde).asInstanceOf[Algo[T]]
      } else {
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
          val f = LetBinding(Some(x),
                    liftCode(v),
                    liftCode[T](rest))
                    .asInstanceOf[Algo[T]]
          f.asInstanceOf[Algo[T]]
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
          cache += (cde -> f)
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
          val handleMessage = CallMethod[Unit](handleMessageId, List(List()))
          cache += (cde -> handleMessage)
          handleMessage.asInstanceOf[Algo[T]]

        case code"SpecialInstructions.waitLabel($x: SpecialInstructions.waitMode, $y: Double)" =>
          val waitCounter = Variable[Double]
          y match {
            case code"${Const(n)}: Double" =>
              if (n <= 0) {
                throw new Exception("The waitLabel takes a positive value!")
              }
            case _ =>   //  If variable turn number, skip the check
          }

          val f =
                LetBinding(
                  Some(waitCounter),
                  ScalaCode(code"0.0"),
                  DoWhile(code"$waitCounter < $y",
                    LetBinding(Some(waitCounter),
                              ScalaCode(code"${waitCounter} + 1"),
                              Wait()
                              ))).asInstanceOf[Algo[T]]

          cache += (cde -> f)
          f

        case code"SpecialInstructions.waitAndReply($y: Double)" =>
          val waitCounter = Variable[Double]
          y match {
            case code"${Const(n)}: Double" =>
              if (n <= 0) {
                throw new Exception("The waitLabel takes a positive value!")
              }
            case _ =>   //  If variable turn number, skip the check
          }

          val f =
                LetBinding(
                  Some(waitCounter),
                  ScalaCode(code"0.0"),
                  DoWhile(code"$waitCounter < $y",
                    LetBinding(Some(waitCounter),
                              ScalaCode(code"${waitCounter} + 1"),
                              LetBinding(None, 
                                Wait(), 
                                CallMethod[Unit](handleMessageId, List(List()))
                                )))).asInstanceOf[Algo[T]]

          cache += (cde -> f)
          f

        // asynchronously call a remote method
        case code"SpecialInstructions.asyncMessage[$mt]((() => {${m@ MethodApplication(msg)}}: mt))" =>
          if (methodsIdMap.get(msg.symbol.toString).isDefined){
            val recipientActorVariable: OpenCode[Actor] = msg.args.head.head.asInstanceOf[OpenCode[Actor]]
            val argss: List[List[OpenCode[_]]] = msg.args.tail.map(args => args.toList.map(arg => code"$arg")).toList

            val convertLocal = Variable[Boolean]

            val f = LetBinding(Some(convertLocal),
                  ScalaCode(code"""
                  if ($actorSelfVariable._container!= null) {
                    $actorSelfVariable._container.proxyIds.contains($recipientActorVariable.id)
                  } else {
                    false
                  }"""),
                  IfThenElse(code"$convertLocal",
                    ScalaCode(
                      code"""
                      val future = meta.runtime.Future[$mt](value = Some($m))
                      future
                      """
                    ).asInstanceOf[Algo[T]],
                    AsyncSend[T, mt.Typ](
                      actorSelfVariable.toCode,
                      recipientActorVariable,
                      methodsIdMap(msg.symbol.toString),
                      argss)))
            cache += (cde -> f)
            f
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
                  // println(f"Curried method name is ${mtd}")
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
                    try {
                      argss.append(mtd2.args.last.head)
                    } catch {
                      case e: Exception => throw new Exception(s"Unable to find ${mtd} in the method table in $cde")
                    }
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

        // If a method is both redirect and sso? We would like sso to merge it. 
        case code"${MethodApplication(ma)}:Any "
          if methodsIdMap.get(ma.symbol.toString).isDefined =>
            // println("Method application name is " + ma.symbol.toString)
            //extracting arguments and formatting them
            var argss: List[List[OpenCode[_]]] = ma.args.tail.map(args => args.toList.map(arg => code"$arg")).toList

            val methodName: String = ma.symbol.toString()

            val recipientActorVariable =
              ma.args.head.head.asInstanceOf[OpenCode[Actor]]

            // val f = if (redirectMap.contains(methodName)) {
            //   val renamedMethods: List[String] = redirectMap(methodName)

            //   val redirectedLocalMtdName: String = s"${actorName}.${methodName.split("\\.").last}"
            //   val redirectedChildMtdName: String = s"${recipientActorVariable.Typ.rep.toString.split("\\.").last}.${methodName.split("\\.").last}"

            //   if (renamedMethods.contains(redirectedLocalMtdName)) {
            //     println(s"Redirect ${methodName} to local call ${redirectedLocalMtdName}")
            //     // ScalaCode(cde)
            //     CallMethod[T](methodsIdMap(redirectedLocalMtdName), argss)
            //   } else if (renamedMethods.contains(redirectedChildMtdName)) {
            //     println(s"Redirect ${methodName} to child ${redirectedChildMtdName}")
            //     Send[T](actorSelfVariable.toCode,
            //       recipientActorVariable,
            //       methodsIdMap(redirectedChildMtdName),
            //       argss, true)
            //   } else {
            //     throw new Exception(s"Redirected method ${methodName} to no known dest! ${redirectedLocalMtdName}, ${redirectedChildMtdName}")
            //   }
            // } else {
            //   if (ssoMtds.contains(methodName) && ssoEnabled) {
            //     val ssoMtdName: String = s"${actorName}.${methodName.split("\\.").last}_sso"
            //     if (methodsIdMap.get(ssoMtdName).isEmpty) {
            //       methodsIdMap = methodsIdMap + (ssoMtdName -> Method.getNextMethodId)
            //       val newMtdInfo = methodsMap(methodName).replica(ssoMtdName)
            //       methodsMap = methodsMap + (ssoMtdName -> newMtdInfo)
            //       addedSSOMtd = newMtdInfo :: addedSSOMtd 
            //     }
            //     CallMethod[T](methodsIdMap(ssoMtdName), argss)
            //     // ScalaCode(cde)
            //   } else 
            //if (actorSelfVariable.toCode == recipientActorVariable) {
            //     CallMethod[T](methodsIdMap(methodName), argss)
            //   } else {
              //   Send[T](actorSelfVariable.toCode,
              //     recipientActorVariable,
              //     methodsIdMap(methodName),
              //     argss, true)
              // }
            // }
            
            val convertLocal = Variable[Boolean]

            val f = if (actorSelfVariable.toCode != recipientActorVariable) {
                LetBinding(Some(convertLocal), 
                  ScalaCode(code"""
                  if ($actorSelfVariable._container!= null) {
                    $actorSelfVariable._container.proxyIds.contains($recipientActorVariable.id)
                  } else {
                    false
                  }"""),
                  IfThenElse(code"$convertLocal",
                    ScalaCode(cde), // If convert local, then call directly
                    Send[T](actorSelfVariable.toCode,
                    recipientActorVariable,
                    methodsIdMap(methodName),
                    argss, true))
                )
            } else {
              // Calling an overloaded method locally
              val actorRep = actorSelfVariable.Typ.rep.toString.split(className_separator).last
              val funcName = ma.symbol.toString.split(className_separator).last
              val mtdName = f"${actorRep}.${funcName}"
              assert(methodsIdMap.get(mtdName).isDefined)
              CallMethod[T](methodsIdMap(mtdName), argss).asInstanceOf[Algo[T]]
            }
            cache += (cde -> f)
            f 
          
        case _ =>
          liftCodeLastResort(cde)
        }
      }
    }

    def liftCodeLastResort[T: CodeType](cde: OpenCode[T]): Algo[T] = {
      val liftedCode = liftCodeOther(cde)
      //if liftCodeOther returns something, return that
      if (liftedCode.isDefined) {
        liftedCode.get
      } else {
        //otherwise, analyze if the cde is legitimate ScalaCode (does not contain any other recognizable code pattern
        // somewhere inside (e.g. an unsupported code pattern could contain a Foreach somewhere inside of it and that
        // would cause problems if it was lifted as ScalaCode)
        cde analyse {
          case d if d != cde =>
            val c = liftCode(d)
            c match {
              case scalacode: ScalaCode[_] => 
              case _                       =>
                // println(Console.RED + s"Lifter warning: possible unsupported code: $cde" + Console.RESET)
                throw new Exception("Unsupported code inside " + cde)
            }
        }
        val f = ScalaCode(cde)
        f.asInstanceOf[Algo[T]]
      }
    }

    /**
     * This method lift a method from MethodInfo representation to LiftedMethod 
     * We only call liftCode if method body contains a special instruction or issues calls. 
     * Otherwise, we use the ScalaCode representation directly 
     */ 
    def liftMethod(mtd: MethodInfo[_])(cache: MutMap[OpenCode[_], Algo[_]]): LiftedMethod[_] = {
      mtd match {
        case m: MethodInfo[a] => {
            import m.A 
            val cde: OpenCode[m.A] = m.body.asOpenCode 

            if (m.symbol.endsWith(".main")) {
              new LiftedMethod[m.A](m.symbol, liftCode[m.A](cde), m.tparams, m.vparams, methodsIdMap(m.symbol), true)
            } else if (blockingAnalysis(cde, m.symbol)) {
              new LiftedMethod[m.A](m.symbol, liftCode[m.A](cde), m.tparams, m.vparams, methodsIdMap(m.symbol), true)
            } else {
              new LiftedMethod[m.A](m.symbol, ScalaCode(cde), m.tparams, m.vparams, methodsIdMap(m.symbol), false)
            }
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

        case code"${MethodApplication(ma)}:Any " =>
          val recipientActorVariable = ma.args.head.head.asInstanceOf[OpenCode[Actor]]
          var argss: List[List[OpenCode[_]]] = ma.args.tail.map(args => args.toList.map(arg => code"$arg")).toList
          // println(cde + " recipient variable is " + recipientActorVariable)
          // Check if calling an overloaded method
          if(actorSelfVariable.toCode == recipientActorVariable){
            val actorRep = actorSelfVariable.Typ.rep.toString.split(className_separator).last
            val funcName = ma.symbol.toString.split(className_separator).last
            val mtdName = f"${actorRep}.${funcName}"
            if (methodsIdMap.get(mtdName).isDefined){
              Some(CallMethod[T](methodsIdMap(mtdName), argss).asInstanceOf[Algo[T]])
            } else {
              None
            }
          } else {
            // println("Method application " + ma.symbol.toString.split(className_separator).last)
            None
          }

        case _ => 
          None
      }
    }


    var endMethods: List[LiftedMethod[_]] = MMap(actorName).filter(x => methodsMap.get(x).isDefined).map(actorMtd => {
      val lm = liftMethod(methodsMap(actorMtd))(cache)
      if (lm.symbol.endsWith(".main")) {
        mainAlgo = lm.body 
        None 
      } else {
        Some(lm)
      }}).filter(x => x!=None).toList.map(x => x.get)
    endMethods = endMethods ::: addedSSOMtd.map(m => {liftMethod(m)(cache)})
    val handleMsg = addHandleMessageMtd()
    endMethods = handleMsg :: endMethods 
    // println(f"Parent names are ${parentNames}")
    ActorType[T](clasz.name,
                 parentNames,
                 fields,
                 endMethods,
                 mainAlgo,
                 clasz.self.asInstanceOf[Variable[T]])
  }

  /**
   * This method analyses whether a method body is blocking: contains either blocking call or wait statements. 
   * It also analysis whether a method contains special instructions.
   */
  def blockingAnalysis(cde: OpenCode[_], mtdName: String): Boolean = {
    val mtdNameSegs = mtdName.split("\\.")
    val mtdSymbolNoPrefix = mtdNameSegs.last
    val agentPath = mtdNameSegs.dropRight(1).mkString("\\.")

    cde analyse {
      // case code"SpecialInstructions.handleMessages()" =>
      //   if (mtdSymbolNoPrefix != "main"){
      //     throw new Exception(f"${mtdName} contains special instruction handleMessages!")
      //   }
      case code"SpecialInstructions.waitLabel($x: SpecialInstructions.waitMode, $y: Double)" =>
        return true
      case code"SpecialInstructions.waitAndReply($y: Double)" =>
        return true
    }
    false
  }
}
