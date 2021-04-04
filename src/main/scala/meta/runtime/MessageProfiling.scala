package meta.runtime

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import Actor.AgentId

class MessageProfiling {
  private var sketch: MutMap[AgentId, MutMap[AgentId, Int]] = MutMap[AgentId, MutMap[AgentId, Int]]()

  // def getSketch: Set[(AgentId, Map[AgentId, Int])] = sketch.map(x => (x._1, x._2.toMap)).toSet

  def getSketch(sim1: AgentId, sim2: AgentId): Int = {
    if (sim1 < sim2) {
      sketch(sim1)(sim2)
    } else {
      sketch(sim2)(sim1)
    }
  }

  def clearSketch(sim1: AgentId, sim2: AgentId): Unit = {
    if (sim1 < sim2) {
      sketch(sim1) -= sim2 
    } else {
      sketch(sim2) -= sim1
    }
  }

  // todo: add overflow logic 
  // record message one at a time
  def recordMessage(sim1: AgentId, sim2: AgentId): Unit = {

    val simPair = List(sim1, sim2).sorted

    if (sketch.get(simPair.head).isDefined) {
        if (sketch(simPair.head).get(simPair.last).isDefined) {
            sketch(simPair.head)(simPair.last) += 1
        } else {
            sketch(simPair.head).update(simPair.last, 1)
        }
    } else {
        sketch.update(simPair.head, MutMap(simPair.last -> 1))
    }
  }
}