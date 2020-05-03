package meta.deep.codegen

import meta.deep.IR.Predef._
import meta.deep.algo.AlgoInfo.{CodeNodeMtd, CodeNodePos, EdgeInfo}
import meta.deep.algo.{AlgoInfo, CallMethod, Send, AsyncSend}
import meta.deep.member.Method
import meta.deep.runtime.Actor

import scala.collection.mutable.{ArrayBuffer, ListBuffer}

/** This optimization removes unnecessary messaging. Unnecessary messages are the ones that are sent to a stateless
  *  server. A stateless server here means a class whose methods don't change any of its attributes. Because of that,
  *  its methods can be moved to the actors that need its services and turned into local methods of those actors,
  *  and all of the messaging can be replaced with local method calls instead. Since calling local methods doesn't
  *  take any time, while sending messages does, the optimization will add artificial waits, to simulate sending
  *  a message, and because of that it will not change the behavior of the actor.
  *
  * WORKS ONLY IF USED BEFORE [[EdgeMerge]
  */
//TODO when copying a method copy it into my ActorType to stay consistent
class SSO(
    val statelessServers: List[String],
    val removeWait: Boolean = true)
    extends StateMachineElement() {

  /** map of all the changes that need to be executed. each [[EdgeInfo]] key in this map will be substituted
    *  by the list of [[EdgeInfo]]s in the value
    */
  var changes: Map[EdgeInfo, List[EdgeInfo]] = Map()

  /** for each copied method, saves the originalId -> newId, as to not copy the same method twice
    *
    */
  var oldToNewMtdIds: Map[String, Map[Int, Int]] = Map()

  /** a flag which indicates whether the optimization done or not
    *  since the algorithm is iterative, it will not be over until there's an iteration without any changes in it
    */
  var optimizationDone = false

  override def run(compiledActorGraphs: List[CompiledActorGraph])
    : List[CompiledActorGraph] = {
    var graphs: List[CompiledActorGraph] = List()
    while (!optimizationDone) {
      optimizationDone = true
      graphs = compiledActorGraphs.map(element => {
        optimizeElement(
          element,
          compiledActorGraphs.filter(otherElement => otherElement != element))
      })
    }
    graphs.foreach(g =>
      g.graph.foreach(edge => edge.positionStack = g.positionStack.head))
    graphs.foreach(g => utilObj.mtdToPosNodes(g.graph))
    //    graphs.foreach(g => GraphDrawing.drawGraph(g.graph, g.name+" SSOmerged"))
    graphs
  }

  /** checks if this element sends messages to a stateless server, and if it does,
    *  it copies the methods used and changes the sends to local method calls
    * @param element - element whose graph is being optimized
    * @param rest - the rest of the elements, used to find the methods used by send edges
    */
  def optimizeElement(element: CompiledActorGraph,
                      rest: List[CompiledActorGraph]): CompiledActorGraph = {

    /** copies the method from neededElement to element and returns the new methodId
      *
      * @param element element to copy the method to
      * @param neededElement element whose method is being copied
      * @param methodId id of the needed method
      */
    def copyMethod(element: CompiledActorGraph,
                   neededElement: CompiledActorGraph,
                   methodId: Int): Int = {
      //first check if its already copied. if it is, just return the local method id
      if (oldToNewMtdIds
            .getOrElse(element.name, Map[Int, Int]())
            .get(methodId)
            .isDefined) {
        oldToNewMtdIds(element.name)(methodId)
      } else {
        //get the next available method id, and copy the states and variables of the other elements
        val newMethodId = Method.getNextMethodId

        val mergedVariables = utilObj.mergeVariables(element, neededElement)
        element.parameterList = mergedVariables._1
        element.variables = mergedVariables._2
        element.variables2 = mergedVariables._3
        neededElement.actorTypes.foreach(at => {
          element.actorTypes.head.states =
            (element.actorTypes.head.states ::: at.states).distinct
        })
        //find the part of the graph that needs to be copied
        val methodToCopyGraph: ArrayBuffer[EdgeInfo] = utilObj.getEdgesByMtdId(neededElement.graph, methodId)
        val oldPos: Int = methodToCopyGraph.head.from.asInstanceOf[CodeNodePos].pos
        val freePos: Int = utilObj.getFreePos(element.graph)

        //moving the tos and froms to fit this graph,
        // fixing the method id,
        // fixing the reference from another actor to self,
        // from another actors response message to this response message
        // and from another actors return value this return value
        methodToCopyGraph.foreach(edge1 => {
          edge1.methodId1 = newMethodId
          neededElement.actorTypes.foreach(at => {
            val selfThis =
              element.actorTypes.head.self.asInstanceOf[Variable[Actor]]
            val otherThis = at.self.asInstanceOf[Variable[Actor]]
            //FIXME this will maybe break the app after merging a few graphs together
            // because here theres no check if the right return value is taken (from all of the merged graphs)
            val selfReturnValue = element.returnValue.head
            val otherReturnValue = neededElement.returnValue.head
            val selfResponseMessage = element.responseMessage.head
            val otherResponseMessage = neededElement.responseMessage.head
            if (edge1.code != null) {
              edge1.code = edge1.code.subs(otherThis).~>(selfThis.toCode)
              edge1.code =
                edge1.code.subs(otherReturnValue).~>(selfReturnValue.toCode)
              edge1.code = edge1.code
                .subs(otherResponseMessage)
                .~>(selfResponseMessage.toCode)
            }
          })
        })
        utilObj.moveGraphPositions(methodToCopyGraph, freePos - oldPos, 0)

        //add the new method to the graph
        element.graph = element.graph ++ methodToCopyGraph
        //remember that the method was added. do it here, so in case of recursion it will not get copied again, just called
        oldToNewMtdIds = oldToNewMtdIds + (element.name -> (oldToNewMtdIds
          .getOrElse(element.name, Map[Int, Int]()) + (methodId -> newMethodId)))

        //after copying the graph check if it has a call method somewhere,
        // if it does, that method has to be copied from the other actor as well
        // and the edges calling the method need to be changed
        var methodCallIndex = 0
        methodToCopyGraph.foreach(edge1 => {
          if (edge1.methodCallInfo._1 != null) {
            val calledMethodId = edge1.methodCallInfo._1.methodId
            var copiedMethodId = -1
            //if it was already copied, no need to do it again
            if (oldToNewMtdIds
                  .getOrElse(element.name, Map[Int, Int]())
                  .get(calledMethodId)
                  .isDefined) {
              copiedMethodId = oldToNewMtdIds(element.name)(calledMethodId)
            } else {
              copiedMethodId =
                copyMethod(element, neededElement, calledMethodId)
              oldToNewMtdIds = oldToNewMtdIds + (element.name -> (oldToNewMtdIds.getOrElse(
                element.name,
                Map[Int, Int]()) + (methodId -> newMethodId)))
            }
            edge1.label = edge1.label.replaceFirst(calledMethodId.toString,
                                                   copiedMethodId.toString)
            edge1.methodCallInfo =
              (CallMethod[Any](copiedMethodId, edge1.methodCallInfo._1.argss),
               methodCallIndex)
            methodCallIndex = (methodCallIndex + 1) % 3
            //if this edge contains a CodeNodeMtd, its method id has to be changed
            edge1.from match {
              case c: CodeNodeMtd =>
                edge1.from = c.copy(id = copiedMethodId)
              case _ =>
            }
            edge1.to match {
              case c: CodeNodeMtd =>
                edge1.to = c.copy(id = copiedMethodId)
              case _ =>
            }
          }
        })
        //need to fix the restore positions of each method call
        // and also for each copied method call, set the positions correctly
        val copiedMethodCalls =
          methodToCopyGraph.groupBy(edge1 => edge1.methodCallInfo._1)
        copiedMethodCalls.foreach(group => {
          if (group._1 != null) {
            setPosRef(group._2)
            setMtdRef(group._2)
          }
        })
        newMethodId
      }
    }

    /** * given a list of call method edges, it will set them the right storePosRef
      * called after copying a callMethod from another graph
      *
      * @param callMethodEdges - list of call method edges, where each call method will have exactly 3 edges
      *                        (otherwise it will not work). the second of the three edges has to be the pos ref
      */
    @scala.annotation.tailrec
    def setPosRef(callMethodEdges: ArrayBuffer[EdgeInfo]): Unit = {
      val (currentMethodEdges, restMethodEdges) = callMethodEdges.splitAt(3)
      currentMethodEdges.head.storePosRef = List(
        List(currentMethodEdges.tail.head))
      if (restMethodEdges.nonEmpty) {
        setPosRef(restMethodEdges)
      }
    }

    /** given a list of call method edges, it will set them the right tos and froms, to the right [[CodeNodeMtd]]
      *
      * @param callMethodEdges - list of call method edges, where each call method will have exactly 3 edges
      *                        (otherwise it will not work)
      */
    @scala.annotation.tailrec
    def setMtdRef(callMethodEdges: ArrayBuffer[AlgoInfo.EdgeInfo]): Unit = {
      val (currentMethodEdges, restMethodEdges) = callMethodEdges.splitAt(3)
      val methodId = currentMethodEdges.head.methodCallInfo._1.methodId
      currentMethodEdges.head.to = CodeNodeMtd(methodId)
      currentMethodEdges.tail.head.from = CodeNodeMtd(methodId, end = true)
      if (restMethodEdges.nonEmpty) {
        setMtdRef(restMethodEdges)
      }
    }

    /** creates 3 call method edges for the newMethodId
      *
      * @param newMethodId -
      * @param oldMethodId -
      * @param sendEdge
      * @return
      */
    def createCallMethodEdges(newMethodId: Int,
                              oldMethodId: Int,
                              sendEdge: EdgeInfo): ArrayBuffer[EdgeInfo] = {
      val newEdges = utilObj.createCallMethodEdges(newMethodId, sendEdge)
      //rewrite the compile only methods to runtime methods
      CreateActorGraphs.methodVariableTableStack(newMethodId) = CreateActorGraphs.methodVariableTableStack(oldMethodId)
      CreateActorGraphs.methodVariableTable(newMethodId) = CreateActorGraphs.methodVariableTable(oldMethodId)
      utilObj.rewriteCallMethod(newEdges)
    }


    //steps:
    //1. find the actorType which has this method
    //2. find the method's node position
    //3. copy the method to this elements graph
    //4. create the call method edges which will replace the send edges
    // (and translate the graph by a certain amount, since the might be more or less edges after replacing)
    //5. replace the send edges with call method edges
    element.graph.foreach(edge => {
      if (edge.sendInfo != null) {
        val send = edge.sendInfo._1
        val methodId = send.methodId
        val neededElement = (element :: rest).find(element =>
          element.graph.exists(edge2 => edge2.methodId1 == methodId))
        if (neededElement.isEmpty)
          throw new Exception(
            "Message requests a non existent method")
        //check if the element containing this graph is a stateless server
        if (neededElement.get.actorTypes.forall(at =>
              statelessServers.contains(at.name))) {
          optimizationDone = false
          val newMethodId = copyMethod(element, neededElement.get, methodId)

          // TODO: remove two trailing edges, as done in actor merge
          if (send.blocking && !edge.sendInfo._2) { // remove non-leading send edges
            changes = changes + (edge -> List())
          } else {
            var newEdges = createCallMethodEdges(newMethodId, methodId, edge)
            if (send.blocking) {
              newEdges = utilObj.addGlue(newEdges, edge.methodId1, removeWait)
              changes = changes + (edge -> newEdges.toList)
            } else {
              // TODO: doesn't work
              val moveThreshold = newEdges.head.from.asInstanceOf[CodeNodePos].pos
              val moveAmount = 1
              utilObj.moveGraphPositions(element.graph, moveAmount, moveThreshold)
              changes = changes + (edge -> newEdges.toList)
            }
          }
        }
      }
    })
    changes.foreach(change => {
      element.graph = element.graph.flatMap(edge =>
        if (edge == change._1) change._2 else List(edge))
//      GraphDrawing.drawGraph(element.graph, element.name + "_SSO")
    })
    element
  }
}
