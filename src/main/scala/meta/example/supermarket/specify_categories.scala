package meta.example.supermarket.goods

import meta.example.supermarket.utils

import scala.collection.mutable.{ListBuffer, Map}

case class categoryAmount(var Vegetable: Double=0.0,
                        var Meat: Double=0.0,
                        var Snack: Double=0.0,
                        var Grain: Double=0.0,
                        var Dairy: Double=0.0)

class categories {
  import categories._

  private val categoryMap: Map[String, List[String]] = Map[String, List[String]]()
  private def addToSummary(name: String, newCategory: CategoryFields, newCategoryNamePrice: namePriceUnit):Unit = {
    summary.append((name, newCategory, newCategoryNamePrice))
    totalCnt += 1
  }

  // Capitalize the Name
  def addAll: Unit = {
    addToSummary("Vegetable", Vegie, vegiess)
    addToSummary("Meat", Meat, meatss)
    addToSummary("Snack", Snack, snackss)
    addToSummary("Grain", Grain, grainss)
    addToSummary("Dairy", Dairy, dairyss)
  }

  addAll

  def getCnt: Int = { totalCnt }

  def getSummary: List[(String, CategoryFields, namePriceUnit)] = { summary.toList }

  def getCategoryNames: List[String] = { summary.toList.map(item => item._1.capitalize) }

  def getArticleNames(categoryName: String): List[String] ={
    summary.find(x => x._1==categoryName.capitalize).get
      ._3
      .map(article => article._1.capitalize)
  }

  def getArticlePrices(categoryName: String): List[Double] ={
    summary.find(x => x._1==categoryName.capitalize).get
      ._3
      .map(article => article._2)
  }

  assert(utils.ccArgToList(categoryAmount()).map(x => x._1).equals(getCategoryNames))
}

object categories {
  type namePriceUnit = List[(String, Double, Int)]
  val kg: Int = 1000
  val bar: Int = 100
  val box: Int = 250
  val bag: Int = 300
  val cup: Int = 50 // a cup of yogurt
  val piece: Int = 200 // a piece of cheese/cucumber

  var totalCnt: Int = 0;
  val summary: ListBuffer[(String, CategoryFields, namePriceUnit)] = ListBuffer()

  val Vegie: CategoryFields = CategoryFields(5, 1.0)
  // (name, price, unit)
  // Unify the unit to simplify random selection of the article
  val vegiess: namePriceUnit = List(
    ("eggplant", 2, piece),
    ("potato", 0.8, piece),
    ("onion", 0.8, piece),
    ("broccoli", 2, piece),
    ("cucumber", 1.5, piece),
    ("carrots", 1, piece))

  val Meat: CategoryFields = CategoryFields(15, 0.8)
  val meatss: namePriceUnit = List(
    ("chicken", 15, kg),
    ("beef", 35, kg),
    ("pork", 25, kg),
    ("lamb", 45, kg))

  val Snack: CategoryFields = CategoryFields(100, 1.0)
  val snackss: namePriceUnit = List(
    ("kitkat", 3.5, bag),
    ("ferraro", 5, box),
    ("darkChocolate", 1.8, bar),
    ("whiteChocolate", 1.8, bar))

  val Grain: CategoryFields = CategoryFields(50, 0.6)
  val grainss: namePriceUnit = List(
    ("cereal", 4, kg),
    ("oatmeal", 4, kg),
    ("rice", 2, kg),
    ("noodles", 3, kg),
    ("spaghetti", 1.5, kg),
    ("pasta", 1.5, kg))

  // Treat egg as vegetable as it has expiration date is later
  val Dairy: CategoryFields = CategoryFields(7, 0.8)
  val dairyss: namePriceUnit = List(
    ("Milk", 2, kg),
    ("Yogurt", 1, cup),
    ("Cheese", 5, piece),
    ("Cream", 1, cup),
    ("egg", 3, box))
}
