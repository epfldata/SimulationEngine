package lib
package test

import Grid._

class Grid2DSnapshotScalaSpec extends org.scalatest.FlatSpec {

  "Serialize a 2d grid snapshot" should "return a list of ints" in {
    val c = new Grid2DSnapshot(List(1, 0, 1, 0), 2, 2, Map(1 -> "black"))
    println(c.serialize())
  }
}