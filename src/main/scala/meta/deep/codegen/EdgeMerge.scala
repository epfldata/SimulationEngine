package meta.deep.codegen

//import com.sun.tools.javac.util.Position
import meta.deep.IR.Predef._
import meta.deep.algo.AlgoInfo.{CodeNodePos, EdgeInfo}

import scala.collection.mutable.ArrayBuffer

/**
  * This class merges edges from the graph, if a program path cannot change between the edges.
  * This means, when we look at a graph of the format: n1-e1-n2-e2-n3 (where n denotes a node
  * and e denotes a edge) that n2 has no other edge e, which points to n2 or has no other edge
  *  e, which points from n2.
  */
class EdgeMerge() extends StateMachineElement() {

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
    * This function removes unnecessary nodes by combining two edges into one
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

    //This class is used to represent a triple of nodes (n1,n2,n3), where n2 can be removed
    case class MergeInfo(startNode: Int, middleNode: Int, endNode: Int)

    //This list saves all
    var mergeList: List[MergeInfo] = List()

    /** The code is executed between three nodes:
      * This means, that code can be merged between 3 nodes if following is fulfilled:
      * Node 2: Has exactly one outgoing and one incoming edge and the incoming edge is not a wait and the outgoing edge has no condition
      * Additionally following has to hold:
      * * Detecting cycles and handle them differently
      * * Not allowed to merge, if the state of the edges is different
      *      (that means, they are from different original actors, thus different registers are used in the edge)
      */
    // TODO Cycle detection not implemented, since wait removes cycles

    outgoing.zipWithIndex
      .filter(_._1==1)
      .map(_._2)
      .filter(nodeId =>
        groupedGraphEnd.get(nodeId).isDefined &&
        groupedGraphStart.get(nodeId).isDefined &&
        (!groupedGraphEnd(nodeId)(0).waitEdge) &&
        (groupedGraphStart(nodeId)(0).cond == null) &&
        (groupedGraphEnd(nodeId)(0).edgeState == groupedGraphStart(nodeId)(0).edgeState) &&
        (groupedGraphEnd(nodeId)(0).positionStack == groupedGraphStart(nodeId)(0).positionStack) &&
        incoming(nodeId)==1)
      .foreach(nodeId => {
          mergeList = MergeInfo(
            groupedGraphEnd(nodeId)(0).from.getNativeId,
            nodeId,
            groupedGraphStart(nodeId)(0).to.getNativeId) :: mergeList
        }
      )

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
      // Create a new edgeInfo for the merged edge (newEdge)
      val firstEdge: EdgeInfo = groupedGraphStart(entry.startNode)
        .find(_.to.getNativeId == entry.middleNode)
        .get
      val secondEdge: EdgeInfo = groupedGraphStart(entry.middleNode)(0)
      val newEdge: EdgeInfo = EdgeInfo(
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
      groupedGraphStart(entry.startNode).append(newEdge)

      //Keep references of replaced nodes updated
      replacedNodes = replacedNodes + (entry.middleNode -> entry.startNode)
      replacedNodesEnd = replacedNodesEnd + (entry.middleNode -> entry.endNode)
      replacedNodes = replacedNodes.mapValues(x =>
        if (x == entry.middleNode) entry.startNode else x)
      replacedNodesEnd = replacedNodesEnd.mapValues(x =>
        if (x == entry.middleNode) entry.endNode else x)
    }

    val graph2 = groupedGraphStart.foldLeft(ArrayBuffer[EdgeInfo]())((a, b) => {
      // Update jumping positions of nodes by changing start and end edges to point to the new created edge
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
