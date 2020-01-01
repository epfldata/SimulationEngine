package meta.example.supermarket

object Vegetarian {
  // 1: 1 unit of food (1 agent)
  val randShoppingList: Vector[categoryAmount] = Vector(
    categoryAmount(1, 0, 0, 0, 1),
    categoryAmount(1, 0, 0, 1, 1),
    categoryAmount(3, 0, 2, 2, 0),
    categoryAmount(3, 0, 0, 3, 0),
    categoryAmount(3, 0, 0, 1, 0)
  )
}
