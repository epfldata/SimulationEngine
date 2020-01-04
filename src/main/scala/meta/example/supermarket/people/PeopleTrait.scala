package meta.example.supermarket.people

import meta.deep.runtime.Actor
import meta.example.supermarket._
import meta.example.supermarket.categories.{articleName, gram, getArticleUnit}
import meta.example.supermarket.utils.{randElement, toShoppingList}

trait People extends Actor{

//  val age: Int
  val frequency: Int
  val priceConscious: Double
  val needBased: Boolean
  val shoppingList: ShoppingList
  val mealPlan: Vector[(articleName, gram)]
  var supermarket: Supermarket = Supermarket.store
  assert(supermarket.vegetables.size>1) // store has been properly initialized
  val fridge: Fridge = new Fridge

  def buyRandItems(shoppingList: categoryAmount): Unit = {
    val foods = utils.ccArgToVector(shoppingList)
    foods.foreach(
      categoryAmountPair => {
        1.to(categoryAmountPair._2.asInstanceOf[Int]).foreach(_ => {
          val randFood: String = supermarket.getRandFood(categoryAmountPair._1)
          println("Customer buys random food! " + randFood)
          buyItem(randFood)
        })
      }
    )
  }

  def buyListedItems(meal: Vector[(articleName, Int)]): Unit ={
    val shoppingList: Map[String, Int] = toShoppingList(meal).toMap
    meal.foreach(articlePair => {
      if (fridge.getAmount(articlePair._1)<(frequency*articlePair._2)) {
        println("Customer buys food from shopping list! " + articlePair._1)
        1.to(shoppingList(articlePair._1)).foreach(_ => buyItem(articlePair._1))
      }
    })
  }

  def buyItem(item: String): Unit = {
    fridge.add(supermarket.sell(item))
  }

  // Random consumption behavior
  def consumeFood: Unit = {
    val someFood: String = randElement(fridge.getAvailFood)
    println("Customer consumes 100g of random food " + someFood)
    fridge.consume(someFood, 100)
  }

  // Target consumption behavior
  def consumeFood(mealPlan: Vector[(articleName, gram)]): Unit = {
//    val consumed: Vector[Int] = mealPlan.map(pair => {
    mealPlan.foreach(pair => {
      println("Customer consumed " + pair._1 + " Amount " + fridge.consume(pair._1, pair._2))
    })
  }

  def customerInfo: Unit = {
    println("Customer's Actor id " + id + " shopping frequency " + frequency)
  }
}
