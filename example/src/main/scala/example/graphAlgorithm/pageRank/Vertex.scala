package example
package graphAlgorithm.pageRank

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
// Single-source shortest path
@lift
class Vertex(val isSource: Boolean) extends Actor {
    var sum: Double = 0
    var numVertices: Int = 0
    var rank: Double = 0

    var neighbors: List[Vertex] = null
    private var futures: List[Future[Boolean]] = null

    def updateValue(propose: Double): Boolean = {        
        sum = propose + sum
        true
    }

    private def pageRank(): Double = {
        if (numVertices == 0) {
            0
        } else {
            0.15 / numVertices + 0.85 * sum
        }
    }

    def main(): Unit = {
        numVertices = neighbors.size
        while (true) {
            rank = pageRank()
            futures = neighbors.map(x => {
                asyncMessage(() => x.updateValue(rank))
            })
            while (futures.exists(x => !x.isCompleted)){
                waitAndReply(1)
            }
            sum = 0
            // println("The page rank of node " + id + " is " + rank)
        }
    }
}