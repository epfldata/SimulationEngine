package lib
package EpistemicLogic

object EpistemicLogicCommon {

  sealed trait EpistemicSentence extends Product with Serializable

  case class P[T](proposition: T) extends EpistemicSentence {
    override def toString(): String = {
      "(Proposition '" + proposition + "')"
    }
  }

  case class NotE(e: EpistemicSentence) extends EpistemicSentence {
    override def toString(): String = {
      "(\u00ac" + e.toString() + ")"
    }
  }

  // and: not (a -> not b)
  case class AndE(e1: EpistemicSentence, e2: EpistemicSentence) extends EpistemicSentence {
    override def toString(): String = {
      "(and " + e1.toString() + " " + e2.toString() + ")"
    }
  }

  // or: not a -> b
  case class OrE(e1: EpistemicSentence, e2: EpistemicSentence) extends EpistemicSentence {
    override def toString(): String = {
      "(or " + e1.toString() + " " + e2.toString() + ")"
    }
  }

  case class ImpE(e1: EpistemicSentence, e2: EpistemicSentence) extends EpistemicSentence {
    override def toString(): String = {
      "(imply " + e1.toString() + " " + e2.toString() + ")"
    }
  }

  case class Ka[T](agentId: T, e: EpistemicSentence) extends EpistemicSentence {
    override def toString(): String = {
      "(Agent " + agentId + " knows " + e.toString() + ")"
    }
  }

  //  case class Ba(agentId: Int, e: EpistemicSentence) extends EpistemicSentence

  def ors(es: List[EpistemicSentence]): EpistemicSentence = {
    es.tail.foldLeft(es.head)((x, y) => ImpE(NotE(x), y))
  }

  def ands(es: List[EpistemicSentence]): EpistemicSentence = {
    //    es.tail.foldRight(es.head)((x, y) => NotE(ImpE(x, NotE(y))))
    es.tail.foldLeft(es.head)((x, y) => AndE(x, y))
  }
}
