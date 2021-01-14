package example
package epistemicLogicExamples
package RomeoJuliet

import lib.EpistemicLogic._
import lib.EpistemicLogic.EpistemicLogicCommon._

import lib.Bot.MessengerBot
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._
import meta.runtime.Actor.AgentId

@lift
class Juliet(val lover: Romeo) extends Person {

  val k: KnowledgeBase = new KnowledgeBase
  // A person is consistent
  k.default()

  k.remember(Set(P(Romantic()), P(NotInLove())))

  private def goDance(): Unit = {
    if (Random.nextBoolean() && k.know(P(NotInLove()))) {
      println("Juliet falls in love!")
      k.forget(Set(P(NotInLove())))
      k.remember(Set(P(Lover(id, lover.id))))
    }
  }

  // Juliet can't say no to her father's order
  def arrangeMarriage(proposer: AgentId): Unit = {
    println("Juliet is arranged into marriage!")
    k.remember(Set(P(ArrangeMarriage(proposer))))
//    getNews(P(ArrangeMarriage(proposer))) // clumsy
  }

  // Juliet gets news periodically
  private def getNews(arranged: P[ArrangeMarriage]): Unit = {
    if (!k.know(P(NotInLove))) {
      val proposer: AgentId = arranged.proposition.a
      val loverId: AgentId = lover.id   // clumsy

      if (proposer != loverId) {
        println("Juliet learnt that she couldn't be together with her lover!")
        k.remember(Set(Ka(id, P(NotTogether))))
        if (lover.confess(P(Lover(id, loverId))) &&
          k.know(P(Romantic())) &&
          lover.runAway(P(RunAway(id, loverId)))
        ) {
          k.know(P(RunAway(id, loverId)))
          println("Juliet runs away with her lover!")
        }
      } else {
        println("Juliet is happy!")
      }
    }
  }

  def main(): Unit = {
    while (true) {
      handleMessages()
      goDance()
      waitLabel(Turn, 5)
    }
  }
}
