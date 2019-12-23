package meta.example.supermarket.goods

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.example.supermarket.Supermarket
import squid.quasi.lift

@lift
class Item1 extends Actor with Broccoli with ItemTrait{
  var age: Int = 1 // age when the item is on display
  var supermarket: Supermarket = null

  def main(): Unit = {
    while(age <= freshUntil) {
//      if (age==2){ purchase }
//      if (age==5){ consume }
      updateFreshness(age, freshUntil)
      state.get
      SpecialInstructions.waitTurns(1)
      age = age + 1
    }
    if (!state.isConsumed){
      discard
      supermarket.recordWaste(category, priceUnit, state.isPurchased)
    }
  }
}
