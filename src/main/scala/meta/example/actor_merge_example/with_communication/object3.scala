package meta.example.actor_merge_example.with_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object3() extends Actor {

  def introduction: Int = {
    println("This is object 3")
    98
  }

  def main(): Unit = {
    while(true){
      waitTurns(1)
    }
  }
}
