package meta.example.monitor_example.builtin_monitor
import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

@lift
class object2 extends Actor {

  def main(): Unit = {
    while(true){
      if (scala.util.Random.nextBoolean()){
        println("Object2 infected!")
        monitor.logAggregate("Infectious")
        monitor.logTimeseries("Infectious")
      }
      if (scala.util.Random.nextBoolean()){
        println("Object2 Recovered!")
        monitor.logAggregate("Recovered")
        monitor.logTimeseries("Recovered")
      }
      waitTurns(1)
    }
  }
}