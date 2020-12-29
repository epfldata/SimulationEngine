package lib.Bot

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

@lift
class LoggerBot(val item: String, val reportFrequency: Int, val detailed: Boolean) extends Actor {

  var summary: Int = 0
  var timeseries: List[Int] = List[Int]()

  def accumulate(): Unit = {
    summary = summary + 1
  }

  def append(v: Int): Unit = {
    timeseries = v :: timeseries
  }

  def printLogInfo(): Unit = {
    if (currentTurn % reportFrequency == 0) {
      if (detailed) {
        println(s"$item timeseries: " + timeseries)
      } else {
        println(s"$item total: " + summary)
      }
    }
  }

  def readSummary(): Int = {
    summary
  }

  def readHistory(): List[Int] = {
    timeseries
  }

  def main(): Unit = {
    while (true) {
      handleMessages()
      printLogInfo()
      waitLabel(Turn, 1)
    }
  }
}