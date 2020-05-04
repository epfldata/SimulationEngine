package meta.example.actor_merge_example.with_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object2(val neighbor: object1) extends Actor {

  def main(): Unit = {
    while(true){
      println(neighbor.introduction)
      waitTurns(1)
    }
  }
}
