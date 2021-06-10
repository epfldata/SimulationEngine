package meta.runtime

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import Actor.AgentId

// For a large number of agents, we can make it bin-based 
class ChattyAgents(var mergeThreshold: Int) {
  private val profile = new MessageProfiling()

  private var pairCandidates: ListBuffer[List[AgentId]] = new ListBuffer()

  def recordMessage(sim1: AgentId, sim2: AgentId): Unit = {
    profile.recordMessage(sim1, sim2)
    if (profile.getSketch(sim1, sim2) >= mergeThreshold) {
      pairCandidates += List(sim1, sim2)
      profile.clearSketch(sim1, sim2)
    }
  }

  def recordMessage(messages: List[Message]): Unit = {
    messages.foreach(m => {
      recordMessage(m.senderId, m.receiverId)
    })
  }

  // the threshold beyond which we kick in the merging mechanism
  def setMergeThreshold(threshold: Int) = {
      mergeThreshold = threshold
  }

  def getMergeThreshold: Int = {
      mergeThreshold
  }

  def getPairedMergeCandidates: List[List[AgentId]] = {
      pairCandidates.toList
  }

  def clearMergeCandidates(): Unit = {
    pairCandidates.clear()
  }
}