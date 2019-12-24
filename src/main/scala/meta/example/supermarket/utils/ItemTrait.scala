package meta.example.supermarket.goods

import meta.deep.runtime.Actor
import meta.example.supermarket.Supermarket
import meta.example.supermarket.utils.to2Dec
import squid.quasi.lift

//@lift
trait Item extends Actor{

  val name: String
  val price: Double
  val priceUnit: Int
  val discount: Double
  val stock: Int
  val category: String
  val freshUntil: Int
  val visibility: Double

  var age: Int
  var state: ItemState = ItemState()
  var supermarket: Supermarket = Supermarket.store

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

  def itemInfo: Unit = {
    println("Item id " + id + " name " + name + " Category: " + category +
      " Age: " + age + " Freshness: " + to2Dec(1-1.0*age/freshUntil) +
      " State: " + state.get
    )
  }

  def cleanExpired: Unit = {
    if (!state.isConsumed){
      discard
      itemInfo
      supermarket.recordWaste(category, priceUnit, state.isPurchased)
    }
  }
}
