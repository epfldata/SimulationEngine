package meta.example.monitor_example.builtin_monitor

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object1 extends Actor {
  val name: String = "object 1"
  var isInfected: Boolean = false

  def main(): Unit = {
    while (true) {
      isInfected = scala.util.Random.nextBoolean()
      if (isInfected){
        println("Object1 infected!")
        monitor.logAggregate("Infectious")
        monitor.logTimeseries("Infectious")
      }
      waitTurns(1)
    }
  }
}
