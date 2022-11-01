package example
package piccolo

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import scala.collection.mutable.{Map => MutMap}

// The locality policy dictates how the kernel and table partitions are colocated
@lift
class PageRankKernel(var current: Table, var next: Table,  val partitionedGraph: GraphTable) extends Actor {

    var graph: Map[Int, List[Int]] = null
    var outlinks: List[Int] = null
    var rank: Double = 0
    var update: Double = 0
    val propagationFactor: Double = 0.85

    def main(): Unit = {
        graph = partitionedGraph.getIterator()
        
        while (true) {
            graph.foreach(i => {
                outlinks = i._2
                rank = current.get(i._1).get
                update = propagationFactor * rank / outlinks.size
                outlinks.foreach(j => {
                    next.update(j, update)
                })
                waitRounds(1)
            })
            // swap next and current
            graph.foreach(i => {
                outlinks = i._2
                rank = next.get(i._1).get
                update = propagationFactor * rank / outlinks.size
                outlinks.foreach(j => {
                    current.update(j, update)
                })
                waitRounds(1)
            })
        }
    }
}