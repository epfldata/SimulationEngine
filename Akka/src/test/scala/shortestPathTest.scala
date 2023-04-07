package simulation.akka
package test

import org.scalatest.FlatSpec
import meta.runtime.Actor
import simulation.akka.API._

class shortestPath extends FlatSpec {
    val totalVertices: Int = 50
    val totalRounds: Int = 50
    
    case class Distance(dist: Int) extends Serializable
    case object ShortestPathTimeseries extends SimulationTimeseries {
        // a sequential worker applies the mapper to each agent
        override def mapper(x: Serializable): Serializable = {
            Distance(x.asInstanceOf[generated.core.test.shortestPath.Vertex].dist)
        }
        // the driver sends an Iterable[Serializable] to the log controller. Log controller collects Iterable[Iterable[Serializable]]
        // and applies the reducer method to reduce the intermediate data 
        override def reducer(x: Iterable[Iterable[Serializable]]): Iterable[Serializable] = {
            x.flatten
        }
    }

    f"The single source shortest path algorithm over a linked list with ${totalVertices} vertices, sequential workers" should f"update the distance of all vertices in ${totalVertices} rounds" in {
        val agents = generated.core.test.shortestPath.InitData()
        API.OptimizationConfig.logControllerEnabled = true
        API.OptimizationConfig.timeseriesSchema = ShortestPathTimeseries
        val snapshot1 = API.Simulate(agents, totalRounds)
        // assert(snapshot1.sims.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist).toSet == Range(0, totalVertices).toSet)
        API.Simulate.timeseries.foreach(t => { println(t) })
    }
}