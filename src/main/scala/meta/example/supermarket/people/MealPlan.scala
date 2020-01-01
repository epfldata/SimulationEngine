package meta.example.supermarket.people

import meta.example.supermarket.{ShoppingList, Vegetarian, Breakfast}

trait MealPlanV1_1 {
//  val eatingHabbit: Int = 1
  val shoppingList: ShoppingList = new ShoppingList(Vegetarian.randShoppingList(0))
}

trait MealPlanV1_4 {
  val shoppingList: ShoppingList = new ShoppingList(Breakfast.option2)
}


