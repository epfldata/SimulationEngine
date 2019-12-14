package meta.example.supermarket.testItemsOnlyExample

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods.{Item1, Item2}
@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()
    val supermarket = new Supermarket
    l.append(supermarket)

    val item1 = new Item1
    item1.supermarket = supermarket
    l.append(item1)

    val item2 = new Item2
    item2.supermarket = supermarket
    l.append(item2)

    l.toList
  }
}
