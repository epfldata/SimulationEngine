package example
package gameOfLifeRPC

import scala.collection.mutable.{Map => MutMap}

import cloudcity.lib.Graph.Torus2DGraph

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(width: Int, height: Int): IndexedSeq[Actor] = {
            // sequential order of input agents
            val cells = Range(0, width * height).map(i => {
                val cell = if (Random.nextBoolean()){ 
                    new Cell(1)
                } else {
                    new Cell(0)
                }
                // Not strictly necessary. Just to be sure.
                cell.id = i
                cell
            })

            // 2D space
            val graph: Map[Long, Iterable[Long]] = Torus2DGraph(width, height)

            cells.zipWithIndex.map(c => {
                c._1.connectedAgents = graph(c._2).map(i => cells(i.toInt))
            })
            cells
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

  val mainClass = MainInit.liftedMain

  compileSims(List(cls1), Some(mainClass))
}