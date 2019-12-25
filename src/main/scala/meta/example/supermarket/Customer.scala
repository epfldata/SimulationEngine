package meta.example.supermarket.people

import meta.classLifting.SpecialInstructions
import meta.example.supermarket.priceOrderedPQ
import squid.quasi.lift

@lift
class Customer extends People with BiWeeklyShopper{

  def main(): Unit = {
    while(true) {
      SpecialInstructions.handleMessages()
      buyFood("vegetable", getRandFood("vegetable"))
      SpecialInstructions.waitTurns(1)
      consumeFood
      SpecialInstructions.waitTurns(1)
    }
  }
}
