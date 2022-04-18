package example
package graphAlgorithm.shortestPath


import scala.util.Random
import scala.collection.mutable.ListBuffer

object MainInit {
    val liftedMain = meta.classLifting.liteLift {
        def apply(n: Int, p: Double): List[Actor] = {
            val allAgents: List[Vertex] = 1.to(n).map(i => {
                if (i == n/2) {
                    new Vertex(true)
                } else {
                    new Vertex(false)
                }
            }).toList
            allAgents.foreach(vertex => {
                vertex.neighbors = allAgents.filter(x => {x !=vertex && p>Random.nextDouble()}).map(x => (1, x))
            })
            allAgents
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Vertex] = Vertex.reflect(IR)
  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1), Some(mainClass))
}