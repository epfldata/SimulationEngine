package simulation.base
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
        val snapshot1 = new Base(agents, 10).run()
        assert(snapshot1.sims.map(i => i.asInstanceOf[generated.core.test.shortestPath.Vertex].dist) == List(5, 6, 7, 8, 9, 0, 1, 2, 3, 4))
    }
}