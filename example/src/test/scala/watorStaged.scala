package example.staged.wator

import meta.classLifting.SpecialInstructions._
import stagedSims.Sim
import meta.runtime._

trait watorCell {
    var energy: Int
    var age: Int
}

case class Fish(var energy: Int, var age: Int = 0) extends watorCell

case class Water(var energy: Int=0, var age: Int = 0) extends watorCell

case class Shark(var energy: Int, var age: Int = 0) extends watorCell

@Sim
class Cell(var identity: watorCell) extends Actor {

    private var isReserved: Boolean = false

    private var peekNeighbors: List[Future[(Long, watorCell)]] = List()
    private var tryMoving: Future[Boolean] = null

    private var targetCell: Cell = null

    def getIdentity(): (Long, watorCell) = (id, identity)

    def relocate(newId: watorCell): Boolean = {
        if (!isReserved){
            isReserved = true
            val currentEnergy = identity.energy
            identity = newId
            // Eat what used to be here
            identity.energy = identity.energy + currentEnergy
            true
        } else {
            false
        }
    }

    def main(): Unit = {
        while (true) {

            if (identity.isInstanceOf[Water]){
                // println(id + " water!")
            } else {
                // See if there is any fish or shark around
                // println(id + " sends messages to neighbors!")

                peekNeighbors = connectedAgents.map(x => x._2.asInstanceOf[Cell]).toList.map(v => asyncMessage[(Long, watorCell)](() => v.getIdentity))

                while (!(peekNeighbors.nonEmpty && peekNeighbors.forall(x => x.isCompleted))) {
                    waitLabel(Turn, 1)
                }

                val neighborIds = peekNeighbors.map(i => i.popValue.get).asInstanceOf[List[(Long, watorCell)]]

                // println("Neighbor ids are " + neighborIds)

                val waterCells: List[(Long, watorCell)] = neighborIds.filter(x => x._2.isInstanceOf[Water])

                val emptySpot: Option[Cell] = if (waterCells.isEmpty){ 
                    None
                } else {
                    val r: Int = scala.util.Random.nextInt(waterCells.length)
                    // println("The index of next empty spot in water cells is " + r + " " + waterCells(r)._1)

                    assert(connectedAgents.get(waterCells(r)._1).isDefined)
                    Some(connectedAgents(waterCells(r)._1).asInstanceOf[Cell])
                }

                val fishCells: List[(Long, watorCell)] = neighborIds.filter(x => x._2.isInstanceOf[Fish])

                val nearbyFish: Option[Cell] = if (fishCells.isEmpty){
                    None
                } else {
                    val r: Int = scala.util.Random.nextInt(fishCells.length)
                    // println("The index of next fish spot is " + r + " " + fishCells(r)._1)

                    assert(connectedAgents.get(fishCells(r)._1).isDefined)
                    Some(connectedAgents(fishCells(r)._1).asInstanceOf[Cell])
                }

                // println(id + " " + identity + " nearby fish: " + nearbyFish + " empty spots " + emptySpot)


                val currentAge = identity.age
                if (identity.isInstanceOf[Fish]){
                    if (emptySpot.isDefined) {
                        targetCell = emptySpot.get

                        tryMoving = asyncMessage[Boolean](() => emptySpot.get.relocate(identity))

                        // println(id + " tries to swim to nearby water!")
                        while (!tryMoving.isCompleted) {
                            waitLabel(Turn, 1)
                        }

                        val relocateSuccess: Boolean = tryMoving.popValue.get

                        // If the agent has moved but no one moved to this place, reset it
                        if (relocateSuccess && !isReserved){
                            // reproduce
                            if (currentAge >= 10) {
                                println(id + " produces new fish!")
                                identity = Fish(10)
                            } else {
                                identity = Water(0)
                            }
                        }
                    }
                } else {
                    if (nearbyFish.isDefined || emptySpot.isDefined){
                        if (nearbyFish.isDefined) {
                            targetCell = nearbyFish.get
                        } else {
                            // println(id + " shark tries to swim away!")
                            targetCell = emptySpot.get
                        } 

                        tryMoving = asyncMessage[Boolean](() => targetCell.relocate(identity))

                        while (!tryMoving.isCompleted) {
                            waitLabel(Turn, 1)
                        }

                        // If the agent has moved but no one moved to this place, reset it
                        if (tryMoving.popValue.get && !isReserved){
                            if (currentAge >= 10){
                                println(id + " produces new sharks!")
                                identity = Shark(10)
                            } else {
                                identity = Water(0)
                            }
                        }
                    }

                    identity.energy = identity.energy - 1
                } 

                if (identity.energy <= 0) {
                    identity = Water(0)
                } 

                if (identity.age > 10){
                    identity.age = 0
                }

                identity.age = identity.age + 1
            }

            waitLabel(Turn, 1)
            isReserved = false
        }
    }
}

class watorStaged extends org.scalatest.FlatSpec {
    import meta.API._

    "Wa-Tor example" should "run and print out animals' activities" in {

        val width: Int = 10
        val height: Int = 10

        val totalPoints: Int = width * height
        // 2D space
        val neighborRadius: Int = 1

        val points = (1 to totalPoints).map(x => {
            val r = scala.util.Random.nextInt(10)
            if (r==1) {
                new Cell(Shark(10))
            } else if (r < 7) {
                new Cell(Fish(10))
            } else {
                new Cell(Water(0))
            }
        })

        (1 to totalPoints).foreach(i =>
            points(i-1).connectedAgents = lib.Grid.Torus2D.getNeighborCells(width, height)(i-1, neighborRadius).map(j => points(j)).map(x => (x.id, x)).toMap
        )

        val config = new SimulationConfig(points.toList, 100, false)
        StartSimulation[AkkaStaged.type](config)
    }
}