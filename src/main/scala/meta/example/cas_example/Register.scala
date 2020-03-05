package meta.example.cas_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift

@lift
class Register() extends Actor {
  private var value: Int = 0

  def read(): Int = {
    value
  }

  def write(new_val: Int): Boolean = {
    value = new_val
    true
  }

  def main(): Unit = {
    while(true) {
      println("Content of register: " + read())
      SpecialInstructions.waitTurns(1)
    }
  }
}