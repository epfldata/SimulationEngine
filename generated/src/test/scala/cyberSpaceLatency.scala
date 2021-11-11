package generated.example.test

import meta.API._

import scala.collection.mutable.{Map, StringBuilder}

class cyberSpaceLatencyTests extends org.scalatest.FlatSpec {

    val example: String ="Cyber space"

    val logger: StringBuilder = new StringBuilder()
    val population: Int = 100
    val totalServers: Int = 10

    val totalTurns: Int = 10

    val containers: Int = totalServers

    val boundLatency: List[Int] = List(1, 10, 50)


    for (latency <- boundLatency) {
        f"${example} example with ${population} agents and ${containers} containers with latency bound ${latency}" should "run" in {
            val agents = generated.example.cyberSpace.InitData(population, totalServers, latency)

            val c = new SimulationConfig(agents, totalTurns, true, latency)
            logger ++= c.toString()
            val containerConfig = c.staticPartition(containers)(BoundedLatency)
            val run1 = StartSimulation.bench[AkkaMessagingLayer.type](containerConfig)(logger)
        }
    }

    f"${example} example" should "run successfully" in {
        println(logger)
    }
}

