package meta.example.new_instance_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object1 extends Actor {
  val name: String = "object 1"

  def main(): Unit = {
    while (true) {
      println("Object1 " + id)
      waitTurns(1)
    }
  }
}
