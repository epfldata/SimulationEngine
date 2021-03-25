package meta.deep.codegen

//import com.sun.tools.javac.util.Position
import meta.deep.IR.Predef._
import meta.deep.member.{CodeNodePos, EdgeInfo}
import scala.collection.mutable.ListBuffer
import meta.deep.member.CompiledActorGraph

/**
  * This class merges edges from the graph, if a program path cannot change between the edges.
  * This means, when we look at a graph of the format: n1-e1-n2-e2-n3 (where n denotes a node
  * and e denotes a edge) that n2 has no other edge e, which points to n2 or has no other edge
  *  e, which points from n2.
  */

class EdgeMerge() extends StateMachineElement() {

  case class MergeInfo(startNode: Int, middleNode: Int, endNode: Int)

  var mergeList: List[MergeInfo] = List()

  override def run(compiledActorGraphs: List[CompiledActorGraph])
    : List[CompiledActorGraph] = {

    val graphs = compiledActorGraphs.map(element => {
      element.graph = iterativeOpt(element.graph)(2)
      element
    })
    //graphs.foreach(g => GraphDrawing.drawGraph(g.graph, g.name + "_commandmerged"))
    graphs
  }

  def iterativeOpt(graph: ListBuffer[EdgeInfo])(remainAttempts: Int): ListBuffer[EdgeInfo] = {
    if (remainAttempts <= 0 ){
      graph
    } else {
      val groupedGraph = preprocess(graph)
      // meta.Util.debug(s"Iterative optimization attempt ${remainAttempts}. Find ${mergeList.size} optimization(s)!")
      if (mergeList.nonEmpty){
        iterativeOpt(optimizeCode(groupedGraph))(remainAttempts - 1)
      } else {
        graph 
      }
    }
  }

  // Fill in the merge list and return groupedGraphStart 
  def preprocess(graph: ListBuffer[EdgeInfo]): Map[Int, ListBuffer[EdgeInfo]] = {
    // Reset mergeList to empty 
    mergeList = List() 

    val nodes: List[Int] =
      graph.toList.flatMap(x => List(x.from.getNativeId, x.to.getNativeId)).distinct 

    val groupedGraphStart = graph.groupBy(_.from.getNativeId)
    val groupedGraphEnd = graph.groupBy(_.to.getNativeId)

    /** The code is executed between three nodes:
      * This means, that code can be merged between 3 nodes if following is fulfilled:
      * Node 2: Has exactly one outgoing and one incoming edge and the incoming edge is not a wait and the outgoing edge has no condition
      * Additionally following has to hold:
      * * Detecting cycles and handle them differently
      * * Not allowed to merge, if the state of the edges is different
      *      (that means, they are from different original actors, thus different registers are used in the edge)
      */
    // Return MergeInfo if a node is eligible for merging 
    def eligibleNodes(nodeId: Int): Option[MergeInfo] = {
      if (groupedGraphEnd.get(nodeId).isDefined &&
          groupedGraphEnd(nodeId).size==1 && 
          groupedGraphStart.get(nodeId).isDefined &&
          groupedGraphStart(nodeId).size==1 && 
          (!groupedGraphEnd(nodeId).head.waitEdge) &&
          (groupedGraphStart(nodeId).head.cond == null) &&
          (groupedGraphEnd(nodeId).head.edgeState == groupedGraphStart(nodeId).head.edgeState) &&
          (groupedGraphEnd(nodeId).head.positionStack == groupedGraphStart(nodeId).head.positionStack) && 
          groupedGraphEnd(nodeId).head.methodId1 == groupedGraphStart(nodeId).head.methodId1) { // Preserve the method boundary 
            Some(MergeInfo(
              groupedGraphEnd(nodeId)(0).from.getNativeId,
              nodeId,
              groupedGraphStart(nodeId)(0).to.getNativeId))
      } else {
        None 
      }
    }

    nodes.foreach(i =>
      if (eligibleNodes(i).isDefined){
        mergeList = eligibleNodes(i).get :: mergeList 
      }
    )

    groupedGraphStart
  }

  /**
    * This function removes unnecessary nodes by combining two edges into one
    * Preserve the method boundary. 
    * @param graph which should be optimized
    * @return new graph with optimized code
    */
  def optimizeCode(groupedGraphStart: Map[Int, ListBuffer[EdgeInfo]]): ListBuffer[EdgeInfo] = {

    assert(mergeList.nonEmpty)    
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

      // Create a new edgeInfo for the merged edge (newEdge)
      val firstEdge: EdgeInfo = groupedGraphStart(entry.startNode)
        .find(_.to.getNativeId == entry.middleNode)
        .get
      val secondEdge: EdgeInfo = groupedGraphStart(entry.middleNode).head 

      assert(firstEdge.methodId1 == secondEdge.methodId1)
      assert(firstEdge.edgeState == secondEdge.edgeState)
      assert(secondEdge.to.getNativeId == entry.endNode)
      
      val newEdge: EdgeInfo = EdgeInfo(
        firstEdge.label + ", " + secondEdge.label,
        firstEdge.from,
        secondEdge.to,
        code"${firstEdge.code}; ${secondEdge.code}",
        secondEdge.waitEdge,
        firstEdge.isMethod,
        firstEdge.cond,
        firstEdge.storePosRef ::: secondEdge.storePosRef,
        firstEdge.edgeState,
        graphId = -1, 
        firstEdge.positionStack, 
        methodId1 = firstEdge.methodId1 
      )
    
      val edgesShareStart = groupedGraphStart.get(entry.startNode)
      edgesShareStart.get.remove(edgesShareStart.get.indexOf(firstEdge))

      groupedGraphStart(entry.middleNode).remove(0)
      edgesShareStart.get.append(newEdge)

      //Keep references of replaced nodes updated
      replacedNodes = replacedNodes + (entry.middleNode -> entry.startNode)
      replacedNodesEnd = replacedNodesEnd + (entry.middleNode -> entry.endNode)
      replacedNodes = replacedNodes.mapValues(x =>
        if (x == entry.middleNode) entry.startNode else x)
      replacedNodesEnd = replacedNodesEnd.mapValues(x =>
        if (x == entry.middleNode) entry.endNode else x)
    }

    val graph2 = groupedGraphStart.foldLeft(ListBuffer[EdgeInfo]())((a, b) => {
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
