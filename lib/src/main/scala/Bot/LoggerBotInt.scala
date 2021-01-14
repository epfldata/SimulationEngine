package lib
package Bot

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
//import squid.quasi.dbg_lift

@lift
class LoggerBotInt(val item: String,
                val reportFrequency: Int) extends Actor {

  var timeseries: List[Int] = List()
  var sum: Int = 0

  def log(v: Int): Unit = {
    sum = sum + v
    timeseries = v :: timeseries
  }

  def printLogInfo(): Unit = {
    if (currentTurn % reportFrequency == 0) {
      println(s"$item timeseries: " + sum)
    }
  }

  def readSummary(): Int = {
    sum
  }

  def readHistory(): List[Int] = {
    timeseries
  }

  def main(): Unit = {
    while (!deleted) {
      handleMessages()
      printLogInfo()
      waitLabel(Turn, 1)
    }
  }
}