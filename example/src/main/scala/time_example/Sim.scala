package meta.example.time_example

import meta.deep.runtime.Actor
import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

import scala.util.Random.nextBoolean

@lift
class Sim(var time: Double) extends Actor {

//  def interrupt(targetTime: Double): Unit = {
//    if (targetTime == currentTime){
//      println("Alert! " + targetTime + " time elapsed")
//    }
//  }

  def timeUp(): Unit = {
    println("Time is up!")
  }

  var delay: Int = 5

  def main(): Unit = {
    interrupt(delay, ()=>timeUp())

    while (true) {
//      interrupt(5)

      if (nextBoolean()){
//        println("Wait turn!")
          println("Wait turn! " + " Id " + id)
        waitTurns(1)
      } else {
        println("Wait time " + time + " Id " + id)
        waitTime(time)
        println("Wait time finished " + id)
      }
      handleMessages()
    }
  }
}