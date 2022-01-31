package meta.test.newSim

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import meta.compile._

@lift
class NewSim() extends Actor {

    def main(): Unit = {
        while (true){
            println("This is agent " + id)
            val c = new NewSim()
            println("Create new agent " + c.id)
            waitLabel(Turn, 1)
        }
    }
}

class NewSimTest extends FlatSpec {
    import meta.deep.IR.Predef._

    // "Generating new agents" should "compile" in {
    //     val liftMyClass: ClassWithObject[NewSim] = NewSim.reflect(IR)
    //     val liftedMain = meta.classLifting.liteLift {
    //         def apply(): List[Actor] = {
    //             List(new NewSim())
    //         }
    //     }

    //     compileSims(List(liftMyClass), 
    //         mainInit = Some(liftedMain), 
    //         initPkgName = Some(this.getClass().getPackage().getName()),
    //         destFolder = "core/src/test/scala/generated/newSim/")
    // }

    "Agents population" should "grow exponentially in base" in {
        val agents = generated.meta.test.newSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[BaseMessagingLayer.type](c)
        assert(r.sims.size == 32)
    }

    "Added agents" should "be visible in the next round in base" in {
        val agents = generated.meta.test.newSim.InitData()
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
        val agents = generated.meta.test.newSim.InitData()
        val c = new SimulationConfig(agents, 3)
        val r2 = StartSimulation[BaseMessagingLayer.type](c)
        assert(r2.sims.size == 8)
        val r3 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(r2.sims, 1))
        assert(r3.sims.size == 16)
        val r4 = StartSimulation[BaseMessagingLayer.type](new SimulationConfig(r3.sims, 2))
        assert(r4.sims.size == 64)
    }

    "Agents population" should "grow exponentially in Akka" in {
        val agents = generated.meta.test.newSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[AkkaMessagingLayer.type](c)
        assert(r.sims.size == 32)
    }

    "Added agents" should "be visible in the next round in Akka" in {
        val agents = generated.meta.test.newSim.InitData()
        val c = new SimulationConfig(agents, 1)
        val r2 = StartSimulation[AkkaMessagingLayer.type](c)
        assert(r2.sims.size == 2)
        val r3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r2.sims, 1))
        assert(r3.sims.size == 4)
        val r4 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r3.sims, 1))
        assert(r4.sims.size == 8)
        val r5 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r4.sims, 1))
        assert(r5.sims.size == 16)
    }

    "Agents" should "be composable in Akka" in {
        val agents = generated.meta.test.newSim.InitData()
        val c = new SimulationConfig(agents, 3)
        val r2 = StartSimulation[AkkaMessagingLayer.type](c)
        assert(r2.sims.size == 8)
        val r3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r2.sims, 1))
        assert(r3.sims.size == 16)
        val r4 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r3.sims, 2))
        assert(r4.sims.size == 64)
    }

    "Agents population" should "grow exponentially in Spark" in {
        val agents = generated.meta.test.newSim.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[SparkMessagingLayer.type](c)
        assert(r.sims.size == 32)
    }

    "Added agents" should "be visible in the next round in Spark" in {
        val agents = generated.meta.test.newSim.InitData()
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
        val agents = generated.meta.test.newSim.InitData()
        val c = new SimulationConfig(agents, 3)
        val r2 = StartSimulation[SparkMessagingLayer.type](c)
        assert(r2.sims.size == 8)
        val r3 = StartSimulation[SparkMessagingLayer.type](new SimulationConfig(r2.sims, 1))
        assert(r3.sims.size == 16)
        val r4 = StartSimulation[SparkMessagingLayer.type](new SimulationConfig(r3.sims, 2))
        assert(r4.sims.size == 64)
    }
}