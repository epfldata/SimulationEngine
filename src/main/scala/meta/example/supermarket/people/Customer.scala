package meta.example.supermarket.people

import meta.classLifting.SpecialInstructions
import meta.example.supermarket.{categories, categoryAmount, utils}
import squid.quasi.lift

@lift
class Customer1 extends People with BiWeeklyShopper{

  val shoppingList: categoryAmount = categoryAmount(3, 1, 1, 1, 2)
  val priceSensitiveness: Double = 1

  def main(): Unit = {
    while(true) {
      SpecialInstructions.handleMessages()
//      buyItem("vegetable", getRandFood("vegetable"))
      buyItems(shoppingList)
      SpecialInstructions.waitTurns(1)
      consumeFood
      SpecialInstructions.waitTurns(1)
    }
  }
}
