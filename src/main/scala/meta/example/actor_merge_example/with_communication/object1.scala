package meta.example.actor_merge_example.with_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object1() extends Actor {

  def introduction: Int = {
    println("Bingo!")
    10
  }

  def main(): Unit = {
    while(true){
      introduction
      waitTurns(1)
    }
  }
}