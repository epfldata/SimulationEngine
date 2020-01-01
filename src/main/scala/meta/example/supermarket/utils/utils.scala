package meta.example.supermarket

object utils {
  // Convert the parameter list of case class to a list. See utilsSpec
  def ccArgToList(cc: Product): List[(String, Any)] = {
    cc.getClass
      .getDeclaredFields
      .map( _.getName )
      .zip( cc.productIterator.to )
      .toList
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
}
