package meta.example.supermarket

import meta.example.supermarket.categories._
import meta.example.supermarket.utils.divCeil

object Meal {

  type gram = Int
  val option1: Vector[(articleName, gram)] = Vector(
    ("Milk", 200),
    ("Cereal", 200)
  )

  val option2: Vector[(articleName, gram)] = Vector(
    ("Milk", 200),
    ("Cereal", 200),
    ("Egg", 50)
  )

  def toShoppingList(meal: Vector[(articleName, gram)]): Vector[(articleName, categoryName, Int)] = {
    meal.map(
      pair => (pair._1, getArticleCategory(pair._1), divCeil(pair._2, getArticleUnit(pair._1)))
    )
  }

  val shopOption1: Vector[(articleName, categoryName, Int)] = toShoppingList(option1)
}
