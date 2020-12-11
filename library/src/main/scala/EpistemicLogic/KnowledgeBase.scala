package library.EpistemicLogic
import Sentence._

object KnowledgeBase {
  type Rule = EpistemicSentence => Boolean
//  def genKnowledge[T](variable: T, schema: => T => EpistemicSentence): EpistemicSentence = {
//    schema(variable)
//  }
}

// Consider making EpistemicSentence a type parameter
class KnowledgeBase {
  import KnowledgeBase._
  var knowledgeBase: Set[EpistemicSentence] = Set()
  var constraints: List[EpistemicSentence => Boolean] = List()
  var thoughtProcess: Map[Int, Set[EpistemicSentence]] = Map[Int, Set[EpistemicSentence]]()

  def getKnowledgeBase: Set[EpistemicSentence] = knowledgeBase

  def addConstraints(x: => EpistemicSentence => Boolean): Unit = {
    constraints = x :: constraints
  }

  def default(): Unit = {
    // consistent
    addConstraints(x => !know(Solver.deduce(NotE(x), knowledgeBase)))
  }

  def recordThoughtProcess(epoch: Int, knowledge: Set[EpistemicSentence]): Unit = {
    thoughtProcess += (epoch -> knowledge)
  }

  // assume the knowledge has been checked consistency
  def learn(newKnowledge: Set[EpistemicSentence]): Unit = {
//    println("Learn " + newKnowledge)
    var filteredKnowledge: Set[EpistemicSentence] = newKnowledge
    for (c <- constraints) {
      filteredKnowledge = filteredKnowledge.filter(k => c(k))
    }
    knowledgeBase = Solver.deduction(filteredKnowledge.union(knowledgeBase))
  }

  // learn with a specified rule
  def ruleBasedLearn(newKnowledge: Set[EpistemicSentence], rule: Rule): Unit = {
    learn(newKnowledge.filter(k => rule(k)))
  }

  def know(sentence: EpistemicSentence): Boolean = {
    knowledgeBase.contains(sentence)
  }

  def knowAny(sentences: Set[EpistemicSentence]): Boolean = {
    knowledgeBase.diff(sentences) != knowledgeBase
  }

  // return inferred knowledge from the counterFacts without actually modifying the knowledge base
  def speculate(counterFacts: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    Solver.speculate(counterFacts, knowledgeBase)
  }

  // remove the outdated knowledge from the knowledge base
  def forget(oldKnowledge: Set[EpistemicSentence]): Unit = {
    knowledgeBase = knowledgeBase.diff(oldKnowledge)
  }

  def forgetAll(): Unit = {
    knowledgeBase = Set[EpistemicSentence]()
  }

  def replace(oldKnowledge: EpistemicSentence, newKnowledge: EpistemicSentence): Unit = {
    forget(Set(oldKnowledge))
    learn(Set(newKnowledge))
  }

  def knowledgeAboutAnother[T](another: T): Set[EpistemicSentence] = {
    knowledgeBase.filter(e =>
      e.isInstanceOf[Ka[T]] && e.asInstanceOf[Ka[T]].agentId == another)
  }
}