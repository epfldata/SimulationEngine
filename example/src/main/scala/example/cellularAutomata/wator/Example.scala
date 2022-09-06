package example
package cellularAutomata.wator

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {
        def apply(width: Int, height: Int, cfreq: Int): List[Actor] = {

            val totalPoints: Int = width * height
            // 2D space
            val neighborRadius: Int = 1

            val points = (1 to totalPoints).map(x => {
                val r = scala.util.Random.nextInt(10)
                if (r==1) {
                    new Cell(example.cellularAutomata.wator.Shark(10), cfreq)
                } else if (r < 7) {
                    new Cell(example.cellularAutomata.wator.Fish(10), cfreq)
                } else {
                    new Cell(example.cellularAutomata.wator.Water(0), cfreq)
                }
            })

            lib.Graph.Torus2DGraph(points, width, height, neighborRadius)
            points.toList
        }
    }
    
    val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

    compileSims(List(cls1), Some(liftedMain))
}