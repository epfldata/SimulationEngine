package example
package epistemicLogicExamples

import lib.EpistemicLogic._

package object MuddyChildren {
  import meta.runtime.Actor.AgentId

  case class ChildStatus(id: AgentId, isMuddy: Boolean, isForward: Boolean, epoch: Int)
  case class HearParent(epoch: Int)
  case class ChildMuddy(id: AgentId, isMuddy: Boolean = true)

  def seeAllNeighbor(): EpistemicSentence = {
    P("Child sees all neighbors")
  }

  // at least one muddy child
  def announce(ids: List[AgentId]): EpistemicSentence = {
    Utils.ors(ids.map(i => P(ChildMuddy(i))))
  }

  // speculate: agent i hasn't stepped up in the past n round => agent i knows there are at least n muddy children
  def inferOtherAgent(i: AgentId, neighbor: List[AgentId], mChildren: Int): Set[EpistemicSentence] = {
    //    assert(!neighbor.contains(id))
    // return a list of all combinations of size m
    def helper(l: List[List[AgentId]], n: Int): List[List[AgentId]] = {
      if (n > 1) {
        val ans = for (i <- l; j <- neighbor if j > i.last) yield (i ++ List(j))
        helper(ans, n-1)
      } else if (n == 0) {
        List()
      }else {
        l
      }
    }

    val ans: List[List[AgentId]] = helper(neighbor.map(i => List(i)), mChildren)

    if (ans.isEmpty) {
      Set(Ka(i, P(ChildMuddy(i))))
    } else {
      Set(Ka(i, Utils.ors(ans.map(cs => Utils.ands(cs.map(c => P(ChildMuddy(c))))))))
    }
  }

  def counterExampleLearning(es: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    es.map(x => x match {
      case Ka(i, e) =>
        //                        println("Learned forward case 1" + e);
        Ka(i, NotE(e))
      case _ => {
        //                        println("Learned forward case 2 " + x);
        NotE(x)}
    })
  }
}
