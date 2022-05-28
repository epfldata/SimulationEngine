package generated.example.test

import meta.API._
import meta.runtime.{Actor}
import java.io._

import plotly._, element._, layout._, Plotly._

class stockMarketTest extends org.scalatest.FlatSpec {
    val agents = generated.example.stockMarket.InitData(100)
    val totalRounds = 300

    "Running stock market example" should "see prices change and market state change" in {
        val c = new SimulationConfig(agents, totalRounds)
        val s = StartSimulation[AkkaMessagingLayer.type](c)
        
        val price_timeseries = s.sims.filter(x => x.isInstanceOf[generated.example.stockMarket.Market]).head.asInstanceOf[generated.example.stockMarket.Market].stock.prices

        val xs = (1 to totalRounds)
        Seq(
            Scatter(xs, price_timeseries).withName("Time series")
        ).plot(
            title = "Stock market price"
        )
    }
}