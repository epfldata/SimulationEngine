package meta.example.supermarket

import meta.example.supermarket.categories.{articleName, categoryName}

class ShoppingList(var randItems: categoryAmount, var targetItems: Vector[(articleName, Int)]) {
  var isRandom: Boolean = true

  def this(randItems: categoryAmount){
    this(randItems, targetItems=null)
  }

  def this(targetItems: Vector[(articleName, Int)]){
    this(randItems=null, targetItems)
    this.isRandom = false
  }
}
