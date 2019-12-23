package meta.example.supermarket.goods

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.example.supermarket.Supermarket
import squid.quasi.lift

@lift
class Item2 extends Actor with Potato with ItemTrait{
  var age = 0
  var supermarket: Supermarket = null

  def main(): Unit = {
    while(age <= freshUntil) {
      if (age==2){ purchase }
//      if (age==4){ consume }
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
