package meta.example.lock_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift

@lift
class Competitor2() extends Actor {

  var sharedObj: SharedObject = null
//  val name: AgentId = id

  def propose(): String = {
    println("Competitor proposes!" + id.toString)
    sharedObj.vote(id.toString)
  }

  def main(): Unit = {
    while(true) {
      SpecialInstructions.handleMessages()
      println(propose())
      SpecialInstructions.waitTurns(1)
    }
  }
}