package meta.example.lock_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.{lift, dbg_lift}

/**
  * Consensus object has one operation, propose()
  * No two processes decide differently
  * Every decided value is a proposed value
  *
  * SWMR register
  */
@lift
class Consensus() extends Actor {

  private var isLocked: Boolean = false
  private var winner: String = ""

  def propose(name: String): String = {
    println("Vote received for " + name)
    if (!isLocked) {
      isLocked = true
      winner = name
    }
    winner
  }

  def main(): Unit = {
    while(true) {
      isLocked = false
      SpecialInstructions.waitTurns(1)
    }
  }
}