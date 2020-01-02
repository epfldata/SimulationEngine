import meta.example.supermarket.{categoryAmount, utils}
import org.scalatest._

class utilsSpec extends FlatSpec with Matchers{

  "ccArgToList" should "convert the parameter list of a case class to a list" in {
    val foo: categoryAmount = categoryAmount(1, 2, 3, 4, 5)
    utils.ccArgToVector(foo) should be
    List(("Vegetable", 1.0), ("Meat", 2.0), ("Snack", 3.0), ("Grain", 4.0), ("Dairy", 5.0))
  }

  "toInt" should "convert a Boolean to int. Return 1 if true 0 otherwise" in {
    utils.toInt(true) should be (1)
    utils.toInt(false) should be (0)
  }

  "to2Dec" should "keep 2 decimal places of a floating point val" in {
    utils.to2Dec(1.23456) should be (1.23)
  }

  "divCeil" should "return the ceil of two integer division" in {
    utils.divCeil(4, 2) should be (2)
    utils.divCeil(4, 3) should be (2)
    utils.divCeil(1, 5) should be (1)
  }

  "toShoppingList which is based on divCeil" should "convert weight (gram) to the number of items" in {
    utils.toShoppingList(Vector(("Egg", 50))) should be (Vector(("Egg", "Dairy", 1)))
  }

//  info("Util methods work as expected!")
}
