package example
package diffusion

import squid.quasi.lift

import lib.Grid.Torus2D._

@lift
class MainInit {
    def main(): Unit = {
        val width: Int = 25
        val height: Int = 25

        val totalPoints: Int = width * height
        var expectedResult: Double = 0.0

        // 2D space
        val neighborRadius: Int = 1

        val points = (1 to totalPoints).map(x => {
            val randTemp = Random.nextInt(100)
            expectedResult = expectedResult + randTemp
            new Cell(randTemp)
        })

        println("Expected steady state result: " + expectedResult / totalPoints)

        (0 to totalPoints-1).foreach(i =>
            points(i).connectedAgents = getNeighborCells(width, height)(i, neighborRadius).map(j => points(j))
        )

        SimRuntime.newActors ++= points
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1), Some(mainClass))
}