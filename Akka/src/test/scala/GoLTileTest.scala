package simulation.akka
package test

import meta.runtime._
import simulation.akka.API._
import org.scalatest.FlatSpec
import scala.util.Random

class GoLTileTest extends FlatSpec {

    trait Coordinate
    case class Coordinate2D(x: Int, y: Int) extends Coordinate
    // case class TileCoordinate(x: Coordinate2D, y: Coordinate2D) extends Coordinate

    trait ComponentMessage extends Message
    class Boolean2DArrayMessage(val content: Iterable[Boolean], val cid: (Coordinate2D, Coordinate2D)) extends ComponentMessage

    trait Component[T, C] {
        def topo(c: C): Iterable[C] = ???
        def actionPerCell(v: T, neighbors: Iterable[T]): T = ???
        def tbs(c: Component[T, C]): () => ComponentMessage = ???
        def tbr(msg: ComponentMessage): Unit = ???
    }

    // For simplicity, hard code Boolean type instead of taking a type variable
    class Boolean2DArray(val cid: (Coordinate2D, Coordinate2D)) extends Component[Boolean, Coordinate2D] {
        // For simplicity, assume only vertical partitioning (send an adjacent row). only rows are padded
        // a 2D array is uniquely defined by its shape (upper left, lower right)
        lazy val rows: Int = (cid._2.x - cid._1.x)+2
        lazy val cols: Int = (cid._2.y - cid._1.y)

        var oldBoard: Array[Array[Boolean]] = Array.ofDim[Boolean](rows, cols)
        var newBoard: Array[Array[Boolean]] = Array.ofDim[Boolean](rows, cols)

        // Fill in the 2D grid with init values in the shape
        def fill(init: IndexedSeq[Boolean]): Unit = {
            var ctr: Int = 0
            for (i <- (0 to (cid._2.x - cid._1.x)-1)) {
                for (j <- (0 to cols-1)) {
                    oldBoard(i+1)(j) = init(ctr)
                    ctr+=1
                }
            }
        }

        // For simplicity, consider only top and bottom two directions.
        override def tbs(c: Component[Boolean, Coordinate2D]): () => ComponentMessage = {
            c match {
                case c: Boolean2DArray => {
                    c.cid match { 
                        case (Coordinate2D(x1, y1), Coordinate2D(x2, y2)) if (y1 == cid._1.y && y2 == cid._2.y) =>
                            // bottom
                            if (x1 > cid._2.x) {
                                () => new Boolean2DArrayMessage(oldBoard(rows-1), (Coordinate2D(cid._2.x, cid._1.y), cid._2))
                            // top
                            } else {
                                () => new Boolean2DArrayMessage(oldBoard(1), (cid._1, Coordinate2D(cid._1.x, cid._2.y)))
                            }
                        case _ =>
                            throw new Exception(f"Unsupported tbs direction in ${c}")
                            () => new Boolean2DArrayMessage(oldBoard.flatten.toVector, cid)
                    }
                }
                case _ =>
                    () => new Boolean2DArrayMessage(oldBoard.flatten.toVector, cid)
            }
        }

        override def tbr(msg: ComponentMessage): Unit = {
            msg match {
                case x: Boolean2DArrayMessage => {
                    x.cid match {
                        case (Coordinate2D(x1, y1), Coordinate2D(x2, y2)) if (y1 == cid._1.y && y2 == cid._2.y) =>                         
                            if (x1 > cid._2.x) {
                                x.content.copyToArray(oldBoard(rows-1))
                            } else {
                                x.content.copyToArray(oldBoard(0))
                            }
                        case _ =>
                            throw new Exception("Boolean 2d array, unsupported messages!")
                    }
                }
                case _ => throw new Exception("Unsupported messages!")
            }
        }

        def update(): Unit = {
            for (i <- (1 to rows-1)) {
                for (j <- (0 to cols-1)) {
                    newBoard(i)(j) = actionPerCell(oldBoard(i)(j), topo(Coordinate2D(i, j)).map(k => oldBoard(k.x)(k.y)))
                    // manual inline (commented out) results in 50% speedup for 100k agents, 80ms->50ms
                }
            }
            oldBoard = newBoard
        }
    }

    class GameOfLifeTile(cid: (Coordinate2D, Coordinate2D)) extends Boolean2DArray(cid) {
        @inline
        override def topo(c: Coordinate2D): Iterable[Coordinate2D] = {
            val x = c.x % rows
            val y = c.y / rows

            for {
                i <- -1 to 1
                j <- -1 to 1
                if !(i == 0 && j == 0)
                    dx = (x + i + rows) % rows
                    dy = (y + j + cols) % cols
            } yield Coordinate2D(dx, dy)
        }

        @inline
        override def actionPerCell(v: Boolean, vs: Iterable[Boolean]): Boolean = {
            val aliveNeighbors = vs.filter(_==true).size
            if (v) {
                if (aliveNeighbors > 3 || aliveNeighbors < 1) {
                    false
                } else {
                    true
                }
            } else {
                if (aliveNeighbors == 3) {
                    true
                } else {
                    false
                }
            }
        }
    }

    class GameOfLifeTileAgent(val tile: GameOfLifeTile) extends Actor {
        var msgGenerator: Map[Long, () => ComponentMessage] = Map[Long, ()=> ComponentMessage]()
        
        override def run(): Int = {
            receivedMessages.foreach(i => {
                tile.tbr(i.asInstanceOf[ComponentMessage])
            })
            receivedMessages.clear()
            connectedAgentIds.foreach(i => {
                sendMessage(i, msgGenerator(i)())
            })
            tile.update()
            1
        }
    }

    f"Create a number of tile agents for game of life example" should "run" in {
        val totalTiles: Int = 50
        
        val rowsPerTile: Int = 200
        val colsPerTile: Int = 10

        val tiles = Range(0, totalTiles).map(i => {
            val x = new GameOfLifeTile((Coordinate2D(rowsPerTile*i, 0), Coordinate2D(rowsPerTile*(i+1)-1, colsPerTile)))
            x.fill(Range(0, rowsPerTile*colsPerTile).map(_ => Random.nextBoolean))
            x
        })
        
        val agents: IndexedSeq[GameOfLifeTileAgent] = tiles.map(t => {
            new GameOfLifeTileAgent(t)
        })

        if (totalTiles > 1) {
            Range(0, totalTiles).foreach(j => {
                val i = j.toLong
                agents(j).id = i
                j match {
                    case 0 => agents(j).connectedAgentIds = Vector(i+1)
                    case x if x ==totalTiles-1 => agents(j).connectedAgentIds = Vector(i-1)
                    case _ => agents(j).connectedAgentIds = Vector(i-1, i+1)
                }
            })
        }

        agents.foreach(a => {
            a.msgGenerator = a.connectedAgentIds.map(i => (i, a.tile.tbs(tiles(i.toInt)))).toMap
        })
        val snapshot1 = API.Simulate(agents, 200)
    }
}