package meta.test.newSim

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

@lift
class NewSim() extends Actor {

    def main(): Unit = {
        while (true){
            val c = new NewSim()
            waitAndReply(1)
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
            initPkgName = Some("core.test.newSim"),
            destFolder = "gen-core/src/main/scala/newSim/")
    }
}