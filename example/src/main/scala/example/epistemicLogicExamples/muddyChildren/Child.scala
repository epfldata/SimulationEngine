package example
package epistemicLogicExamples
package MuddyChildren

import MCHelper._
import lib.EpistemicLogic._
import lib.Bot.MessengerBot

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Child(val isMuddy: Boolean) extends Actor {

  var neighbors: List[Child] = Nil
  var neighborIds: List[Actor.AgentId] = Nil

  var epoch: Int = 0
  var isForward: Boolean = false

  val knowledgeBase: KnowledgeBase = new KnowledgeBase()
  knowledgeBase.default()

  // RPC
  def tell(): P[ChildStatus] = {
    P(ChildStatus(id, isMuddy, isForward, epoch))
  }

  // Child won't answer if there are neighbors they haven't seen
  // Children answers simultaneously; can't change one's answer based on observing the same round behaviour
  // RPC
  def answer(): Unit = {
    if (knowledgeBase.know(seeAllNeighbor())) {
      epoch = epoch + 1
      knowledgeBase.recordLearning(epoch, Set(P(HearParent(epoch))))
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
  }

  // non-RPC
  def think(c: P[ChildStatus]): Unit = {
    val announce: EpistemicSentence = P(HearParent(epoch))
    // A child thinks only after hearing from the parent in this epoch
    // If the observation has an earlier epoch, than ignore it because already seen it
    if (knowledgeBase.know(announce) && !isForward && c.proposition.epoch == epoch) {
      val ans: Set[EpistemicSentence] = if (c.proposition.isForward) {
        val f: EpistemicSentence = if (c.proposition.isMuddy) {
          P(ChildMuddy(c.proposition.id))
        }else {
          NotE(P(ChildMuddy(c.proposition.id)))
        }
        knowledgeBase.recordLearning(epoch, Set(Ka(c.proposition.id, f)))
        counterExampleLearning(knowledgeBase.speculate(Set(Ka(c.proposition.id, NotE(f)))))
      } else {
        inferOtherAgent(c.proposition.id, (id :: neighborIds.toList).filterNot(x => x == c.proposition.id), epoch)
      }
      knowledgeBase.recordLearning(epoch, ans)
    }
  }

  // non-RPC
  def see(): List[P[ChildStatus]] = {
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
//      println("==== Agent " + id + " knows epochs " + knowledgeBase.learningProcess.keys + " ==== ")
//      knowledgeBase.printLearningProcess(epoch)
      handleMessages()
    }
  }
}

