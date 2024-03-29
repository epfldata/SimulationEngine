package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Future}
import meta.API._
import org.scalatest.{FlatSpec, DoNotDiscover}

@lift
class AgentWithBlockingCall(val n: AgentWithBlockingCall) extends Actor {

    var future: Future[Int] = null
    var blockingReplyValue: List[Int] = List()
    var totalBlockingMtdCalls: Int = 0

    def blockingMtd(): Int = {
        totalBlockingMtdCalls = totalBlockingMtdCalls + 1
        waitRounds(1)
        totalBlockingMtdCalls
    }

    def main(): Unit = {
        while (true){
            if (n != null){
                future = asyncCall(() => n.blockingMtd(), 1)
                while (!future.isCompleted){
                    waitAndReply(1)
                }
                blockingReplyValue = future.popValue.get :: blockingReplyValue
            }
            waitAndReply(1)
        }
    }
}

@lift
class AgentWithBlockingCallLocal() extends Actor {

    var future: Future[Int] = null
    var totalBlockingMtdCalls: Int = 0
    var totalNonBlockingMtdCalls: Int = 0

    def blockingMtd(): Unit = {
        totalBlockingMtdCalls = totalBlockingMtdCalls + 1
        waitRounds(1)
    }

    def nonBlockingMtd(): Unit = {
        totalNonBlockingMtdCalls = totalNonBlockingMtdCalls + 1
    }

    def main(): Unit = {
        while (true){
            nonBlockingMtd()
            blockingMtd()
            waitAndReply(1)
        }
    }
}

@DoNotDiscover
class blockingMethodCallTest extends FlatSpec {
    import meta.deep.IR.TopLevel._
    import meta.runtime.Actor

    "An agent with blocking method call" should "compile" in {
        val liftMyClass: ClassWithObject[AgentWithBlockingCall] = AgentWithBlockingCall.reflect(IR)
        val liftedMain = meta.classLifting.liteLift {
            def apply(): IndexedSeq[Actor] = {
                val a = new AgentWithBlockingCall(null)
                val b = new AgentWithBlockingCall(a)
                val c = new AgentWithBlockingCall(a)
                Vector(a, b, c)
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
            def apply(): IndexedSeq[Actor] = {
                val a = new AgentWithBlockingCallLocal()
                Vector(a)
            }
        }

        compileSims(List(liftMyClass), 
            mainInit = Some(liftedMain), 
            initPkgName = Some("core.test.blockingMethodCallLocal"),
            destFolder = "gen-core/src/main/scala/blockingMethodCallLocal/")
    }
}