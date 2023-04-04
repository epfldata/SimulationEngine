package simulation.base
package test

import API._
import org.scalatest.FlatSpec

class shortestPathTest extends FlatSpec {

    val totalVertices: Int = 50
    f"The single source shortest path algorithm over a linked list with ${totalVertices} vertices" should f"update the distance of all vertices in ${totalVertices} rounds" in {
        val agents = generated.core.test.shortestPath.InitData()
        val snapshot1 = Simulate(agents, totalVertices)
        assert(snapshot1.sims.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist).toSet == Range(0, totalVertices).toSet)
    }
}