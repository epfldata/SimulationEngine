package meta.example.supermarket

import meta.example.supermarket.categories.{articleName, categoryName}

//case class itemAmount(var name: String, var quantity: Double)

class ShoppingList(var randItems: categoryAmount, var targetItems: Vector[(articleName, categoryName, Int)]) {
  var isRandom: Boolean = true

  def this(randItems: categoryAmount){
    this(randItems, targetItems=null)
  }

  def this(targetItems: Vector[(articleName, categoryName, Int)]){
    this(randItems=null, targetItems)
    this.isRandom = false
  }
}
