package example
package segregation

import lib.Bot.LoggerBotTimeseries
import lib.Grid.{Grid, Torus2D}

import scala.collection.mutable.Map
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class WorldMap(val width: Int, val height: Int) extends Actor {

  val loggerBot: LoggerBotTimeseries = new LoggerBotTimeseries("similarity", 10)

  var grid: Option[Grid[Int]] = None
  val similarities: Map[Int, Double] = Map[Int, Double]()
  val world: Map[Int, Person] = Map[Int, Person]()

  var totalReports: Int = 0
  var segregationLevel: Double = 0

  def init(): Unit = {
    grid = Some(new Torus2D(width, height))
  }

  def placeAgent(p: Person): Option[Int] = {
    grid.get.placeAgent(p, true)
  }

  def getNeighbors(loc: Int, radius: Int): List[Person] = {
    grid.get.getAgentNeighbors(loc, radius).asInstanceOf[List[Person]]
  }

  def move(currentLoc: Int, p: Person): Int = {
    val newLoc: Option[Int] = placeAgent(p)
    if (newLoc.isDefined) {
      grid.get.removeAgent(currentLoc, p)
      similarities.remove(currentLoc)
      newLoc.get
    } else {
      currentLoc
    }
  }

  // record the similarity report from Sims
  def report(loc: Option[Int], similarity: Double): Unit = {
    totalReports = totalReports + 1
    similarities(loc.get) = similarity
  }

  // segregation is measured as the average similarities
  def recordSegregation(): Unit = {
    if (similarities.size > 0) {
      segregationLevel = similarities.values.sum / similarities.size
      asyncMessage(() => loggerBot.append(segregationLevel))
    }
  }

  def main(): Unit ={
    init()
    while(true){
      handleMessages()
      recordSegregation()
//      if (totalReports == (Actor.totalSims - 1)){
//        totalReports = 0
//        waitLabel(Time,(1)
//      }
      waitLabel(Turn,1)
    }
  }
}