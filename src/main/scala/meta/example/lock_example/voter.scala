package meta.example.lock_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift

@lift
class Voter() extends Actor {

  var consensus_object: Consensus = null

  def vote(): String = {
    println("Voter proposes! " + id.toString)
    consensus_object.propose(id.toString)
  }

  def main(): Unit = {
    while(true) {
      println("winner is " + vote())
      SpecialInstructions.waitTurns(1)
    }
  }
}