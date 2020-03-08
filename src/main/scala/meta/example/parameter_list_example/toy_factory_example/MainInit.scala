package meta.example.parameter_list_example.toy_factory_example

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
//    val foo: toy = new toy("Woody", 100)
    val bar: toyFactory = new toyFactory
    List(bar)
  }
}
