package example
package stateless_server

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class InitActors extends Actor {
  def init() = {
    val server = new RandomNumberServer()
    var printers = List[Actor]()
    for (i <- 0 until 1000) {
      val x = new RandomPrinter()
      x.server = server
      x.i = i
      printers = x :: printers
    }
    server :: printers
  }
}
