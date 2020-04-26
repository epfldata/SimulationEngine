package meta.example.monitor_example.agent_monitor

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object1(var own_monitor: monitorSim) extends Actor {

  def main(): Unit = {
    while (true) {
      if (scala.util.Random.nextBoolean()){
        println("Object1 wins the game!")
        own_monitor.logAggregate()
      }
      waitTurns(1)
    }
  }
}
