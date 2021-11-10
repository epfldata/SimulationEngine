package generated.example.test

import meta.API._
import java.io._

import scala.collection.mutable.{Map, StringBuilder}
import meta.runtime.Container

class latencyBoundTests extends org.scalatest.FlatSpec {

    var crossTest: Map[Int, Long] = Map()
    val logger: StringBuilder = new StringBuilder()
    val width: Int = 100
    val height: Int = 100
    val totalTurns: Int = 100
    val containers: Int = 10

    val boundLatency1: Int = 1
    val boundLatency2: Int = 10
    val boundLatency3: Int = 5

    f"Game of life example with ${width * height} agents and ${containers} containers with latency bound ${boundLatency1}" should "run" in {
        val agents = generated.example.gameOfLife.InitData(width, height)

        val c = new SimulationConfig(agents, totalTurns, true, boundLatency1)
        val containerConfig = c.staticPartition(containers)(BoundedLatency)

        val run1 = StartSimulation.bench[AkkaMessagingLayer.type](containerConfig)(logger)
        crossTest += (1 -> run1)
    }

    f"Game of life example with ${width * height} agents and ${containers} containers with latency bound ${boundLatency2}" should "run" in {
        val agents = generated.example.gameOfLife.InitData(width, height)

        val c = new SimulationConfig(agents, totalTurns, true, boundLatency2)
        val containerConfig = c.staticPartition(containers)(BoundedLatency)

        val run2 = StartSimulation.bench[AkkaMessagingLayer.type](containerConfig)(logger)

        crossTest += (2 -> run2)
    }

    f"Game of life example with ${width * height} agents and ${containers} containers with latency bound ${boundLatency3}" should "run" in {
        val agents = generated.example.gameOfLife.InitData(width, height)

        val c = new SimulationConfig(agents, totalTurns, true, boundLatency3)
        val containerConfig = c.staticPartition(containers)(BoundedLatency)

        val run2 = StartSimulation.bench[AkkaMessagingLayer.type](containerConfig)(logger)

        crossTest += (3 -> run2)
    }

    // The bound latency is a lower bound. If agents are all waiting before that, then partitions merge.
    "Lower latency bound" should "increase the performannce" in {
        println(logger)
        assert(crossTest(1) > crossTest(2))
        assert(crossTest(1) > crossTest(3))
    }
}

