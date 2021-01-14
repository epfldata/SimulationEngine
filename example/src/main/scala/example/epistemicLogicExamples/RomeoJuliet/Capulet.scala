package example
package epistemicLogicExamples
package RomeoJuliet

import lib.EpistemicLogic._
import lib.EpistemicLogic.EpistemicLogicCommon._

import lib.Bot.MessengerBot
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

// Juliet's father
@lift
class Capulet extends Person {
  var daughter: Juliet = null

  val k: KnowledgeBase = new KnowledgeBase
  // A person is consistent
  k.default()

  k.remember(Set(NotE(P("Juliet is married"))))

  val arbitraryTime: Int = Random.nextInt(10) + 1

  def arrangeMarriage(bg: P[Propose]): Boolean = {
    if (k.know(P("Juliet is married"))) {
      false
    } else {
      if (bg.proposition.rich && bg.proposition.powerful) {

        println("Great, I will allow you to marry Juliet!")

        k.forget(Set(NotE(P("Juliet is married"))))
        k.remember(Set(P("Juliet is married")))

        daughter.arrangeMarriage(bg.proposition.a)
        true
      } else {    // wait for the next proposer
        false
      }
    }
  }

  def main(): Unit = {
    while (true) {
      handleMessages()
      waitLabel(Turn, arbitraryTime)
    }
  }
}
