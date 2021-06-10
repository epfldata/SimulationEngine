package example
package cellularAutomata.wator

// object Example {
//     import meta.API._

//     def main(args: Array[String]): Unit = {

//         val width: Int = args(0).toInt
//         val height: Int = args(1).toInt

//             val totalPoints: Int = width * height
//             // 2D space
//             val neighborRadius: Int = 1

//             val points = (1 to totalPoints).map(x => {
//                 val r = scala.util.Random.nextInt(10)
//                 if (r==1) {
//                     println("generate shark!")
//                     new Cell(Shark(10))
//                 } else if (r < 7) {
//                     println("generate fish!")
//                     new Cell(Fish(10))
//                 } else {
//                     new Cell(Water(0))
//                 }
//             })

//             (1 to totalPoints).foreach(i =>
//                 points(i-1).connectedAgents = lib.Grid.Torus2D.getNeighborCells(width, height)(i-1, neighborRadius).map(j => points(j)).map(x => (x.id, x)).toMap
//             )

//             points.toList

//             val config = new SimulationConfig(points.toList, 100, false)
//             StartSimulation[BaseStaged.type](config)
//         }
// }

object Example extends App {
    
    val liftedMain = custMacros.liftMethod {
        def apply(width: Int, height: Int): List[Actor] = {

            val totalPoints: Int = width * height
            // 2D space
            val neighborRadius: Int = 1

            val points = (1 to totalPoints).map(x => {
                val r = scala.util.Random.nextInt(10)
                if (r==1) {
                    new Cell(example.cellularAutomata.wator.Shark(10))
                } else if (r < 7) {
                    new Cell(example.cellularAutomata.wator.Fish(10))
                } else {
                    new Cell(example.cellularAutomata.wator.Water(0))
                }
            })

            (1 to totalPoints).foreach(i =>
                points(i-1).connectedAgents = lib.Grid.Torus2D.getNeighborCells(width, height)(i-1, neighborRadius).map(j => points(j)).map(x => (x.id, x)).toMap
            )

            points.toList

        }
    }
    
    val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

    compileSims(List(cls1), Some(liftedMain))
}