package meta.example.actor_merge_example.no_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object1(val name: String) extends Actor {
  var deposit: Double = 1000
  val daily_interest: Double =  0.0001

  def greeting: String = {
    "Hello " + name
  }

  def checkBalance: Unit = {
    println(greeting + " Your current balance is " + deposit)
  }

  def depositInterest(): Unit = {
    deposit = deposit * (1 + daily_interest)
  }

  def main(): Unit = {
    while(true){
      checkBalance
      depositInterest()
      waitTurns(1)
    }
  }
}