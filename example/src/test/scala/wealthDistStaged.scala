package example.staged.wealthDist

import meta.classLifting.SpecialInstructions._
import stagedSims.Sim
import meta.runtime._

import example.sugarscape.wealthDistribution.Person

@Sim
class Cell(var energy: Int, 
        val interval: Int, 
        val energyCapacity: Int, 
        val gridWidth: Int, 
        val gridHeight: Int) extends Actor {

    private var isReserved: Boolean = false

    var identity: Person = null

    private var peekNeighbors: List[Future[(Long, Int)]] = List()
    private var tryMoving: Future[Boolean] = null

    private var asyncReceiver: Cell = null

    private var daysElapsed: Int = 0

    private var clock: Int = 0

    private var visionFields: List[Long] = List()

    // Return 0 if there is someone here already
    def getEnergy(): (Long, Int) = {
        if (identity!=null) {
            (id, 0)
        } else {
            (id, energy)
        }
    }

    def relocate(newId: Person): Boolean = {
        assert(newId!=null)

        if (!isReserved){
            isReserved = true
            identity = newId
            // Consume the sugar
            println("Gains energy: " + energy)

            identity.energy = identity.energy + energy
            energy = 0
            daysElapsed = 0
            visionFields = lib.Grid.Torus2D.getNeighborCells(gridWidth, gridHeight)(id.toInt-1, identity.vision).map(x => (1+x).toLong)
            true
        } else {
            false
        }
    }

    def main(): Unit = {
        if (identity != null){
            visionFields = lib.Grid.Torus2D.getNeighborCells(gridWidth, gridHeight)(id.toInt-1, identity.vision).map(x => (1+x).toLong)
        }

        while (true) {
            // Seaching for sugar
            if (identity!= null) {
                peekNeighbors = visionFields.map(i => connectedAgents(i).asInstanceOf[Cell]).map(x => {
                    asyncMessage[(Long, Int)](() => x.getEnergy)
                })

                while (!(peekNeighbors.nonEmpty && peekNeighbors.forall(x => x.isCompleted))) {
                    waitLabel(Turn, 1)
                }

                val neighborIds = peekNeighbors.map(i => i.popValue.get).asInstanceOf[List[(Long, Int)]]

                // println("Neighbor ids are " + neighborIds)

                val maxSugar: Option[Cell] = {

                    // println("Neighbor info " + neighborIds)
                    val res = neighborIds.foldLeft((0, 0): Tuple2[Long, Int])((x, z) => {
                        if (z._2 > x._2) {
                            (z._1, z._2)
                        } else {
                            x
                        }
                    })

                    // println("Result is " + res)

                    if (res._2 == 0) {
                        None
                    } else {
                        Some(connectedAgents(res._1).asInstanceOf[Cell])
                    }
                }

                // println(id + " " + identity + " sugar cells " + maxSugar)

                if (maxSugar.isDefined) {
                    asyncReceiver = maxSugar.get

                    tryMoving = asyncMessage[Boolean](() => asyncReceiver.relocate(identity))

                    // println(id + " tries to consume the sugar!")

                    while (!tryMoving.isCompleted) {
                        waitLabel(Turn, 1)
                        clock = clock + 1
                    }

                    val relocateSuccess: Boolean = tryMoving.popValue.get

                    if (relocateSuccess){
                        println(id + " moves successfully!")
                        if (!isReserved) {
                            identity = null
                        }
                    }
                }

                if (identity!=null){
                    identity.energy = identity.energy - identity.metabolism
                }
            }
        
            // Sugar grows back at each cell after the interval
            if (energy == 0 && daysElapsed==interval) {
                energy = energyCapacity
                daysElapsed = 0
            }

            if (identity!=null){
                if (identity.energy==0){
                    println("Person dies!")
                    identity = null
                } else if (identity.lifeExpectancy >= identity.age){
                    // reproduce
                    println("Person reproduces!")
                    identity = example.sugarscape.wealthDistribution.wealthDistributionConfig.newPerson()
                }
            }

            val waitTicks = example.sugarscape.wealthDistribution.wealthDistributionConfig.ticksInDay - clock

            if (waitTicks > 0){
                waitLabel(Turn, waitTicks)
            } else {
                waitLabel(Turn, 1)
            }
            
            daysElapsed = daysElapsed + 1
            isReserved = false
        }
    }
}

class wealthDistStaged extends org.scalatest.FlatSpec {
    import meta.API._

    "wealth distribution example" should "run and people should reproduce and die" in {

        val width: Int = 10
        val height: Int = 10

        // Sugar
        val meanEnergy: Int = 10
        val meanInterval: Int = 5
        val meanEnergyCapacity: Int = 10
    
        // Human
        val personMeanEnergy: Int = 30
        val personMeanMetabolism: Int = 8
        val lifeExpectancy: Int = 60
        val vision: Int = 3

        val totalPoints: Int = width * height
        // 2D space
        val neighborRadius: Int = 1

        val points = (1 to totalPoints).map(x => {
            val r = scala.util.Random.nextInt(10)
            if (r < 3) {
                val c = new Cell(scala.util.Random.nextInt(meanEnergy), scala.util.Random.nextInt(meanInterval), scala.util.Random.nextInt(meanEnergyCapacity), width, height)

                c.identity = Person(personMeanEnergy * scala.util.Random.nextGaussian().toInt, personMeanMetabolism * scala.util.Random.nextGaussian().toInt, lifeExpectancy * scala.util.Random.nextGaussian().toInt, vision * scala.util.Random.nextGaussian().toInt)

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

        val config = new SimulationConfig(points.toList, 100, false)
        StartSimulation[BaseStaged.type](config)
    }
}
