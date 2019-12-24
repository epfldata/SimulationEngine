package meta.example.supermarket.testItemsOnlyExample

import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer
import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods.{Item, Item1, Item2, Item3}
import meta.example.supermarket.Customer
import meta.example.supermarket.priceOrderedPQ

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()

    val customer = new Customer

    l.append(customer)
    var ctr = 0
    val vegetables: priceOrderedPQ = new priceOrderedPQ

    val potato = new Item3
    l.append(potato)

    vegetables.pq ++= List(potato.asInstanceOf[Item])

    while (ctr < 5){
      val item1 = new Item1
      val item2 = new Item2
      vegetables.pq ++= List(item1.asInstanceOf[Item], item2.asInstanceOf[Item])
      l.append(item1, item2)
      ctr = ctr+1
    }

    Supermarket.store.vegetables = vegetables.pq
    l.toList
  }
}
