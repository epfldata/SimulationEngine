package generated.core.test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec
import java.io.File

/**
 * Test messaging for override methods
 */

class lifterTest4 extends FlatSpec {

    "Calling a remote overriden method" should "invoke the child method" in {
        val agents = generated.core.test.inheritance2.InitData()
        val c = new SimulationConfig(agents, 5)
        val r = StartSimulation[BaseMessagingLayer.type](c)
    }
}