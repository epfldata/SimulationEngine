package cloudcity.lib
package Graph

import meta.runtime.{Actor}
import scala.reflect.ClassTag

import Topo._

trait Component[V] extends Topo[V] {
    val componentId: VectorCoord
    var componentAgentId: Long = 0.toLong
    var connectedComponents: IndexedSeq[Component[V]] = Vector[Component[V]]()
    var children: IndexedSeq[Component[V]] = Vector[Component[V]]()

    val rows: Int
    val cols: Int
    // number of rows padded on top
    val upperNeighborRadius: Int
    // number of rows padded at bottom
    val lowerNeighborRadius: Int
    var currentBoard: Array[Array[V]]
    var newBoard: Array[Array[V]]

    // An index that maps an absolute row id to a local row id (with padding) of an incoming vector message 
    var incomingVectorIndex: Map[Int, Int] = Map()
    // An index that maps an absolute row id to a sequence of local row ids (with padding) for outgoing vector messages 
    var outgoingVectorIndex: Map[Int, IndexedSeq[Int]] = Map()

    // val initialValue: IndexedSeq[(VertexId, V)]
    val initialValue: IndexedSeq[V]
    val edges: Map[VertexId, IndexedSeq[VertexId]]
    val initialMessages: Option[BatchComponentMessage[V]]
    
    def partition(segments: Int)(implicit classTag: ClassTag[V]): IndexedSeq[Component[V]] = {
        val rowsPerTile: Int = (rows / segments).toInt
        val partitionedComponents: IndexedSeq[Component[V]] = this match {
            case c: Array2D[V] =>         
                Range(0, segments).map(i => {
                    new Array2D[V](rowsPerTile, cols, None, c.initialValue.slice(i*rowsPerTile*cols, (i+1)*rowsPerTile*cols),  
                    edges.filter(e => (Range(i*rowsPerTile*cols, (1+i)*rowsPerTile*cols).contains(e._1) || Range(i*rowsPerTile*cols, (1+i)*rowsPerTile*cols).contains(e._2))), c.neighborKernel,
                    vertexUpdateRule, VectorCoord(rowsPerTile*i, rowsPerTile*(i+1)-1)).asInstanceOf[Component[V]]
                })
            case c: Graph[V] => 
                Range(0, segments).map(i => {
                    new Graph[V](rowsPerTile, cols, None, c.initialValue.slice(i*rowsPerTile*cols, (i+1)*rowsPerTile*cols),  
                    edges.filter(e => (Range(i*rowsPerTile*cols, (1+i)*rowsPerTile*cols).contains(e._1) || Range(i*rowsPerTile*cols, (1+i)*rowsPerTile*cols).contains(e._2))), 
                    vertexUpdateRule, VectorCoord(rowsPerTile*i, rowsPerTile*(i+1)-1)).asInstanceOf[Component[V]]
                })        
        }

        Range(0, segments).foreach(i => {
            partitionedComponents(i).connectedComponents = Vector(partitionedComponents((i-1+segments)%segments), partitionedComponents((i+1)%segments))
        })
        children = partitionedComponents
        partitionedComponents
    }

    def merge(children: IndexedSeq[Component[V]]): Unit = ???
    // Given the local coordinate of a cell in the topo, return the list of connected neighbor values
    def gather(x: Int, y: Int): Iterable[V] = ???

    // Determine the value of upperNeighborRadius and lowerNeighborRadius based on edges or kernel
    def computeNeighborRadius(): (Int, Int) = {
        val vector_min: Int = componentId.getRowId * cols
        val vector_max: Int = vector_min + rows * cols
        val incomingEdges = edges.filter(i => {
            (i._1 < vector_min) || (i._1 >= vector_max) && !i._2.filter(j => (vector_min<=j) && (j < vector_max)).isEmpty
        })
        val (upperEdges, lowerEdges) = incomingEdges.toSeq.sortBy(_._1).partition(_._1 < vector_min)

        val helperMtd = (sortedEdges: Seq[(VertexId, Seq[VertexId])], rowIdxOffset: Int) => {
            var cache: Int = -1
            var rowIdxCtr: Int = rowIdxOffset
            sortedEdges.map(i => {
                val rid = i._1 / cols
                if (rid != cache) {
                    cache = rid
                    incomingVectorIndex = incomingVectorIndex + (rid -> rowIdxCtr)
                    rowIdxCtr += 1
                }
            })
            rowIdxCtr
        } 

        // Minimum number of rows to store incoming messages from vertices with lower vertex ids 
        val upperNeighborRadius = helperMtd(upperEdges, 0)
        // Minimum number of rows to store incoming messages from vertices with higher vertex ids
        val lowerNeighborRadius = helperMtd(lowerEdges, upperNeighborRadius + rows) - upperNeighborRadius - rows
        (upperNeighborRadius, lowerNeighborRadius)
    }

