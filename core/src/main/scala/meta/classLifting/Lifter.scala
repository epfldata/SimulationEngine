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
  // Custom encoding
  private val modifier_suffix: String = "_"
  val recognizedModifiers: List[String] = List("override", "private").map(i => s"${i}${modifier_suffix}")

  // Squid-specific
  private val method_separator: String = "\\." // "Vehicle.getPrice"

  var rootAgents: List[String] = List("Actor")

  // Return a list of modifiers and the name w.o modifiers
  private def decode_modifiers(input: String): (ListBuffer[String], String) = {
    recognizedModifiers.foldLeft((ListBuffer[String](), input))((x, modifier) => {
      if (x._2.contains(modifier)){
        ((x._1 += modifier.stripSuffix("_")), x._2.replace(modifier, ""))
      } else x
    })
  }

  // Parse the method symbol and call decode modifiers
  private def parse_method_symbol(name: String): (ListBuffer[String], String) = {
    val names: Array[String] = name.split(method_separator)
    assert(names.length==2)
    decode_modifiers(names(1))
  }
  
  // The dependency graph of the agents as an adjacency matrix
  private def buildDependencyGraph(agents: List[Clasz[_ <: Actor]]): Map[String, List[String]] = {
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
  private def dependencyGraphNoRoot(graph: Map[String, List[String]]): Map[String, List[String]] = {
    graph.map(i => (i._1, i._2.filterNot(x => rootAgents.contains(x))))
      .filterNot(_._2.isEmpty)
  }

  /**
   * Check if any local method calls handle messages or wait and reply
   */
  def validMethodDef(name: String, rawCode: String): Boolean = {
    !(name!="main" && (rawCode.contains("SpecialInstructions.waitAndReply") 
      || rawCode.contains("SpecialInstructions.handleMessages")))
  }

  // Each class in Scala can inherit from at most one class and 
  // Squid does not lift traits, we can evaluate the parents sequentially
  private def copyToSubclasses[T](dependencyGraph: Map[String, List[String]], classValuesMap: Map[String, List[T]], newItemsUpdate: (String, List[(String, T)]) => Unit) = {
    // Each class in Scala can inherit from at most one class and 
    // Squid does not lift traits, we can evaluate the parents sequentially
    val unplaced: ListBuffer[String] = ListBuffer()
    unplaced++=dependencyGraph.map(x => x._1)

    while (unplaced.nonEmpty) {
      // copy to agent class a
      val a = unplaced.remove(0)
      // all the agent classes tha a depends on
      val nextLayer: List[String] = dependencyGraph(a) 
      // Recursively traverse all dependent agents. 
      if (!nextLayer.exists(x => unplaced.contains(x))) {
        try{
          // Resolve conflicts using shadowing
          val newItems = nextLayer.map(i => (i, classValuesMap(i))).foldLeft(classValuesMap(a).map((a, _)))((x, red) => {
            red._2.diff(x.map(_._2)).map((red._1, _)) ::: x
          }).filter(_._1!=a)
          // Items in newItems have form (copyFromClass, content)
          newItemsUpdate(a, newItems)
        }
        catch {
          case e: Exception => throw new Exception(f"Exception when copying to subclass ${a}. ${e}")
        }
      } else {
        unplaced.append(a)
      }
    }
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

  /** Maps field names to their field objects
   * 
   */ 
  val fieldsMap: MutMap[String, List[Field]] = MutMap()

  /* Map actor name and symbol names (owner.method) of its methods
    * store in String because we would like to add new methods 
    */

  var MMap: Map[String, List[String]] = Map()

  
  /**
   * Map each subclass with its superclasses, if any
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
          val decoded_name = parse_method_symbol(raw_mtdName)
          val mtdName = f"${c.name}.${decoded_name._2}"
          // println(f"Inside init. Mtd name is ${mtdName} raw name is ${raw_mtdName}")

          if (validMethodDef(decoded_name._2, method.body.asOpenCode.showScala)){ 
            val nextId: Int = Method.getNextMethodId

            // If both @override X and override_X are defined, then override_X shadows the other
            if (!(methodsIdMap.get(mtdName).isDefined && mtdName==raw_mtdName)){
              methodsIdMap = methodsIdMap + (mtdName -> nextId)
              val cde: OpenCode[method.A] = method.body.asOpenCode
              methodsMap = methodsMap + (mtdName -> new MethodInfo(
                decoded_name._1,
                mtdName, 
                method.tparams, 
                method.vparamss, 
                cde, 
                true)(method.A))
              if (!method.body.toString().contains("this@")) {
                ssoMtds = mtdName :: ssoMtds 
              }
              // Add a method tag for the raw method name with prefix to simplify matching process
              if (mtdName != raw_mtdName) {
                // Both raw and decoded method names point to the same function
                methodsIdMap = methodsIdMap + (raw_mtdName -> nextId)
                List(mtdName, raw_mtdName)
              } else {
                List(mtdName)
              }
            } else {
              // discard the shadowed method def
              List()
            }
          } else {
            throw new Exception(f"Invalid definition for method ${raw_mtdName}")
          }
      })
      // assert(classMethodNames.distinct.size == classMethodNames.size)
      // Inject each agent type with method handleMessages to increase code reuse
      methodsIdMap += (c.name +".handleMessages" -> Method.getNextMethodId)
      // Track function definitions of each class
      MMap = MMap + (c.name -> classMethodNames)

      val fields: List[Field] = c.fields.map(x => {
        val varName: String = x.symbol.asTerm.name.toString
        if (x.init.isDefined) {   // state variable
          Field(ListBuffer(),
            varName, 
            x.A.rep.tpe.toString, 
            IR.showScala(x.init.get.rep), 
            x.set.nonEmpty, 
            false)
        } else {                  // parameter
          Field(ListBuffer(),
            varName, 
            x.A.rep.tpe.toString, 
            "",
            x.set.nonEmpty, 
            true)
        }})
      fieldsMap += (c.name -> fields)
    }
  }

  def addMethodsToSubclasses(): Unit = {
    // Create a map for class name and methods including only the suffix
    val localMethodNamesMap: Map[String, List[String]] = 
      MMap.map(x => (x._1, x._2.map(i => parse_method_symbol(i))
        .filterNot(j => j._2=="main"    // do not add main 
          || MMap(x._1).contains(f"${x._1}.private_${j._2}")  // or private
          || recognizedModifiers.exists(i => j._1.contains(i))) // or methods with encoding, which are duplicated
          .map(_._2).distinct))

    val newMethodsUpdate: (String, List[(String, String)]) => Unit =
      (copyToClass: String, newMethods: List[(String, String)]) => {
          val newMtdNames = newMethods.map(p => {
            val mtdName = s"$copyToClass.${p._2}"
            methodsIdMap = methodsIdMap + (mtdName -> Method.getNextMethodId)
            methodsMap = methodsMap + (mtdName -> methodsMap(s"${p._1}.${p._2}").replica(mtdName, true))
            mtdName
          })
          MMap = MMap.updated(copyToClass, MMap(copyToClass):::newMtdNames)
        }
    copyToSubclasses(this.dependencyGraph, localMethodNamesMap, newMethodsUpdate)
  }

  // No need to override a value inherited from parents. Just copy the def
  def addFieldsToSubclasses(): Unit = {
    // keep only public fields of each agent class
    val publicFieldsMap: Map[String, List[Field]] = 
      fieldsMap.map(x => {
        (x._1, x._2.filterNot(i => i.modifiers.contains("private")))
      }).toMap

    // Update the fieldsMap with newly added fields
    val newFieldsUpdate: (String, List[(String, Field)]) => Unit = 
      (copyToClass: String, newFields: List[(String, Field)]) => {
          fieldsMap(copyToClass) = fieldsMap(copyToClass) ::: newFields.map(_._2).map(i => i.replica)
      }
    copyToSubclasses[Field](this.dependencyGraph, publicFieldsMap, newFieldsUpdate)
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
        addMethodsToSubclasses()
      }

      val endTypes = startClasses.map(c => {
        liftActor(c)
      })

      // The modifier of fields may change during lifting. Add to subclass later.
      if (childrenDepGraph.size>0){
        addFieldsToSubclasses()
      }

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
          val methodSymStr: String = actorMtd.split(method_separator).last
          val methodInfo: MethodInfo[_] = methodsMap(actorMtd)

          val argss: List[List[OpenCode[_]]] =
            methodInfo.vparams.zipWithIndex.map(x => {
              x._1.zipWithIndex.map(y => {
                code"$p1.argss(${Const(x._2)})(${Const(y._2)})"
              })
            })
          
          // println(f"Method ${methodSymStr} defInGeneratedCode is ${methodInfo.defInGeneratedCode}")

          IfThenElse(
            code"""$p1.methodInfo==${Const(methodSymStr)}""",
            IfThenElse(
              code"${Const(methodInfo.defInGeneratedCode)}",
              // ScalaCode(f"wrapper_${methodSym}($p1.argss.flatten)")
              ScalaCode(code"$actorSelfVariable.handleNonblockingMessage($p1)"),
              LetBinding(
              Option(resultMessageCall),
              CallMethod[Any](methodId, argss),
              ScalaCode(
                code"""$p1.reply($actorSelfVariable, $resultMessageCall)"""))
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

    var defInGeneratedCode: Boolean = true

    // avoid translating same code repeatedly
    def liftCode[T: CodeType](cde: OpenCode[T]): Algo[T] = {
      if (cache.get(cde).isDefined) {
        // println("Save translation! " + cde)
        cache(cde).asInstanceOf[Algo[T]]
      } else {
        // println(cde)
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

        case code"($x: Iterable[$tb]).foreach[$ta](($y: tb) => $foreachbody)" =>
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

        case code"SpecialInstructions.markPrivate($n: _*): Unit" =>
          n.unsafe_asClosedCode.run.foreach(i => {
            val targetField = fieldsMap(actorName).find(f => f.name==i)
            if (targetField.isEmpty){
              println(f"Warning: private attribute ${i} is not found in agent ${actorName}")
            } else {
              targetField.get.modifiers += "private"
            }
          })          
          NoOp[Unit].asInstanceOf[Algo[T]]

        case code"SpecialInstructions.handleMessages()" =>
          defInGeneratedCode = false
          val handleMessage = CallMethod[Unit](handleMessageId, List(List()))
          cache += (cde -> handleMessage)
          handleMessage.asInstanceOf[Algo[T]]

        case code"SpecialInstructions.waitLabel($x: SpecialInstructions.waitMode, $y: Double)" =>
          defInGeneratedCode = false
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
          defInGeneratedCode = false
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
          defInGeneratedCode = false
          if (methodsIdMap.get(msg.symbol.toString).isDefined){
            val recipientActorVariable: OpenCode[Actor] = msg.args.head.head.asInstanceOf[OpenCode[Actor]]
            val argss: List[List[OpenCode[_]]] = msg.args.tail.map(args => args.toList.map(arg => code"$arg")).toList
            val mname = msg.symbol.toString.split(method_separator).last
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
                      mname,
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
              mtd.split(method_separator).last,
              List(argss.toList))
        }

        case code"${MethodApplication(ma)}:Any "
          if methodsIdMap.get(ma.symbol.toString).isDefined =>
            // println("Method application name is " + ma.symbol.toString)
            //extracting arguments and formatting them
            var argss: List[List[OpenCode[_]]] = ma.args.tail.map(args => args.toList.map(arg => code"$arg")).toList

            val methodName: String = ma.symbol.toString()

            val recipientActorVariable =
              ma.args.head.head.asInstanceOf[OpenCode[Actor]]
            
            val convertLocal = Variable[Boolean]

            val funcName = ma.symbol.toString.split(method_separator).last
            val f = if (actorSelfVariable.toCode != recipientActorVariable) {
              defInGeneratedCode = false
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
                  funcName,
                  argss, true))
              )
            } else {
              // Calling an overloaded method locally
              val actorRep = actorSelfVariable.Typ.rep.toString.split(method_separator).last
              val mid = methodsIdMap.get(f"${actorRep}.${funcName}")
              assert(mid.isDefined)
              CallMethod[T](mid.get, argss).asInstanceOf[Algo[T]]
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
     */ 
    def liftMethod(mtd: MethodInfo[_])(cache: MutMap[OpenCode[_], Algo[_]]): LiftedMethod[_] = {
      mtd match {
        case m: MethodInfo[a] => {
            import m.A 
            val cde: OpenCode[m.A] = m.body.asOpenCode
            val body: Algo[m.A] = liftCode[m.A](cde)
            new LiftedMethod[m.A](m.symbol, body, m.tparams, m.vparams, methodsIdMap(m.symbol), true)
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

        // Failed patterns
        // code"($x: Iterable[$tb]).map[$a1, Iterable[a1]](($y: tb) => $body: a1)($z): Iterable[a1] "
        // code"($x: List[$tb]).map(($y: tb) => $body: a1)($z): List[a1] "
        // code"($x: Traversable[$tb]).map[$a1, Traversable[a1]](($y: tb) => $body: a1)($z): Traversable[a1] "
        // code"($x: Traversable[$tb]).map(($y: tb) => $body: $a1)($z): Traversable[a1] "
        // work
        case code"($x: List[$tb]).map[$a1, List[a1]](($y: tb) => $body: a1)($z): List[a1] " =>
          val f = FlatMap[tb.Typ, a1.Typ](x, y,
            liftCode(code"List($body)"))
          Some(f.asInstanceOf[Algo[T]])

        // case code"($x: Iterable[$tb]).flatMap[$a1, Iterable[a1]](($y: tb) => $body: Iterable[a1])($z): Iterable[a1] " =>
        case code"($x: List[$tb]).flatMap[$a1, List[a1]](($y: tb) => $body: List[a1])($z): List[a1] " =>
          val f = FlatMap[tb.Typ, a1.Typ](x, y,
            liftCode(code"$body"))
          Some(f.asInstanceOf[Algo[T]])

        case code"($x: Iterable[$tb]).forall(($y: tb) => $body): Boolean" =>
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

        case code"($x: Iterable[$tb]).exists(($y: tb) => $body): Boolean" =>
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
            val actorRep = actorSelfVariable.Typ.rep.toString.split(method_separator).last
            val funcName = ma.symbol.toString.split(method_separator).last
            val mtdName = f"${actorRep}.${funcName}"
            if (methodsIdMap.get(mtdName).isDefined){
              Some(CallMethod[T](methodsIdMap(mtdName), argss).asInstanceOf[Algo[T]])
            } else {
              None
            }
          } else {
            // println("Method application " + ma.symbol.toString.split(method_separator).last)
            None
          }

        case _ => 
          None
      }
    }

    // println(f"MMap is ${MMap}")
    // println(f"Methods map is ${methodsMap}")

    var endMethods: List[LiftedMethod[_]] = MMap(actorName).filter(x => methodsMap.get(x).isDefined).map(actorMtd => {
      val lm = liftMethod(methodsMap(actorMtd))(cache)
      // println(f"Lifting method ${actorMtd} ${defInGeneratedCode}")
      methodsMap(actorMtd).defInGeneratedCode = defInGeneratedCode
      defInGeneratedCode = true // overwritten by liftCode when encountering special inst.

      if (lm.symbol.endsWith(".main")) {
        mainAlgo = lm.body 
        None 
      } else {
        Some(lm)
      }}).filter(x => x!=None).toList.map(x => x.get)
    endMethods = endMethods ::: addedSSOMtd.map(m => {liftMethod(m)(cache)})
    // Generate handleMessageMtd strictly after defInGeneratedCode has been updated
    val handleMsg = addHandleMessageMtd()
    endMethods = handleMsg :: endMethods 
    // println(f"Parent names are ${parentNames}")
    ActorType[T](clasz.name,
                 parentNames,
                 fieldsMap(clasz.name),
                 endMethods,
                 mainAlgo,
                 clasz.self.asInstanceOf[Variable[T]])
  }
}
