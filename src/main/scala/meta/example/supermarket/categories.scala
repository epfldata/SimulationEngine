package meta.example.supermarket

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object categories {
  type categoryName = String
  type articleName = String
  type price = Double
  type priceUnit = Int
  type discount = Double
  type stock = Int
  type namePriceUnit = List[(articleName, price, priceUnit, discount, stock)]

  case class Attr(name: String, attrVal: Any)
  case class Article(name: articleName, fields: List[Attr])
  case class Category(name: categoryName, fields: List[Attr], children: List[Article])
  // Parameters of CategoryFields and ArticleFields should be DISJOINT
  case class ArticleFields(price: price, priceUnit: priceUnit, discount: discount, stock: stock)
  case class CategoryFields(freshUntil: Int, visibility: Double)

  val kg: Int = 1000
  val bar: Int = 100
  val box: Int = 250
  val bag: Int = 300
  val cup: Int = 50 // a cup of yogurt
  val carton: Int = 250
  val piece: Int = 200 // a piece of cheese/cucumber

  var totalCategories: Int = 0;
  val summary: ListBuffer[(categoryName, CategoryFields, namePriceUnit)] = ListBuffer()

  val Vegie: CategoryFields = CategoryFields(5, 1.0)
  // Unify the unit to simplify random selection of the article
  val vegieStock: stock = 5 // Leave stock as an attribute of item rather than category for more flexibility
  val vegiess: namePriceUnit = List(
    ("eggplant", 2, piece, 0, 3),
    ("potato", 0.8, piece, 0, 2),
    ("onion", 0.8, piece, 0, 2),
    ("broccoli", 2, piece, 0, vegieStock),
    ("cucumber", 1.5, piece, 0, vegieStock),
    ("carrots", 1, piece, 0, vegieStock))

  val Meat: CategoryFields = CategoryFields(15, 0.8)
  val meatStock: stock = 3
  val meatss: namePriceUnit = List(
    ("chicken", 15, kg, 0, meatStock),
    ("beef", 35, kg, 0, meatStock),
    ("pork", 25, kg, 0, meatStock),
    ("lamb", 45, kg, 0, meatStock))

  val Snack: CategoryFields = CategoryFields(100, 1.0)
  val snackStock: stock = 10
  val snackss: namePriceUnit = List(
    ("kitkat", 3.5, bag, 0, snackStock),
    ("ferraro", 5, box, 0, snackStock),
    ("darkChocolate", 1.8, bar, 0, snackStock),
    ("whiteChocolate", 1.8, bar, 0, snackStock))

  val Grain: CategoryFields = CategoryFields(50, 0.6)
  val grainStock: stock = 4
  val grainss: namePriceUnit = List(
    ("cereal", 4, kg, 0, grainStock),
    ("oatmeal", 4, kg, 0, grainStock),
    ("rice", 2, kg, 0, grainStock),
    ("noodles", 3, kg, 0, grainStock),
    ("spaghetti", 1.5, kg, 0, grainStock),
    ("pasta", 1.5, kg, 0, grainStock))

  // Treat egg as vegetable as it has expiration date is later
  val Dairy: CategoryFields = CategoryFields(7, 0.8)
  val dairyStock: stock = 3
  val dairyss: namePriceUnit = List(
    ("Milk", 2, kg, 0, dairyStock),
    ("Yogurt", 1, cup, 0, dairyStock),
    ("Cheese", 5, piece, 0, dairyStock),
    ("Cream", 1, cup, 0, dairyStock),
    ("egg", 3, carton, 0, dairyStock))

  private def addToSummary(name: categoryName, newCategory: CategoryFields, newCategoryNamePrice: namePriceUnit):Unit = {
    summary.append((name, newCategory, newCategoryNamePrice))
    totalCategories += 1
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

  def getCnt: Int = { totalCategories }

  def getSummary: List[(categoryName, CategoryFields, namePriceUnit)] = { summary.toList }

  def getCategoryNames: List[categoryName] = { summary.toList.map(item => item._1.capitalize) }

  def getArticleNames(categoryName: categoryName): List[articleName] = {
    summary.find(x => x._1==categoryName.capitalize).get
      ._3
      .map(article => article._1.capitalize)
  }

  def getArticleNames: List[articleName] = {
    getCategoryNames.flatMap(
      categoryName => getArticleNames(categoryName)
    )
  }

  def getArticlePrices(categoryName: categoryName): List[price] = {
    summary.find(x => x._1==categoryName.capitalize).get
      ._3
      .map(article => article._2)
  }

  def getArticlePrices: List[price] = {
    getCategoryNames.flatMap(
      categoryName => getArticlePrices(categoryName)
    )
  }

  def getArticleStocks(categoryName: categoryName): List[stock] = {
    summary.find(x => x._1==categoryName.capitalize).get
      ._3
      .map(article => article._5)
  }

  def getArticleStocks: List[stock] = {
    getCategoryNames.flatMap(
      categoryName => getArticleStocks(categoryName)
    )
  }

  val articleUnitMap: Map[articleName, priceUnit] = (getArticleNames zip getArticleUnits).toMap
  var articleCategoryMap: mutable.Map[articleName, categoryName] = mutable.Map()

  getCategoryNames.foreach(
    category => getArticleNames(category).foreach(article => articleCategoryMap+=(article -> category))
  )

  def getArticleUnits(categoryName: categoryName): List[priceUnit] = {
    summary.find(x => x._1==categoryName.capitalize).get
      ._3
      .map(article => article._3)
  }

  def getArticleUnits: List[priceUnit] = {
    getCategoryNames.flatMap(
      categoryName => getArticleUnits(categoryName)
    )
  }

  def getArticleUnit(article: articleName): priceUnit = {
//    println("article unit map is " + articleUnitMap)
    assert(articleUnitMap contains article)
    articleUnitMap.get(article).get
  }

  def getArticleCategory(article: articleName): categoryName = {
//    println("article category map is " + articleCategoryMap)
    assert(articleCategoryMap contains article)
    articleCategoryMap.get(article).get
  }
}
