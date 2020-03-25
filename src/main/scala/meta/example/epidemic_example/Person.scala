package meta.example.epidemic_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift

@lift
class Person (var age: Int, var canton: School) extends Actor {

  def main(): Unit = {
    while (true) {
      println("Age: " + age + " Infected? " + canton.checkInfection(age))
      waitTurns(1)
    }
  }
}
