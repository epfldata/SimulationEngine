import meta.example.supermarket.Fridge
import meta.example.supermarket.goods._
//import meta.example.supermarket.{categories, categoryAmount, utils}
import org.scalatest._

//@Ignore
class FridgeSpec extends FlatSpec with Matchers {
  val fridge: Fridge = new Fridge()
  val item1_1 = new Item1
  val item1_2 = new Item1
  val item1_3 = new Item1
  val item1_4 = new Item1
  val item2 = new Item2

  "New fridge" should "have be empty" in {
    fridge.getAmount("Carrots") should be (0)
    fridge.isEmpty should be (true)
  }

  "Add an item to the fridge" should "update the amount and storage" in {
    fridge.add(item1_1)
//    fridge.amountMap should be (Map(item1.name -> item1.priceUnit))
    fridge.getAmount(item1_1.name) should be (item1_1.priceUnit)
  }

  "Add multiple items" should "work" in {
    fridge.add(item2)
    fridge.getAmount(item2.name) should be (item2.priceUnit)
    fridge.add(item1_2)
    fridge.getAmount(item1_1.name) should be (2*item1_1.priceUnit)
//    println(fridge.storage)
  }

  "Query non-existing article" should "return 0" in {
    fridge.getAmount("Kitkat") should be (0)
  }

  "Consume an unexpired article" should "set the article state to consumed" in {
    val prior = fridge.storage
    fridge.consume(item2.name, item2.priceUnit)
    fridge.storage.get(item2.name).get should have size 0
    fridge.getAmount(item2.name) should be (0)
    item2.state.get should be ("isConsumed")
    fridge.opened(item2.name) should be (0)
  }

  "Partially consume an item" should "change the amountMap and opened, but not storage" in {
    fridge.consume(item1_1.name, 50)
    fridge.storage.get(item1_1.name).get should have size 2
    fridge.opened(item1_1.name) should be (item1_1.priceUnit-50)
    fridge.getAmount(item1_1.name) should be (2*item1_1.priceUnit-50)

    fridge.consume(item1_1.name, 50)
    fridge.storage.get(item1_1.name).get should have size 2
    fridge.opened(item1_1.name) should be (item1_1.priceUnit-100)
    fridge.getAmount(item1_1.name) should be (2*item1_1.priceUnit-100)

    fridge.consume(item1_1.name, 100)
    fridge.storage.get(item1_1.name).get should have size 1
    fridge.opened(item1_1.name) should be (200)
    fridge.getAmount(item1_1.name) should be (item1_1.priceUnit)
    item1_1.state.get should be ("isConsumed")
    item1_2.state.get should be ("onDisplay") // initial state hasn't been updated
  }

  "Consume multiple items" should "work properly" in {
    fridge.add(item1_3)
    fridge.add(item1_4)
    fridge.getAmount(item1_1.name) should be (3*item1_1.priceUnit)
    fridge.consume(item1_2.name, 500) should be (500)
    item1_2.state.get should be ("isConsumed")
    item1_3.state.get should be ("isConsumed")
    item1_4.state.get should be ("onDisplay")
    fridge.opened.get(item1_1.name).get should be (100)
    fridge.getAmount(item1_1.name) should be (3*item1_1.priceUnit - 500)
  }

  "Consume more items than there are" should "return the amount of items consumed" in {
    fridge.storage.get(item1_1.name)
    fridge.consume(item1_1.name, 200) should be (100)
  }

  "Get available food" should "return a vector of available food" in {
    fridge.getAvailFood should be (Vector())
    fridge.add(new Item1)
    fridge.add(new Item2)
    fridge.getAvailFood.toSet should be (Vector(item1_1.name, item2.name).toSet)
    fridge.consume(item1_1.name, 100)
    fridge.consume(item1_1.name, 100)
    fridge.getAvailFood.toSet should be (Vector(item2.name).toSet)
  }

  "Remove expired food" should "update the wastage summary" in {
    val aboutToExpireItem: Item = new Item3
    aboutToExpireItem.state.purchase
    aboutToExpireItem.state.get should be ("isPurchased")
    fridge.add(aboutToExpireItem)
    fridge.consume(aboutToExpireItem.name, 50)
    fridge.getAmount(aboutToExpireItem.name) should be (aboutToExpireItem.priceUnit-50)
    fridge.opened(aboutToExpireItem.name) should be (aboutToExpireItem.priceUnit-50)

    aboutToExpireItem.cleanExpired()
    aboutToExpireItem.state.get should be ("isExpired")

    fridge.rmExpired(aboutToExpireItem.name)
    fridge.getAmount(aboutToExpireItem.name) should be (0)
    fridge.opened(aboutToExpireItem.name) should be (0)
    aboutToExpireItem.state.get should be ("isDiscarded")
  }

  "When an item expires" should "not be consumed" in {
    val expireFoo: Item = new Item3
    val expireBar: Item = new Item3
    Vector(expireBar, expireFoo).foreach(
      item=> { fridge.add(item); item.state.purchase })
    expireFoo.cleanExpired()
    expireBar.cleanExpired()
    expireFoo.state.get should be ("isExpired")
    expireBar.state.get should be ("isExpired")
    fridge.storage(expireFoo.name) should have size (2)
    fridge.opened(expireFoo.name) should be (expireFoo.priceUnit)
    fridge.consume(expireFoo.name, 100) should be (0)
    fridge.storage(expireFoo.name) should have size (0)
    fridge.opened(expireFoo.name) should be (0)
    fridge.amountMap(expireBar.name) should be (0)
    expireFoo.state.get should be ("isDiscarded")
    expireBar.state.get should be ("isDiscarded")
  }

  "Add new items of the same name" should "not change the opened amount" in {
    val egg1: Item = new Item25
    val egg2: Item = new Item25
    fridge.add(egg1)
    egg1.state.purchase
    fridge.opened(egg1.name) should be (egg1.priceUnit)
    fridge.consume(egg1.name, 50)
    fridge.opened(egg1.name) should be (egg1.priceUnit-50)
    fridge.add(egg2)
    egg2.state.purchase
    fridge.opened(egg1.name) should be (egg1.priceUnit-50)
    fridge.amountMap(egg1.name) should be (2*egg1.priceUnit-50)
  }
}
