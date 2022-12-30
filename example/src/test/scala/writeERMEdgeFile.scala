package example

import java.io._
import scala.util.Random

object writeERMEdgeFile {
  def main(args: Array[String]): Unit = {
   val p: Double = args(0).toDouble
   val vertices: Int = args(1).toInt

   val pw = new PrintWriter(new FileOutputStream(new File(f"ERM_${p}_${vertices}.txt"),false))
   
   val agentIds = Range(0, vertices)

   // Initialize it during the simulation
   val vertexValue = List(0, 0, 0, 0, 0)

   // [vertex id, vertex value, [[dest id, edge value], [dest id, edge value], ...]]
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