package simulation.akka
package test

import simulation.akka.API._
import org.scalatest.FlatSpec
import java.io._
import meta.runtime.Actor

class simulateUntilTest extends FlatSpec {
    val totalRounds: Int = 50
    
    f"The epidemics example" should "stop as soon as the termination condition is met" in {
      val population = 10000
      val graph = cloudcity.lib.Graph.ErdosRenyiGraph(population, 0.01)
      val agents = generated.example.epidemic.InitData(graph)
      val conf = Map("role" -> "Standalone", 
            "port" -> 25200, 
            "name" -> "Epidemics", 
            "data" -> "timeseries")
      Simulate.apply(agents, totalRounds, conf, Some((ts: Iterable[Iterable[Serializable]]) => {
        val x = ts.last.filter(i => i match {
          case y: generated.example.epidemic.Person => y.health == 1
        }).size
        println("Total infected agents: " + x)
        x > population / 2
      }))
    }
}