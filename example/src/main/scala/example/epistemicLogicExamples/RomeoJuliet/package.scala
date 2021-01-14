package example
package epistemicLogicExamples

import lib.EpistemicLogic._
import lib.EpistemicLogic.EpistemicLogicCommon._

package object RomeoJuliet {
  import meta.runtime.Actor.AgentId

  case class Romantic() {
    override def toString: String = "I am romantic"
  }

  case class NotInLove() {
    override def toString: String = "I am not in love"
  }

  case class Die(a: AgentId) {
    override def toString: String = s"Person $a is died"
  }

  case class RunAway(a: AgentId, b: AgentId) {
    override def toString: String = s"$a and $b run away!"
  }

  case class Propose(a: AgentId, rich: Boolean, powerful: Boolean) {
    override def toString: String = s"I want to marry your daughter and I am rich: $rich and powerful: $powerful"
  }

  case class ArrangeMarriage(a: AgentId) {
    override def toString: String = s"I have an arranged marriage with $a"
  }

  case class Lover(a: AgentId, b: AgentId) {
    override def toString: String = s"$a is in love with $a"
  }

  case class NotTogether() {
    override def toString: String = "I can't be with my lover"
  }

  def getLover(k: KnowledgeBase): List[AgentId] = {
    val lover: Set[P[Lover]] = k.knowAnyType[Lover](" in love with ").asInstanceOf[Set[P[Lover]]]
    if (lover.size!=0) {
      lover.toList.map(x => x.proposition.a)
    } else {
      List()
    }
  }

  def getArrangedPartner(k: KnowledgeBase): List[AgentId] = {
    val partner: Set[P[ArrangeMarriage]] = k.knowAnyType[ArrangeMarriage](" have an arranged marriage with ").filter(x => x.isInstanceOf[P[ArrangeMarriage]]).asInstanceOf[Set[P[ArrangeMarriage]]]
    if (partner.size!=0) {
      partner.toList.map(x => x.proposition.a)
    } else {
      List()
    }
  }

  def getDesiredBackground(k: Set[EpistemicSentence], keyword: String): Set[EpistemicSentence] = {
    k.filter(x => x.toString.contains(keyword))
  }

  // If a person is romantic and can't be be with the lover, then s/he would die for love

  trait Person extends Actor
}
