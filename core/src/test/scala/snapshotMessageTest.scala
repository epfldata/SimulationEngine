package meta.test.snapshot

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import scala.util.Random

@lift
class Sender(val r: Receiver) extends Actor {

    def main(): Unit = {
        while (true){
            asyncMessage(() => r.rpc1())
            waitAndReply(1)
        }
    }
}

@lift
class Receiver() extends Actor {
    def rpc1(): Int = {
        println("rpc 1!")
        2
    }

    def main(): Unit = {
        while (true){
            waitAndReply(1)
        }
    }
}

class SnapshotMessageTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "The snapshot example" should "compile" in {
        val liftSender: ClassWithObject[Sender] = Sender.reflect(IR)
        val liftReceiver: ClassWithObject[Receiver] = Receiver.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val r: Receiver = new Receiver()

                r :: (1 to 7).map(x => {
                    val s: Sender = new Sender(r)
                    s
                }).toList 
            }
        }

        compileSims(List(liftSender, liftReceiver), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.snapshot"),
            destFolder = "gen-core/src/main/scala/snapshot/")
    }
}