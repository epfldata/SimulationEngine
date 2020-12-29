package example
package epistemicLogicExamples
package vectorClock

import lib.EpistemicLogic._
import meta.runtime.Actor.AgentId


class VectorClock extends KnowledgeBase {

  def getInfo(p: AgentId): Set[EpistemicSentence] = {
    knowledgeBase.filter(s => {
      s match {
        case P(ProcessTime(i, t)) => i == p
        case _ => false
      }
    })
  }

  def getTiming(p: AgentId): Int = {
    val timeInfo: Set[EpistemicSentence] = getInfo(p)
    assert(timeInfo.size <= 1)
    if (timeInfo.isEmpty) {   // during initialization
      -1
    } else {
      timeInfo.head.asInstanceOf[P[ProcessTime]].proposition.time
    }
  }

  // an added time has to be greater than the existing time
  def timingRule(s: EpistemicSentence): Boolean = {
    s match {
      case P(ProcessTime(i, t)) => getTiming(i) < t
      case _ => false
    }
  }

  // find the existing knowledge about s, and replace them
  def updateTimingInfo(s: EpistemicSentence): Unit = {
    s match {
      case P(ProcessTime(i, t)) => {
        forget(getInfo(i))
        remember(Set(s))
      }
      case _ =>
    }
  }

  // constraints for vector clock
  default()
  addConstraints(x => {
    timingRule(x)
  })
}
