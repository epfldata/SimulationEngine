package meta.example.time_example

import meta.deep.runtime.Actor
import meta.classLifting.SpecialInstructions.{waitTime, waitTurns}
import squid.quasi.lift

import scala.util.Random.nextBoolean

@lift
class Sim(var time: Double) extends Actor {

  def interrupt(targetTime: Double): Unit = {
    if (targetTime == currentTime){
      println("Alert! " + targetTime + " has elapsed")
    }
  }

  def main(): Unit = {
    while (true) {
      interrupt(5)
      if (nextBoolean()){
        println("Wait turn!")
        waitTurns(1)
      } else {
        println("Wait time! " + id)
        waitTime(time)
        println("Wait time has finished " + id)
      }
    }
  }
}