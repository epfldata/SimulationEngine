package example.cellularAutomata.wator

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import meta.runtime.{Actor, Future}
import custMacros.Sim

trait watorCell {
    var energy: Int
}

case class Fish(var energy: Int) extends watorCell

case class Water(var energy: Int=0) extends watorCell

case class Shark(var energy: Int) extends watorCell

// @Sim
@lift
class Cell(var identity: watorCell) extends Actor {

    private var isReserved: Boolean = false

    private var peekNeighbors: List[Future[(Long, watorCell)]] = List()
    private var tryMoving: Future[Boolean] = null

    private var asyncReceiver: Cell = null

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
                    // handleMessages()
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


                if (identity.isInstanceOf[Fish]){
                    if (emptySpot.isDefined) {
                        val currentEnergy = identity.energy

                        assert(emptySpot.isDefined)
                        asyncReceiver = emptySpot.get

                        tryMoving = asyncMessage[Boolean](() => emptySpot.get.relocate(identity))

                        tryMoving = asyncMessage[Boolean](() => asyncReceiver.relocate(identity))

                        // println(id + " tries to swim to nearby water!")
                        while (!tryMoving.isCompleted) {
                            waitLabel(Turn, 1)
                            // handleMessages()
                        }

                        val relocateSuccess: Boolean = tryMoving.popValue.get
                        // println(id + " relocates : " + relocateSuccess)

                        // If the agent has moved but no one moved to this place, reset it
                        if (relocateSuccess && !isReserved){
                            println(id + " fish swims away!")
                            identity = Water(0)
                        }
                    }
                } else if (identity.isInstanceOf[Shark]) {
                    // println(id + " is a shark!")
                    if (nearbyFish.isDefined) {
                        asyncReceiver = nearbyFish.get

                        tryMoving = asyncMessage[Boolean](() => asyncReceiver.relocate(identity))

                        // println(id + " tries to eat the nearby fish!")

                        while (!tryMoving.isCompleted) {
                            waitLabel(Turn, 1)
                            // handleMessages()
                        }

                        // If the agent has moved but no one moved to this place, reset it
                        if (tryMoving.popValue.get && !isReserved){
                            println(id + " eats the fish!")
                            identity = Water(0)
                        }
                    } else if (emptySpot.isDefined) {
                        // println(id + " shark tries to swim away!")
                        asyncReceiver = emptySpot.get
                        tryMoving = asyncMessage[Boolean](() => asyncReceiver.relocate(identity))

                        while (!tryMoving.isCompleted) {
                            waitLabel(Turn, 1)
                            // handleMessages()
                        }

                        // If the agent has moved but no one moved to this place, reset it
                        if (tryMoving.popValue.get && !isReserved){
                            println(id + " shark left!")
                            identity = Water(0)
                        }

                        // if (isReserved) {
                        //     println("Error! Shark should not be reserved!")
                        // }
                    } 
                } 

                if (identity.energy <= 0) {
                    identity = Water(0)
                } else {
                    identity.energy = identity.energy - 1
                }
            }

            waitLabel(Turn, 1)
            isReserved = false
            // handleMessages()
        }
    }
}