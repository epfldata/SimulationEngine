package meta.example.actor_merge_example.with_communication

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object1() extends Actor {

  var savings: Double = 800
  val interestRate: Double = 0.000002

  def check_balance: Unit = {
    println("Your current saving is " + savings)
  }

  def deposit(amount: Double): Unit = {
    println("Deposit amount: " + amount)
    savings = savings + amount
    print_receipt()
  }

  def print_receipt(): Unit = {
    println("Receipt: current balance " + savings)
  }

  def interest(): Double = {
    savings * interestRate
  }

  def main(): Unit = {
    while(true){
      deposit(interest())
      waitTurns(1)
    }
  }
}