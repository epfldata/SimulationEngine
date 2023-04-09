package example
package gameOfLifeRPC

import scala.collection.mutable.{Map => MutMap}

import cloudcity.lib.Graph.GenerateGraph.Torus2DGraph

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(width: Int, height: Int): List[Actor] = {
            val totalPoints: Int = width * height
            // 2D space
            val graph: Map[Long, Iterable[Long]] = Torus2DGraph(width, height)

            val cells = graph.map(i => {
                val cell = if (Random.nextBoolean()){ 
                    new Cell(1)
                } else {
                    new Cell(0)
                }
                cell.id = i._1
                (i._1, cell)
            })

            cells.map(c => {
                c._2.connectedAgents = graph(c._1).map(i => cells(i))
            })

            cells.values.toList
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

  val mainClass = MainInit.liftedMain

  compileSims(List(cls1), Some(mainClass))
}