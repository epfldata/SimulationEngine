package meta.example.supermarket.goods

case class ItemState(var onDisplay: Boolean = true,
                     var isPurchased: Boolean = false,
                     var isDiscarded: Boolean = false,
                     var isConsumed: Boolean = false) {
  def get: Unit = {
    if (onDisplay) { println("onDisplay") }
    if (isPurchased) { println("isPurchased") }
    if (isDiscarded) { println("isDiscarded") }
    if (isConsumed) { println("isConsumed") }
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
