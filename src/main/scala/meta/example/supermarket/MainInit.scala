package meta.example.supermarket
import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

@lift 
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()
    val shop = new Supermarket
    l.append(shop)
    for(i <- 0 to 4) {
       val customer = new Customer
       customer.shop = shop
       l.append(customer)
    }
    l.toList
  }
}
