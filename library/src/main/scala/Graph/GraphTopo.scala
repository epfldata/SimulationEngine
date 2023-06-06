package cloudcity.lib
package Graph

import meta.runtime.{Actor, Message}
import scala.reflect.ClassTag
import scala.collection.mutable.Buffer

import Topo._

class Graph[V: ClassTag](
    val rows: Int,
    val cols: Int,
    val initialMessages: Option[BatchComponentMessage[V]],
    val initialValue: IndexedSeq[V],
    val edges: Map[VertexId, IndexedSeq[VertexId]],
    val vertexUpdateRule: (V, Iterable[V]) => V,
    val componentId: VectorCoord
) extends Component[V] {
    // absolute 2d coord to relative 2d pos: (4, 10) => [(-1, 0), (1, 2)]
    var neighborOffset: Map[Coordinate2D, Iterable[Coordinate2D]] = Map()

    def stageInputNeighborOffset(): Unit = {
        val offset: Int = componentId.getRowId * cols
        var vertexIdCtr: Int = offset

        for (i <- (0 to rows-1)) {
            for (j <- (0 to cols-1)) {
                neighborOffset = neighborOffset + (Coordinate2D(upperNeighborRadius + i, j) -> edges.filter(e => e._2.contains(vertexIdCtr)).map(k => coordinateOffset(i, j, k._1)))
                vertexIdCtr += 1
            }
        }
    }

    private def coordinateOffset(i: Int, j: Int, v: VertexId): Coordinate2D = {
        val vector_min: Int = componentId.getRowId * cols
        val vector_max: Int = vector_min + rows * cols 

        // Absolute 2D coordinate
        val rid = v / cols
        val cid = v % cols

        if ((v < vector_max) && (v >= vector_min)) {
            Coordinate2D(rid - i, cid - j)
        } else {
            Coordinate2D(incomingVectorIndex(rid) - i, cid-j)
        }
    }

    stageInputNeighborOffset()

    override def gather(x: Int, y: Int): Iterable[V] = {
        neighborOffset(Coordinate2D(x, y)).map(i => {
            currentBoard(i.x + x)(i.y + y)
        })
    }

    val (upperNeighborRadius, lowerNeighborRadius) = computeNeighborRadius()

    // println("Upper radius is " + upperNeighborRadius + " lower radius is " + lowerNeighborRadius)

    var currentBoard: Array[Array[V]] = Array.ofDim[V](rows + upperNeighborRadius + lowerNeighborRadius, cols)
    // padding needs to be changed in case of arbitrary graph
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