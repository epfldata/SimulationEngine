package simulation.akka
package test

import simulation.akka.API._
import org.scalatest.FlatSpec
import meta.API.{SimulationData, DeforestationStrategy}

class piccolo extends FlatSpec {
    val totalRounds: Int = 100

    f"The page rank algorithm with vertices, sequential workers" should f"complete" in {
        val agents = generated.example.piccolo.InitData()
        val conf = Map("role" -> "Standalone", "port" ->25400, "name" -> "Piccolo", "data" -> "timeseries")
        val ts = API.Simulate(agents, totalRounds, conf)(DeforestationStrategy.NoReduction)
        ts.timeseries.foreach(t => { println(t) })
    }
}