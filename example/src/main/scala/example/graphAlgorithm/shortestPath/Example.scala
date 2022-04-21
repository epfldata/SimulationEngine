package example
package graphAlgorithm.shortestPath


import scala.util.Random
import scala.collection.mutable.ListBuffer
import lib.Graph.Graph

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

            val built: Vertex => Unit = vertex => {
                val nodes = allAgents.filter(x => {
                    x !=vertex && p>Random.nextDouble()
                })
                vertex.connectedAgents = nodes
                vertex.outEdgeWeights = (1 to nodes.length).map(_=>1).toList
            }

            (new Graph).build[Vertex](allAgents, built)
            allAgents
        }
    }
}

object Example extends App {

  val cls1: ClassWithObject[Vertex] = Vertex.reflect(IR)
  val mainClass = MainInit.liftedMain
    
  compileSims(List(cls1), Some(mainClass))
}