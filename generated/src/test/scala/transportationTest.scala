package generated.example.test

import meta.API._
import meta.runtime.{Actor}
import java.io._

class transportationTest extends org.scalatest.FlatSpec {
    
    val agents = generated.example.transportation.InitData(10, 900, 100)

    // val total_diff = (x: List[Int], y: List[Int]) => (0 until totalVertices).filter {
    //     i => x(i) != y(i)
    // }.length 

    // type MapperOut = (Long, Int)
    // type ReducerOut = List[Int]

    // // record the shortest distance from each vertex to the single source
    // val mapper: Actor => MapperOut = (agent) => (agent.id, agent.asInstanceOf[generated.example.graphAlgorithm.shortestPath.Vertex].dist)

    // val reducer: List[MapperOut] => ReducerOut = (x) => x.sortWith((s, t) => s._1 < t._1).map(a => a._2)

    val totalRounds = 600

    "Running transportation" should "see passengers move" in {
        val c = new SimulationConfig(agents, totalRounds)

        StartSimulation[AkkaMessagingLayer.type](c)
            // StartSimulation.runAndReduce[AkkaMessagingLayer.type, MapperOut, ReducerOut](c)(mapper, reducer)

        // val resultsTail = results.tail
        // // val pw = new PrintWriter(new FileOutputStream(new File("shortestPath.csv"),false))
        // // results.foreach(x => pw.write(x))
        
        // val measured_diffs = (0 until totalRounds-1).map(i => {
        //     total_diff(results(i), resultsTail(i))
        // })

        // agents.foreach(a => a.SimReset())
        // val c2 = new SimulationConfig(agents, totalRounds)
        
        // val results2: List[List[Int]] = StartSimulation.runAndReduce[AkkaMessagingLayer.type, MapperOut, ReducerOut](c2)(mapper, reducer)

        // val resultsTail2 = results2.tail

        // val measured_diffs2 = (0 until totalRounds-1).map(i => {
        //     total_diff(results2(i), resultsTail2(i))
        // })

        // // println(measured_diffs)
        // // println(measured_diffs2)
        // assert(measured_diffs == measured_diffs2)
    }
}