package meta.runtime

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import Actor.AgentId

// For a large number of agents, we can make it bin-based 
class ChattyAgents(var mergeThreshold: Int) {
  private val profile = new MessageProfiling()

  private var mergeCandidates: ListBuffer[Set[AgentId]] = new ListBuffer()

  def recordMessage(sim1: AgentId, sim2: AgentId): Unit = {
    profile.recordMessage(sim1, sim2)
    if (profile.getSketch(sim1, sim2) >= mergeThreshold) {
      addMergeCandidates(sim1, sim2)
    }
  }

  // the threshold beyond which we kick in the merging mechanism
  def setMergeThreshold(threshold: Int) = {
      mergeThreshold = threshold
  }

  def getMergeThreshold: Int = {
      mergeThreshold
  }

  def getMergeCandidates: List[Set[AgentId]] = {
      mergeCandidates.toList
  }

  def clearMergeCandidates(): Unit = {
    mergeCandidates.clear()
  }

  def addMergeCandidates(sim1: AgentId, sim2: AgentId): Unit = {
    var existingPlaces: List[Int] = List()

    mergeCandidates.zipWithIndex.foreach(x => {
      if (x._1.contains(sim1) || x._1.contains(sim2)) {
        existingPlaces = x._2 :: existingPlaces
      }
    })

    // head < tail.head
    existingPlaces = existingPlaces.sorted
    // 3 cases: 
    // If sim1 and sim2 are both new to mergeCandidates, create a new set for them 
    // If only one is new, then place both sims into the original array 
    // If none is new, merge the two original array 
    existingPlaces match {
      case Nil => 
        mergeCandidates += Set(sim1, sim2)
      case head :: Nil =>
        mergeCandidates(head) = mergeCandidates(head).union(Set(sim1, sim2))
      case head :: tail => 
        val foo = mergeCandidates.remove(tail.head)
        mergeCandidates(head) = mergeCandidates(head).union(foo)
    }
  }
}