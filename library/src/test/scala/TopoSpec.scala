package cloudcity.lib
package Graph.test

import cloudcity.lib.Graph._
import org.scalatest.FlatSpec

class TopoSpecShow extends FlatSpec {
    "Create a new 2D array that is transformed to a tile agent using neighbor kernel" should "work" in {
        val rows: Int = 5
        val cols: Int = 5

        val golArray = new Array2D[Boolean](rows, cols, 
            None,
            (1 to rows * cols).map(i => true), 
            // Map(0 -> Vector(1, 3, 4), 
            //     1 -> Vector(0, 2, 3, 4, 5),
            //     2 -> Vector(1, 4, 5),
            //     3 -> Vector(0, 1, 4, 6, 7),
            //     4 -> Vector(0, 1, 2, 3, 5, 6, 7, 8),
            //     5 -> Vector(1, 2, 4, 7, 8),
            //     6 -> Vector(3, 4, 7),
            //     7 -> Vector(3, 4, 5, 6, 8),
            //     8 -> Vector(4, 5, 7)),
            cloudcity.lib.Graph.GenerateGraph.Torus2DGraph(rows, cols).map(i => (i._1.toInt, i._2.map(_.toInt))),
            Array(Coordinate2D(-1, -1), Coordinate2D(-1, 0), Coordinate2D(-1, 1),
                Coordinate2D(0, -1), Coordinate2D(0, 1),
                Coordinate2D(1, -1), Coordinate2D(1, 0), Coordinate2D(1, 1)),
            (x: Boolean, neighbors: Iterable[Boolean]) => {
                val aliveNeighbors = neighbors.filter(_==true).size
                x match {
                    case true => {
                        if (aliveNeighbors>3 || aliveNeighbors<2) {
                            false
                        } else {
                            true
                        }
                    }
                    case false => if (aliveNeighbors == 3) {
                        true 
                    } else {
                        false
                    }
                }
            }, VectorCoord(0, rows))

        // A 2D array of agent
        val golAgent = golArray.toAgent()
        // represent the entire board as one agent
        (0 to 5).foreach(i => {
            val start = System.currentTimeMillis()
            golAgent.run()
            val end = System.currentTimeMillis()
            println(end-start)
        })
    }

    "Create a graph that is transformed to a tile agent" should "work" in {
        val rows: Int = 3
        val cols: Int = 3

        val golArray = new Graph[Boolean](rows, cols, 
            None,
            (1 to rows * cols).map(i => true), 
            Map(0 -> Vector(1, 3, 4), 
                1 -> Vector(0, 2, 3, 4, 5),
                2 -> Vector(1, 4, 5),
                3 -> Vector(0, 1, 4, 6, 7),
                4 -> Vector(0, 1, 2, 3, 5, 6, 7, 8),
                5 -> Vector(1, 2, 4, 7, 8),
                6 -> Vector(3, 4, 7),
                7 -> Vector(3, 4, 5, 6, 8),
                8 -> Vector(4, 5, 7)),
            // cloudcity.lib.Graph.GenerateGraph.Torus2DGraph(rows, cols).map(i => (i._1.toInt, i._2.map(_.toInt))),
            (x: Boolean, neighbors: Iterable[Boolean]) => {
                val aliveNeighbors = neighbors.filter(_==true).size
                x match {
                    case true => {
                        if (aliveNeighbors>3 || aliveNeighbors<2) {
                            false
                        } else {
                            true
                        }
                    }
                    case false => if (aliveNeighbors == 3) {
                        true 
                    } else {
                        false
                    }
                }
            }, VectorCoord(0, rows))

        // A 2D array of agent
        val golAgent = golArray.toAgent()
        // represent the entire board as one agent
        (0 to 5).foreach(i => {
            val start = System.currentTimeMillis()
            golAgent.run()
            val end = System.currentTimeMillis()
            println(end-start)
        })
    }
}

class TopoSpec extends FlatSpec {

    "Create a new vertex" should "work" in {
        val golCell = new Vertex[Boolean](100, true, (x: Boolean, neighbors: Iterable[Boolean]) => {
            val aliveNeighbors = neighbors.filter(_==true).size
            x match {
                case true => {
                    if (aliveNeighbors>3 || aliveNeighbors<2) {
                        false
                    } else {
                        true
                    }
                }
                case false => if (aliveNeighbors == 3) {
                    true 
                } else {
                    false
                }
            }
        })

        val golAgent = golCell.toAgent()
        // dummy fake connection, just check mailbox
        golAgent.connectedAgentIds = List(2, 3, 4, 5)
        golAgent.run()
        assert(golAgent.sendMessages.size == 4)
        assert(golAgent.id == 100)
    }


    "Create a new 2D array without transforming to a tile agent" should "work" in {
        val rows: Int = 100
        val cols: Int = 1000

        val golArray = new Array2D[Boolean](rows, cols, 
            None,
            (1 to rows * cols).map(i => true), 
            cloudcity.lib.Graph.GenerateGraph.Torus2DGraph(rows, cols).map(i => (i._1.toInt, i._2.map(_.toInt))),
            Array(Coordinate2D(-1, -1), Coordinate2D(-1, 0), Coordinate2D(-1, 1),
                Coordinate2D(0, -1), Coordinate2D(0, 1),
                Coordinate2D(1, -1), Coordinate2D(1, 0), Coordinate2D(1, 1)),
            (x: Boolean, neighbors: Iterable[Boolean]) => {
                val aliveNeighbors = neighbors.filter(_==true).size
                x match {
                    case true => {
                        if (aliveNeighbors>3 || aliveNeighbors<2) {
                            false
                        } else {
                            true
                        }
                    }
                    case false => if (aliveNeighbors == 3) {
                        true 
                    } else {
                        false
                    }
                }
            }, VectorCoord(0, rows))

        // A 2D array of agent
        val golAgents = golArray.getVertices.map(_.toAgent)

        // represent the entire board as one agent
        (0 to 5).foreach(i => {
            val start = System.currentTimeMillis()
            golAgents.map(j => j.run())
            val end = System.currentTimeMillis()
            println(end-start)
        })
    }
}