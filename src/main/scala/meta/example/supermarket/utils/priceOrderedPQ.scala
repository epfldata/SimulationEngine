package meta.example.supermarket

import meta.example.supermarket.goods.Item
import scala.collection.mutable.PriorityQueue

class priceOrderedPQ {
  def PriceOrdering = new Ordering[Item] {
    def compare(a: Item, b: Item) = a.price.compare(b.price)
  }

  val pq = new PriorityQueue[Item]()(PriceOrdering)
}