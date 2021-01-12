package lib.EpistemicLogic
package test

import org.scalatest.FlatSpec
import lib.EpistemicLogic.EpistemicLogicCommon._

class KnowledgeBaseSpec extends FlatSpec {

  case class fact(s: String)

  val p1: EpistemicSentence = P[fact](fact("Sun rises from the East"))
  val p2: EpistemicSentence = P[fact](fact("Earth is flat"))
  val p3: EpistemicSentence = NotE(p2)

  "No constraint" should "return a knowledgeBase without consistency guarantee" in {
    val kb: KnowledgeBase = new KnowledgeBase()
    val k1 = kb.learn(Set(p1, p3))
    kb.remember(k1)
    val k2 = kb.learn(Set(NotE(p1), p2))
    kb.remember(k2)

    assert(kb.know(NotE(p1)))
    assert(kb.know(p1))
    assert(kb.know(p2))
//    assert(kb.know(AndE(p1, NotE(p1))))
  }

  "Default constraint" should "return a consistent knowledgeBase" in {
    val kb: KnowledgeBase = new KnowledgeBase()
    kb.default()

    val k1 = kb.learn(Set(p1, p3))
    kb.remember(k1)
    val k2 = kb.learn(Set(NotE(p1), p2))
    kb.remember(k2)

    assert(!kb.know(NotE(p1)))
    assert(!kb.know(p2))
  }

  def whatIKnowAboutAnother(id: Int)(e: EpistemicSentence): Boolean = {
    e.isInstanceOf[Ka[Int]] && e.asInstanceOf[Ka[Int]].agentId == id
  }

  "knowledgeAboutAnother" should "return the known propositions about another agent" in {
    val e1: EpistemicSentence = Ka[Int](1, P("Time 5"))
    val e2: EpistemicSentence = Ka[Int](1, P("Time 6"))

    val kb: KnowledgeBase = new KnowledgeBase()
    kb.default()
    kb.remember(kb.learn(Set(e1, e2)))

    assert(kb.ruleBasedKnow(whatIKnowAboutAnother(1)).size == 2)
  }

  "Add new constraint" should "apply the new constraints over the knowledge base" in {

    val e1: EpistemicSentence = Ka(1, P("Time 5 unit"))
    val e2: EpistemicSentence = Ka(1, P("Time 6 unit"))
    val e3: EpistemicSentence = Ka(5, P("Time 5 unit"))
    val e4: EpistemicSentence = Ka(5, P("Time 7 unit"))

    val kb: KnowledgeBase = new KnowledgeBase()
    kb.default()

    def getTime(s: String): Int = {
      val prefix: String = "Time "
      val posfix: String = " unit"
      s.slice(s.indexOf(prefix) + prefix.length, s.indexOf(posfix)).toInt
    }

    kb.addConstraints(x => {
      x match {
        case Ka(i, p) => kb.ruleBasedKnow(whatIKnowAboutAnother(i.asInstanceOf[Int])).toList match {
          case Nil => true
          case y :: l =>
            getTime(y.toString) < getTime(x.toString)
        }
        case _ => true
      }
    })

    assert(kb.constraints.length == 2)

    val k1 = kb.learn(Set(e2))
    kb.remember(k1)
    val k2 = kb.learn(Set(e1))   // should not modify the state of the knowledge
    kb.remember(k2)
    val k3 = kb.learn(Set(e3, e4))
    kb.remember(k3)

    assert(!kb.know(e1))
    assert(kb.know(e3))
    assert(kb.know(e4))
  }
}
