package example

import java.io._
import lib.Grid

object writeTorusEdgeFile {
  def main(args: Array[String]): Unit = {
   val width: Int = args(0).toInt
   val height: Int = args(1).toInt
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

object writeTorusEdgeFileGraphx {
  def main(args: Array[String]): Unit = {
   val width: Int = args(0).toInt
   val height: Int = args(1).toInt
   val radius: Int = 1

   val pw = new PrintWriter(new FileOutputStream(new File(f"2DTorus_${width*height}_graphx.txt"),false))
   for (i <- Range(0, width*height)){
      val neighbors = Grid.Torus2D.getNeighborCells(width, height)(i, radius)
      neighbors.foreach(j => {
         pw.write(f"$i $j\n")
         pw.flush()
      })
   }
   pw.close()
 }  
}

object writeTorusEdgeFileWithClock {
   def main(args: Array[String]): Unit = {
      val width: Int = args(0).toInt
      val height: Int = args(1).toInt
      val radius: Int = 1

      val pw = new PrintWriter(new FileOutputStream(new File(f"2DTorus_${width*height}_clock.txt"),false))
      pw.write(f"[0,0,[${Range(0, width*height+1).map(i => f"[${i},0]").mkString(",")}]]\n")
      for (i <- Range(0, width*height)){
         val neighbors = Grid.Torus2D.getNeighborCells(width, height)(i, radius)
         val neighbor_string = neighbors.map(i => f"[${i+1},0]").mkString(",")
         pw.write(f"[${i+1},0,[$neighbor_string]]\n")
         pw.flush()
      }
      pw.close()
   }
}