package meta.test.custRunner

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime._
import meta.API._
import org.scalatest.FlatSpec
import meta.compile._
import meta.runtime.simulation.Base

// A test that shows how users can define their own runners
class CustRunnerTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "Define a custom do nothing runner" should "print do nothing messages" in {
        val agents = generated.meta.test.custAgent.InitData()
        val c = new SimulationConfig(agents, 5)
        val custRunner = new SimsRunner[BaseMessagingLayer.type] {
            def run(c: SimulationConfig): SimulationSnapshot = {
                for (i <- 1 to c.totalTurn) {
                    println("A custom runner that does nothing!")
                }
                SimulationSnapshot(List(), List())
            }
        }
        StartSimulation[BaseMessagingLayer.type](c)(custRunner)
    }
    
    "Define a custom runner without logging round information" should "print only agent messages" in {
        val agents = generated.meta.test.custAgent.InitData()
        val c = new SimulationConfig(agents, 5)

        val custRunner = new SimsRunner[BaseMessagingLayer.type] {
            class noRoundBase(c: SimulationConfig) extends Base(c.actors, c.totalTurn, c.messages) {
                override def run(): SimulationSnapshot = {
                    while (currentTurn < totalTurn) {
                        val mx = collectedMessages.groupBy(_.receiverId)
                        val res = actors.filterNot(_.deleted).map(a => {
                            val targetMessages = a.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
                            a.run(targetMessages)
                        })
                        collectedMessages = res.flatMap(_._1)
                        proceed(res.map(_._2).max)
                    }

                    SimulationSnapshot(actors, collectedMessages)
                }
            }

            def run(c: SimulationConfig): SimulationSnapshot = {
                new noRoundBase(c).run()
            }
        }
        StartSimulation[BaseMessagingLayer.type](c)(custRunner)
    }
}