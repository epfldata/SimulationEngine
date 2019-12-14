package meta.example.supermarket

object utils {
  // Take a case class definition and convert it to List[]
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
}
