package meta.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Future, Message}
import meta.API._
import org.scalatest.{FlatSpec, DoNotDiscover}

@lift
class AgentWithBlockingCall(val n: AgentWithBlockingCall) extends Actor {

    var future: Future[Int] = null
    var blockingReplyValue: List[Int] = List()
    var totalBlockingMtdCalls: Int = 0

    def blockingMtd(): Int = {
        totalBlockingMtdCalls = totalBlockingMtdCalls + 1
        barrierSync()
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
        barrierSync()
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