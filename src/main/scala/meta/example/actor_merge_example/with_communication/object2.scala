package meta.example.actor_merge_example.with_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object2(val bank: object1) extends Actor {

  val dailySaving: Double = 50

  def main(): Unit = {
    while(true){
      // fail case, fix the first offset for leadingEdges in replaceSends
//      bank.deposit(dailySaving)
      bank.deposit(50)
      waitTurns(1)
    }
  }
}
