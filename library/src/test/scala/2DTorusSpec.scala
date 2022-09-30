package lib
package test

class Torus2DSpec extends FlatSpec with Matchers {

 val w1: Int = 3
 val h1: Int = 3

 an [IndexOutOfBoundsException] should be thrownBy Grid.Torus2D.getNeighborCells(w1, h1)(w1 * h1, 1)

 "neighborCells" should "return the neighbors of the given radius" in {

    Grid.Torus2D.getNeighborCells(w1, h1)(0, 1).toSet shouldBe Set(1, 2, 3, 4, 5, 6, 7, 8)
    Grid.Torus2D.getNeighborCells(w1, h1)(4, 1).toSet shouldBe Set(0, 1, 2, 3, 5, 6, 7, 8)
 }
}