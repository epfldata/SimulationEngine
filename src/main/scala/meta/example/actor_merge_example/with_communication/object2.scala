package meta.example.actor_merge_example.with_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object2(val bank: object1) extends Actor {

  def main(): Unit = {
    while(true){
      bank.check_balance
      bank.deposit(10)
      waitTurns(1)
    }
  }
}
