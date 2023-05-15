package meta.test

import meta.runtime.{Actor, CommunicationGraph, WorkerProxy}
import meta.API._
import org.scalatest.FlatSpec
import scala.collection.mutable.Map

class CommunicationGraphTest extends FlatSpec {
    val c: CommunicationGraph = new CommunicationGraph()

    "Adding edges of different directions to a communication graph" should "update the graph in place" in {
        c.constructGraph(Seq((1, 2, 2), (2, 1, 1)))
        assert(c.graph == Map[Long, Map[Long, Int]](1.toLong -> Map[Long, Int](2.toLong -> 3), 2.toLong -> Map[Long, Int](1.toLong -> 3)))
    }

    "Combine graph" should "add new edges and update existing edge weights" in {
        val g2: CommunicationGraph = new CommunicationGraph()
        g2.constructGraph(Seq((3, 2, 1), (2, 1, 4)))
        c.combineGraph(g2)
        // expected graph
        val g3: CommunicationGraph = new CommunicationGraph()
        g3.constructGraph(Seq((2, 3, 1), (1, 2, 7)))
        assert(g3.graph == c.graph)
    }
}