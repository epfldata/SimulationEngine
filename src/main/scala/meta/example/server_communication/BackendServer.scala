package meta.example.server_communication

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class BackendServer() extends Actor {

  def getContent: String = {
    val r = System.nanoTime().toString
    r
  }

  def main(): Unit = {
    while(true) {
      SpecialInstructions.waitTurns(1)
    }
  }
}
