package example

import java.io._
import scala.util.Random

object writeSBMEdgeFile {
  def main(args: Array[String]): Unit = {
   val p: Double = args(0).toDouble
   val vertices: Int = args(1).toInt
   val blocks: Int = args(2).toInt

   val pw = new PrintWriter(new FileOutputStream(new File(f"SBM_${p}_${vertices}.txt"),false))
   
   val agentIds = Range(0, vertices)

   // Initialize it during the simulation
   val vertexValue = List(0, 0, 0, 0, 0)

    val verticesPerBlock: Int = (vertices / blocks).toInt

   // [vertex id, vertex value, [[dest id, edge value], [dest id, edge value], ...]]
   agentIds.foreach(a => {
      val b = (a / verticesPerBlock).toInt
      val neighbors = agentIds.slice(b*vertices/blocks, (b+1)*vertices/blocks).filter(i => {
         (i!=a) && (p > Random.nextDouble())
      })
      pw.write(f"[$a,[${vertexValue.mkString(",")}],[${neighbors.map(i => f"[$i,0]").mkString(",")}]]\n")
      pw.flush()
   })
 
   pw.close()
 }  
}