package generated.example.test

import meta.API._
import meta.runtime.{Actor}
import java.io._

class stockMarketTest extends org.scalatest.FlatSpec {
    val agents = generated.example.stockMarket.InitData(5)
    val totalRounds = 500

    "Running stock market example" should "see prices change and market state change" in {
        val c = new SimulationConfig(agents, totalRounds)
        val result = StartSimulation[AkkaMessagingLayer.type](c)
    }
}