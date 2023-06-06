package cloudcity.lib
package Graph

import scala.reflect.ClassTag
import Topo._

class Array2D[V: ClassTag](
    val rows: Int,
    val cols: Int,
    val initialMessages: Option[BatchComponentMessage[V]],
    val initialValue: IndexedSeq[V],
    val edges: Map[VertexId, IndexedSeq[VertexId]],
    val neighborKernel: Array[Coordinate2D],
    val vertexUpdateRule: (V, Iterable[V]) => V,
    val componentId: VectorCoord
) extends GenerativeKernel[V] {
    
    val upperNeighborRadius: Int = neighborKernel.foldLeft(0)((i, j) => Math.max(i, j.y))    
    val lowerNeighborRadius: Int = Math.abs(neighborKernel.foldLeft(Int.MaxValue)((i, j) => Math.min(i, j.y)))
    // println("Upper radius is " + upperNeighborRadius + " lower radius is " + lowerNeighborRadius)

    var currentBoard: Array[Array[V]] = Array.ofDim[V](rows + upperNeighborRadius + lowerNeighborRadius, cols)
    initialMessages match {
        case None => 
            var ctr: Int = 0
            for (i <- (upperNeighborRadius to rows+upperNeighborRadius-1)) {
                for (j <- (0 to cols-1)) {
                    currentBoard(i)(j) = initialValue(ctr)
                    ctr+=1
                }
            }
            updateLocalMessage()
        case Some(x) => x.content.foreach(k => tbr(k))
    }

    var newBoard: Array[Array[V]] = Array.ofDim[V](rows + upperNeighborRadius + lowerNeighborRadius, cols)
    for (i <- currentBoard.indices) {
        for (j <- currentBoard(i).indices) {
            newBoard(i)(j) = currentBoard(i)(j)
        }
    }
}
