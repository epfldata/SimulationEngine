package example
package rumor

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Env() extends Actor {

  var rumorReach: Int = 0 

  def reportRumor(): Unit = {
      rumorReach = rumorReach + 1 
  }

  def main(): Unit = {
    while(true){
      waitLabel(Turn,1)
      handleMessages()
      println("Current rumor count: " + rumorReach)
    }
  }
}
