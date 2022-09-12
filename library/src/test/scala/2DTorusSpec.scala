package lib
package test

import java.io._

class Torus2DSpec extends FlatSpec with Matchers {

 val w1: Int = 3
 val h1: Int = 3

 an [IndexOutOfBoundsException] should be thrownBy Grid.Torus2D.getNeighborCells(w1, h1)(w1 * h1, 1)

 "neighborCells" should "return the neighbors of the given radius" in {

    Grid.Torus2D.getNeighborCells(w1, h1)(0, 1).toSet shouldBe Set(1, 2, 3, 4, 5, 6, 7, 8)
    Grid.Torus2D.getNeighborCells(w1, h1)(4, 1).toSet shouldBe Set(0, 1, 2, 3, 5, 6, 7, 8)
 }

 "Generate edge file for Giraph" should "print graph txt" in {
   val width: Int = 1000
   val height: Int = 1000
   val radius: Int = 1

   val pw = new PrintWriter(new FileOutputStream(new File(f"2DTorus_${width*height}.txt"),false))
   for (i <- Range(0, width*height)){
      val neighbors = Grid.Torus2D.getNeighborCells(width, height)(i, radius)
      val neighbor_string = neighbors.map(i => f"[$i,0]").mkString(",")
      pw.write(f"[$i,0,[$neighbor_string]]\n")
      pw.flush()
   }
   pw.close()
 }
}