package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import scala.util.Random
import squid.lib.transparencyPropagating

@lift
class Sender(val r: Receiver) extends Actor {

    def main(): Unit = {
        while (true){
            asyncCall(() => r.rpc1(), 1)
            asyncCall(r.rpc2, 2)
            callAndForget(r.rpc3, 3)
            waitRounds(1)
        }
    }
}

@lift
class Receiver() extends Actor {
    var counter1: Int = 0
    var counter2: Int = 0
    var counter3: Int = 0

    def rpc1(): Unit = {
        counter1 = counter1 + 1
    }

    @transparencyPropagating
    def rpc2(): Unit = {
      counter2 = counter2 + 1
    }

    @transparencyPropagating
    def rpc3(): Unit = {
      counter3 = counter3 + 1
    }


    def main(): Unit = {
        while (true){
            waitAndReply(1)
        }
    }
}

class MessageLatencyTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "The message latency example" should "compile" in {
        val liftSender: ClassWithObject[Sender] = Sender.reflect(IR)
        val liftReceiver: ClassWithObject[Receiver] = Receiver.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val r: Receiver = new Receiver()
                val s: Sender = new Sender(r)
                List(r, s)
            }
        }

        compileSims(List(liftSender, liftReceiver), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.messageLatency"),
            destFolder = "gen-core/src/main/scala/messageLatency/")
    }
}