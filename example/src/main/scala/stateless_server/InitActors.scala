package meta.example.stateless_server

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class InitActors extends Actor {
  def init() = {
    val server = new RandomNumberServer()
    var printers = List[Actor]()
    for (i <- 0 until 1) {
      val x = new RandomPrinter(server)
      x.i = i
      printers = x :: printers
    }
    server :: printers
  }
}
