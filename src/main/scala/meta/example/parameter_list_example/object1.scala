package meta.example.parameter_list_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.{lift, dbg_lift}

@lift
class object1(val name: String) extends Actor {

  def main(): Unit = {
    while (true) {
      println("Name is " + name)
      waitTurns(1)
    }
  }
}
