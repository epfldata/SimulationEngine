package meta.example.rumor 

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift 
class Env() extends Actor {

  var rumorReach: Int = 0 

  def reportRumor(): Unit = {
      rumorReach = rumorReach + 1 
  }

  def main(): Unit = {
    while(true){
      waitTurns(1)
      handleMessages()
      println("Current rumor count: " + rumorReach)
    }
  }
}
