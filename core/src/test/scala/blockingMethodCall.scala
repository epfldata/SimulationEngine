package meta.test.blockingMethodCall

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Future, Message}
import meta.API._
import org.scalatest.FlatSpec
import meta.runtime.simulation.Base


@lift
class AgentWithBlockingCall(val n: AgentWithBlockingCall) extends Actor {

    var future: Future[Boolean] = null

    def blockingMtd(): Boolean = {
        println(id + " processes blocking mtd!")
        waitLabel(Turn, 1)
        println(id + " finishes processing!")
        true
    }

    def nonBlockingMtd(): Boolean = {
        println(id + " processes an asynchrnous mtd!")
        false
    }

    def main(): Unit = {
        while (true){
            if (n != null){
                future = asyncMessage(() => n.nonBlockingMtd())
                while (!future.isCompleted){
                    waitAndReply(1)
                }
                println("Receive the reply for nonBlockingMtd " + future.popValue.get)

                future = asyncMessage(() => n.blockingMtd())
                while (!future.isCompleted){
                    waitAndReply(1)
                }
                println("Receive the reply for blockingMtd " + future.popValue.get)
                
                // n.nonBlockingMtd()
                // n.blockingMtd()
            }
            waitAndReply(1)
        }
    }
}

@lift
class AgentWithBlockingCallLocal() extends Actor {

    def blockingMtd(): Boolean = {
        println(id + " processes blocking mtd!")
        waitLabel(Turn, 1)
        println(id + " finishes processing!")
        true
    }

    def nonBlockingMtd(): Boolean = {
        println(id + " processes a nonblocking mtd!")
        false
    }

    def main(): Unit = {
        while (true){
            blockingMtd()
            nonBlockingMtd()
            waitAndReply(1)
        }
    }
}

class blockingMethodCallTest extends FlatSpec {
    import meta.classLifting.Lifter
    import meta.deep.IR.TopLevel._
    import meta.deep.codegen._
    import meta.runtime.Actor
    import meta.deep.IR.Predef._ 

    "An agent with blocking method call" should "compile" in {
        val liftMyClass: ClassWithObject[AgentWithBlockingCall] = AgentWithBlockingCall.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val a = new AgentWithBlockingCall(null)
                val b = new AgentWithBlockingCall(a)
                val c = new AgentWithBlockingCall(a)
                List(a, b, c)
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.blockingMethodCall"),
            destFolder = "gen-core/src/main/scala/blockingMethodCall/")
    }

    "An agent that calls a local blocking method call" should "compile" in {
        val liftMyClass: ClassWithObject[AgentWithBlockingCallLocal] = AgentWithBlockingCallLocal.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): List[Actor] = {
                val a = new AgentWithBlockingCallLocal()
                List(a)
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.blockingMethodCallLocal"),
            destFolder = "gen-core/src/main/scala/blockingMethodCallLocal/")
    }
}