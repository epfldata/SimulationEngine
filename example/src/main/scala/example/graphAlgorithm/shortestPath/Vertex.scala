package example
package graphAlgorithm.shortestPath

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

// Single-source shortest path
@lift
class Vertex(val isSource: Boolean) extends Actor {
    var dist: Int = scala.Int.MaxValue
    var propagateUpdate: Boolean = false

    var neighbors: List[(Int, Vertex)] = null
    private var futures: List[Future[Boolean]] = null
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
                futures = neighbors.map(x => {
                    broadcastDist = dist + x._1
                    asyncMessage(() => x._2.updateValue(broadcastDist))
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