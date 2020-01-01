package supermarket
import meta.example.supermarket.{Supermarket, categoryAmount}
import meta.example.supermarket.goods.{Item1, ItemState, newItemsMap}
import org.scalatest._

import scala.collection.mutable

class ItemSpec extends FlatSpec with Matchers {
  val item1 = new Item1

  "Age of new item" should "be 0" in {
    item1.age should be (0)
  }

  "State of new item" should "be onDisplay" in {
    item1.state.get should be ("onDisplay")
  }

  "Fields of Item1" should "match values defined" in {
    item1.id should be (1)
    item1.category should be ("Vegetable")
    item1.name should be ("Eggplant")
    item1.discount should be (0.0)
    item1.stock should be (3)
    item1.priceUnit should be (200)
    item1.price should be (2.0)
    item1.freshUntil should be (5)
    item1.visibility should be (1.0)
  }

  "Transition functions defined in ItemState" should "update the state" in {
    item1.state.purchase
    item1.state.get should be ("isPurchased")
    item1.state.consume
    item1.state.get should be ("isConsumed")
    item1.state.discard
    item1.state.get should be ("isDiscarded")
  }

  "Item update state" should "update the state with new state" in {
    val foo: Item1 = new Item1
    foo.id should be (2)
    foo.state.get should be ("onDisplay")
    foo.updateState("isPurchased", foo.state)
    foo.state.get should be ("isPurchased")
    foo.updateState("isDiscarded", foo.state)
    foo.state.get should be ("isDiscarded")
    foo.updateState("isConsumed", foo.state)
    foo.state.get should be ("isConsumed")
    a [IllegalArgumentException] should be thrownBy {
      foo.updateState("randomState", foo.state)
    }
  }

  "Item action" should "update the state with new state" in {
    val foo: Item1 = new Item1
    foo.id should be (3)
    foo.state.get should be ("onDisplay")
    foo.purchase
    foo.state.get should be ("isPurchased")
    foo.discard
    foo.state.get should be ("isDiscarded")
    foo.consume
    foo.state.get should be ("isConsumed")
  }

  "store" should "have no isInvalids and waste summary is empty" in {
    Supermarket.store.isInvalids.size should be (0)
    Supermarket.store.wasteSummary should be (categoryAmount(0, 0, 0, 0, 0))
  }

  "Clean expired" should "set the state to discard and record waste" in {
    val foo: Item1 = new Item1
    foo.id should be (4)
    foo.cleanExpired
    foo.state.get should be ("isDiscarded")
    Supermarket.store.wasteSummary should be (categoryAmount(foo.priceUnit, 0, 0, 0, 0))
  }

  "Supermarket isInvalids" should "record items been discarded through cleanExpired only" in {
    Supermarket.store.isInvalids.size should be (1)
    Supermarket.store.isInvalids should be (mutable.Queue(4))
  }

  "Invalid item state" should "throw IllegalArgumentException" in {
    val randState = ItemState(false, false, false, false)
    a [IllegalArgumentException] should be thrownBy {
      randState.get
    }
  }

  "ItemMap" should "contain mappings" in {
    assert(newItemsMap.itemMap.size > 0)
  }
}
