package generated.core.blockingMethodCall

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor, Future, Message}
import meta.API._
import org.scalatest.FlatSpec
import meta.runtime.simulation.Base

class blockingMethodCallTest extends FlatSpec {
    import meta.classLifting.Lifter
    import meta.deep.IR.TopLevel._
    import meta.deep.codegen._
    import meta.runtime.Actor
    import meta.deep.IR.Predef._ 

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
        val agents = generated.core.test.blockingMethodCall.InitData()
        val c = new SimulationConfig(agents, 20)
        StartSimulation[BaseMessagingLayer.type](c)(custRunner)
    }

    "Calling the blocking method of itself" should "run in multiple rounds" in {
        val agents = generated.core.test.blockingMethodCallLocal.InitData()
        val c = new SimulationConfig(agents, 5)
        StartSimulation[BaseMessagingLayer.type](c)(custRunner)
    }
}