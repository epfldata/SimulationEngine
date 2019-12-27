package meta.example.supermarket.testCustomer

import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer
import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods._
import meta.example.supermarket.people.Customer

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()

    val customer = new Customer

    l.append(customer)
    var ctr = 0

    val potato = new Item3
    l.append(potato)
    Supermarket.store.add(potato.asInstanceOf[Item])

    while (ctr < 2){
      val item1 = new Item1
      val item2 = new Item2
      Supermarket.store.add(List(
        item1.asInstanceOf[Item],
        item2.asInstanceOf[Item]))
      l.append(item1, item2)
      ctr = ctr+1
    }

    l.toList
  }
}
