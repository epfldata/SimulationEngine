package meta.example.actor_merge_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift 

@lift 
class actor1 extends Actor {
  var age_actor1: Int = 0

  def main(): Unit = {
    while (age_actor1 < 7){
      println("Age of actor 1 is " + age_actor1)
      SpecialInstructions.waitTurns(1)
      age_actor1 = age_actor1 + 1
    }
  }
}
