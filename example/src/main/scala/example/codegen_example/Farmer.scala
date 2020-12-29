package example
package codegen_example

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Farmer() extends Actor {
  var happiness: Int = 1: Int
  var peers: List[Farmer] = List[Farmer]()
  var market: Market = null: Market

  def notifyPeers(): Unit = {
    peers.foreach { p =>
      p.tell(this, happiness)
    }
  }

  def tell(actor: Actor, h: Int): Unit = {
    happiness = happiness - h
  }

  def main(): Unit = {
    while (true) {
      val testResult = market.sell2(500)
      println("TEST_VAR", testResult)
      waitLabel(Turn,1)
    }
  }

}
