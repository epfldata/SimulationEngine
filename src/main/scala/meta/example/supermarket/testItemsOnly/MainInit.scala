package meta.example.supermarket.testItemsOnlyExample

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods._
@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()

    val item1 = new Item1
    Supermarket.store.add(item1.asInstanceOf[Item])
    l.append(item1)

    val item2 = new Item2
    Supermarket.store.add(item2.asInstanceOf[Item])
    l.append(item2)

    val item3 = new Item3
    Supermarket.store.add(item3.asInstanceOf[Item])
    l.append(item3)

    l.toList
  }
}
