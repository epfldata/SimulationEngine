package meta.example.supermarket.people

import meta.classLifting.SpecialInstructions
import squid.quasi.lift

@lift
class Customer2 extends People with BiWeekly with MealPlanV1_4 with TargetShopper {

  def main(): Unit = {
    while(true) {
      SpecialInstructions.handleMessages()
      buyListedItems(shoppingList.targetItems)
      SpecialInstructions.waitTurns(1)
      consumeFood(mealPlan)
      SpecialInstructions.waitTurns(1)
    }
  }
}
