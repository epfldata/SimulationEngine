package generated.example.test

import meta.API._
import meta.runtime.{Actor}

class shortestPathTest extends org.scalatest.FlatSpec {
    "Compiled shortest path example with 10 nodes" should "run" in {
        val agents = generated.example.graphAlgorithm.shortestPath.InitData(100, 0.1)
        // println(agents.map(x => x.asInstanceOf[generated.example.graphAlgorithm.shortestPath.Vertex].neighbors.size))

        type MapperOut = (Long, Int)
        type ReducerOut = List[Int]

        // record the shortest distance from each vertex to the single source
        val mapper: Actor => MapperOut = (agent) => (agent.id, agent.asInstanceOf[generated.example.graphAlgorithm.shortestPath.Vertex].dist)

        val reducer: List[MapperOut] => ReducerOut = (x) => x.sortWith((s, t) => s._1 < t._1).map(a => a._2)

        val c = new SimulationConfig(agents, 5)
        // val containerConfig = c.staticPartition(10)(BoundedLatency)
        val results = StartSimulation.runAndReduce[AkkaMessagingLayer.type, MapperOut, ReducerOut](c)(mapper, reducer)

        // val results = StartSimulation[AkkaMessagingLayer.type](c)
        results.foreach(println)
    }
}

class pageRankTest extends org.scalatest.FlatSpec {
    "Compiled page rank example with 100 nodes" should "run" in {
        val agents = generated.example.graphAlgorithm.pageRank.InitData(100, 0.1)

        type MapperOut = (Long, Double)
        type ReducerOut = List[Double]

        // record the shortest distance from each vertex to the single source
        val mapper: Actor => MapperOut = (agent) => (agent.id, agent.asInstanceOf[generated.example.graphAlgorithm.pageRank.Vertex].rank)

        val reducer: List[MapperOut] => ReducerOut = (x) => x.sortWith((s, t) => s._1 < t._1).map(a => a._2)

        val c = new SimulationConfig(agents, 30)
        // val containerConfig = c.staticPartition(10)(BoundedLatency)
        val results = StartSimulation.runAndReduce[AkkaMessagingLayer.type, MapperOut, ReducerOut](c)(mapper, reducer)

        // val results = StartSimulation[AkkaMessagingLayer.type](c)
        results.foreach(println)
    }
}