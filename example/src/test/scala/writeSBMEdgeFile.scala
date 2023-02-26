package example

import java.io._
import scala.util.Random

object writeSBMEdgeFileGraphx {
  def main(args: Array[String]): Unit = {
   val p: Double = args(0).toDouble
   val vertices: Int = args(1).toInt
   val blocks: Int = args(2).toInt

   val pw = new PrintWriter(new FileOutputStream(new File(f"SBM_${p}_${vertices}_graphx.txt"),false))
   
   val clockId: Int = 0
   val agentIds = Range(1, vertices+1)

   // Initialize it during the simulation

   val verticesPerBlock: Int = (vertices / blocks).toInt
   
   pw.write(f"$clockId $clockId\n")
   agentIds.foreach(i => {
      pw.write(f"$clockId $i\n")
      pw.flush()
   })

   // [vertex id, vertex value, [[dest id, edge value], [dest id, edge value], ...]]
   Range(0, blocks).foreach(i => {
      agentIds.slice(i*vertices/blocks, (i+1)*vertices/blocks).foreach(j => {
         val neighbors = Range(i*vertices/blocks, (i+1)*vertices/blocks).filter(k => {(k!= j) &&  Random.nextDouble() < p})
         neighbors.foreach(n => {
            pw.write(f"$j $n\n")
            pw.flush()
         })
      })
   }) 
   pw.close()
 }  
}

object writeSBMEdgeFileWithClock {
   def main(args: Array[String]): Unit = {
      val p: Double = args(0).toDouble
      val vertices: Int = args(1).toInt
      val blocks: Int = args(2).toInt

      val pw = new PrintWriter(new FileOutputStream(new File(f"SBM_${p}_${vertices}.txt"),false))
      
      val clockId: Int = 0
      val agentIds = Range(1, vertices+1)

      // Initialize it during the simulation
      val vertexValue = List(0, 0, 0, 0, 0)

      val verticesPerBlock: Int = (vertices / blocks).toInt

      // [vertex id, vertex value, [[dest id, edge value], [dest id, edge value], ...]]
      // write the clock vertex
      pw.write(f"[$clockId,[${vertexValue.mkString(",")}],[${(clockId :: agentIds.toList).map(i => f"[$i,0]").mkString(",")}]]\n")
      
      Range(0, blocks).foreach(i => {
         agentIds.slice(i*vertices/blocks, (i+1)*vertices/blocks).foreach(j => {
            val neighbors = Range(i*vertices/blocks, (i+1)*vertices/blocks).filter(k => {(k!= j) &&  Random.nextDouble() < p})
            pw.write(f"[$j,[${vertexValue.mkString(",")}],[${neighbors.map(i => f"[$i,0]").mkString(",")}]]\n")
            pw.flush()
         })
      }) 
   
      pw.close()
   }
}