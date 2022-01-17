package lib.Grid

import scala.util.Random
import scala.collection.mutable.ListBuffer

object Torus2D {
  // 0-based 
  def getNeighborCells(width: Int, height: Int)(x: Int, radius: Int): List[Int] = {
    // radius: optionally, we can have neighbors of radius 2 or more. Right now we define 8 neighbors with radius=1
    
    if (x < 0 || x >= width * height) {
      println(f"Index out of bound! ${x}")
      throw new IndexOutOfBoundsException
    }

    var r = 1

    val neighbors = ListBuffer[Int]()

    val nw = x- radius * width - radius
    val area = width * height

    Range(0, 2*radius+1).foreach(i => {
      Range(0, 2*radius+1).foreach(j => {
        neighbors.append(mod(nw + i + j*width, area))
      })
    })

    // Remove x from the neighbor list
    neighbors--=List(x)
    neighbors.toList
  }

  def mod(a: Int, b: Int): Int = {
		val r = a % b
		if (r < 0) {
			r + b
		} else {
			r
		}
	}
}