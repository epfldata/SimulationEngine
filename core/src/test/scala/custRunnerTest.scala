package meta.test.custRunner

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime._
import meta.API._
import org.scalatest.FlatSpec
import meta.compile._

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
}