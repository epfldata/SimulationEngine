package example
package gameOfLife

import squid.quasi.lift
import scala.collection.mutable.{Map => MutMap}

import lib.Grid.Torus2D._

object MainInit {
    val liftedMain = custMacros.liftMethod {
        def apply(width: Int, height: Int): List[Actor] = {

            val totalPoints: Int = width * height
            // 2D space
            val neighborRadius: Int = 1

            val points = (1 to totalPoints).map(x => { 
                new Cell(Random.nextBoolean())
            })

            (1 to totalPoints).foreach(i =>
                points(i-1).connectedAgents = getNeighborCells(width, height)(i-1, neighborRadius).map(j => points(j)).map(x => (x.id, x)).toMap
            )

            points.toList
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

  val mainClass = MainInit.liftedMain

  compileSims(List(cls1), Some(mainClass))
}