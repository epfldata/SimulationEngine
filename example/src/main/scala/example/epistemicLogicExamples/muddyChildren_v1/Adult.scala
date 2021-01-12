package example
package epistemicLogicExamples
package MuddyChildren_v1

import lib.EpistemicLogic._
import lib.EpistemicLogic.EpistemicLogicCommon._

import lib.Bot.MessengerBot

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Adult(val children: List[Child]) extends Actor {

  val knowledgeBase: KnowledgeBase = new KnowledgeBase()
  knowledgeBase.default()

  val allChildrenMuddy: ListBuffer[EpistemicSentence] = new ListBuffer[EpistemicSentence]()
  val allChildrenUnaware: ListBuffer[EpistemicSentence] = new ListBuffer[EpistemicSentence]()

  private def see(): Unit = {
    knowledgeBase.forgetAll()

    val messenger: MessengerBot = new MessengerBot()
    val wReply: List[Option[Future[P[ChildStatus]]]] = children.map(c => asyncMessage(() => c.tell()))
    val ans: List[P[ChildStatus]] = messenger.waitUntilAllReply(wReply).asInstanceOf[List[P[ChildStatus]]]

    ans.foreach(c => {
      val f: EpistemicSentence = P(ChildMuddy(c.proposition.id, c.proposition.isMuddy))
      if (c.proposition.isForward){
        knowledgeBase.remember(Set(f, Ka(c.proposition.id, f)))
      } else {
        knowledgeBase.remember(Set(f, NotE(Ka(c.proposition.id, f))))
      }
    })
  }

  // Ask all children simultaneously, and wait for all children to answer
  private def ask(): Unit = {
    val messenger: MessengerBot = new MessengerBot()
    val wReceive: List[Option[Future[Unit]]] = children.map(c => asyncMessage(() => c.answer()))
    messenger.waitUntilAllReceive(wReceive)
  }

  def main(): Unit = {
    children.foreach(c => {
      allChildrenMuddy.append(P(ChildMuddy(c.id)))
      allChildrenUnaware.append(NotE(Ka(c.id, P(ChildMuddy(c.id, c.isMuddy)))))
    })

    while (true) {
      see()
      if (knowledgeBase.knowAny(allChildrenMuddy.toSet)) {
        while (knowledgeBase.knowAny(allChildrenUnaware.toSet)) {
          println("There is at least one muddy child.")
          println("Step up if you know whether you are clean or muddy")
          ask()
          see()
        }
      }
      waitLabel(Turn,1)
    }
  }
}
