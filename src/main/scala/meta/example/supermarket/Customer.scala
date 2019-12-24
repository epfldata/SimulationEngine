package meta.example.supermarket

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Customer extends Actor {
//  var supermarket: Supermarket = null
  val supermarket: Supermarket = Supermarket.store
  var fridge: priceOrderedPQ = new priceOrderedPQ

  def buyVegetable: Unit = {
    println("Customer buys vegetable! ")
    fridge.pq.enqueue(supermarket.sell("Vegetable", "Broccoli", 1))
  }

  def consumeFood: Unit = {
    println("Customer consumed vegetable! ")
    fridge.pq.dequeue().consume
  }

  def main(): Unit = {
    while(true) {
      SpecialInstructions.handleMessages()
      buyVegetable
      SpecialInstructions.waitTurns(1)
      consumeFood
      SpecialInstructions.waitTurns(1)
    }
  }
}
