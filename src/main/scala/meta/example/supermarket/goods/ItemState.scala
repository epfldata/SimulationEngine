package meta.example.supermarket.goods

import java.io.InvalidClassException

case class ItemState(var onDisplay: Boolean = true,
                     var isPurchased: Boolean = false,
                     var isDiscarded: Boolean = false,
                     var isConsumed: Boolean = false) {

  def get: String = {
    if (onDisplay) { "onDisplay" }
    else if (isDiscarded) { "isDiscarded" }
    else if (isConsumed) { "isConsumed" }
    else if (isPurchased) { "isPurchased" }
    else throw new IllegalArgumentException
  }

  def discard: Unit = {
    isDiscarded = true
    onDisplay = false
    isConsumed = false
  }

  def purchase: Unit = {
    isPurchased = true
    onDisplay = false
    isDiscarded = false
  }

  def consume: Unit = {
    isConsumed = true
    onDisplay = false
    isDiscarded = false
  }
}
