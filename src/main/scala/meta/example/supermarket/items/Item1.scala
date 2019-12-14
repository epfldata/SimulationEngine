package meta.example.supermarket.goods

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import meta.example.supermarket.Supermarket
import squid.quasi.lift

@lift
class Item1 extends Actor with Broccoli with ItemTrait{
  var age: Int = 0
  var state: ItemState = ItemState()
  var supermarket: Supermarket = null

  def updateFreshness(elapsedDays: Int): Unit ={
    if (elapsedDays >= freshUntil){
      if (!state.isConsumed){
        discard
      }
    }
    else {
      println("Current age " + age + " freshness " + (1-1.0*age/freshUntil))
      age = age + 1
    }
  }

  // partial waste at home, no partial waste at supermarket
  def discard: Unit = {
    updateState("isDiscarded", state)
    supermarket.recordWaste(category, priceUnit, state.isPurchased)
  }

  def purchase: Unit = { updateState("isPurchased", state) }
  def consume: Unit = { updateState("isConsumed", state) }

  def main(): Unit = {
    var elapsedDays = 0
    while(elapsedDays <= freshUntil) {
      if (elapsedDays==2){ purchase }
      if (elapsedDays==5){ consume }
      updateFreshness(elapsedDays)
      state.get
      SpecialInstructions.waitTurns(1)
      elapsedDays = elapsedDays + 1
    }
  }
}
