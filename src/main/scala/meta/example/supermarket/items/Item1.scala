package meta.example.supermarket.goods

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Item1 extends Item with Eggplant {
  var age: Int = 1 // age when the item is on display

  def main(): Unit = {
    // state change is instantaneous now as supermarket is no longer an agent
    while(age < freshUntil && !state.isConsumed) {
        itemInfo
        SpecialInstructions.waitTurns(1)
        age = age + 1
    }
    cleanExpired
  }
}
