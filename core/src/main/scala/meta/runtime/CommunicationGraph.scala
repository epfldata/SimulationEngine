package meta.runtime

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import Actor.AgentId

// Per Worker
class CommunicationGraph {
    val graph: MutMap[AgentId, MutMap[AgentId, Int]] = MutMap[AgentId, MutMap[AgentId, Int]]()

    protected def combineLeft(x: MutMap[AgentId, Int], y: MutMap[AgentId, Int]): Unit = {
        y.foreach(i => {
            x.update(i._1, x.getOrElse(i._1, 0)+i._2)
        })
    }

    // Seq[(sendId, receiverId, edgeWeight)]
    def constructGraph(messages: Seq[(AgentId, AgentId, Int)]): Unit = {
        messages.foreach(m => {
            m match {
                case (sid, rid, weight) => {
                    // Update edges in both directions
                    val edges1 = graph.getOrElseUpdate(sid, MutMap[AgentId, Int]())
                    edges1.update(rid, edges1.get(rid) match {
                        case None => weight
                        case Some(x) => x + weight
                    })

                    val edges2 = graph.getOrElseUpdate(rid, MutMap[AgentId, Int]())
                    edges2.update(sid, edges2.get(sid) match {
                        case None => weight
                        case Some(x) => x + weight
                    })
                }
            }
        })
    }

    def clear(): Unit = {
        graph.clear()
    }

    def combineGraph(g: CommunicationGraph): Unit = {
        g.graph.foreach(v => {
            val edges = graph.getOrElseUpdate(v._1, MutMap[AgentId, Int]())
            combineLeft(edges, v._2)
        })
    }
}