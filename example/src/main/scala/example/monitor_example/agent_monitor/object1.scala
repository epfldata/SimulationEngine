package example
package monitor_example.agent_monitor


import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class object1(var own_monitor: monitorSim) extends Actor {

  def main(): Unit = {
    while (true) {
      if (scala.util.Random.nextBoolean()){
        println("Object1 wins the game!")
        own_monitor.logAggregate()
      }
      waitLabel(Turn,1)
      handleMessages()
    }
  }
}
