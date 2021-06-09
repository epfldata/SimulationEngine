package example
package sugarscape.wealthDistribution

object wealthDistributionConfig {
    val ticksInDay: Int = 10

    // Sugar
    val meanEnergy: Int = 10
    val meanInterval: Int = 5
    val meanEnergyCapacity: Int = 10

    // Human
    val personMeanEnergy: Int = 30
    val personMeanMetabolism: Int = 8
    val lifeExpectancy: Int = 60
    val vision: Int = 3

    def newPerson(): example.sugarscape.wealthDistribution.Person = {
        var genVision = vision * scala.util.Random.nextGaussian().toInt
        if (genVision > 4) {
            genVision = 4
        }

        var lifespan = lifeExpectancy * scala.util.Random.nextGaussian().toInt
        if (lifespan > 100) {
            lifespan = 100
        }

        example.sugarscape.wealthDistribution.Person(personMeanEnergy * scala.util.Random.nextGaussian().toInt, personMeanMetabolism * scala.util.Random.nextGaussian().toInt, lifespan, genVision)
    }
}

// object Example {
//     import meta.API._

//     def main(args: Array[String]): Unit = {

//         val width: Int = args(0).toInt
//         val height: Int = args(1).toInt

//         // Sugar
//         val meanEnergy: Int = 10
//         val meanInterval: Int = 5
//         val meanEnergyCapacity: Int = 10
    
//         // Human
//         val personMeanEnergy: Int = 30
//         val personMeanMetabolism: Int = 8
//         val lifeExpectancy: Int = 60
//         val vision: Int = 3

//         val totalPoints: Int = width * height
//         // 2D space
//         val neighborRadius: Int = 1

//         val points = (1 to totalPoints).map(x => {
//             val r = scala.util.Random.nextInt(10)
//             if (r < 3) {
//                 val c = new Cell(scala.util.Random.nextInt(meanEnergy), scala.util.Random.nextInt(meanInterval), scala.util.Random.nextInt(meanEnergyCapacity), width, height)

//                 c.identity = Person(personMeanEnergy * scala.util.Random.nextGaussian().toInt, personMeanMetabolism * scala.util.Random.nextGaussian().toInt, lifeExpectancy * scala.util.Random.nextGaussian().toInt, vision * scala.util.Random.nextGaussian().toInt)

//                 c
//             } else {
//                 new Cell(scala.util.Random.nextInt(meanEnergy), scala.util.Random.nextInt(meanInterval), scala.util.Random.nextInt(meanEnergyCapacity), width, height)
//             }
//         })

//         val indexedPoints = points.map(x => (x.id, x)).toMap

//         // Fully connected. A person can go anywhere
//         (1 to totalPoints).foreach(i =>
//             points(i-1).connectedAgents = indexedPoints
//         )

//         points.toList

//         val config = new SimulationConfig(points.toList, 100, false)
//         StartSimulation[BaseStaged.type](config)
//     }
// }

object Example extends App {
    
    val liftedMain = custMacros.liftMethod {

        def apply(width: Int, height: Int): List[Actor] = {

            import example.sugarscape.wealthDistribution.wealthDistributionConfig._

            val totalPoints: Int = width * height
            // 2D space
            val neighborRadius: Int = 1

            val points = (1 to totalPoints).map(x => {
                val r = scala.util.Random.nextInt(10)
                if (r < 3) {
                    val c = new Cell(scala.util.Random.nextInt(meanEnergy), scala.util.Random.nextInt(meanInterval), scala.util.Random.nextInt(meanEnergyCapacity), width, height)
                    c.identity = newPerson()
                    c
                } else {
                    new Cell(scala.util.Random.nextInt(meanEnergy), scala.util.Random.nextInt(meanInterval), scala.util.Random.nextInt(meanEnergyCapacity), width, height)
                }
            })

            val indexedPoints = points.map(x => (x.id, x)).toMap

            // Fully connected. A person can go anywhere
            (1 to totalPoints).foreach(i =>
                points(i-1).connectedAgents = indexedPoints
            )

            points.toList
        }
    }
    
    val cls1: ClassWithObject[Cell] = Cell.reflect(IR)

    compileSims(List(cls1), Some(liftedMain))
}