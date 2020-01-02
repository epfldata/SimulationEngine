package meta.example.supermarket

import meta.example.supermarket.categories.{getArticleCategory, getArticleUnit}

import scala.util.Random

object utils {
  /**
    * Convert the parameter list of case class to a Vector
    */
  def ccArgToVector(cc: Product): Vector[(String, Any)] = {
    cc.getClass
      .getDeclaredFields
      .map( _.getName )
      .zip( cc.productIterator.to )
      .toVector
  }

  def toInt(b: Boolean): Int = {
    if (b) 1 else 0
  }

  def to2Dec(value: Double): Double = {
    (value * 100).round / 100.toDouble
  }

  def divCeil(a: Int, b: Int): Int = {
    a/b+toInt(a%b!=0)
  }

  /**
    * This function takes a Vector[(ArticleName, Gram)] and converts it to
   * Vector[(ArticleName, AgentCount)]
   */
  def toShoppingList(meal: Vector[(String, Int)]): Vector[(String, Int)] = {
    meal.map(
      pair => (pair._1, divCeil(pair._2, getArticleUnit(pair._1)))
    )
  }

  def randElement(foo: Vector[String]): String = {
    foo(Random.nextInt(foo.size))
  }
}
