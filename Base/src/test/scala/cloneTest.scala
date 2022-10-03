package simulation.base
package test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

class CloneTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "Clone the mutable agent at round 5 and resume the cloner" should "have different states for clonee and cloner" in {
        val agents = generated.core.test.simClone.InitData()
        val snapshot1 = new Base(agents, 5).run()
        val clonee = snapshot1.sims.head.asInstanceOf[generated.core.test.simClone.MutableSim]
        val cloner = clonee.SimClone()
        // The init state of cloner is the final state of clonee
        assert(cloner.asInstanceOf[generated.core.test.simClone.MutableSim].counter == 5)
        // Run another simulation from cloner won't change the state of clonee
        val snapshot2 = new Base(List(cloner), 5).run()
        assert(snapshot2.sims.head.asInstanceOf[generated.core.test.simClone.MutableSim].counter == 10)
        assert(clonee.counter == 5)
    }
}