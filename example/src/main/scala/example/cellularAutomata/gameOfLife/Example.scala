package example
package gameOfLife

import squid.quasi.lift
import meta.runtime.SimRuntime
import scala.collection.mutable.{Map => MutMap}

import lib.Grid.Torus2D._

@lift
class MainInit {
    def main(): Unit = {
        val width: Int = 15
        val height: Int = 15

        val totalPoints: Int = width * height

        // 2D space
        val neighborRadius: Int = 1

        val points = (1 to totalPoints).map(x => {
            new Cell(Random.nextBoolean())
        })

        (1 to totalPoints).foreach(i =>
            points(i-1).connectedAgents = getNeighborCells(width, height)(i-1, neighborRadius).map(j => points(j)).map(x => (x.id, x)).toMap
        )

        SimRuntime.newActors ++= points
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1), Some(mainClass))
}