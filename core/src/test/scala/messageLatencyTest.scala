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
class SenderWithBound(val r: ReceiverForBound) extends Actor {

    def main(): Unit = {
        while (true){
            asyncCall(() => r.rpc1(), 5)
            asyncCall(r.rpc2, 5)
            callAndForget(r.rpc3, 5)
            waitRounds(1)
        }
    }
}

@lift
class ReceiverForBound() extends Actor {
    var counter: Int = 0

    def rpc1(): Unit = {
        counter = counter + 1
        println("Received a message for rpc1 at time " + time)
    }

    @transparencyPropagating
    def rpc2(): Unit = {
      counter = counter + 1
      println("Received a message for rpc2 at time " + time)
    }

    @transparencyPropagating
    def rpc3(): Unit = {
      counter = counter + 1
      println("Received a message for rpc3 at time " + time)
    }


    def main(): Unit = {
        while (true){
            handleRPC()
            waitRounds(1)
        }
    }
}

class MessageLatencyTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "The message latency example" should "compile" in {
        val liftSender: ClassWithObject[SenderWithBound] = SenderWithBound.reflect(IR)
        val liftReceiver: ClassWithObject[ReceiverForBound] = ReceiverForBound.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val r: ReceiverForBound = new ReceiverForBound()
                val s: SenderWithBound = new SenderWithBound(r)
                List(r, s)
            }
        }

        compileSims(List(liftSender, liftReceiver), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.messageLatency"),
            destFolder = "gen-core/src/main/scala/messageLatency/")
    }
}