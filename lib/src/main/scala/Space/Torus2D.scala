package lib
package Space

trait Torus2D {

  var width: Int
  var height: Int

  // May include the requesting cell as a neighboring cell for radius>1
  def neighborCells(idx: Int, radius: Int): Set[Int] = {
    if (idx < 0 || idx >= width * height) {
      throw new IndexOutOfBoundsException
    }

    // 4 neighbor cells
    val neighbor4: Set[Int] = Set("N", "S", "E", "W").map(d => directionalCell(d, idx))
    // 8 neighbor cells (+ NW, SE, NE, SW)
    val neighbor8: Set[Int] = Set("W", "E", "N", "S").zip(neighbor4).map(pair => directionalCell(pair._1, pair._2)) ++ neighbor4

    val perIt: Set[Int] = neighbor8

    if (radius > 1) {
      perIt ++ perIt.flatMap(c => neighborCells(c, radius - 1))
    } else {
      perIt
    }
  }

  // Return the cell directly at the given direction of the center idx
  private def directionalCell(s: String, idx: Int): Int = {
    val f = (x: Int, isUpper: Boolean, boundary: Int, wrapped: Int) => {
      if (isUpper) {
        if (x > boundary) wrapped else x
      } else {
        if (x < boundary) wrapped else x
      }
    }

    s match {
      case "N" =>
        val foo: Int = idx - width
        f(foo, false, 0, foo + width * height)
      case "S" =>
        val max: Int = width * height - 1
        val foo: Int = idx + width
        f(foo, true, max, foo % width)
      case "E" =>
        val rBound: Int = (1 + idx / width) * width - 1
        val foo: Int = idx + 1
        f(foo, true, rBound, foo - width)
      case "W" =>
        val lBound: Int = (idx / width) * width
        val foo: Int = idx - 1
        f(foo, false, lBound, foo + width)
    }
  }
}
