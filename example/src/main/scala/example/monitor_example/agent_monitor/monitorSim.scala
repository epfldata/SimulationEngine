package example
package monitor_example.agent_monitor

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class monitorSim() extends Actor {

  var aggregate: Int = 0

  def logAggregate(): Unit = {
    aggregate = aggregate + 1
  }

  def printAggregate(): Unit = {
    println("Total wins: " + aggregate)
  }

  def main(): Unit = {
    while(true){
      printAggregate()
      waitLabel(Turn,1)
      handleMessages()
    }
  }
}