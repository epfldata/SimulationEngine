package meta.example.supermarket

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Customer extends Actor {
  import Profile._

  var shop: Supermarket = null
  var customers: Mix = EPFL
  var rnd = scala.util.Random

  /* Generate a customer randomly based on the ratio defined in Mix */
  def randCustomer(): Unit = {
    var foo = rnd.nextInt(customers.regularshopper) //include 0, exclusive of the other end
    if (foo < customers.vegielover){
      println("Welcome a vegie lover customer!")
      shop.sell(veganShopper)
    } else if (foo <= customers.meatlover) {
      println("Welcome a meat lover customer!")
      shop.sell(meatShopper)
    } else if (foo <= customers.partylover) {
      println("Welcome a party lover customer!")
      shop.sell(partyShopper)
    } else if (foo <= customers.regularshopper) {
      println("Welcome a regular customer!")
      shop.sell(regularShopper)
    } else {
      println("Invalid!")
    }
  }

  def main(): Unit = {
    while(true) {
      randCustomer()
      SpecialInstructions.waitTurns(1)
    }
  }
}