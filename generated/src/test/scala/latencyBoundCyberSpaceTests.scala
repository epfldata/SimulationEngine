package generated.example.test

import meta.API._
import java.io._

import scala.collection.mutable.{Map, StringBuilder}
import meta.runtime.Container

class latencyBoundCyberSpaceTests extends org.scalatest.FlatSpec {

    var crossTest: Map[Int, Long] = Map()
    val logger: StringBuilder = new StringBuilder()
    val population: Int = 1000
    val totalServers: Int = 10
    val syncPeriod: Int = 10

    val totalTurns: Int = 100

    val containers: Int = totalServers

    val boundLatency1: Int = 1
    val boundLatency2: Int = 10
    val boundLatency3: Int = 50

    f"Cyber space example with ${population} agents and ${containers} containers with latency bound ${boundLatency1}" should "run" in {
        val agents = generated.example.cyberSpace.InitData(population, totalServers, syncPeriod)

        val c = new SimulationConfig(agents, totalTurns, true, boundLatency1)
        val containerConfig = c.staticPartition(containers)(BoundedLatency)

        val run1 = StartSimulation.bench[AkkaMessagingLayer.type](containerConfig)(logger)
        crossTest += (1 -> run1)
    }

    f"Cyber space example with ${population} agents and ${containers} containers with latency bound ${boundLatency2}" should "run" in {
        val agents = generated.example.cyberSpace.InitData(population, totalServers, syncPeriod)

        val c = new SimulationConfig(agents, totalTurns, true, boundLatency2)
        val containerConfig = c.staticPartition(containers)(BoundedLatency)

        val run2 = StartSimulation.bench[AkkaMessagingLayer.type](containerConfig)(logger)

        crossTest += (2 -> run2)
    }

    f"Cyber space example with ${population} agents and ${containers} containers with latency bound ${boundLatency3}" should "run" in {
        val agents = generated.example.cyberSpace.InitData(population, totalServers, syncPeriod)

        val c = new SimulationConfig(agents, totalTurns, true, boundLatency3)
        val containerConfig = c.staticPartition(containers)(BoundedLatency)

        val run2 = StartSimulation.bench[AkkaMessagingLayer.type](containerConfig)(logger)

        crossTest += (3 -> run2)
    }

    // The bound latency is a lower bound. If agents are all waiting before that, then partitions merge.
    "Cyber space example with lower latency bound" should "increase the performance" in {
        println(logger)
        assert(crossTest(1) > crossTest(2))
        assert(crossTest(1) > crossTest(3))
    }
    /** Sample result for 10000. Time per iteration can increase, due to memory contention
      * Agents:10; Turns:100; Bound:1 TotalTime:70340; TimePerIteration:703.4
      * Agents:10; Turns:100; Bound:10 TotalTime:11718; TimePerIteration:117.18
      * Agents:10; Turns:100; Bound:50 TotalTime:39131; TimePerIteration:391.31
      */
}

