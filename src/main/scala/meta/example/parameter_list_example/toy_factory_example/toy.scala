package meta.example.parameter_list_example.toy_factory_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class toy(val name: String, val number: Int) extends Actor {

  def main(): Unit = {
    while (true) {
      println("Toy Tag: name " + name + " Number " + number)
      waitTurns(1)
    }
  }
}
