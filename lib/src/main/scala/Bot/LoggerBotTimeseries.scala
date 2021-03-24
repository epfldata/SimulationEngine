package lib
package Bot

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

@lift
class LoggerBotTimeseries(val item: String,
                          val reportFrequency: Int) extends Actor {

  var latest: Double = 0
  var timeseries: List[(Double, Double)] = List()

  var currentTurn: Int = 0 

  def append(v: Double): Unit = {
    timeseries = (currentTurn.asInstanceOf[Double], v) :: timeseries
    latest = v
  }

  def printLogInfo(): Unit = {
    if (currentTurn % reportFrequency == 0) {
      println(s"$item: $latest")
    }
  }

  def main(): Unit = {
    while (!deleted) {
      handleMessages()
      printLogInfo()
      waitLabel(Turn, 1)
      currentTurn = currentTurn + 1
    }
  }
}