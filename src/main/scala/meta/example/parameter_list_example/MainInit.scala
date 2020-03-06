package meta.example.parameter_list_example

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val foo: object1 = new object1("Bob")

    List(foo)
  }
}
