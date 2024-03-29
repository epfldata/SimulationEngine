package example
package gameOfLife

import cloudcity.lib.Graph.LoadGraph

// This MainInit demonstrates how to use an external edge file as the social graph of a simulation
object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(edgeFilePath: String): IndexedSeq[Actor] = {
            var edges: Map[Long, Iterable[Long]] = LoadGraph(edgeFilePath)
            // map is unordered, hence input agents can be out of order
            edges.map(i => {
                val cell = if (Random.nextBoolean) {
                    new Cell(1)
                } else {
                    new Cell(0)
                }
                cell.id = i._1
                cell.connectedAgentIds = i._2
                cell
            }).toVector
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

  val mainClass = MainInit.liftedMain

  compileSims(List(cls1), Some(mainClass))
}