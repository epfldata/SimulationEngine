package generated.core

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

class NewSimTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "Agents population" should "grow exponentially in base" in {
        val agents = generated.core.test.newSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[BaseMessagingLayer.type](c)
        assert(r.sims.size == 32)
    }

    "Added agents" should "be visible in the next round in base" in {
        val agents = generated.core.test.newSim.InitData()
        val c = new SimulationConfig(agents, 1)
        val r2 = StartSimulation[BaseMessagingLayer.type](c)
        assert(r2.sims.size == 2)
        val r3 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(r2.sims, 1))
        assert(r3.sims.size == 4)
        val r4 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(r3.sims, 1))
        assert(r4.sims.size == 8)
        val r5 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(r4.sims, 1))
        assert(r5.sims.size == 16)
    }

    "Agents" should "be composable in base" in {
        val agents = generated.core.test.newSim.InitData()
        val c = new SimulationConfig(agents, 3)
        val r2 = StartSimulation[BaseMessagingLayer.type](c)
        assert(r2.sims.size == 8)
        val r3 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(r2.sims, 1))
        assert(r3.sims.size == 16)
        val r4 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(r3.sims, 2))
        assert(r4.sims.size == 64)
    }

    // ctx.spawn is not thread-safe in Akka
    // "Agents population" should "grow exponentially in Akka" in {
    //     val agents = generated.core.test.newSim.InitData()
    //     val c = new SimulationConfig(agents, 5)
    //     val r = StartSimulation[AkkaMessagingLayer.type](c)
    //     assert(r.sims.size == 32)
    // }

    // "Added agents" should "be visible in the next round in Akka" in {
    //     val agents = generated.core.test.newSim.InitData()
    //     val c = new SimulationConfig(agents, 1)
    //     val r2 = StartSimulation[AkkaMessagingLayer.type](c)
    //     assert(r2.sims.size == 2)
    //     val r3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r2.sims, 1))
    //     assert(r3.sims.size == 4)
    //     val r4 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r3.sims, 1))
    //     assert(r4.sims.size == 8)
    //     val r5 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r4.sims, 1))
    //     assert(r5.sims.size == 16)
    // }

    // "Agents" should "be composable in Akka" in {
    //     val agents = generated.core.test.newSim.InitData()
    //     val c = new SimulationConfig(agents, 3)
    //     val r2 = StartSimulation[AkkaMessagingLayer.type](c)
    //     assert(r2.sims.size == 8)
    //     val r3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r2.sims, 1))
    //     assert(r3.sims.size == 16)
    //     val r4 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r3.sims, 2))
    //     assert(r4.sims.size == 64)
    // }

    "Agents population" should "grow exponentially in Spark" in {
        val agents = generated.core.test.newSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[SparkMessagingLayer.type](c)
        assert(r.sims.size == 32)
    }

    "Added agents" should "be visible in the next round in Spark" in {
        val agents = generated.core.test.newSim.InitData()
        val c = new SimulationConfig(agents, 1)
        val r2 = StartSimulation[SparkMessagingLayer.type](c)
        assert(r2.sims.size == 2)
        val r3 = StartSimulation[SparkMessagingLayer.type](new SimulationConfig(r2.sims, 1))
        assert(r3.sims.size == 4)
        val r4 = StartSimulation[SparkMessagingLayer.type](new SimulationConfig(r3.sims, 1))
        assert(r4.sims.size == 8)
        val r5 = StartSimulation[SparkMessagingLayer.type](new SimulationConfig(r4.sims, 1))
        assert(r5.sims.size == 16)
    }

    "Agents" should "be composable in Spark" in {
        val agents = generated.core.test.newSim.InitData()
        val c = new SimulationConfig(agents, 3)
        val r2 = StartSimulation[SparkMessagingLayer.type](c)
        assert(r2.sims.size == 8)
        val r3 = StartSimulation[SparkMessagingLayer.type](new SimulationConfig(r2.sims, 1))
        assert(r3.sims.size == 16)
        val r4 = StartSimulation[SparkMessagingLayer.type](new SimulationConfig(r3.sims, 2))
        assert(r4.sims.size == 64)
    }
}