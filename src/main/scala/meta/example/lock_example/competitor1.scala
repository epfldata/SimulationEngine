package meta.example.lock_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift

@lift
class Competitor1() extends Actor {

  var sharedObj: SharedObject = null

  def propose(): String = {
    println("Competitor proposes!" + id.toString)
    sharedObj.vote(id.toString)
  }

  def checkBlock(): String = {
    "is blocking?"
  }


  def main(): Unit = {
    while(true) {
//      SpecialInstructions.handleMessages()
//      if (propose()!= id.toString){
//        propose()
//      }
      println(propose())
      println(checkBlock())
      SpecialInstructions.waitTurns(1)
    }
  }
}