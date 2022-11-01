package simulation.akka
package test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

class piccolo extends FlatSpec {
    import meta.deep.IR.Predef._

    val totalRounds: Int = 100

    f"The page rank algorithm with vertices, sequential workers" should f"complete" in {
        val agents = generated.example.piccolo.InitData()
        // API.OptimizationConfig.mergedWorker()
        val snapshot1 = API.Simulate(agents, totalRounds)
    }
}