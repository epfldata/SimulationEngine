package generated.example.test

import meta.API._

import scala.collection.mutable.{Map, StringBuilder}

class gameOfLifeLatencyTests extends org.scalatest.FlatSpec {

    val example: String = "Game of life"
    val logger: StringBuilder = new StringBuilder()
    val width: Int = 100
    val height: Int = 100
    val totalTurns: Int = 100
    val containers: Int = 10

    val boundLatency: List[Int] = List(1, 10, 5)

    for (latency <- boundLatency) {
        f"${example} example with ${width * height} agents and ${containers} containers with latency bound ${latency}" should "run" in {
            val agents = generated.example.gameOfLife.InitData(width, height)

            val c = new SimulationConfig(agents, totalTurns, true, latency)
            val containerConfig = c.staticPartition(containers)(BoundedLatency)

            val run1 = StartSimulation.bench[AkkaMessagingLayer.type](containerConfig)(logger)
        }
    }

    f"${example} example" should "run successfully" in {
        println(logger)
    }
}

