package meta.example.segregation

import org.scalatest.{FlatSpec, Matchers}

class torus2DTest extends FlatSpec with Matchers {

  case class torusTest(var width: Int, var height: Int) extends Torus2D

  val w1: Int = 3
  val h1: Int = 3

  an [IndexOutOfBoundsException] should be thrownBy torusTest(w1, h1).neighborCells(w1 * h1, 1)

  "neighborCells" should "return the neighbors of the given radius" in {
    // neighbor 4
//    torusTest(w1, h1).neighborCells(0, 1) shouldBe Set(1, 2, 3, 6)
//    torusTest(w1, h1).neighborCells(4, 1) shouldBe Set(1, 3, 5, 7)
//    torusTest(5, 3).neighborCells(7, 2) shouldBe Set(2, 6, 7, 8, 12, 1, 5, 11, 13, 3, 9)
      torusTest(w1, h1).neighborCells(0, 1) shouldBe Set(1, 2, 3, 4, 5, 6, 7, 8)
      torusTest(w1, h1).neighborCells(4, 1) shouldBe Set(0, 1, 2, 3, 5, 6, 7, 8)
//      torusTest(5, 3).neighborCells(7, 2) shouldBe Set(2, 6, 7, 8, 12, 1, 5, 11, 13, 3, 9)
  }
}