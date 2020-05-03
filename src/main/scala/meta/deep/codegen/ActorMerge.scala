package meta.deep.codegen

import meta.deep.IR.Predef._
import meta.deep.algo.AlgoInfo.{CodeNodePos, EdgeInfo}
import meta.deep.algo.{AlgoInfo, CallMethod, Send}
import meta.deep.codegen.CreateActorGraphs.MutVarType
import meta.deep.member.Method
import meta.deep.member.ActorType

import scala.collection.mutable
import scala.collection.mutable.{ArrayBuffer, ListBuffer}
import meta.classLifting.Lifter
import meta.deep.runtime.Actor

/**
  * Combines two actor types together and creates a new actor type. The original ones are still there, so the new one
  * can be used afterwards if needed.
  * A new added graph can be accessed with Name1_Name2
  * @param mergeData a list of actortype pair names which should be merged. It is not allowed to merge the same actor with itself.
  */
class ActorMerge(mergeData: List[(String, String)])
    extends StateMachineElement() {

//  val oldToNewMtdIds: mutable.Map[String, mutable.Map[Int, Int]] = mutable.Map()

  override def run(compiledActorGraphs: List[CompiledActorGraph])
    : List[CompiledActorGraph] = {
    //For testing assume, the merge of first two actors

    var newActorGraphs = compiledActorGraphs

    mergeData.foreach(mergeType => {
      val cAG1 = newActorGraphs.find(_.name == mergeType._1)
      val cAG2 = newActorGraphs.find(_.name == mergeType._2)

      if (cAG1.isEmpty || cAG2.isEmpty) {
        throw new RuntimeException("Actortype not found")
      }

      val a1 = cAG1.get
      val a2 = cAG2.get

      a1.graph.foreach(edge => edge.graphId = 1)
      a2.graph.foreach(edge => edge.graphId = 2)

      // replace send messages with local message calls
      var a1_updated_graph: ArrayBuffer[EdgeInfo] = a1.graph.clone()
      var a2_updated_graph: ArrayBuffer[EdgeInfo] = a2.graph.clone()

//      println(Lifter.methodsIdMap)
//      val oldToNewMtdIds: mutable.Map[Int, Int] = mutable.Map()
//      val oldMethodId: Int = 2
//      oldToNewMtdIds += (copyMethod(oldMethodId, a1_updated_graph) -> oldMethodId)

//      replaceSends(a1_updated_graph, mergeType._2, 1)
      replaceSends(a2_updated_graph, mergeType._1, 2)
//      GraphDrawing.drawGraph(a2_updated_graph, a2.name + "_with_no_mtd")

      val wa1 = waitGraph(a1_updated_graph)
//      GraphDrawing.drawGraph(wa1, a1.name+ "_wait")

      val wa2 = waitGraph(a2_updated_graph)
//      GraphDrawing.drawGraph(wa2, a2.name + "_wait")

      val mg = generateMergedStateMachine(wa1, wa2)
//      GraphDrawing.drawMergeGraph(mg, a1.name + "_" + a2.name + "_merge")

      val finalGraph = combineActors(mg, a1_updated_graph, a2_updated_graph)
//      GraphDrawing.drawGraph(finalGraph, a1.name + "_" + a2.name + "_merged")

      /*
        The merged graph should have a single return value, reuse the one from a1
       */
      val a1This = a1.actorTypes.head.self.asInstanceOf[Variable[Actor]]
      val a2This = a2.actorTypes.head.self.asInstanceOf[Variable[Actor]]
      val a1Return = a1.returnValue.head
      val a2Return = a2.returnValue.head

      finalGraph.foreach(edge =>
        if (edge.code!= null){
          edge.code = edge.code.subs(a2This).~>(a1This.toCode)
          edge.code = edge.code.subs(a2Return).~>(a1Return.toCode)
        })

      newActorGraphs = CompiledActorGraph(
        a1.name + "_" + a2.name,
        (a1.parentNames ::: a2.parentNames).distinct,
        a1.parameterList.filter(x => x._2!=a2.actorTypes.head.X.rep.toString()) :::
          a2.parameterList.filter(x => x._2!=a1.actorTypes.head.X.rep.toString()),
        finalGraph,
        a1.variables.filter(x => !(x.A <:< a2.actorTypes.head.X)) :::
          a2.variables.filter(x => !(x.A <:< a1.actorTypes.head.X)),
        a1.variables2 :::
          a2.variables2.tail
            .filter(x => (x.A.rep.toString() != "squid.lib.package.MutVar[meta.deep.runtime.ResponseMessage]"))
            .filter(x => x.A.rep.toString() != "scala.collection.mutable.Map[String,meta.deep.runtime.ResponseMessage]"),
        a1.actorTypes ::: a2.actorTypes,
        a1.positionStack ::: a2.positionStack,
        a1.returnValue,
        a1.responseMessage,
        a1.responseMessagess,
      ) :: newActorGraphs
    })

    newActorGraphs
  }

  def newCallMethodEdges(methodId: Int,
                         sendEdge: EdgeInfo): ArrayBuffer[EdgeInfo] = {
    val newEdges = utilObj.createCallMethodEdges(methodId, sendEdge)
    utilObj.rewriteCallMethod(newEdges)
  }

  def getReceiverName(msg: Send[_]): String = {
    msg.actorRef.rep.dfn.typ.toString.split("\\.").last
  }

  /*
   * Identify the part of the graph containing the method
   * Return the methodId
   */
  def copyMethod(methodId: Int, graph: ArrayBuffer[EdgeInfo]): Int = {
    val newId = Method.getNextMethodId
    val methodGraph = graph.filter(edge => edge.methodId1== methodId)
                           .map(edge => edge.myCopy())
    methodGraph.foreach(edge => edge.methodId1 = newId)
    val oldPos = methodGraph.head.from.asInstanceOf[CodeNodePos].pos
//    var freePos = utilObj.getFreePos()
    newId
  }

  // For testing, allow some hardcode value with remainders
  def replaceSends(graph: ArrayBuffer[EdgeInfo], receiverType: String, graphId: Int): Unit = {
    /* ScalaCode (return value = receiver),
    * LetBinding (resolve the type of the receiver and create and binding mutvar),
    * Send, Send, Send, Send, Send
     */
    val firstOffset: Int = 2 // two edges precede the leading edge (setup the receiver and environment)
    val lastOffset: Int = 4 // four edges after the leading edge (total four send edges)

    val leadingSendEdge: ArrayBuffer[EdgeInfo] = graph.filter(edge => (edge.sendInfo!=null)
      && (getReceiverName(edge.sendInfo._1) == receiverType)
      && (edge.sendInfo._2))

    var newEdgesMap: Map[Int, ArrayBuffer[EdgeInfo]] = Map[Int, ArrayBuffer[EdgeInfo]]()

    // TODO: replace 2 with algorithms that find the correct method id
    graph --= leadingSendEdge
      .map(edge => {
        val edgeIdx = graph.indexOf(edge)
        newEdgesMap = newEdgesMap + (edgeIdx -> {
          val newEdges: ArrayBuffer[EdgeInfo] = newCallMethodEdges(2, edge)
          newEdges.head.from = graph(edgeIdx - firstOffset).from
          newEdges.last.to = graph(edgeIdx + lastOffset).to
          newEdges.foreach(x => {
            x.positionStack = edge.positionStack
            x.graphId = graphId
          })
          newEdges
        })
        edgeIdx})
      .flatMap(x => Range(x-firstOffset, x + lastOffset + 1)) // Range exclude the last element
      .map(x => graph(x))

    newEdgesMap.values.foreach(x => graph++=x)

    // TODO: rewrite the mtd lookup, using push-based registration rather than repeated looping
    // Because we might have unresolved positions (methods in the other actor, don't convert to pos nodes yet
    utilObj.mtdToPosNodes(graph)
  }


  /**
    * This functions generates an abstract graph with only wait edges.
    * This means, that from a startnode all paths are followed, until a wait edge is reached and the two positions are put
    * as a node into the waitgraph
    * This is required to generate a merged state machine, where only the wait edges generate a new state
    * @param graph the input graph, which should be anaylzed
    * @return a wait graph
    */
  def waitGraph(graph: ArrayBuffer[EdgeInfo]): ArrayBuffer[EdgeInfo] = {
    val graphStart = graph.groupBy(_.from.getNativeId)
    val newGraph: ArrayBuffer[EdgeInfo] = ArrayBuffer[EdgeInfo]()
    var startNodes: List[Int] = List()
    var edgeList = List[(Int, Int)]()

    def waitGraphInner(currentNode: Int,
                       edgeInfo: EdgeInfo,
                       visited: List[Int]): Unit = {
      //We are looping
      if (visited.contains(currentNode)) {
        return
      }

      if (edgeInfo == null) {
        //Already handled that node as start node
        if (startNodes.contains(currentNode)) {
          return
        }
        startNodes = currentNode :: startNodes
      }

      val startNode = graphStart.getOrElse(currentNode, ArrayBuffer())

      //NOTE: this cannot happen due to extension at creating
      if (startNode.isEmpty) {
        println("End node", currentNode)
      }

      startNode.foreach(edge => {
        //Remove all data about the node, make it as abstract as possible
        var edgeTargetPos = edge.to.getNativeId
//        if (replaced.get(edgeTargetPos).isDefined){
//          edgeTargetPos = replaced(edgeTargetPos)
//        }
        val edgeTargetCodeNode = CodeNodePos(edgeTargetPos)

        var newEdgeInfo =
          EdgeInfo(currentNode + "->" + edgeTargetPos,
                   edge.from,
                   edgeTargetCodeNode,
                   null,
                   edge.waitEdge,
                   isMethod = false,
                   null,
                   List())

        if (edgeInfo != null) {
          newEdgeInfo =
            EdgeInfo(edgeInfo.from.getNativeId + "->" + edgeTargetPos,
                     edgeInfo.from,
                     edgeTargetCodeNode,
                     null,
                     edge.waitEdge,
                     isMethod = false,
                     null,
                     List())}

        if (edge.waitEdge) {
          if (!edgeList.contains(
                (newEdgeInfo.from.getNativeId, newEdgeInfo.to.getNativeId))) {
            newGraph.append(newEdgeInfo)
            edgeList = (newEdgeInfo.from.getNativeId,
                        newEdgeInfo.to.getNativeId) :: edgeList
          }
          waitGraphInner(edgeTargetPos, null, List())
        } else {
          waitGraphInner(edgeTargetPos, newEdgeInfo, currentNode :: visited)
        }
      })
    }

    waitGraphInner(0, null, List())

    newGraph
  }

  /**
    * This functions generates a product graph based on the transitions on wait graphs
    * @param graph1 a waitgraph of one graph
    * @param graph2 a waitgraph of a second graph
    * @return a merged graph containing the combined transitions between the two graphs
    */
  def generateMergedStateMachine(
      graph1: ArrayBuffer[EdgeInfo],
      graph2: ArrayBuffer[EdgeInfo]): ArrayBuffer[MergeInfo] = {
    val startGraph1 = graph1.groupBy(_.from.getNativeId)
    val startGraph2 = graph2.groupBy(_.from.getNativeId)

    val mergedGraph: ArrayBuffer[MergeInfo] = ArrayBuffer[MergeInfo]()

    var newStateCounter: Int = 0
    var stateMapping: Map[(Int, Int), Int] = Map[(Int, Int), Int]()

    for (stateA <- startGraph1.keys.toList.sorted) {
      for (stateB <- startGraph2.keys.toList.sorted) {
        for (symbol1 <- startGraph1(stateA)) {
          for (symbol2 <- startGraph2(stateB)) {

            val startPos =
              stateMapping.getOrElse((stateA, stateB), newStateCounter)
            if (startPos == newStateCounter) {
              newStateCounter = newStateCounter + 1
              stateMapping = stateMapping + ((stateA, stateB) -> startPos)
            }
            val endStateA = symbol1.to.getNativeId
            val endStateB = symbol2.to.getNativeId

            val endPos =
              stateMapping.getOrElse((endStateA, endStateB), newStateCounter)
            if (endPos == newStateCounter) {
              newStateCounter = newStateCounter + 1
              stateMapping = stateMapping + ((endStateA, endStateB) -> endPos)
            }

            val newEdgeInfo = MergeInfo(
              CodeNodePos(startPos),
              CodeNodePos(endPos),
              (symbol1.from.asInstanceOf[CodeNodePos],
               symbol1.to.asInstanceOf[CodeNodePos]),
              (symbol2.from.asInstanceOf[CodeNodePos],
               symbol2.to.asInstanceOf[CodeNodePos])
            )
            mergedGraph.append(newEdgeInfo)
          }
        }
      }
    }

    val mergedGraphReachableList = ArrayBuffer[Int]()
    val startMergedgraph = mergedGraph.groupBy(_.from.getNativeId)

    def calculateReachable(currentNode: Int): Unit = {
      val node = startMergedgraph(currentNode)
      if (mergedGraphReachableList.contains(currentNode)) {
        return
      }
      mergedGraphReachableList.append(currentNode)
      node.foreach(edge => calculateReachable(edge.to.getNativeId))
    }

    calculateReachable(0)

    val mergedGraphReachable = ArrayBuffer[MergeInfo]()
    //This removes unreached states
    mergedGraph.foreach(edge => {
      if (mergedGraphReachableList.contains(edge.from.getNativeId)) {
        if (mergedGraphReachableList.contains(edge.to.getNativeId)) {
          mergedGraphReachable.append(edge)
        }
      }
    })

    mergedGraphReachable
  }

  /**
    * This functions combines two graphs together.
    * Therefore it goes along the edges of the first graph until a wait edge is reached and continues then with the second
    * graph until a wait edge is reached. This is done for all start positions in the merged graph.
    * At the end the end of a sub-graph is combined with the start of the next-subgraph.
    * @param mergedGraph the merged state machine
    * @param graph1 the graph of actor1
    * @param graph2 the graph of actor2
    * @return a combined graph between actor1 and actor2
    */
  def combineActors(mergedGraph: ArrayBuffer[MergeInfo],
                    graph1: ArrayBuffer[EdgeInfo],
                    graph2: ArrayBuffer[EdgeInfo]): ArrayBuffer[EdgeInfo] = {
    val graph1Start = graph1.groupBy(_.from.getNativeId)
    val graph2Start = graph2.groupBy(_.from.getNativeId)
    val graph1Reachable: collection.mutable.Map[Int, List[Int]] =
      collection.mutable.Map[Int, List[Int]]()
    val graph2Reachable: collection.mutable.Map[Int, List[Int]] =
      collection.mutable.Map[Int, List[Int]]()

    def calculateReachableStates(graphStart: Map[Int, ArrayBuffer[EdgeInfo]],
                                 currentNode: Int,
                                 visited: List[Int]): List[Int] = {
      //Node already visited
      if (visited.contains(currentNode)) {
        return Nil
      }

      val edgesX = graphStart.get(currentNode)
      //No further edges to handle, end node, so this node is reachable
      if (edgesX.isEmpty) {
        return List(currentNode)
      }

      val edges = edgesX.get
      var reachable: List[Int] = Nil

      edges.foreach(edge => {
        if (edge.waitEdge) {
          reachable = edge.to.getNativeId :: reachable
        } else {
          reachable = calculateReachableStates(
            graphStart,
            edge.to.getNativeId,
            currentNode :: visited) ::: reachable
        }
      })
      reachable.distinct
    }

    graph1Start.keys.foreach(x => {
      graph1Reachable(x) = calculateReachableStates(graph1Start, x, Nil)
    })
    graph2Start.keys.foreach(x =>
      graph2Reachable(x) = calculateReachableStates(graph2Start, x, Nil))

    val mergedGraphStart = mergedGraph.groupBy(_.from.pos)

    var handledGraphStarts = List[Int]()

    var reachedStatesGlobal = Map[Int, Map[Int, Int]]()
    var startGraphGlobalMap = Map[Int, Int]()
    var posCounter = 0
    val globalGraph: ArrayBuffer[EdgeInfo] = ArrayBuffer[EdgeInfo]()

    //old to new ones: startState, contextState (-1 if first graph, else end of first graph, edge)
    var edgeMapping = Map[EdgeInfo, List[EdgeInfo]]()

    def generateGraph(fromPos: Int): Unit = {
      val start = mergedGraphStart(fromPos)
      // A position has a steady start state per graph, it just depends on the control flow which next state is reached
      val start1Pos = start(0).graph1._1.pos
      val start2Pos = start(0).graph2._1.pos

      val graph: ArrayBuffer[EdgeInfo] = ArrayBuffer[EdgeInfo]()

      val reachableStates = start.map(x => (x.graph1._2.pos, x.graph2._2.pos))

      startGraphGlobalMap = startGraphGlobalMap + (fromPos -> posCounter)

      /**
        * Algorithm idea:
        * For graph 1 follow edges until reaching a reachable state
        * Then for graph2 follow edges until reaching a reachable state
        */
      var reachedStateTmp = -1
      var reachedStates = Map[Int, Int]()
      // Graph 1: once per state
      val posMapping = collection.mutable.Map[Int, Int]()
      // Graph 2: reset for each subgraph (wait per graph1)
      val posMapping2 = collection.mutable.Map[Int, Int]()

      def generateEdges(graphStart: Map[Int, ArrayBuffer[EdgeInfo]],
                        graphPos: Int,
                        graphStart2: Map[Int, ArrayBuffer[EdgeInfo]],
                        graphPos2: Int,
                        posMappingI: collection.mutable.Map[Int, Int]): Unit = {
        val start1Edges = graphStart.getOrElse(graphPos, ArrayBuffer())

        val nodePos = posCounter

        //This cannot happen, due to extension at beginning ... No node from this edge, continue with second graph here
        if (start1Edges.isEmpty) {
          println("End reached", graphPos, start)
        }

        start1Edges.foreach(edge => {
          val edgeTargetId: Int = edge.to.getNativeId

          if (graphStart2 != null && !reachableStates.exists(
                x =>
                  graph1Reachable
                    .getOrElse(edgeTargetId, List())
                    .contains(x._1))) {
            /*println("DEBUG 1: State not possible", graphPos, edgeTargetId)*/
            return
          }
          if (graphStart2 == null && !reachableStates
                .filter(_._1 == reachedStateTmp)
                .exists(x =>
                  graph2Reachable
                    .getOrElse(edgeTargetId, List())
                    .contains(x._2))) {
            /*println("DEBUG 2: State not possible",
                    graphPos,
                    edgeTargetId,
                    reachedStateTmp)*/
            return
          }

          val nextPos = posMappingI.getOrElse(edgeTargetId, posCounter + 1)
          var newPos = false
          if (nextPos > posCounter) {
            posCounter = posCounter + 1
            posMappingI(edgeTargetId) = posCounter
            newPos = true
          }

          //Since we combine two graphs, the first one is not allowed to wait
          var waitEdge = edge.waitEdge
          if (graphStart2 != null) {
            waitEdge = false
          }

          val newEdge = EdgeInfo(edge.label,
                                 CodeNodePos(nodePos),
                                 CodeNodePos(nextPos),
                                 edge.code,
                                 waitEdge,
                                 edge.isMethod,
                                 edge.cond,
                                 edge.storePosRef,
                                 positionStack = edge.positionStack)

          // Following idea:
          // An old edge, gets duplicated n times.
          // To know to which edge we have to jump to, we have to save a mapping from the old stored edge, to all new stored "same" edges
          // Since at the moment of generating the code, we are not aware, where all the edges are, we save in a map, the old edge, with a list
          // of the n new edges. Then at the end, we can replace the storePosRef with the corresponding list of edges
          var storedEdgeMapping = edgeMapping.getOrElse(edge, List[EdgeInfo]())
          val to1Pos = if (graphStart2 != null) -1 else reachedStateTmp
          storedEdgeMapping = newEdge :: storedEdgeMapping
          edgeMapping = edgeMapping + (edge -> storedEdgeMapping)

          //Set edgeState to current State in graph
          newEdge.edgeState = (fromPos, to1Pos)

          graph.append(newEdge)

          if (!edge.waitEdge) {
            if (newPos) {
              generateEdges(graphStart,
                            edgeTargetId,
                            graphStart2,
                            graphPos2,
                            posMappingI)
            }
          } else {
            if (graphStart2 != null) {
              reachedStateTmp = edgeTargetId
              posMapping2.clear()
              generateEdges(graphStart2, graphPos2, null, 0, posMapping2)
              reachedStateTmp = -1
            } else {
              reachedStates = reachedStates + (posCounter -> start
                .find(z =>
                  reachedStateTmp == z.graph1._2.pos && edgeTargetId == z.graph2._2.pos)
                .get
                .to
                .pos)
            }
          }
        })
      }

      generateEdges(graph1Start, start1Pos, graph2Start, start2Pos, posMapping)
      reachedStatesGlobal = reachedStatesGlobal + (fromPos -> reachedStates)

      handledGraphStarts = fromPos :: handledGraphStarts

      globalGraph.appendAll(graph)

      start.foreach(edge => {
        if (!handledGraphStarts.contains(edge.to.pos)) {
          posCounter = posCounter + 1
          generateGraph(edge.to.pos)
        }
      })
    }

    generateGraph(0)

    //Combine the subgraphs together, so that subgraph 0 to 1 is merged with 1 to 2
    reachedStatesGlobal.foreach(x => {
      x._2.foreach(edgeData => {
        globalGraph.append(
          EdgeInfo("",
                   CodeNodePos(edgeData._1),
                   CodeNodePos(startGraphGlobalMap(edgeData._2)),
                   code"()",
                   waitEdge = false,
                   isMethod = false,
                   null,
                   Nil))
      })
    })

    //Rewrite store pos references
    globalGraph.foreach(edge => {
      edge.storePosRef = edge.storePosRef.map(edgeRefGroup => {
        edgeRefGroup.flatMap(edgeRef => {
          val newEdges = edgeMapping(edgeRef)
          newEdges
        })
      })
    })

    globalGraph
  }

}

/**
  * this class, stores only meta data needed for merging
  * @param from position of startnode
  * @param to position of endnode
  * @param graph1 actual position of start and end node of graph1
  * @param graph2 actual position of start and end node of graph2
  */
case class MergeInfo(from: CodeNodePos,
                     to: CodeNodePos,
                     graph1: (CodeNodePos, CodeNodePos),
                     graph2: (CodeNodePos, CodeNodePos))
