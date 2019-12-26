package meta.example.supermarket.people

import meta.deep.runtime.Actor
import meta.example.supermarket.{Supermarket, priceOrderedPQ}

import scala.util.Random

trait People extends Actor{

//  val age: Int
  val frequency: Int
  var supermarket: Supermarket = Supermarket.store

  assert(supermarket.vegetables.size>1)
  var fridge: priceOrderedPQ = new priceOrderedPQ

  def getRandFood(category: String): String = {
    category.capitalize match {
      case "Vegetable" => supermarket.vegetables(Random.nextInt(3))
      case "Meat" => supermarket.meats(Random.nextInt(supermarket.meats.size))
      case "Dairy" => supermarket.dairys(Random.nextInt(supermarket.dairys.size))
      case "Snack" => supermarket.snacks(Random.nextInt(supermarket.snacks.size))
      case "Grain" => supermarket.grains(Random.nextInt(supermarket.grains.size))
      case _ => {println("Unrecognized food category name for generating food! Category is " + category); throw new Exception}
    }
  }

  def buyFood(category: String, item: String): Unit = {
    println("Customer buys food! " + category + " " + item)
    fridge.pq.enqueue(supermarket.sell(category, item))
  }

  def consumeFood: Unit = {
    println("Customer consumed vegetable! ")
    fridge.pq.dequeue().consume
  }

  def customerInfo: Unit = {
    println("Customer's Actor id " + id + " shopping frequency " + frequency)
  }
}
