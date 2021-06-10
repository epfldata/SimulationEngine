package example
package segregation


import scala.collection.mutable.{Map, ListBuffer}
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class WorldMap(val width: Int, val height: Int) extends Actor {

    val similarities: Map[Int, Double] = Map[Int, Double]()

    val world: Map[Int, Long] = Map[Int, Long]()

    val freeSpots: ListBuffer[Int] = ListBuffer[Int]()

    var totalReports: Int = 0
    var segregationLevel: Double = 0

    def placeAgent(location: Int, aid: Long): Unit = {
        world += (location -> aid)
        freeSpots --= List(location)
    }

    // Given a location index, return the list of neighbor ids
    def getNeighbors(loc: Int): List[Long] = {
        val neighborLocations: List[Int] = lib.Grid.Torus2D.getNeighborCells(width, height)(loc, 1)

        neighborLocations.filter(x => world.get(x).isDefined).map(x => world(x))
    }

    def move(currentLoc: Int): Int = {
        if (!freeSpots.isEmpty){
            val moveTo = freeSpots.head
            freeSpots.append(currentLoc)
            similarities.remove(currentLoc)
            moveTo
        } else {
            currentLoc
        }
    }

    // record the similarity report from Sims
    def report(loc: Int, similarity: Double): Unit = {
        totalReports = totalReports + 1
        similarities(loc) = similarity
    }

    def main(): Unit ={
        freeSpots.appendAll(Range(0, width*height).toList)

        while(true){
            
            if (similarities.size > 0) {
                segregationLevel = similarities.values.sum / similarities.size

                println("Segregation level " + segregationLevel)
            }

            waitLabel(Turn,1)
        }
    }
}