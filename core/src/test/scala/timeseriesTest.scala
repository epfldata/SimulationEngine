package meta.test

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

    def inc(): Int = {
        // println(id + " processes inc message!")
        state = state + 1
        0
    }

    def main(): Unit = {
        while (true){
            if (n != null) {
                asyncCall(() => n.inc(), 1) 
                // println(id + " sends a message to increment the neighbor!")
            }
            handleRPC()
            // println(id + " counter value is " + state)
            waitAndReply(1)
        }
    }
}

class timeseriesTest extends FlatSpec {

    "The counter agents" should "compile" in {
        val liftMyClass: ClassWithObject[CounterSim] = CounterSim.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): IndexedSeq[Actor] = {
                val s1 = new CounterSim(null)
                val s2 = new CounterSim(s1)
                val s3 = new CounterSim(s2)
                Vector(s1, s2, s3)
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.timeseries"),
            destFolder = "gen-core/src/main/scala/timeseries/")
    }
}