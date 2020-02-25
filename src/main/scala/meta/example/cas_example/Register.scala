package meta.example.cas_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift

/**
  * CAS object has one operation, propose()
  * No two processes decide differently
  * Every decided value is a proposed value
  *
  * SWMR register
  */

@lift
class Register() extends Actor {
  private var value: Int = 0
//  private var isLocked: Boolean = false
//  private var ownerId: AgentId = 0

  def read(): Int = {
    value
  }

  def write(new_val: Int): Unit = {
    value = new_val
  }

  def main(): Unit = {
    while(true) {
//      SpecialInstructions.handleMessages()
//      isLocked = false
      println("Content of register: " + value)
      SpecialInstructions.waitTurns(1)
    }
  }
}