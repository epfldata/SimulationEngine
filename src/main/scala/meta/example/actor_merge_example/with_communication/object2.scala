package meta.example.actor_merge_example.with_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object2(val bank: object1) extends Actor {

// passing variable doesn't work
//  val dailySaving: Double = 50

  def main(): Unit = {
    while(true){
      bank.deposit(50)
      waitTurns(1)
    }
  }
}
