import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods._
import meta.example.supermarket.people.Customer1

import org.scalatest._

class PeopleSpec extends FlatSpec with Matchers {
  val customer1 = new Customer1
  // populate with Broccoli, Beef, Rice, and WhiteChocolate. Same as defined in shoppingList
  Supermarket.store.initializeItemDeque(Vector(new Item4, new Item8, new Item17, new Item14))

  "Buy listed items" should "populate the fridge" in {
//    println(customer1.mealPlan)
//    println(Supermarket.store.warehouse)
    customer1.buyListedItems(customer1.shoppingList.targetItems)
    println(customer1.fridge.storage)
  }
}
