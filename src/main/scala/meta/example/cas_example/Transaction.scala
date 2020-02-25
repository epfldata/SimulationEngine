package meta.example.cas_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift

/**
  * Consensus object has one operation, propose()
  * No two processes decide differently
  * Every decided value is a proposed value
  *
  * SWMR register
  */
@lift
class Transaction extends Actor {
  var cas: CAS = null
  var register: Register = null

  val old_val = 0
  val new_val = 1

  def tx_begin(): Boolean = {
    println("Optimization in action!")
    cas.cas(register, old_val, new_val)
  }

  def main(): Unit = {
    while(true) {
      println("Transaction begins! " + tx_begin)
      SpecialInstructions.waitTurns(1)
    }
  }
}
