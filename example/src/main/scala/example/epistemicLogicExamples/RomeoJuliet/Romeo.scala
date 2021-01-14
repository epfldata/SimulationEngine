package example
package epistemicLogicExamples
package RomeoJuliet

import lib.EpistemicLogic._
import lib.EpistemicLogic.EpistemicLogicCommon._
import lib.Bot.MessengerBot

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Romeo() extends Person {

  val k: KnowledgeBase = new KnowledgeBase
  // A person is consistent
  k.default()

  k.remember(Set(P(Romantic()), P(NotInLove())))

  var lover: Juliet = null

  def runAway(request: P[RunAway]): Boolean = {
    if (request.proposition.a == lover.id) {
      println("Romeo runs away with his lover!")
      k.remember(Set(P(RunAway(id, lover.id))))
      true
    } else {
      false
    }
  }

  // proposer asks Romeo whether he also loves her
  def confess(p: P[Lover]): Boolean = {
    p.proposition.a == lover.id
  }

  private def goDance(): Unit = {
    if (Random.nextBoolean() && k.know(P(NotInLove()))) {
      println("Romeo falls in love!")
      k.forget(Set(P(NotInLove())))
      k.remember(Set(P(Lover(id, lover.id))))
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
