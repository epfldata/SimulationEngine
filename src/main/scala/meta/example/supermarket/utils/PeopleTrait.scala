package meta.example.supermarket.people

import meta.deep.runtime.Actor
import meta.example.supermarket.Supermarket
import meta.example.supermarket.utils.to2Dec
import squid.quasi.lift

trait People extends Actor{

//  val age: Int
  val frequency: Int
  var supermarket: Supermarket = Supermarket.store

  def customerInfo: Unit = {
    println("Customer's Actor id " + id + " shopping frequency " + frequency)
  }
}
