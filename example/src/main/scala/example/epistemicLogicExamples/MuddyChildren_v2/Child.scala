package example
package epistemicLogicExamples
package MuddyChildren_v2

import lib.EpistemicLogic._
import lib.EpistemicLogic.EpistemicLogicCommon._
import lib.Bot.MessengerBot
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import MuddyChildren_v1.{ChildStatus => _, _}
import Actor.AgentId 

case class ChildStatus(id: AgentId, isMuddy: Boolean)

@lift
class Child(val isMuddy: Boolean) extends Actor {

  var neighbors: List[Child] = Nil
  var neighborIds: List[Actor.AgentId] = Nil

  var epoch: Int = 0
  var isAware: Boolean = false

  val knowledgeBase: KnowledgeBase = new KnowledgeBase()
  knowledgeBase.default()

  // RPC
  // report their current status
  def tell(): P[ChildStatus] = {
    P(ChildStatus(id, isMuddy))
  }

  // RPC
  // A child hears announcement and answers isAware or not 
  def answer(): Option[(Actor.AgentId, Boolean)] = {  
    if (knowledgeBase.know(seeAllNeighbor())) {
      epoch = epoch + 1
      knowledgeBase.recordLearning(epoch, Set(P(HearParent(epoch))))
      if (!isAware) {
        knowledgeBase.recordLearning(epoch, Set(announce((neighborIds ++ List(id)))))
        val fact: EpistemicSentence = if (isMuddy) {
          P(ChildMuddy(id))
        } else {
          NotE(P(ChildMuddy(id)))
        }

        if (knowledgeBase.know(fact)  || knowledgeBase.know(Ka(id, fact))) {
          isAware = true
        }
      }
      println(s"Child $id is aware of its status: $isAware")
      Some((id, isAware))
    } else {
      None    // analogous to first "Attention! Otherwise no guarantee all children heard the parent"
    }
  }

  // RPC
  // Record what a child learns from the given children status announced by the parent 
  def think(cs: List[(Actor.AgentId, Boolean)]): Unit = {

    if (!isAware) {
      cs.filter(x => x._1!=id).foreach(c => {
        val ans: Set[EpistemicSentence] = if (c._2) { // if the agent is aware
          var f: EpistemicSentence = P(ChildMuddy(c._1))
          if (!knowledgeBase.know(f)) {
            f = NotE(f)
          }
          knowledgeBase.recordLearning(epoch, Set(Ka(c._1, f))) // e.g. 2 knows he is muddy or not
          counterExampleLearning(knowledgeBase.speculate(Set(Ka(c._1, NotE(f)))))
        } else {
          inferOtherAgent(c._1, (id :: neighborIds).filterNot(x => x == c._1), epoch)
        }
        knowledgeBase.recordLearning(epoch, ans)
      })
    }
  }

  // non-RPC
  private def see(): List[P[ChildStatus]] = {
    val messenger: MessengerBot = new MessengerBot()
    val wReply: List[Option[Future[P[ChildStatus]]]] = neighbors.map(c => asyncMessage(() => c.tell()))

    // send out the batched messages
    waitLabel(Turn,1)
    // process any incoming requests
    handleMessages()

    val ans: List[P[ChildStatus]] = messenger.waitUntilAllReply(wReply).asInstanceOf[List[P[ChildStatus]]]

    knowledgeBase.remember(Set(seeAllNeighbor()))
    assert(ans.size == neighbors.size)

    // remember what they see
    ans.map(c => {
      val f: EpistemicSentence = if (c.proposition.isMuddy) {
        P(ChildMuddy(c.proposition.id))
      } else {
        NotE(P(ChildMuddy(c.proposition.id)))
      }
      knowledgeBase.recordLearning(epoch, Set(Ka(id, f)))
      c
    })
  }

  def main(): Unit = {
    neighborIds = neighbors.map(n => n.id)
    see()
    // println("Seen all neighbors!")
    while (true) {
      handleMessages()
      waitLabel(Turn, 1)
    }
  }
}

