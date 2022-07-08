package generated.core

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

class CloneTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "Clone the mutable agent at round 5 and resume the cloner" should "have different states for clonee and cloner for base simulation" in {
        val agents = generated.core.test.simClone.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[BaseMessagingLayer.type](c)
        val clonee = r.sims.head.asInstanceOf[generated.core.test.simClone.MutableSim]
        val cloner = clonee.SimClone(Set("counter"))
        // The init state of cloner is the final state of clonee
        assert(cloner.asInstanceOf[generated.core.test.simClone.MutableSim].counter == 5)
        // Run another simulation from cloner won't change the state of clonee
        val r3 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(List(cloner), 5))
        assert(cloner.asInstanceOf[generated.core.test.simClone.MutableSim].counter == 10)
        assert(clonee.counter == 5)
    }

    "Clone the mutable agent at round 5 and resume the cloner" should "have different states for clonee and cloner for Akka simulation" in {
        val agents = generated.core.test.simClone.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[AkkaMessagingLayer.type](c)
        val clonee = r.sims.head.asInstanceOf[generated.core.test.simClone.MutableSim]
        val cloner = clonee.SimClone(Set("counter"))
        // The init state of cloner is the final state of clonee
        assert(cloner.asInstanceOf[generated.core.test.simClone.MutableSim].counter == 5)
        // Run another simulation from cloner won't change the state of clonee
        StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(List(cloner), 5))
        assert(cloner.asInstanceOf[generated.core.test.simClone.MutableSim].counter == 10)
        assert(clonee.counter == 5)
    }
}