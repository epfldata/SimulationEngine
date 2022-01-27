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

    "Generating new agents" should "compile" in {
        val liftMyClass: ClassWithObject[NewSim] = NewSim.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                List(new NewSim())
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some(this.getClass().getPackage().getName()),
            destFolder = "core/src/test/scala/generated/newSim/")
    }

    "Newly added agents" should "be visible in the next round" in {
        val agents = generated.meta.test.newSim.InitData()
        val c = new SimulationConfig(agents, 1)
        val r2 = StartSimulation[AkkaMessagingLayer.type](c)
        assert(r2.sims.size == 2)
        val r3 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r2.sims, 1))
        assert(r3.sims.size == 4)
        // val r5 = StartSimulation[AkkaMessagingLayer.type](new SimulationConfig(r3.sims, 3))
        // assert(r5.sims.size == 32)
    }
}