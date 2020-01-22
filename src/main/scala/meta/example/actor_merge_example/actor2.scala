package meta.example.actor_merge_example

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift 

@lift 
class actor2 extends Actor {
  var age_actor2: Int = 0

  def main(): Unit = {
    while (age_actor2 < 7){
      println("Age of actor 2 is " + age_actor2)
      SpecialInstructions.waitTurns(1)
      age_actor2 = age_actor2 + 1
    }
  }
}
