package generated.core

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

class ResetSimTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "SimReset" should "update the agent in-place" in {
        val agents = generated.core.test.resetSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[BaseMessagingLayer.type](c)
        val beforeReset = agents(2).asInstanceOf[generated.core.test.resetSim.Vertex]
        // println(beforeReset.counter)
        assert(beforeReset.counter == 12)
        beforeReset.SimReset()
        assert(beforeReset.counter == 0)
        
        // Run another simulation from cloner won't change the state of clonee
        agents.foreach(x => x.SimReset())
        val r3 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(agents, 5))
        
        assert(agents(1).asInstanceOf[generated.core.test.resetSim.Vertex].counter == 12)
    }


    "SimReset" should "update the agent in-place in Akka" in {
        val agents = generated.core.test.resetSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[AkkaMessagingLayer.type](c)
        val beforeReset = agents(2).asInstanceOf[generated.core.test.resetSim.Vertex]
        // println(beforeReset.counter)
        assert(beforeReset.counter == 12)
        beforeReset.SimReset()
        assert(beforeReset.counter == 0)
        
        // Run another simulation from cloner won't change the state of clonee
        agents.foreach(x => x.SimReset())
        val r3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(agents, 5))
        assert(agents(1).asInstanceOf[generated.core.test.resetSim.Vertex].counter == 12)
    }

    "SimReset with preserved variables" should "update the agent in-place in Akka" in {
        val agents = generated.core.test.resetSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[AkkaMessagingLayer.type](c)
        val beforeReset = agents(2).asInstanceOf[generated.core.test.resetSim.Vertex]
        // println(beforeReset.counter)
        assert(beforeReset.counter == 12)
        beforeReset.SimReset(Set("counter"))
        assert(beforeReset.counter == 12)
        
        // Run another simulation from cloner won't change the state of clonee
        agents.foreach(x => x.SimReset(Set("counter")))
        val r3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(agents, 5))
        assert(agents(1).asInstanceOf[generated.core.test.resetSim.Vertex].counter == 24)
    }
}
