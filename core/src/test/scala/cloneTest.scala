package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

@lift
class MutableSim() extends Actor {

    var counter: Int = 0

    def main(): Unit = {
        while (true){
            counter = counter + 1
            waitAndReply(1)
        }
    }
}

class CloneTest extends FlatSpec {

    "Mutable agent" should "compile" in {
        val liftMyClass: ClassWithObject[MutableSim] = MutableSim.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): IndexedSeq[Actor] = {
                Vector(new MutableSim())
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.simClone"),
            destFolder = "gen-core/src/main/scala/simClone/")
    }
}