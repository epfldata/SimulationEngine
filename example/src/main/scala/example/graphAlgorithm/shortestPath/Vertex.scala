package example
package graphAlgorithm.shortestPath

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

// Single-source shortest path
@lift
class Vertex(val isSource: Boolean, val uniformEdgeWeight: Int) extends Actor {
    var dist: Int = scala.Int.MaxValue
    var propagateUpdate: Boolean = false
    private var futures: List[Future[Boolean]] = null
    private var tmp_neighbor: Vertex = null
    private var broadcastDist: Int = scala.Int.MaxValue

    def updateValue(propose: Int): Boolean = {
        if (propose < dist){
            dist = propose
            propagateUpdate = true
            true
        } else {
            false
        }
    }

    def main(): Unit = {
        if (isSource){
            dist = 0
            propagateUpdate = true
        }

        while (true) {
            if (propagateUpdate){
                futures = connectedAgents.map(x => {
                    broadcastDist = dist + uniformEdgeWeight
                    tmp_neighbor = x.asInstanceOf[Vertex]
                    asyncMessage(() => tmp_neighbor.updateValue(broadcastDist))
                })
                while (futures.exists(x => !x.isCompleted)){
                    waitAndReply(1)
                }
            } else {
                waitAndReply(1)
            }
        }
    }
}