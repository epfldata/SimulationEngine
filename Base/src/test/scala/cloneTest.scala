package simulation.base
package test

import API._
import org.scalatest.FlatSpec

class CloneTest extends FlatSpec {
    "Clone a mutable agent at round 5 and resume the cloner" should "have different states for clonee and cloner" in {
        val agents = generated.core.test.simClone.InitData()
        val snapshot1 = Simulate(agents, 5)
        val clonee = snapshot1.sims.head.asInstanceOf[generated.core.test.simClone.MutableSim]
        val cloner = clonee.SimClone()
        // The init state of cloner is the final state of clonee
        assert(cloner.asInstanceOf[generated.core.test.simClone.MutableSim].counter == 5)
        // Run another simulation from cloner won't change the state of clonee
        val snapshot2 = Simulate(Vector(cloner), 5)
        assert(snapshot2.sims.head.asInstanceOf[generated.core.test.simClone.MutableSim].counter == 10)
        assert(clonee.counter == 5)
    }
}