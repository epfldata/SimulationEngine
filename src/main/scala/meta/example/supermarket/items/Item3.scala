package meta.example.supermarket.goods

import meta.classLifting.SpecialInstructions
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Item3 extends Item with Potato {
  var age: Int = 0 // age when the item is on display

  def main(): Unit = {
    while (age < freshUntil && !state.isConsumed) {
      itemInfo
      SpecialInstructions.waitTurns(1)
      age = age + 1
    }
    cleanExpired
  }
}
