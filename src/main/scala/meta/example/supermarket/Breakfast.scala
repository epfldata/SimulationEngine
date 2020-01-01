package meta.example.supermarket

import meta.example.supermarket.categories.{articleName, categoryName}

object Breakfast {
  val option1: Vector[(articleName, categoryName, Int)] = Vector(
    ("Milk", "Dairy", 1),
    ("Cereal", "Grain", 1)
  )

  val option2: Vector[(articleName, categoryName, Int)] = Vector(
    ("Milk", "Dairy", 1),
    ("Cereal", "Grain", 1),
    ("Egg", "Dairy", 1)
  )
}
