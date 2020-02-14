package meta.example.lock_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift

@lift
class SharedObject() extends Actor {

  private var isLocked: Boolean = false
  private var winner: String = ""

  def vote(name: String): String = {
    println("Vote received for " + name + " Current state isLocked " + isLocked + " last winner " + winner )
    if (!isLocked) {
      isLocked = true
      winner = name
    }
    winner
  }

  def init(): Unit = {
    println("Re-initialize the shared object!")
    isLocked = false
    winner = ""
  }

  def main(): Unit = {
    while(true) {
      SpecialInstructions.handleMessages()
      init()
      SpecialInstructions.waitTurns(1)
    }
  }
}