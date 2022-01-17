package meta.deep.member

import meta.deep.algo.{AlgoInfo, Send, CallMethod}
import meta.deep.IR.Predef._
import scala.collection.mutable.ListBuffer

/**
* Models an edge between to nodes
*
* @param label          a random name, which is displayed when drawing the graph
* @param from           start node
* @param to             end node
* @param code           actual code, which is executed when
* @param waitEdge       an information, that this edge is increasing the timer
* @param isMethod       is filled out automatically by using the isMethod variable of this class
* @param sendInfo       if this edge is a send, keeps a reference to [[Send]] for relevant info, and also a
*                       boolean which is true if this is the first in sequence of edges representing the send
* @param methodId1      if the edge is part of method, keeps its id
* @param methodCallInfo if the edge is a part of call method, keep the reference to [[CallMethod]] and also the
*                       ordinal number of this edge (first, second or third)
*/

case class EdgeInfo(
    var label: String,
    var from: CodeNode,
    var to: CodeNode,
    var code: OpenCode[Unit],
    waitEdge: Boolean = false,
    isMethod: Boolean = AlgoInfo.isMethod,
    var cond: OpenCode[Boolean] = null,
    var storePosRef: List[List[EdgeInfo]] = Nil, //Refers to callback reference in state
    var edgeState: (Int, Int) = (-1, -1), //Default state in (int, int), modified when merging actors
    var graphId: Int = -1,
    var positionStack: Variable[ListBuffer[List[((Int, Int), Int)]]] =
    AlgoInfo.positionStack,
    var sendInfo: (Send[_], Boolean) = null,
    var methodId1: Int = AlgoInfo.methodId,
    var methodCallInfo: (CallMethod[_], Int) = (null, -1),
) {

    def myCopy(): EdgeInfo = {
        EdgeInfo(label,
            from,
            to,
            code,
            waitEdge,
            isMethod,
            cond,
            storePosRef,
            edgeState,
            graphId,
            positionStack,
            sendInfo,
            methodId1,
            methodCallInfo)
    }

    /**
     * This method converts method nodes to position nodes
     * @param methodLookupTable contains a mapping between the method id and the start position of the method
     * @param methodLookupTableEnd contains a mapping between the method id and end position of the method
     */
    def convertToPosOnly(methodLookupTable: Map[Int, Int],
                        methodLookupTableEnd: Map[Int, Int]): Unit = {
        from match {
            case CodeNodeMtd(methodId, end) =>
                if (!end) {
                from = CodeNodePos(methodLookupTable(methodId))
                } else {
                from = CodeNodePos(methodLookupTableEnd(methodId))
                }
            case CodeNodePos(_) =>
        }

        to match {
            case CodeNodeMtd(methodId, end) =>
                if (!end) {
                to = CodeNodePos(methodLookupTable(methodId))
                } else {
                to = CodeNodePos(methodLookupTableEnd(methodId))
                }
            case CodeNodePos(_) =>
        }
    }
}