package simulation.base
package test

import API._
import org.scalatest.FlatSpec

class methodNameTest extends FlatSpec {

    "Calling methods with separator characters in their names" should "invoke the correct method" in {
        val agents = generated.core.test.methodNames.InitData()
        val snapshot = Simulate(agents, 30)
    }
}