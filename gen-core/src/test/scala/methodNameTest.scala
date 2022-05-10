package generated.core

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

/**
 * Test names with modifier separators
 */

class methodNameTest extends FlatSpec {
    import meta.deep.IR.Predef._
    import meta.classLifting.Lifter

    "Calling methods whose names contain separator characters" should "invoke the correct method" in {
        val agents = generated.core.test.methodNames.InitData()
        val c = new SimulationConfig(agents, 30)
        val r = StartSimulation[BaseMessagingLayer.type](c)
    }
}