    def tbs(c: Component[V]): () => BatchComponentMessage[V] = {
        c.componentId match { 
            case VectorCoord(startRow, endRow) =>
                () => BatchComponentMessage((startRow to endRow).flatMap(i => {
                    outgoingVectorIndex.getOrElse(i, Vector[Int]()).map(j => 
                        ComponentMessage(currentBoard(j), RowCoord(j-upperNeighborRadius))
                    )
                }))
            case _ =>
                throw new Exception(f"Unsupported tbs direction in ${c}")
        }
    } 

    def tbr(msg: ComponentMessage[V]): Unit = {
        msg.componentId match {
            case RowCoord(rid) =>   
                msg.content.copyToArray(currentBoard(incomingVectorIndex(rid)))
            case _ =>
                throw new Exception(f"Unrecognized component coordinate ${msg.componentId}!")            
        }
    }

    def display(): Unit = {
        println("*****************")
        currentBoard.foreach(row => {
            row.foreach(elem => print(elem + " "))
            println()
        })
    }

    def getVertices(): IndexedSeq[Vertex[V]] = {
        val vertexIdOffset = componentId.getRowId * cols
        initialValue.indices.map(i => {
            new Vertex[V](vertexIdOffset + i, initialValue(i), vertexUpdateRule)
        })
    }

    def updateLocalMessage(): Unit = {
        for (i <- (0 to upperNeighborRadius)) {
            currentBoard(i) = currentBoard(rows+i-1)
        }
        for (i <- (rows+upperNeighborRadius to rows+upperNeighborRadius+lowerNeighborRadius-1)) {
            currentBoard(i) = currentBoard(i-rows-1)
        }
    }
    
    // Static fixed edge relation
    override def toAgent(): Actor = {
        class TileActor extends Actor {
            val msgGenerator: Map[Long, Iterable[() => BatchComponentMessage[V]]] = connectedComponents.groupBy(_.componentAgentId).map(i => (i._1, i._2.map(tbs(_))))

            override def run(): Int = {
                receivedMessages.foreach(i => {
                    i match {
                        case x: BatchComponentMessage[V] => {
                            x.content.foreach(k => tbr(k))
                        }
                    }
                })

                for (i <- (upperNeighborRadius to rows+upperNeighborRadius-1)) {
                    for (j <- (0 to cols-1)) {
                        newBoard(i)(j) = vertexUpdateRule(currentBoard(i)(j), gather(i, j))
                    }
                }
                
                currentBoard = newBoard
                // If received remote messages, can be overwritten in the next round when processing receivedMessages
                updateLocalMessage()

                // send batch msgs 
                msgGenerator.foreach(i => {
                    i._2.foreach(j => sendMessage(i._1, j()))
                })
                1
            }
        }
        val tileActor = new TileActor
        componentAgentId = tileActor.id
        tileActor
    }

        // Static fixed edge relation
    def dbgAgent(): Actor = {
        class TileActor extends Actor {
            val msgGenerator: Map[Long, Iterable[() => BatchComponentMessage[V]]] = connectedComponents.groupBy(_.componentAgentId).map(i => (i._1, i._2.map(tbs(_))))

            override def run(): Int = {
                display()
                receivedMessages.foreach(i => {
                    i match {
                        case x: BatchComponentMessage[V] => {
                            x.content.foreach(k => tbr(k))
                        }
                    }
                })

                for (i <- (upperNeighborRadius to rows+upperNeighborRadius-1)) {
                    for (j <- (0 to cols-1)) {
                        newBoard(i)(j) = vertexUpdateRule(currentBoard(i)(j), gather(i, j))
                    }
                }
                
                currentBoard = newBoard
                // If received remote messages, can be overwritten in the next round when processing receivedMessages
                updateLocalMessage()

                // send batch msgs 
                msgGenerator.foreach(i => {
                    i._2.foreach(j => sendMessage(i._1, j()))
                })
                1
            }
        }
        val tileActor = new TileActor
        componentAgentId = tileActor.id
        tileActor
    }
}