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
        agents.foreach(a => assert(a.asInstanceOf[generated.core.test.resetSim.Vertex].counter == 12))
        // Reset the Sim except for connectedAgents
        agents.foreach(x => x.SimReset(Set("connectedAgents")))
        agents.foreach(a => assert(a.asInstanceOf[generated.core.test.resetSim.Vertex].counter == 0))

        val r3 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(agents, 5))
        agents.foreach(a => assert(a.asInstanceOf[generated.core.test.resetSim.Vertex].counter == 12))
    }


    "SimReset" should "update the agent in-place in Akka" in {
        val agents = generated.core.test.resetSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[AkkaMessagingLayer.type](c)
        agents.foreach(a => assert(a.asInstanceOf[generated.core.test.resetSim.Vertex].counter == 12))
        // Reset the Sim except for connectedAgents
        agents.foreach(x => x.SimReset(Set("connectedAgents")))
        agents.foreach(a => assert(a.asInstanceOf[generated.core.test.resetSim.Vertex].counter == 0))
        
        // Run another simulation from cloner won't change the state of clonee
        val r3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(agents, 5))
        agents.foreach(a => assert(a.asInstanceOf[generated.core.test.resetSim.Vertex].counter == 12))
    }
}
