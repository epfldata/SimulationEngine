package meta.example.supermarket

import meta.example.supermarket.categories.{articleName, gram}
import scala.util.Random

object Carnivore {
  // 1: 1 unit of food (1 agent)
  val randShoppingList: Vector[categoryAmount] = Vector(
    categoryAmount(1, 0, 0, 0, 1),
    categoryAmount(0, 1, 0, 1, 1),
    categoryAmount(1, 1, 2, 2, 0),
    categoryAmount(1, 1, 1, 3, 0),
    categoryAmount(1, 1, 1, 1, 0)
  )

  val mealPlan: Vector[Vector[(articleName, gram)]] = Vector(
    Vector(
      ("Egg", 50),
      ("Milk", 200),
      ("Bacon", 100),
      ("Oatmeal", 100)
    ),
    Vector(
      ("Broccoli", 200),
      ("Beef", 500),
      ("Rice", 200),
      ("WhiteChocolate", 50)
    ),
    Vector(
      ("Mushroom", 200),
      ("Carrots", 50),
      ("Chicken", 200),
      ("Milk", 200),
      ("Noodles", 200)
    )
  )

  def getRandShoppingList(listNum: Int = 1): categoryAmount = {
    randShoppingList(Random.nextInt(randShoppingList.size))
  }
}
