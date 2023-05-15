package meta.test

import meta.runtime._
import meta.API._
import org.scalatest.FlatSpec

class WorkerProxyTest extends FlatSpec {
    class TestWorker(wid: Int, localIds: Set[Long], 
        agentLookup: Map[Long, Int]) extends AdaptiveWorkerProxy {
            this.workerId = wid
            this.localAgentIds = localIds
            this.agentLocationLookup = agentLookup
        c.constructGraph(Seq((1, 3, 1), (1, 2, 7), (1, 5, 2)))
    }

    "The cost of a vertex" should "depend on the edge weights" in {
        val worker = new TestWorker(1, Set(1, 3), Map[Long, Int](2.toLong -> 2, 
            5.toLong -> 2))
        assert(worker.cost(1) == Vector((2, 9), (1, 1)))
        worker.partitionThreshold = 1
        assert(worker.partitionRequest() == Vector((1, 2, 9)))
    }

    "Partition request" should "be empty if agents communicate most frequently with others in the same component" in {
        val worker = new TestWorker(1, Set(1, 2), Map[Long, Int](3.toLong -> 2, 
            5.toLong -> 2))
        assert(worker.cost(1) == Vector((1, 7), (2, 3)))
        assert(worker.cost(2) == Vector((1, 7)))
        assert(worker.cost(3) == Vector((1, 1)))
        worker.partitionThreshold = 1
        assert(worker.partitionRequest() == Vector())
    }
}