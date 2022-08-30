package example
package gameOfLife

import scala.collection.mutable.{Map => MutMap}

import lib.Graph.Torus2DGraph

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(width: Int, height: Int, cfreq: Int): List[Actor] = {
            val totalPoints: Int = width * height
            // 2D space
            val neighborRadius: Int = 1

            val points = (1 to totalPoints).map(x => {
                new Cell(Random.nextBoolean(), cfreq)
            })

            Torus2DGraph(points, width, height, neighborRadius)
            points.toList
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

  val mainClass = MainInit.liftedMain

  compileSims(List(cls1), Some(mainClass))
}