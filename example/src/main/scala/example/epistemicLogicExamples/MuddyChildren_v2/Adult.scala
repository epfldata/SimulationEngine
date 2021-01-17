package example
package epistemicLogicExamples
package MuddyChildren_v2

import lib.EpistemicLogic._
import lib.EpistemicLogic.EpistemicLogicCommon._
import lib.Bot.MessengerBot
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import MuddyChildren_v2_util._
import MuddyChildren_v1.ChildMuddy

import scala.collection.mutable.Map

@lift
class Adult(val children: List[Child]) extends Actor {

  val knowledgeBase: KnowledgeBase = new KnowledgeBase()
  knowledgeBase.default()

  val allChildrenMuddy: ListBuffer[EpistemicSentence] = new ListBuffer[EpistemicSentence]()
  var existsUnawareChild: Boolean = true

  var childrenStatus: List[(Actor.AgentId, Boolean)] = List()

  var childrenIndexed: Map[Actor.AgentId, Child] = Map[Actor.AgentId, Child]()

  private def see(): Unit = {
    val messenger: MessengerBot = new MessengerBot()
    val wReply: List[Option[Future[P[ChildStatus]]]] = children.map(c => asyncMessage(() => c.tell()))
    val ans: List[P[ChildStatus]] = messenger.waitUntilAllReply(wReply).asInstanceOf[List[P[ChildStatus]]]

    ans.foreach(c => {
      val f: EpistemicSentence = P(ChildMuddy(c.proposition.id, c.proposition.isMuddy))
      knowledgeBase.remember(Set(f, NotE(Ka(c.proposition.id, f))))
    })
  }

  // Ask children simultaneously, and wait for all answers
  // Return whether each child is aware or not, indexed by their ids
  private def ask(): Option[List[(Actor.AgentId, Boolean)]] = {
    val messenger: MessengerBot = new MessengerBot()
    val wReceive: List[Option[Future[Option[(Actor.AgentId, Boolean)]]]] = children.map(c => asyncMessage(() => c.answer()))
    val replies: List[Option[(Actor.AgentId, Boolean)]] = messenger.waitUntilAllReply(wReceive).asInstanceOf[List[Option[(Actor.AgentId, Boolean)]]]
    if (replies.contains(None)) {
      None
    } else {
      Some(replies.map(x => x.get))
    }
  }

  // Tell children who is aware at the end of each round 
  private def announce(): Unit = {
    val messenger: MessengerBot = new MessengerBot()
    val wReceive: List[Option[Future[Unit]]] = children.map(c => asyncMessage(() => c.think(childrenStatus)))
    messenger.waitUntilAllReply(wReceive)
  }

  def main(): Unit = {
    children.foreach(c => {
      allChildrenMuddy.append(P(ChildMuddy(c.id)))
      childrenIndexed(c.id) = c
    })

    see()
    // println("Seen all children!")
    while (true) {
      if (knowledgeBase.knowAny(allChildrenMuddy.toSet)) {
        while (existsUnawareChild) {

          println("There is at least one muddy child.")
          println("Speak up if you know whether you are clean or muddy")

          val reply: Option[List[(Actor.AgentId, Boolean)]] = ask()

          if (reply.nonEmpty) {
            reply.get.foreach(x => if (x._2) {
              // If a child answers yes, then update the knowledgebase to show that s/he now knows
              knowledgeBase.forget(Set(NotE(Ka(x._1,
                P(ChildMuddy(x._1, childrenIndexed(x._1).isMuddy))))))
              knowledgeBase.remember(Set(Ka(x._1,
                P(ChildMuddy(x._1, childrenIndexed(x._1).isMuddy)))))
            })

            if (reply.get.exists(x => x._2 == false)) {
              childrenStatus = reply.get
              announce()
            } else {
              existsUnawareChild = false
            }
          } else {
            println("Empty reply!")
          }
          // println("Adults knowledge base: ")
          // knowledgeBase.knowledgeBase.foreach(println)
        }
      }
      waitLabel(Turn,1)
    }
  }
}
