package example.epistemicLogicMC

import meta.deep.runtime.Actor.AgentId
import library.EpistemicLogic.Sentence._
import library.EpistemicLogic.Utils._

object MCHelper {
  case class ChildStatus(id: AgentId, isMuddy: Boolean, isForward: Boolean, epoch: Int)

  def schema(id: AgentId, isMuddy: Boolean = true): EpistemicSentence = {
    if (isMuddy)
      P("Child " + id + " is muddy")
    else
      NotE(P("Child " + id + " is muddy"))
  }

  def seenNeighbor(id: AgentId)(e: EpistemicSentence): Boolean = {
    getChildId(e) == id
  }

  def getChildId(e: EpistemicSentence): AgentId = {
    val s: String = e.toString
    val prefix: String = "Child "
    val posfix: String = " is muddy"
    s.slice(s.indexOf(prefix) + prefix.length, s.indexOf(posfix)).toInt
  }

  // knowledge for hearing the parent
  def hearParent(epoch: Int): EpistemicSentence = {
    P("Child hears the parent at " + epoch + " round")
  }

  def seeAllNeighbor(): EpistemicSentence = {
    P("Child sees all neighbors")
  }

  def getEpoch(e: EpistemicSentence): Int = {
    val s: String = e.toString
    val prefix: String = "Child hears the parent at "
    val posfix: String = " round"
    s.slice(s.indexOf(prefix) + prefix.length, s.indexOf(posfix)).toInt
  }

  // at least one muddy child
  def announce(ids: List[AgentId]): EpistemicSentence = {
    ors(ids.map(i => schema(i)))
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
      Set(Ka(i, schema(i)))
    } else {
      Set(Ka(i, ors(ans.map(cs => ands(cs.map(c => schema(c)))))))
    }
  }

  def whatNeighborSees(id: AgentId, observations: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    observations.flatMap(x => {
      observations.flatMap(y => {
        if (getChildId(x) != getChildId(y)) {
          Set[EpistemicSentence](
            Ka(id, Ka(getChildId(x), y)),
            Ka(id, Ka(getChildId(y), x)),
            //            Ka(id, Ka(getChildId(x), Ka(id, y))),
            //            Ka(id, Ka(getChildId(y), Ka(id, x))),
          )
        } else {
          Set[EpistemicSentence]()
        }
      })
    })
  }

  def counterExampleLearning(es: Set[EpistemicSentence]): Set[EpistemicSentence] = {
//    Set()
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
