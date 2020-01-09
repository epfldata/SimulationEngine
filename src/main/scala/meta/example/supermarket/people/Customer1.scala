package meta.example.supermarket.people

import meta.classLifting
import meta.classLifting.SpecialInstructions
import meta.example.supermarket._
import squid.quasi.lift

@lift
class Customer1 extends People with BiWeekly with MealPlan1 with TargetShopper {

  def main(): Unit = {

    while(true) {
      SpecialInstructions.handleMessages()
      customerInfo

      buyListedItems(shoppingList.targetItems)
      buyRandItems(shoppingList.randItems)
      customerInfo
      List.range(0, frequency).foreach(_ => {
        consumeFood(mealPlan)
        consumeFood
        customerInfo
        SpecialInstructions.waitTurns(1)
      })
    }
  }
}
