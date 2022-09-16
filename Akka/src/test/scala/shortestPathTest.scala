package simulation.akka
package test

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift
import meta.deep.IR.TopLevel.ClassWithObject
import meta.deep.IR
import meta.runtime.{Actor}
import meta.API._
import org.scalatest.FlatSpec

class shortestPathTest extends FlatSpec {
    import meta.deep.IR.Predef._

    "The single source shortest path algorithm over a linked list with 10 vertices" should "update the distance of all vertices in 10 rounds" in {
        val agents = generated.core.test.shortestPath.InitData()
        val snapshot1 = AkkaRun(agents, 10)
        assert(snapshot1.sims.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist).toSet == Range(0, 10).toSet)
    }
}