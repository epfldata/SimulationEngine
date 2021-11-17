package GUI
package test

import GUI.Graphics._

class Grid2DSnapshotScalaSpec extends org.scalatest.FlatSpec {

  "Serialize a 2d grid snapshot" should "return a list of ints" in {
    val c = Grid2DSnapshotScala(List(1, 0, 1, 0), 2, 2, Map(1 -> "black"))
    println(c.serialize())
  }
}