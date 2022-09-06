package example
package predatorPrey

import lib.Graph.Torus2DGraph

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {
        def apply(width: Int, height: Int, stepUnit: Int): List[Actor] = {

            val totalPoints: Int = width * height
            // 2D space
            val neighborRadius: Int = 1

            val points = (1 to totalPoints).map(x => {
                val c = new Cell(stepUnit)
                val r = scala.util.Random.nextInt(10)
                if (r==1) {
                    c.currentPlayer = Some(example.predatorPrey.Player(false))  // hunters
                } else if (r < 7) {
                    c.currentPlayer = Some(example.predatorPrey.Player(true))   // NPCs
                }
                c
            })

            Torus2DGraph(points, width, height, neighborRadius)
            points.toList
        }
    }
    
    val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

    compileSims(List(cls1), Some(liftedMain))
}