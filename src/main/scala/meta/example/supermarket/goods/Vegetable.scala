package meta.example.supermarket.goods

trait Vegetable {
  val category: String = "Vegetable"
  val freshUntil: Int = 5
  val visibility: Double = 1.0
}

trait Eggplant extends Vegetable {
  val name: String = "Eggplant"
  val price: Double = 2.0
  val priceUnit: Int = 200
  val discount: Double = 0.0
  val stock: Int = 100
}

trait Potato extends Vegetable {
  val name: String = "Potato"
  val price: Double = 0.8
  val priceUnit: Int = 200
  val discount: Double = 0.0
  val stock: Int = 100
}

trait Onion extends Vegetable {
  val name: String = "Onion"
  val price: Double = 0.8
  val priceUnit: Int = 200
  val discount: Double = 0.0
  val stock: Int = 100
}

trait Broccoli extends Vegetable {
  val name: String = "Broccoli"
  val price: Double = 2.0
  val priceUnit: Int = 200
  val discount: Double = 0.0
  val stock: Int = 100
}

trait Cucumber extends Vegetable {
  val name: String = "Cucumber"
  val price: Double = 1.5
  val priceUnit: Int = 200
  val discount: Double = 0.0
  val stock: Int = 100
}

trait Carrots extends Vegetable {
  val name: String = "Carrots"
  val price: Double = 1.0
  val priceUnit: Int = 200
  val discount: Double = 0.0
  val stock: Int = 100
}
