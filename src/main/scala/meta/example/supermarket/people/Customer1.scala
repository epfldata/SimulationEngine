package meta.example.supermarket.people

import meta.classLifting.SpecialInstructions
import meta.example.supermarket._
import squid.quasi.lift

@lift
class Customer1 extends People with BiWeekly with MealPlanV1_1 with TargetShopper {

  def main(): Unit = {
    while(true) {
      SpecialInstructions.handleMessages()
      buyRandItems(shoppingList.randItems)
      SpecialInstructions.waitTurns(1)
      consumeFood
      SpecialInstructions.waitTurns(1)
    }
  }
}
