package meta.test.timeseries

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

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

    "The counter agents" should "compile" in {
        val liftMyClass: ClassWithObject[CounterSim] = CounterSim.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val s1 = new CounterSim(null)
                val s2 = new CounterSim(s1)
                val s3 = new CounterSim(s2)
                List(s1, s2, s3)
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some(this.getClass().getPackage().getName()),
            destFolder = "core/src/test/scala/generated/timeseries/")
    }

    "Recording counter agents" should "reflect the state of agents" in {
        val agents = generated.meta.test.timeseries.InitData()
        val c = new SimulationConfig(agents, 5)
        // select only the state of counters
        val eval: List[Actor] => List[Int] = (agents) => agents.map(_.asInstanceOf[generated.meta.test.timeseries.CounterSim].state)

        val timeseries = StartSimulation.runAndEval[BaseMessagingLayer.type, List[Int]](c)(eval)

        println(timeseries)
        // Record each time stamp
        assert(timeseries.length == 5)
        // Initial states
        assert(timeseries(0).forall(_ == 1))
        // Final states 
        assert(timeseries.last.filter(_ == 5).length == 2)
    }
}