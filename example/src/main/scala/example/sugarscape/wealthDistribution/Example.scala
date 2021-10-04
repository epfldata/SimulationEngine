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
        var genVision = (vision * scala.util.Random.nextGaussian()).toInt
        if (genVision > 4) {
            genVision = 4
        } else if (genVision < 0) {
            genVision = 1
        }

        var lifespan = lifeExpectancy + (20 * scala.util.Random.nextGaussian()).toInt
        if (lifespan > 100) {
            lifespan = 100
        } else if (lifespan < 0) {
            lifespan = lifeExpectancy
        }

        example.sugarscape.wealthDistribution.Person(
            List(2, personMeanEnergy + (2 * scala.util.Random.nextGaussian()).toInt).max, List(2, personMeanMetabolism + (2* scala.util.Random.nextGaussian()).toInt).max, 
            lifespan, 
            genVision)
    }
}

object Example extends App {
    
    val liftedMain = meta.classLifting.liteLift {

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