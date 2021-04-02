package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import Actor.AgentId

// For a large number of agents, we can make it bin-based 
object MessagingStats {
  private var sketch: MutMap[AgentId, MutMap[AgentId, Int]] = MutMap[AgentId, MutMap[AgentId, Int]]()
  private var mergeThreshold: Int = 10
  private var mergeCandidates: ListBuffer[Set[AgentId]] = new ListBuffer()

  def getSketch: Set[(AgentId, Map[AgentId, Int])] = sketch.map(x => (x._1, x._2.toMap)).toSet

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

  // assume sim1 is always less than sim2
  // todo: add overflow logic 
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

    if (sketch(simPair.head)(simPair.last) >=mergeThreshold) {
      addMergeCandidates(sim1, sim2)
      sketch(simPair.head) -= simPair.last
    }

    // merge(1, 2), sketch(1) = Map(2 -> 10, 3 -> 4, 4 -> 5)
    // meta.Util.warning(s"Record message: ${sim1}, ${sim2}, Sketch: ${sketch}")
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