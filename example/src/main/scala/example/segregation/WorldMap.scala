package example
package segregation

import lib.Space.Torus2D

import scala.collection.mutable.{ArrayBuffer, Map}

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class WorldMap() extends Actor with Torus2D {

  var width: Int = segregationModel.worldWidth
  var height: Int = segregationModel.worldHeight
  var radius: Int = segregationModel.neighborhoodRadius

  val emptyLoc: ArrayBuffer[Int] = (0 to (segregationModel.worldWidth * segregationModel.worldHeight-1)).to[ArrayBuffer]

  val similarities: Map[Int, Double] = Map[Int, Double]()
  val world: Map[Int, Person] = Map[Int, Person]()

  var totalReports: Int = 0

  // Return a free location
  private def getPlacement(): Int = {
    val freeLoc: Int = emptyLoc(Random.nextInt(emptyLoc.length))
    emptyLoc -= freeLoc
    freeLoc
  }

  // Return -1 if unable to locate, otherwise return the location
  def registerLocation(p: Person): Int = {
    if (emptyLoc.length == 0) {
      -1
    } else {
      val loc: Int = getPlacement()
      world(loc) = p
      loc
    }
  }

  // Find the neighbor cells and return people who live there
  def getNeighbors(n: Int): List[Person] = {
    val neighborhood: ArrayBuffer[Person] = ArrayBuffer[Person]()
    val neighborLocs: List[Int] = (neighborCells(n, radius) - n).toList // remove the req cell, if in the neighborCells

    // Not supported by current lifter 
//    neighborLocs.filter(loc => world.get(loc).isDefined).map(loc => world.get(loc).get)

    neighborLocs.foreach(loc => {
      val neighbor: Option[Person] = world.get(loc)
      if (neighbor.isDefined) {
        neighborhood.append(neighbor.get)
      }})
    neighborhood.toList
  }

  def move(n: Int): Int = {
    if (emptyLoc.length == 0){
      n
    } else {
      val loc: Int = getPlacement()
      emptyLoc.append(n)                // free the prev loc
      world(loc) = world.remove(n).get  // update the map
      similarities.remove(n)            // remove its prev report
      loc
    }
  }

  // record the similarity report from Sims
  def report(loc: Int, similarity: Double): Unit = {
    totalReports = totalReports + 1
    similarities(loc) = similarity
  }

  // segregation is measured as the average similarities
  def segregationMeasure(): Double = {
    if (similarities.size > 0) {
//      monitor.logTimeseries("Segregation", similarities.values.sum / similarities.size)
      logger.info("{}", similarities.values.sum / similarities.size)
      similarities.values.sum / similarities.size
    } else {
      0
    }
  }

  def printWorld(): Unit = {
    println("***********")
    println("Int" + " View" + " Id")
    world.toList.sortBy(pair => pair._1).foreach(pair => println(pair._1, pair._2.view, pair._2.id))
  }

  def main(): Unit ={
    while(true){
      handleMessages()
      segregationMeasure()
//      if (totalReports == (Actor.totalSims - 1)){
//        totalReports = 0
//        waitLabel(Time,(1)
//      }
//      printWorld()
      waitLabel(Turn,1)
    }
  }
}