package meta.example.monitor_example.agent_monitor
import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

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
      waitTurns(1)
    }
  }
}