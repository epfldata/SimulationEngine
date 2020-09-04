package meta.example.lock_example

import meta.classLifting.SpecialInstructions.{waitTurns}
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Voter(var consensus_object: Consensus) extends Actor {

  private var won: Boolean = false

  def vote(): Unit = {
    if (id == consensus_object.propose(id)){
      println("I win! " + id + " No more propose")
      won = true
    } else {
      println("I lost. Try again! " + id)
    }
  }

  def main(): Unit = {
    while(!won) {
      vote()
      waitTurns(1)
    }
  }
}