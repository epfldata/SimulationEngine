package lib
package Bot

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
//
//import breeze.linalg._
//import breeze.plot._

@lift
class LoggerBotTimeseries(val item: String,
                          val reportFrequency: Int) extends Bot {

  var latest: Double = 0
  var timeseries: List[(Double, Double)] = List()

  def append(v: Double): Unit = {
    timeseries = (currentTurn.asInstanceOf[Double], v) :: timeseries
    latest = v
  }

  def printLogInfo(): Unit = {
    if (currentTurn % reportFrequency == 0) {
      println(s"$item: $latest")
    }
  }

//  def readHistory(): List[Double] = {
//    timeseries
//  }

  def main(): Unit = {
    while (!deleted) {
      handleMessages()
      printLogInfo()
      waitLabel(Turn, 1)
    }
  }
}