package meta.example.supermarket.people

import meta.classLifting
import meta.classLifting.SpecialInstructions
import meta.example.supermarket._
import squid.quasi.lift

@lift
class Customer1 extends People with BiWeekly with MealPlanV1_1 with TargetShopper {

  def main(): Unit = {
    var ctr: Int = 0
    while(true) {
      SpecialInstructions.handleMessages()
      customerInfo
      buyListedItems(shoppingList.targetItems)
      buyRandItems(shoppingList.randItems)
//      (1 to 3).foreach(_ =>{
//        consumeFood(mealPlan)
//        SpecialInstructions.waitTurns(1)
//      })
      ctr = 0
      while(ctr < frequency){
        consumeFood(mealPlan)
        SpecialInstructions.waitTurns(1)
        ctr = ctr + 1
      }
    }
  }
}
