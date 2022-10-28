package simulation.spark
package test

import org.scalatest.FlatSpec

class shortestPath extends FlatSpec {
    import meta.deep.IR.Predef._

    val totalVertices: Int = 50
    val totalRounds: Int = 50

    f"The single source shortest path algorithm over a linked list with ${totalVertices} vertices" should f"update the distance of all vertices in ${totalRounds} rounds" in {
        val agents = generated.core.test.shortestPath.InitData()
        val snapshot1 = API.Simulate(agents, totalRounds)
        // API.Simulate.log.timeseries.foreach(i => println(i._1, i._2.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist)))
        assert(snapshot1.sims.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist).toSet == Range(0, totalVertices).toSet)
        assert(snapshot1.sims.map(i => i.time).toSet == Set(totalRounds))
    }
}