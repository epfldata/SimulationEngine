package example
package time_example

import scala.util.Random.nextBoolean
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

object logInfo {
  def interruptMsg: String = "Time is up!"
}

@lift
class Sim(var time: Double) extends Actor {

  def timeUp(): Unit = {
    logger.info(logInfo.interruptMsg)
  }

  var delay: Int = 5

  def main(): Unit = {
    interrupt(delay, ()=>timeUp())
    while (true) {
      if (nextBoolean()){
          logger.debug("Wait turn! " + " Id " + id)
        waitLabel("turn",1)
      } else {
        logger.debug("Wait time " + time + " Id " + id)
        waitLabel("time", time)
        logger.debug("Wait time finished " + id)
      }
      handleMessages()
    }
  }
}