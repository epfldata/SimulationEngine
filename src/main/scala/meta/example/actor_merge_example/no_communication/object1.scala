package meta.example.actor_merge_example.no_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object2(val name: String) extends Actor {
  var deposit: Double = 1000
  val weekly_interest: Double = 0.0007

  def greeting: String = {
    "Hello " + name
  }

  def checkBalance: Unit = {
    println(greeting + " Your current balance is " + deposit)
  }

  def isWeek(days: Int): Boolean = {
    days % 7 == 0
  }

  def depositInterest(): Unit = {
    deposit = deposit * (1 + weekly_interest)
  }

  def main(): Unit = {
    while(true){
      checkBalance
      if (isWeek(timer)){
        depositInterest()
      }
      waitTurns(1)
    }
  }
}
