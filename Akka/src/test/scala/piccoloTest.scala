package simulation.akka
package test

import simulation.akka.API._
import org.scalatest.FlatSpec

class piccolo extends FlatSpec {
    val totalRounds: Int = 100

    f"The page rank algorithm with vertices, sequential workers" should f"complete" in {
        val agents = generated.example.piccolo.InitData()
        API.OptimizationConfig.logControllerEnabled = true
        API.OptimizationConfig.timeseriesSchema = FullTimeseries
        val snapshot1 = API.Simulate(agents, totalRounds)
        API.Simulate.timeseries.foreach(t => { println(t) })
    }
}