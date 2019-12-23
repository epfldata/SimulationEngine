package meta.example.supermarket.goods

trait ItemTrait {
  var state: ItemState = ItemState()

  def updateState(newState: String, itemState: ItemState): Unit ={
    newState match {
      case "isPurchased" => itemState.purchase
      case "isDiscarded" => itemState.discard
      case "isConsumed" => itemState.consume
      case _ => throw new Exception
    }
  }

  def discard: Unit = { updateState("isDiscarded", state) }

  def purchase: Unit = { updateState("isPurchased", state) }

  def consume: Unit = { updateState("isConsumed", state) }

  def updateFreshness(age: Int, freshUntil: Int): Unit ={
    println("Current age " + age + " freshness " + (1-1.0*age/freshUntil))
  }
}
