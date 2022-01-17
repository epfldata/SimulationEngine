package meta.runtime

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import Actor.AgentId

class MessageProfiling() {
  private var sketch: MutMap[AgentId, MutMap[AgentId, Int]] = MutMap[AgentId, MutMap[AgentId, Int]]()

  // For a given agent sim1, returns the total number of communications it made
  def getTotalMessages(sim1: AgentId): Int = {
    sketch.foldLeft(0)((y, x) => {
      if (x._1 == sim1) {
        y + x._2.values.sum
      } else {
        y + x._2.getOrElse(sim1, 0)
      }
    })
  }

  def clearSketch(): Unit = {
    sketch.clear()
  }

  // Update the sketch
  def recordMessage(sim1: AgentId, sim2: AgentId): Unit = {
    if (sketch.get(sim1).isDefined) {
        if (sketch(sim1).get(sim2).isDefined) {
            sketch(sim1)(sim2) += 1
        } else {
            sketch(sim1).update(sim2, 1)
        }
    } else {
        sketch.update(sim1, MutMap(sim2 -> 1))
    }
  }

  def recordMessage(messages: Seq[Message]): Unit = {
    messages.foreach(m => {
      if (m.senderId > m.receiverId){
        recordMessage(m.receiverId, m.senderId)
      } else {
        recordMessage(m.senderId, m.receiverId)
      }
    })
  }

  def getMergeCandidates(mergeThreshold: Int): List[Tuple2[AgentId, AgentId]] = {
    var mergingCandidates = List[Tuple2[AgentId, AgentId]]()

    for (x <- sketch) {
      for (y <- x._2) {
        if (y._2 > mergeThreshold) {
          mergingCandidates = (x._1, y._1) :: mergingCandidates 
        }
      }
    }

    mergingCandidates
  }
}