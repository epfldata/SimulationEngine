package meta.example.segregation

import meta.deep.runtime.Actor
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import scala.util.Random
import scala.collection.mutable.{Map, ArrayBuffer}
import segregationModel.Loc

@lift
class WorldMap() extends Actor with Torus2D {

  var width: Int = segregationModel.worldWidth
  var height: Int = segregationModel.worldHeight
  var radius: Int = segregationModel.neighborhoodRadius

  val emptyLoc: ArrayBuffer[Loc] = (0 to (segregationModel.worldWidth * segregationModel.worldHeight-1)).to[ArrayBuffer]

  val similarities: Map[Loc, Double] = Map[Loc, Double]()
  val world: Map[Loc, Person] = Map[Loc, Person]()

  // Return a free location
  private def getPlacement(): Loc = {
    val freeLoc: Loc = emptyLoc(Random.nextInt(emptyLoc.length))
    emptyLoc -= freeLoc
    freeLoc
  }

  // Return -1 if unable to locate, otherwise return the location
  def registerLocation(p: Person): Loc = {
    if (emptyLoc.length == 0) {
      -1
    } else {
      val loc: Loc = getPlacement()
      world(loc) = p
      loc
    }
  }

  // Find the neighbor cells and return people who live there
  def getNeighbors(n: Loc): List[Person] = {
    val neighborhood: ArrayBuffer[Person] = ArrayBuffer[Person]()
    val neighborLocs: List[Loc] = (neighborCells(n, radius) - n).toList // remove the req cell, if in the neighborCells

    // Not supported by current lifter 
//    neighborLocs.filter(loc => world.get(loc).isDefined).map(loc => world.get(loc).get)

    neighborLocs.foreach(loc => {
      val neighbor: Option[Person] = world.get(loc)
      if (neighbor.isDefined) {
        neighborhood.append(neighbor.get)
      }})
    neighborhood.toList
  }

  def move(n: Loc): Loc = {
    if (emptyLoc.length == 0){
      n
    } else {
      val loc: Loc = getPlacement()
      emptyLoc.append(n)                // free the prev loc
      world(loc) = world.remove(n).get  // update the map
      similarities.remove(n)            // remove its prev report
      loc
    }
  }

  // record the similarity report from Sims
  def report(loc: Loc, similarity: Double): Unit = {
    similarities(loc) = similarity
  }

  // segregation is measured as the average similarities
  def segregationMeasure(): Double = {
    if (similarities.size > 0) {
      monitor.logTimeseries("Segregation", similarities.values.sum / similarities.size)
      println(similarities.values.sum / similarities.size)
      similarities.values.sum / similarities.size
    } else {
      0
    }
  }

  def printWorld(): Unit = {
    println("***********")
    println("Loc" + " View" + " Id")
    world.toList.sortBy(pair => pair._1).foreach(pair => println(pair._1, pair._2.view, pair._2.id))
  }

  def main(): Unit ={
    while(true){
      segregationMeasure()
//      printWorld()
      waitTurns(1)
    }
  }
}