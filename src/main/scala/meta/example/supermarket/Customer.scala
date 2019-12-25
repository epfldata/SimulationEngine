package meta.example.supermarket.people

import meta.classLifting.SpecialInstructions
import meta.example.supermarket.priceOrderedPQ
import squid.quasi.lift

@lift
class Customer extends People with BiWeeklyShopper{
  var fridge: priceOrderedPQ = new priceOrderedPQ

  def buyVegetable: Unit = {
    println("Customer buys vegetable! ")
    fridge.pq.enqueue(supermarket.sell("Vegetable", "eggplant"))
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
