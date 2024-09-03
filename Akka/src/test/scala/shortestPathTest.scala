package simulation.akka
package test

import org.scalatest.FlatSpec
import simulation.akka.API._
import meta.API.DeforestationStrategy
import spire.std.iterable

class shortestPath extends FlatSpec {
    val totalVertices: Int = 50
    val totalRounds: Int = 50
    
    f"The single source shortest path algorithm over a linked list with ${totalVertices} vertices, sequential workers" should f"update the distance of all vertices in ${totalVertices} rounds" in {
        case class Distance(dist: Int) extends Serializable
        object ShortestPathOptStrategy extends DeforestationStrategy {
            override def mapper(x: Serializable): Serializable = {
                Distance(x.asInstanceOf[generated.core.test.shortestPath.Vertex].dist)
            }
        }

        val agents = generated.core.test.shortestPath.InitData()
        val conf = Map("role" -> "Standalone", 
            "port" -> 25300, 
            "name" -> "ShortestPath", 
            "data" -> "timeseries")
        val ts = API.Simulate(agents, totalRounds, conf)(ShortestPathOptStrategy)
        ts.timeseries.foreach(t => { println(t) })
    }
}