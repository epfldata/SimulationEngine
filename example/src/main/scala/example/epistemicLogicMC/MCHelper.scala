package example.epistemicLogicMC

import meta.deep.runtime.Actor.AgentId
import library.EpistemicLogic.Sentence._
import library.EpistemicLogic.Utils._

object MCHelper {
  case class StepForward(f: EpistemicSentence)

  def pTemplate(id: AgentId): EpistemicSentence = {
    P("Child " + id + " is muddy")
  }

  // at least one muddy child
  def announce(ids: List[AgentId]): EpistemicSentence = {
    ors(ids.map(i => pTemplate(i)))
  }

  def getNeighborId(s: String): AgentId = {
    val prefix: String = "Child "
    val posfix: String = " is muddy"
    s.slice(s.indexOf(prefix) + prefix.length, s.indexOf(posfix)).toInt
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
      Set(Ka(i, pTemplate(i)))
    } else {
      Set(Ka(i, ors(ans.map(cs => ands(cs.map(c => pTemplate(c)))))))
    }
  }

  def whatNeighborSees(id: AgentId, observations: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    observations.flatMap(x => {
      observations.flatMap(y => {
        if (getNeighborId(x.toString) != getNeighborId(y.toString)) {
          Set[EpistemicSentence](
            Ka(id, Ka(getNeighborId(x.toString), y)),
            Ka(id, Ka(getNeighborId(y.toString), x)),
            //            Ka(id, Ka(getNeighborId(x.toString), Ka(id, y))),
            //            Ka(id, Ka(getNeighborId(y.toString), Ka(id, x))),
          )
        } else {
          Set[EpistemicSentence]()
        }
      })
    })
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
