package generated.example.test

import meta.API._
import meta.runtime.{Actor}
import java.io._

class shortestPathTest extends org.scalatest.FlatSpec {
    val totalVertices = 100
    val agents = generated.example.graphAlgorithm.shortestPath.InitData(totalVertices, 0.1)
    val total_diff = (x: List[Int], y: List[Int]) => (0 until totalVertices).filter {
        i => x(i) != y(i)
    }.length 

    type MapperOut = (Long, Int)
    type ReducerOut = List[Int]

    // record the shortest distance from each vertex to the single source
    val mapper: Actor => MapperOut = (agent) => (agent.id, agent.asInstanceOf[generated.example.graphAlgorithm.shortestPath.Vertex].dist)

    val reducer: List[MapperOut] => ReducerOut = (x) => x.sortWith((s, t) => s._1 < t._1).map(a => a._2)

    val totalRounds = 10

    "Reset vertices in shortest path" should "obtain the same results" in {
        val c = new SimulationConfig(agents, totalRounds)

        val results: List[List[Int]] = StartSimulation.runAndReduce[BaseMessagingLayer.type, MapperOut, ReducerOut](c)(mapper, reducer)

        val resultsTail = results.tail
        // val pw = new PrintWriter(new FileOutputStream(new File("shortestPath.csv"),false))
        // results.foreach(x => pw.write(x))
        
        val measured_diffs = (0 until totalRounds-1).map(i => {
            total_diff(results(i), resultsTail(i))
        })

        agents.foreach(a => a.SimReset())
        val c2 = new SimulationConfig(agents, totalRounds)
        
        val results2: List[List[Int]] = StartSimulation.runAndReduce[BaseMessagingLayer.type, MapperOut, ReducerOut](c2)(mapper, reducer)

        val resultsTail2 = results2.tail

        val measured_diffs2 = (0 until totalRounds-1).map(i => {
            total_diff(results2(i), resultsTail2(i))
        })

        // println(measured_diffs)
        // println(measured_diffs2)
        assert(measured_diffs == measured_diffs2)
    }
}

class pageRankTest extends org.scalatest.FlatSpec {

    val agents = generated.example.graphAlgorithm.pageRank.InitData(100, 0.1)
    type MapperOut = (Long, Double)
    type ReducerOut = List[Double]

    // record the shortest distance from each vertex to the single source
    val mapper: Actor => MapperOut = (agent) => (agent.id, agent.asInstanceOf[generated.example.graphAlgorithm.pageRank.Vertex].rank)

    val reducer: List[MapperOut] => ReducerOut = (x) => x.sortWith((s, t) => s._1 < t._1).map(a => a._2)

    val totalRounds: Int = 30

    "Reset vertices in page rank example with 100 nodes" should "obtain the same result" in {
        val c = new SimulationConfig(agents, totalRounds)
        // val containerConfig = c.staticPartition(10)(BoundedLatency)
        val results = StartSimulation.runAndReduce[BaseMessagingLayer.type, MapperOut, ReducerOut](c)(mapper, reducer)
        agents.foreach(_.SimReset())
        val c2 = new SimulationConfig(agents, totalRounds)
        val results2 = StartSimulation.runAndReduce[BaseMessagingLayer.type, MapperOut, ReducerOut](c2)(mapper, reducer)
        assert(results == results2)
    }
}