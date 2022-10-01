package example.cellularAutomata.wator

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import meta.runtime.{Actor, Future}

trait watorCell {
    var energy: Int
    var age: Int
}

case class Fish(var energy: Int, var age: Int = 0) extends watorCell

case class Water(var energy: Int=0, var age: Int = 0) extends watorCell

case class Shark(var energy: Int, var age: Int = 0) extends watorCell

@lift
class Cell(var identity: watorCell, var cfreq: Int) extends Actor {

    private var isReserved: Boolean = false

    private var peekNeighbors: List[Future[watorCell]] = List()
    private var tryMoving: Future[Boolean] = null

    private var targetCell: Cell = null

    def getIdentity(): watorCell = identity

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

                peekNeighbors = connectedAgents.map(x => x.asInstanceOf[Cell]).map(v => asyncCall[watorCell](() => v.getIdentity, 1))

                while (!(peekNeighbors.nonEmpty && peekNeighbors.forall(x => x.isCompleted))) {
                    waitAndReply(1)
                }

                val neighborIds: List[(watorCell, Int)] = peekNeighbors.map(i => i.popValue.get).asInstanceOf[List[watorCell]].zipWithIndex

                // println("Neighbor ids are " + neighborIds)

                val waterCells: List[(watorCell, Int)] = neighborIds.filter(x => x._1.isInstanceOf[Water])

                val emptySpot: Option[Cell] = if (waterCells.isEmpty){ 
                    None
                } else {
                    val r: Int = scala.util.Random.nextInt(waterCells.length)
                    Some(connectedAgents(waterCells(r)._2).asInstanceOf[Cell])
                }

                val fishCells: List[(watorCell, Int)] = neighborIds.filter(x => x._1.isInstanceOf[Fish])

                val nearbyFish: Option[Cell] = if (fishCells.isEmpty){
                    None
                } else {
                    val r: Int = scala.util.Random.nextInt(fishCells.length)
                    // println("The index of next fish spot is " + r + " " + fishCells(r)._1)
                    Some(connectedAgents(fishCells(r)._2).asInstanceOf[Cell])
                }

                val currentAge = identity.age
                if (identity.isInstanceOf[Fish]){
                    if (emptySpot.isDefined) {
                        targetCell = emptySpot.get

                        tryMoving = asyncCall[Boolean](() => emptySpot.get.relocate(identity), 1)

                        // println(id + " tries to swim to nearby water!")
                        while (!tryMoving.isCompleted) {
                            waitAndReply(1)
                        }

                        val relocateSuccess: Boolean = tryMoving.popValue.get

                        // If the agent has moved but no one moved to this place, reset it
                        if (relocateSuccess && !isReserved){
                            // reproduce
                            if (currentAge >= 10) {
                                // println(id + " produces new fish!")
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

                        tryMoving = asyncCall[Boolean](() => targetCell.relocate(identity), 1)

                        while (!tryMoving.isCompleted) {
                            waitAndReply(1)
                        }

                        // If the agent has moved but no one moved to this place, reset it
                        if (tryMoving.popValue.get && !isReserved){
                            if (currentAge >= 10){
                                // println(id + " produces new sharks!")
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


            waitAndReply(cfreq)

            isReserved = false
        }
    }
}