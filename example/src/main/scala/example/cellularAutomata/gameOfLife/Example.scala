package example
package gameOfLife

import squid.quasi.lift
import meta.runtime.SimRuntime

import lib.Grid.Torus2D._
import lib.Bot.LoggerBotInt
import ch.qos.logback.classic.Logger

@lift
class MainInit {
    def main(): Unit = {
        val width: Int = 25
        val height: Int = 25

        val totalPoints: Int = width * height

        // 2D space
        val neighborRadius: Int = 1

        val points = (1 to totalPoints).map(x => {
            new Cell(Random.nextBoolean())
        })

        (0 to totalPoints-1).foreach(i =>
            points(i).connectedAgents = getNeighborCells(width, height)(i, neighborRadius).map(j => points(j))
        )

        SimRuntime.newActors ++= points
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)
//   val logger: ClassWithObject[LoggerBotInt] = LoggerBotInt.reflect(IR)

  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1), Some(mainClass))
}