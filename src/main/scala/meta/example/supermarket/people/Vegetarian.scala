package meta.example.supermarket

import meta.example.supermarket.categories.{articleName, gram}

object Vegetarian {
  // 1: 1 unit of food (1 agent)
  val randShoppingList: Vector[categoryAmount] = Vector(
    categoryAmount(1, 0, 0, 0, 1),
    categoryAmount(1, 0, 0, 1, 1),
    categoryAmount(3, 0, 2, 2, 0),
    categoryAmount(3, 0, 0, 3, 0),
    categoryAmount(3, 0, 0, 1, 0)
  )

  val mealPlan: Vector[Vector[(articleName, gram)]] = Vector(
    Vector(
      ("Milk", 200),
      ("Cereal", 200),
      ("Egg", 50)
    ),
    Vector(
      ("Carrots", 200),
      ("Cucumber", 200),
      ("Potato", 200)
    )
  )
}
