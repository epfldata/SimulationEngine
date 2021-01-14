package example
package epistemicLogicExamples
package RomeoJuliet

import lib.EpistemicLogic._
import lib.EpistemicLogic.EpistemicLogicCommon._

import lib.Bot.MessengerBot
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

// Propose to Juliet
@lift
class Count extends Person {

  val k: KnowledgeBase = new KnowledgeBase
  // A person is consistent
  k.default()

  val bg: Set[EpistemicSentence] = Set(
    P("My family is rich"),
    P("My family is powerful"))

  k.remember(bg)

  var lord: Capulet = null

  def background(): Set[EpistemicSentence] = {
    println("Capulet asks about my background")
    k.knowledgeBase
  }

  def main(): Unit = {
    while(true) {
      println("Count proposes to Lord Capulet for marrying Juliet")
      val getMarry: Boolean = lord.arrangeMarriage(P(Propose(id, true, true)))

      if (getMarry) {
        k.remember(Set(P("I am getting married")))
        println("I am getting married! ")
      } else {
        println("My proposal was rejected!")
      }

      waitLabel(Turn, 50)
    }
  }
}
