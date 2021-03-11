package example
package epistemicLogicExamples
package MuddyChildren_v3

import lib.EpistemicLogic._
import lib.EpistemicLogic.EpistemicLogicCommon._

import lib.Bot.MessengerBot

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import Actor.AgentId
import MuddyChildren_v1.{ChildStatus => _, _}

case class ChildStatus(id: AgentId, isMuddy: Boolean, isForward: Boolean)

@lift
class Child(val isMuddy: Boolean) extends Actor {

  var neighbors: List[Child] = Nil
  var neighborIds: List[AgentId] = Nil

  var epoch: Int = 0
  var isForward: Boolean = false

  val knowledgeBase: KnowledgeBase = new KnowledgeBase()
  knowledgeBase.default()

  // RPC
  // report their current status
  def tell(): P[ChildStatus] = {
    P(ChildStatus(id, isMuddy, isForward))
  }

  // non-RPC
  def act(): Unit = {
      epoch = epoch + 1
      if (!isForward) {        // child only answers once
        knowledgeBase.recordLearning(epoch, Set(announce((neighborIds ++ List(id)).toList)))
        val fact: EpistemicSentence = if (isMuddy) {
          P(ChildMuddy(id))
        } else {
          NotE(P(ChildMuddy(id)))
        }

        if (knowledgeBase.know(fact)  || knowledgeBase.know(Ka(id, fact))) {
          isForward = true
          println("Child " + id + " steps forward!")
        } else {
          println("Child " + id + " stays still!")
        }
      }
  }

  // non-RPC
  // think records what a child learns from the given status.
  private def think(c: P[ChildStatus]): Unit = {
    if (!isForward) {  
      val newKnowledge: Set[EpistemicSentence] = if (c.proposition.isForward) {
        val f: EpistemicSentence = if (c.proposition.isMuddy) {
          P(ChildMuddy(c.proposition.id))
        }else {
          NotE(P(ChildMuddy(c.proposition.id)))
        }
        knowledgeBase.recordLearning(epoch, Set(Ka(c.proposition.id, f)))
        counterExampleLearning(knowledgeBase.speculate(Set(Ka(c.proposition.id, NotE(f)))))
      } else {
        inferOtherAgent(c.proposition.id, (id :: neighborIds).filterNot(x => x == c.proposition.id), epoch)
      }
      knowledgeBase.recordLearning(epoch, newKnowledge)
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
    while (true) {
      see().foreach(s => {
        think(s)
      })
      act()
      handleMessages()
    }
  }
}

