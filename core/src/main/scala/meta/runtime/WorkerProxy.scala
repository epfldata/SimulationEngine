package meta.runtime

import Actor.AgentId

trait WorkerProxy {
    var workerId: Int = -1
    // (AgentId, WorkerId)
    var agentLocationLookup: Map[AgentId, Int] = Map[AgentId, Int]()
    var localAgentIds: Set[AgentId] = Set()
}

trait AdaptiveWorkerProxy extends WorkerProxy {
    var c: CommunicationGraph = new CommunicationGraph()

    var partitionThreshold: Double = 4

    // workerId, cost
    def cost(v: AgentId): Iterable[(Int, Int)] = {
        c.graph.get(v) match {
            case Some(a) => {
                a.groupBy(i => agentLocationLookup.getOrElse(i._1, workerId))
                .map(i => (i._1, i._2.values.sum)).toSeq.sortBy(_._2)(Ordering.Int.reverse)
            }
            case None => Iterable()
        }
    }

    // agent id, target worker, cost
    def partitionRequest(): Iterable[(AgentId, Int, Int)] = {
        localAgentIds.map(a => (a, cost(a))).toSeq.flatMap(a => {
            a._2.headOption match {
                case None => 
                    Iterable()
                case Some(first) => if (first._1 != workerId) {
                    val remoteCost = first._2
                    a._2.find(p => p._1 == workerId) match {
                        case None => Iterable((a._1, a._2.head._2, a._2.head._1))
                        case Some(x) => if (x._2 * partitionThreshold < remoteCost) {
                            Iterable((a._1, a._2.head._1, a._2.head._2))
                        } else {
                            Iterable()
                        }
                    }
                } else 
                    Iterable()
            }
        })
    }
}

// Can extend AdaptiveWorkerProxy instead
trait CustomWorkerProxy extends WorkerProxy