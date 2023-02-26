package example

import java.io._
import scala.util.Random

object writeERMEdgeFileGraphx {
  def main(args: Array[String]): Unit = {
   val p: Double = args(0).toDouble
   val vertices: Int = args(1).toInt

   val pw = new PrintWriter(new FileOutputStream(new File(f"ERM_${p}_${vertices}_graphx.txt"),false))
   
   val clockId = 0
   val agentIds = Range(1, vertices+1)

   pw.write(f"$clockId $clockId\n")
   pw.flush()

   agentIds.foreach(a => {
      pw.write(f"$clockId $a\n")
      pw.flush()
      
      val neighbors = agentIds.filter(i => {
         (i!=a) && (p > Random.nextDouble())
      })
      neighbors.foreach(n => {
         pw.write(f"$a $n\n")
         pw.flush()
      })
   })    
   pw.close()
 }  
}

object writeERMEdgeFileWithClock {
   def main(args: Array[String]): Unit = {
      val p: Double = args(0).toDouble
      val vertices: Int = args(1).toInt

      val pw = new PrintWriter(new FileOutputStream(new File(f"ERM_${p}_${vertices}.txt"),false))
      
      val clockId = 0
      val agentIds = Range(1, vertices+1)

      // Initialize it during the simulation
      val vertexValue = List(0, 0, 0, 0, 0)

      // [vertex id, vertex value, [[dest id, edge value], [dest id, edge value], ...]]
      // generate the clock vertex, connected to every vertex, including itself
      pw.write(f"[0,[${vertexValue.mkString(",")}],[${(clockId :: agentIds.toList).map(i => f"[$i,0]").mkString(",")}]]\n")

      // generate agent vertex
      agentIds.foreach(a => {
         val neighbors = agentIds.filter(i => {
            (i!=a) && (p > Random.nextDouble())
         })
         pw.write(f"[$a,[${vertexValue.mkString(",")}],[${neighbors.map(i => f"[$i,0]").mkString(",")}]]\n")
         pw.flush()
      })    
      pw.close()
   }
}