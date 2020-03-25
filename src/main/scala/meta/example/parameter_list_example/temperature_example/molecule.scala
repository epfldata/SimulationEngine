package meta.example.parameter_list_example.temperature_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class molecule(var temperature: Double, val neighbor: molecule) extends Actor {

  def action(): Unit = {
    if (neighbor!=null){
      temperature = .5*(neighbor.getTemperature + temperature)
    }
  }

  def getTemperature(): Double = {
    temperature
  }

  def main(): Unit = {
    while (true) {
      println("Current temperature of the molecule is " + temperature)
      action()
      waitTurns(1)
    }
  }
}