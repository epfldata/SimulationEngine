package meta.example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Sim() extends Actor {

  def main(): Unit = {
    while(true) {
      handleMessages()
      waitTurns(1)
    }
  }
}