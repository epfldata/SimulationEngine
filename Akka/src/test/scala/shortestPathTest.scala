package simulation.akka
package test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

class shortestPath extends FlatSpec {
    import meta.deep.IR.Predef._

    val totalVertices: Int = 50
    val totalRounds: Int = 50

    f"The single source shortest path algorithm over a linked list with ${totalVertices} vertices, concurrent workers" should f"update the distance of all vertices in ${totalRounds} rounds" in {
        val agents = generated.core.test.shortestPath.InitData()
        API.OptimizationConfig.concurrentWorker()
        val snapshot1 = API.Simulate(agents, totalRounds)
        // API.Simulate.log.timeseries.foreach(i => println(i._1, i._2.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist)))
        assert(snapshot1.sims.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist).toSet == Range(0, totalVertices).toSet)
    }

    f"The single source shortest path algorithm over a linked list with ${totalVertices} vertices, sequential workers" should f"update the distance of all vertices in ${totalVertices} rounds" in {
        val agents = generated.core.test.shortestPath.InitData()
        API.OptimizationConfig.mergedWorker()
        val snapshot1 = API.Simulate(agents, totalVertices)
        assert(snapshot1.sims.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist).toSet == Range(0, totalVertices).toSet)
    }
}