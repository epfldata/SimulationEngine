package meta.test.timeseries

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import meta.compile._

@lift
class CounterSim(val n: CounterSim) extends Actor {
    var state: Int = 1
    val immutableSecret: Int = 10

    def inc(): Unit = {
        println(id + " processes inc message!")
        state = state + 1
    }

    def main(): Unit = {
        while (true){
            if (n != null) {
                asyncMessage[Unit](() => n.inc()) 
                println(id + " sends a message to increment the neighbor!")
            }
            handleMessages()
            println(id + " counter value is " + state)
            waitLabel(Turn, 1)
        }
    }
}

class timeseriesTest extends FlatSpec {
    import meta.deep.IR.Predef._

    // "The counter agents" should "compile" in {
    //     val liftMyClass: ClassWithObject[CounterSim] = CounterSim.reflect(IR)
    //     val liftedMain = meta.classLifting.liteLift {
    //         def apply(): List[Actor] = {
    //             val s1 = new CounterSim(null)
    //             val s2 = new CounterSim(s1)
    //             val s3 = new CounterSim(s2)
    //             List(s1, s2, s3)
    //         }
    //     }

    //     compileSims(List(liftMyClass), 
    //         mainInit = Some(liftedMain), 
    //         initPkgName = Some(this.getClass().getPackage().getName()),
    //         destFolder = "core/src/test/scala/generated/timeseries/")
    // }

    "Recording counter agents" should "reflect the state of agents" in {
        val agents = generated.meta.test.timeseries.InitData()
        val c = new SimulationConfig(agents, 5)

        val r = StartSimulation.record[BaseMessagingLayer.type](c)
        val timeseries = r.map(x => x.sims.map(_.asInstanceOf[generated.meta.test.timeseries.CounterSim]))
        val state_timeseries = timeseries.map(_.map(_.state))
        val secrete_timeseries = timeseries.map(_.map(_.immutableSecret))

        // Record each time stamp
        assert(timeseries.length == 5)
        // Initial states
        assert(state_timeseries(0).forall(_ == 1))
        // Final states 
        assert(state_timeseries.last.filter(_ == 5).length == 2)
        // Secrete should remain unchanged
        assert(secrete_timeseries.forall(x => x.forall(_ == 10)))
    }
}