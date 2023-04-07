package simulation.akka
package test

import org.scalatest.FlatSpec
import simulation.akka.API._

import meta.runtime.{Actor, JsonSerializable}
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes, JsonTypeName}

class shortestPath extends FlatSpec {
    val totalVertices: Int = 50
    val totalRounds: Int = 50

    @JsonTypeName("Distance")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    case class Distance(dist: Int) extends JsonSerializable
    
    @JsonTypeName("ShortestPathTimeseries")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    class ShortestPathTimeseries() extends SimulationTimeseries {
        // a sequential worker applies the mapper to each agent
        override def mapper(x: JsonSerializable): JsonSerializable = {
            Distance(x.asInstanceOf[generated.core.test.shortestPath.Vertex].dist)
        }
        // the driver sends an Iterable[JsonSerializable] to the log controller. Log controller collects Iterable[Iterable[JsonSerializable]]
        // and applies the reducer method to reduce the intermediate data 
    }

    f"The single source shortest path algorithm over a linked list with ${totalVertices} vertices, sequential workers" should f"update the distance of all vertices in ${totalVertices} rounds" in {
        val agents = generated.core.test.shortestPath.InitData()
        API.OptimizationConfig.logControllerEnabled = false
        API.OptimizationConfig.timeseriesSchema = new ShortestPathTimeseries
        val snapshot1 = API.Simulate(agents, totalRounds)
        // assert(snapshot1.sims.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist).toSet == Range(0, totalVertices).toSet)
        API.Simulate.timeseries.foreach(t => { println(t) })
    }
}