package meta.example.supermarket.people

import meta.deep.runtime.Actor
import meta.example.supermarket._
import meta.example.supermarket.categories.{articleName, gram}
import meta.example.supermarket.utils.{randElement}

trait People extends Actor{

//  val age: Int
  val frequency: Int
  val priceConscious: Double
  val needBased: Double
//  val diet: String
  val shoppingList: ShoppingList
  val mealPlan: Vector[(articleName, gram)]
  var supermarket: Supermarket = Supermarket.store
  assert(supermarket.vegetables.size>1) // store has been properly initialized
  val fridge: Fridge = new Fridge

  def buyRandItems(shoppingList: categoryAmount): Unit = {
    val foods = utils.ccArgToVector(shoppingList)
    foods.foreach(
      categoryAmountPair => {
        var itemCtr = 0
        while (itemCtr < categoryAmountPair._2.asInstanceOf[Double]) {
          buyItem(supermarket.getRandFood(categoryAmountPair._1))
          itemCtr = itemCtr + 1
        }
      }
    )
  }

  def buyListedItems(shoppingList: Vector[(articleName, Int)]): Unit ={
    shoppingList.foreach(articlePair => {
      1.to(articlePair._2).foreach(_ => buyItem(articlePair._1))
    })
  }

  def buyItem(item: String): Unit = {
    println("Customer buys food! " + item)
    fridge.add(supermarket.sell(item))
  }

  // Random consumption behavior
  def consumeFood: Unit = {
    fridge.consume(randElement(fridge.getAvailFood), 100)
  }

  // Target consumption behavior
  def consumeFood(mealPlan: Vector[(articleName, gram)]): Unit = {
    val consumed: Vector[Int] = mealPlan.map(pair =>
      fridge.consume(pair._1, pair._2)
    )
    println(consumed)
  }

  def customerInfo: Unit = {
    println("Customer's Actor id " + id + " shopping frequency " + frequency)
  }
}
