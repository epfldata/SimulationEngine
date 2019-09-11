package meta.example.stateless_server

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class InitActors extends Actor {
  def init() = {
    val server = new Serverstateless()
    var printers = List[Actor]()
    for (i <- 0 until 3) {
      val x = new RandomPrinter()
      x.server = server
      x.i = i
      printers = x :: printers
    }
    server :: printers
  }
}
