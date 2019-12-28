package meta.example.supermarket.goods

/* Auto generated from file generateGoods
 Please adjust file categories for modification purpose
 */

trait Meat {
  val category: String = "Meat"
  val freshUntil: Int = 15
  val visibility: Double = 0.8
}

trait Chicken extends Meat {
  val name: String = "Chicken"
  val price: Double = 15.0
  val priceUnit: Int = 1000
  val discount: Double = 0.0
  val stock: Int = 3
}

trait Beef extends Meat {
  val name: String = "Beef"
  val price: Double = 35.0
  val priceUnit: Int = 1000
  val discount: Double = 0.0
  val stock: Int = 3
}

trait Pork extends Meat {
  val name: String = "Pork"
  val price: Double = 25.0
  val priceUnit: Int = 1000
  val discount: Double = 0.0
  val stock: Int = 3
}

trait Lamb extends Meat {
  val name: String = "Lamb"
  val price: Double = 45.0
  val priceUnit: Int = 1000
  val discount: Double = 0.0
  val stock: Int = 3
}
