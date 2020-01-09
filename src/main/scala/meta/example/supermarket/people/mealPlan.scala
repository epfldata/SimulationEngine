package meta.example.supermarket.people

import meta.example.supermarket.categories.{articleName, gram}
import meta.example.supermarket.{Carnivore, ShoppingList, Vegetarian, categoryAmount}

/* Auto generated from genMealPlans */

trait MealPlan1 {
  val preference: String = "Vegetarian"
  val mealCnt: Int = 1
  val mealPlan: Vector[(articleName, gram)] = Vector(0).flatMap(num => Vegetarian.mealPlan(num))
  val randShoppingList: categoryAmount = Vegetarian.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan2 {
  val preference: String = "Vegetarian"
  val mealCnt: Int = 1
  val mealPlan: Vector[(articleName, gram)] = Vector(1).flatMap(num => Vegetarian.mealPlan(num))
  val randShoppingList: categoryAmount = Vegetarian.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan3 {
  val preference: String = "Vegetarian"
  val mealCnt: Int = 1
  val mealPlan: Vector[(articleName, gram)] = Vector(2).flatMap(num => Vegetarian.mealPlan(num))
  val randShoppingList: categoryAmount = Vegetarian.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan4 {
  val preference: String = "Carnivore"
  val mealCnt: Int = 1
  val mealPlan: Vector[(articleName, gram)] = Vector(0).flatMap(num => Carnivore.mealPlan(num))
  val randShoppingList: categoryAmount = Carnivore.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan5 {
  val preference: String = "Carnivore"
  val mealCnt: Int = 1
  val mealPlan: Vector[(articleName, gram)] = Vector(1).flatMap(num => Carnivore.mealPlan(num))
  val randShoppingList: categoryAmount = Carnivore.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan6 {
  val preference: String = "Carnivore"
  val mealCnt: Int = 1
  val mealPlan: Vector[(articleName, gram)] = Vector(2).flatMap(num => Carnivore.mealPlan(num))
  val randShoppingList: categoryAmount = Carnivore.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan7 {
  val preference: String = "Vegetarian"
  val mealCnt: Int = 2
  val mealPlan: Vector[(articleName, gram)] = Vector(0, 1).flatMap(num => Vegetarian.mealPlan(num))
  val randShoppingList: categoryAmount = Vegetarian.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan8 {
  val preference: String = "Vegetarian"
  val mealCnt: Int = 2
  val mealPlan: Vector[(articleName, gram)] = Vector(0, 2).flatMap(num => Vegetarian.mealPlan(num))
  val randShoppingList: categoryAmount = Vegetarian.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan9 {
  val preference: String = "Vegetarian"
  val mealCnt: Int = 2
  val mealPlan: Vector[(articleName, gram)] = Vector(1, 2).flatMap(num => Vegetarian.mealPlan(num))
  val randShoppingList: categoryAmount = Vegetarian.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan10 {
  val preference: String = "Carnivore"
  val mealCnt: Int = 2
  val mealPlan: Vector[(articleName, gram)] = Vector(0, 1).flatMap(num => Carnivore.mealPlan(num))
  val randShoppingList: categoryAmount = Carnivore.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan11 {
  val preference: String = "Carnivore"
  val mealCnt: Int = 2
  val mealPlan: Vector[(articleName, gram)] = Vector(0, 2).flatMap(num => Carnivore.mealPlan(num))
  val randShoppingList: categoryAmount = Carnivore.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan12 {
  val preference: String = "Carnivore"
  val mealCnt: Int = 2
  val mealPlan: Vector[(articleName, gram)] = Vector(1, 2).flatMap(num => Carnivore.mealPlan(num))
  val randShoppingList: categoryAmount = Carnivore.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan13 {
  val preference: String = "Vegetarian"
  val mealCnt: Int = 3
  val mealPlan: Vector[(articleName, gram)] = Vector(0, 1, 2).flatMap(num => Vegetarian.mealPlan(num))
  val randShoppingList: categoryAmount = Vegetarian.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


trait MealPlan14 {
  val preference: String = "Carnivore"
  val mealCnt: Int = 3
  val mealPlan: Vector[(articleName, gram)] = Vector(0, 1, 2).flatMap(num => Carnivore.mealPlan(num))
  val randShoppingList: categoryAmount = Carnivore.getRandShoppingList()
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}

object mealPlanSummary {
  val total: Int = 14

  val mealPlanMap: Map[(String, Int), Vector[String]] = Map(
    ("Vegetarian",3) -> Vector("MealPlan13"),
    ("Carnivore",3) -> Vector("MealPlan14"),
    ("Carnivore",1) -> Vector("MealPlan4", "MealPlan5", "MealPlan6"),
    ("Carnivore",2) -> Vector("MealPlan10", "MealPlan11", "MealPlan12"),
    ("Vegetarian",1) -> Vector("MealPlan1", "MealPlan2", "MealPlan3"),
    ("Vegetarian",2) -> Vector("MealPlan7", "MealPlan8", "MealPlan9")
  )
}
