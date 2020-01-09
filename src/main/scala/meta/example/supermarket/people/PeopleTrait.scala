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
  val preference: String
  val mealCnt: Int
  var supermarket: Supermarket = Supermarket.store
  assert(supermarket.vegetables.size>1) // store has been properly initialized
  val fridge: Fridge = new Fridge

  def buyRandItems(shoppingList: categoryAmount): Unit = {
    if (!needBased){
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
  }

  def buyListedItems(meal: Vector[(articleName, Int)], onBudget: Boolean = true): Unit ={
    val shoppingList: Map[String, Int] = toShoppingList(meal).toMap
    meal.foreach(articlePair => {
      if (fridge.getAmount(articlePair._1)<(frequency*articlePair._2)) {
        println("Customer buys food from shopping list! " + articlePair._1)
        1.to(shoppingList(articlePair._1)).foreach(_ => buyItem(articlePair._1, onBudget))
      }
    })
  }

  def buyItem(item: String, onBudget: Boolean = true): Unit = {
    fridge.add(supermarket.sell(item, onBudget))
  }

  // Random consumption behavior
  def consumeFood: Unit = {
    val someFood: String = randElement(fridge.getAvailFood)
    println("Customer consumes random food " + someFood)
    println(" amount " + fridge.consume(someFood, 200))
  }

  // Target consumption behavior
  def consumeFood(mealPlan: Vector[(articleName, gram)]): Unit = {
    mealPlan.foreach(pair => {
      val consumed: Int = fridge.consume(pair._1, pair._2)
      println("Customer consumed " + pair._1 + " Amount " + consumed)
      if (consumed < pair._2){
        println("Not enough food left! Do shopping!")
        buyListedItems(Vector((pair._1, pair._2)))
      }
    })
  }

  def customerInfo: Unit = {
    println("Customer's Actor id " + id + " frequency " + frequency + " fridge " + fridge)
  }
}
