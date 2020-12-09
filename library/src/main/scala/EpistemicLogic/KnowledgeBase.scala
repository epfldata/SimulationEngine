package library.EpistemicLogic
import Sentence._

// Consider making EpistemicSentence a type parameter
class KnowledgeBase {
  var knowledgeBase: Set[EpistemicSentence] = Set()

  var constraints: List[EpistemicSentence => Boolean] = List()

  def getKnowledgeBase: Set[EpistemicSentence] = knowledgeBase

  def addConstraints(x: => EpistemicSentence => Boolean): Unit = {
    constraints = x :: constraints
  }

  def default(): Unit = {
    // consistent
    addConstraints((x) => (!know(Solver.deduce(NotE(x), knowledgeBase))))
  }

  // assume the knowledge has been checked consistency
  def learn(newKnowledge: Set[EpistemicSentence]): Unit = {
    var filteredKnowledge: Set[EpistemicSentence] = newKnowledge
    for (c <- constraints) {
      filteredKnowledge = filteredKnowledge.filter(k => c(k))
    }
    knowledgeBase = Solver.deduction(filteredKnowledge.union(knowledgeBase))
  }

  def know(sentence: EpistemicSentence): Boolean = {
    knowledgeBase.contains(sentence)
  }

  // return inferred knowledge from the counterFacts without actually modifying the knowledge base
  def speculate(counterFacts: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    Solver.speculate(counterFacts, knowledgeBase)
  }

  // remove the outdated knowledge from the knowledge base
  def forget(oldKnowledge: Set[EpistemicSentence]): Unit = {
    knowledgeBase = knowledgeBase.diff(oldKnowledge)
  }

  def replace(oldKnowledge: EpistemicSentence, newKnowledge: EpistemicSentence): Unit = {
    forget(Set(oldKnowledge))
    learn(Set(newKnowledge))
  }

  def knowledgeAboutAnother[T](another: T): Set[EpistemicSentence] = {
    knowledgeBase.filter(e =>
      (e.isInstanceOf[Ka[T]] && e.asInstanceOf[Ka[T]].agentId == another))
  }
}