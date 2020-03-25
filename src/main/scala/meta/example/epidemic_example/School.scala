package meta.example.epidemic_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class School(var population: Int, var avgAge: Int) extends Actor {

  def checkInfection(age: Int): Boolean ={
    age > 20
  }

  def main(): Unit = {
    while (true) {
      waitTurns(1)
    }
  }
}
