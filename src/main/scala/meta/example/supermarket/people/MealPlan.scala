package meta.example.supermarket.people

import meta.example.supermarket.categories.{articleName, gram}
import meta.example.supermarket.{Carnivore, ShoppingList, Vegetarian, categoryAmount}

trait MealPlanV1_1 {
  val mealPlan: Vector[(articleName, gram)] = Carnivore.mealPlan(0)
  val randShoppingList: categoryAmount = Carnivore.randShoppingList(0)
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}

trait MealPlanV1_4 {
  val mealPlan: Vector[(articleName, gram)] = Vegetarian.mealPlan(0)
  val randShoppingList: categoryAmount = Vegetarian.randShoppingList(0)
  val shoppingList: ShoppingList = new ShoppingList(randShoppingList, mealPlan)
}


