package meta.test.resetSim

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

@lift
class Vertex() extends Actor {

    var counter: Int = 0

    private var neighbor: Vertex = null
    
    def API(): Unit = {
        counter = counter + 1
    }

    def main(): Unit = {
        while (true){
            connectedAgents.foreach(x => {
                neighbor = x.asInstanceOf[Vertex]
                asyncMessage(() => neighbor.API())
            })        
            waitAndReply(1)
        }
    }
}

class Test extends FlatSpec {
    import meta.deep.IR.Predef._

    "Vertex with connections" should "compile" in {
        val liftMyClass: ClassWithObject[Vertex] = Vertex.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val v1 = new Vertex()
                val v2 = new Vertex()
                val v3 = new Vertex()
                val v4 = new Vertex()

                v1.connectedAgents = List(v2, v3, v4)
                v2.connectedAgents = List(v1, v3, v4)
                v3.connectedAgents = List(v1, v2, v4)
                v4.connectedAgents = List(v1, v2, v3)

                List(v1, v2, v3, v4)
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some(this.getClass().getPackage().getName()),
            destFolder = "core/src/test/scala/generated/resetSim/")
    }

    "SimReset" should "update the agent in-place" in {
        val agents = generated.meta.test.resetSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[BaseMessagingLayer.type](c)
        val beforeReset = agents(2).asInstanceOf[generated.meta.test.resetSim.Vertex]
        // println(beforeReset.counter)
        assert(beforeReset.counter == 12)
        beforeReset.SimReset()
        assert(beforeReset.counter == 0)
        
        // Run another simulation from cloner won't change the state of clonee
        agents.foreach(x => x.SimReset())
        val r3 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(agents, 5))
        
        assert(agents(1).asInstanceOf[generated.meta.test.resetSim.Vertex].counter == 12)
    }


    "SimReset" should "update the agent in-place in Akka" in {
        val agents = generated.meta.test.resetSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[AkkaMessagingLayer.type](c)
        val beforeReset = agents(2).asInstanceOf[generated.meta.test.resetSim.Vertex]
        // println(beforeReset.counter)
        assert(beforeReset.counter == 12)
        beforeReset.SimReset()
        assert(beforeReset.counter == 0)
        
        // Run another simulation from cloner won't change the state of clonee
        agents.foreach(x => x.SimReset())
        val r3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(agents, 5))
        assert(agents(1).asInstanceOf[generated.meta.test.resetSim.Vertex].counter == 12)
    }
}
