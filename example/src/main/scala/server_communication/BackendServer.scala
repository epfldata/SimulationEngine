package meta.example.server_communication

import meta.classLifting.SpecialInstructions.{waitTurns, handleMessages}
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
      println("Hello world! Backend " + id + " Turn " + currentTurn)
      waitTurns(1)
      handleMessages()
    }
  }
}
