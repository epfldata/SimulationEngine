package example
package gameOfLifeRPC

import scala.collection.mutable.{Map => MutMap}

import lib.Graph.Torus2DGraph

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(width: Int, height: Int): List[Actor] = {
            val totalPoints: Int = width * height
            // 2D space
            val neighborRadius: Int = 1

            val points = (1 to totalPoints).map(x => {
                if (Random.nextBoolean()){
                    new Cell(1)
                } else {
                    new Cell(0)
                }
            }).toList

            Torus2DGraph(points, width, height, neighborRadius)
            points
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

  val mainClass = MainInit.liftedMain

  compileSims(List(cls1), Some(mainClass))
}