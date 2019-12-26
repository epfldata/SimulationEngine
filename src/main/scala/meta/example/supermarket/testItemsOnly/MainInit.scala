package meta.example.supermarket.testItemsOnly

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
    val item2 = new Item2
    val item3 = new Item3
    Supermarket.store.add(List(
      item1.asInstanceOf[Item],
      item2.asInstanceOf[Item],
      item3.asInstanceOf[Item],
    ))
    l.append(item1, item2, item3)

    l.toList
  }
}
