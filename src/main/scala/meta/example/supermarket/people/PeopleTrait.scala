package meta.example.supermarket.people

import meta.deep.runtime.Actor
import meta.example.supermarket._
import meta.example.supermarket.categories.{articleName, categoryName}

import scala.util.Random

trait People extends Actor{

//  val age: Int
  val frequency: Int
  val priceConscious: Double
  val needBased: Double
//  val diet: String
  val shoppingList: ShoppingList
  var supermarket: Supermarket = Supermarket.store

  assert(supermarket.vegetables.size>1)
  val fridge: ItemDeque = new ItemDeque

  def getRandFood(category: String): String = {
    category.capitalize match {
      case "Vegetable" => supermarket.vegetables(Random.nextInt(supermarket.vegetables.size))
      case "Meat" => supermarket.meats(Random.nextInt(supermarket.meats.size))
      case "Dairy" => supermarket.dairys(Random.nextInt(supermarket.dairys.size))
      case "Snack" => supermarket.snacks(Random.nextInt(supermarket.snacks.size))
      case "Grain" => supermarket.grains(Random.nextInt(supermarket.grains.size))
      case _ => {println("Unrecognized food category name for generating food! Category is " + category); throw new Exception}
    }
  }

  def buyRandItems(shoppingList: categoryAmount): Unit = {
    val foods = utils.ccArgToList(shoppingList)
    foods.foreach(
      categoryAmountPair => {
        var itemCtr = 0
        while (itemCtr < categoryAmountPair._2.asInstanceOf[Double]) {
          buyItem(categoryAmountPair._1, getRandFood(categoryAmountPair._1))
          itemCtr = itemCtr + 1
        }
      }
    )
  }

  def buyListedItems(shoppingList: Vector[(articleName, categoryName, Int)]): Unit ={
    shoppingList.foreach(articlePair => {
      1.to(articlePair._3).foreach(_ => buyItem(articlePair._2, articlePair._1))
    })
  }

  def buyItem(category: String, item: String): Unit = {
    println("Customer buys food! " + category + " " + item)
    fridge += supermarket.sell(category, item)
  }

  def consumeFood: Unit = {
    println("Customer consumed vegetable! ")
    fridge.popLeft.consume
  }

  def customerInfo: Unit = {
    println("Customer's Actor id " + id + " shopping frequency " + frequency)
  }
}
