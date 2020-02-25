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
class CAS extends Actor {

  def cas(reg: Register, old_val: Int, new_val: Int): Boolean = {
    println("cas with old val" + old_val + " new val " + new_val)
    if (reg.read()==old_val){
      reg.write(new_val)
      true
    } else {
      false
    }
  }

  def main(): Unit = {
    while(true) {
//      SpecialInstructions.handleMessages()
      SpecialInstructions.waitTurns(1)
    }
  }
}
