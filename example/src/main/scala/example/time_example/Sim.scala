package example.time_example

import meta.deep.runtime.Actor
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

import scala.util.Random.nextBoolean

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
        waitTurns(1)
      } else {
        logger.debug("Wait time " + time + " Id " + id)
        waitTime(time)
        logger.debug("Wait time finished " + id)
      }
      handleMessages()
    }
  }
}