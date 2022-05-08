package generated.core.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

/**
 * Test messaging for inserted methods
 */

class lifterTest6 extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    "Calling a remote overriden method" should "invoke the child method" in {
        val agents = generated.core.test.inheritance4.InitData()
        val c = new SimulationConfig(agents, 10)
        val r = StartSimulation[BaseMessagingLayer.type](c)
    }
}