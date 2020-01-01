package meta.example.supermarket

object Carnivore {
  // 1: 1 unit of food (1 agent)
  val randShoppingList: Vector[categoryAmount] = Vector(
    categoryAmount(1, 0, 0, 0, 1),
    categoryAmount(0, 1, 0, 1, 1),
    categoryAmount(1, 1, 2, 2, 0),
    categoryAmount(1, 1, 1, 3, 0),
    categoryAmount(1, 1, 1, 1, 0)
  )
}
