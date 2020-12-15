package example.time_example

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def init() = {
    val Sim1 = new Sim(0.5)
    val Sim2 = new Sim(1)
    List(Sim1, Sim2)
  }
}

