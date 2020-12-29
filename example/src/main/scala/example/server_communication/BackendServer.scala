package example
package server_communication

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class BackendServer() extends Actor {

  def getContent: String = {
    val r = System.nanoTime().toString
    r
  }

  def main(): Unit = {
    while(true) {
      println("Hello world! Backend " + id + " Turn " + currentTurn)
      waitLabel(Turn,1)
      handleMessages()
    }
  }
}
