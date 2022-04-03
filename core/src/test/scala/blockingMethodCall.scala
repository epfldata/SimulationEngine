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

    // "An agent with blocking method call" should "compile" in {
    //     val liftMyClass: ClassWithObject[AgentWithBlockingCall] = AgentWithBlockingCall.reflect(IR)
    //     val liftedMain = meta.classLifting.liteLift {
    //         def apply(): List[Actor] = {
    //             val a = new AgentWithBlockingCall(null)
    //             val b = new AgentWithBlockingCall(a)
    //             val c = new AgentWithBlockingCall(a)
    //             List(a, b, c)
    //         }
    //     }

    //     compileSims(List(liftMyClass), 
    //         mainInit = Some(liftedMain), 
    //         initPkgName = Some(this.getClass().getPackage().getName()),
    //         destFolder = "core/src/test/scala/generated/blockingMethodCall/")
    // }

    // "An agent that calls a local blocking method call" should "compile" in {
    //     val liftMyClass: ClassWithObject[AgentWithBlockingCallLocal] = AgentWithBlockingCallLocal.reflect(IR)
    //     val liftedMain = meta.classLifting.liteLift {
    //         def apply(): List[Actor] = {
    //             val a = new AgentWithBlockingCallLocal()
    //             List(a)
    //         }
    //     }

    //     compileSims(List(liftMyClass), 
    //         mainInit = Some(liftedMain), 
    //         initPkgName = Some("meta.test.blockingMethodCallLocal"),
    //         destFolder = "core/src/test/scala/generated/blockingMethodCallLocal/")
    // }

    val custRunner = new SimsRunner[BaseMessagingLayer.type] {
            class showRound(c: SimulationConfig) extends Base(c.actors, c.totalTurn, c.messages) {
                override def run(): SimulationSnapshot = {
                    while (currentTurn < totalTurn) {
                        println(meta.runtime.simulation.util.displayTime(currentTurn))
                        val mx = collectedMessages.groupBy(_.receiverId)
                        val res = actors.filterNot(_.deleted).map(a => {
                        val targetMessages: List[Message] = a.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
                        a.run(targetMessages)
                        }).foldLeft((List[Message](), 1))((a, b) => ((a._1 ::: b._1), if (a._2 > b._2) a._2 else b._2))
                        collect()
                        collectedMessages = res._1
                        proceed(res._2)
                    }
                    SimulationSnapshot(actors, collectedMessages)
                    }
            }

            def run(c: SimulationConfig): SimulationSnapshot = {
                new showRound(c).run()
            }
        }

    "Calling the blocking method of another agent" should "complete in 2 rounds" in {
        val agents = generated.meta.test.blockingMethodCall.InitData()
        val c = new SimulationConfig(agents, 20)
        StartSimulation[BaseMessagingLayer.type](c)(custRunner)
    }

    "Calling the blocking method of itself" should "run in multiple rounds" in {
        val agents = generated.meta.test.blockingMethodCallLocal.InitData()
        val c = new SimulationConfig(agents, 5)
        StartSimulation[BaseMessagingLayer.type](c)(custRunner)
    }
}