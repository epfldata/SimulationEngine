package generated.example.test

import meta.API._
import meta.runtime.{Actor}
import java.io._

class hierarchicalEpidemic extends org.scalatest.FlatSpec {
    val citySize = 100
    val cityPerCountry = 5
    val totalCountries = 4
    val agents = generated.example.epidemic.hierarchical.InitData(citySize, cityPerCountry, totalCountries, 0.1, true)
    val totalRounds = 100

    "Reset vertices in shortest path" should "obtain the same results" in {
        val c = new SimulationConfig(agents, totalRounds)
        val results: SimulationSnapshot = StartSimulation[AkkaDriverWorker.type](c)
    }
}