package meta.deep.codegen

import meta.deep.IR.Predef._
import meta.deep.algo.AlgoInfo.{CodeNodePos, EdgeInfo}

import scala.collection.mutable.ArrayBuffer

class GraphMerge() extends StateMachineElement() {

  override def run(compiledActorGraphs: List[CompiledActorGraph])
    : List[CompiledActorGraph] = {
    val graphs = compiledActorGraphs.map(element => {
      element.graph = optimizeCode(element.graph)
      element
    })
    //graphs.foreach(g => GraphDrawing.drawGraph(g.graph, g.name + "_commandmerged"))
    graphs
  }

  /**
    * This function removes unnecessary edges
    * and rewrites state graph to contain new ones
    * @param graph which should be optimized
    * @return new graph with optimized code
    */
  def optimizeCode(graph: ArrayBuffer[EdgeInfo]): ArrayBuffer[EdgeInfo] = {
    val nodeCount: Int =
      graph.flatMap(x => List(x.from.getId, x.to.getId)).distinct.length

    //Create matrix of incoming and outgoing edges
    //Each row contains outgoing edges to other
    //Each column contains incoming edges from others
    val m = Array.fill[Array[Int]](nodeCount)(Array.fill[Int](nodeCount)(0))
    graph.foreach(x => {
      m(x.from.getNativeId)(x.to.getNativeId) = 1
    })

    val outgoing = m.map(_.sum)
    val incoming = m.transpose.map(_.sum)

    val groupedGraphStart = graph.groupBy(_.from.getNativeId)
    val groupedGraphEnd = graph.groupBy(_.to.getNativeId)

    case class MergeInfo(startNode: Int, middleNode: Int, endNode: Int)
    var mergeList: List[MergeInfo] = List()

    /** The code is executed between two nodes:
      * This means, that code can be merged between 3 nodes if following is fullfilled:
      * Node 2: Has exactly one outgoing and one incoming edge and the incoming edge is not a wait and the outgoing edge has no condition
      * Extra: I am not allowed to merge with the next code, if a cycle is created
      * Extra: I am only allowed to merge the code, if the state is the same, otherwise I have to reset state (check with state and stack)
      */
    // TODO Extra: I am not allowed to merge with the next code, if a cycle is created (since wait required somewhere currently skipped)

    var counter = 1 // Do not merge start with end
    //This loops check, if it is possible to merge with the next node
    while (counter < m.length) {
      if (incoming(counter) == 1 && outgoing(counter) == 1) {
        if (!groupedGraphEnd(counter)(0).waitEdge && groupedGraphStart(counter)(
              0).cond == null) {
          if (groupedGraphEnd(counter)(0).edgeState == groupedGraphStart(counter)(
                0).edgeState && groupedGraphEnd(counter)(0).positionStack == groupedGraphStart(
                counter)(0).positionStack) {
            mergeList = MergeInfo(
              groupedGraphEnd(counter)(0).from.getNativeId,
              counter,
              groupedGraphStart(counter)(0).to.getNativeId) :: mergeList
          }
        }
      }
      counter += 1
    }

    mergeList = mergeList.sortBy(_.startNode)
    var replacedNodes: Map[Int, Int] = Map()

    //For posStoreRef
    var replacedNodesEnd: Map[Int, Int] = Map()

    for (entryOriginal <- mergeList) {
      var entry = entryOriginal
      val replacedNodeStart = replacedNodes.get(entry.startNode)
      val replacedNodeEnd = replacedNodesEnd.get(entry.endNode)

      //Change pointer to correct node, if already replaced
      if (replacedNodeStart.isDefined && replacedNodeEnd.isDefined) {
        entry = MergeInfo(replacedNodeStart.get,
                          entryOriginal.middleNode,
                          replacedNodeEnd.get)
      } else if (replacedNodeStart.isDefined) {
        entry = MergeInfo(replacedNodeStart.get,
                          entryOriginal.middleNode,
                          entryOriginal.endNode)
      } else if (replacedNodeEnd.isDefined) {
        entry = MergeInfo(entryOriginal.startNode,
                          entryOriginal.middleNode,
                          replacedNodeEnd.get)
      }

      //isMethod is not relevant anymore, just interested in first graph for different color
      // Create a new edgeInfo
      val firstEdge: EdgeInfo = groupedGraphStart(entry.startNode)
        .find(_.to.getNativeId == entry.middleNode)
        .get
      val secondEdge: EdgeInfo = groupedGraphStart(entry.middleNode)(0)
      val newNode: EdgeInfo = EdgeInfo(
        firstEdge.label + ", " + secondEdge.label,
        firstEdge.from,
        secondEdge.to,
        code"${firstEdge.code}; ${secondEdge.code}",
        secondEdge.waitEdge,
        isMethod = false,
        firstEdge.cond,
        firstEdge.storePosRef ::: secondEdge.storePosRef,
        firstEdge.edgeState,
        -1,
        firstEdge.positionStack
      )
      assert(firstEdge.edgeState == secondEdge.edgeState)
      assert(secondEdge.to.getNativeId == entry.endNode)
      // Remove old edgeInfo and inserted new created ones
      groupedGraphStart(entry.startNode)
        .remove(groupedGraphStart(entry.startNode).indexOf(firstEdge))
      groupedGraphStart(entry.middleNode).remove(0)
      groupedGraphStart(entry.startNode).append(newNode)

      //Keep references of replaced nodes updated
      replacedNodes = replacedNodes + (entry.middleNode -> entry.startNode)
      replacedNodesEnd = replacedNodesEnd + (entry.middleNode -> entry.endNode)
      replacedNodes = replacedNodes.mapValues(x =>
        if (x == entry.middleNode) entry.startNode else x)
      replacedNodesEnd = replacedNodesEnd.mapValues(x =>
        if (x == entry.middleNode) entry.endNode else x)
    }

    val graph2 = groupedGraphStart.foldLeft(ArrayBuffer[EdgeInfo]())((a, b) => {
      // Update jumping positions by changing start and end edges of them
      b._2.foreach(e => {
        e.storePosRef.foreach(edgeGroup => {
          edgeGroup.foreach(edge => {
            edge.from = CodeNodePos(
              replacedNodes.getOrElse(edge.from.getNativeId,
                                      edge.from.getNativeId))
            edge.to = CodeNodePos(
              replacedNodesEnd.getOrElse(edge.to.getNativeId,
                                         edge.to.getNativeId))
          })
        })
      })

      a.appendAll(b._2)
      a
    })

    graph2
  }
}
