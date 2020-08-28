package meta.example.lock_example

import meta.classLifting.SpecialInstructions.{handleMessages, waitTurns}
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
class Consensus() extends Actor {

  private var isLocked: Boolean = false
  private var winner: AgentId = -1

  def propose(simId: AgentId): AgentId = {
    if (!isLocked) {
      isLocked = true
      winner = simId
    }
    winner
  }

  def main(): Unit = {
    while(true) {
      handleMessages()
      isLocked = false
      waitTurns(1)
    }
  }
}