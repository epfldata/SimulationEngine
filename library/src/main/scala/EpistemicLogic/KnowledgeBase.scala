package library.EpistemicLogic
import Sentence._

// Consider making EpistemicSentence a type parameter
class KnowledgeBase {
  var knowledgeBase: Set[EpistemicSentence] = Set()

  def getKnowledgeBase: Set[EpistemicSentence] = knowledgeBase

  // assume the knowledge has been checked consistency
  def learn(newKnowledge: Set[EpistemicSentence]): Unit = {
    val consistentKnowledge: Set[EpistemicSentence] = newKnowledge.filterNot(k => know(NotE(k)))
    knowledgeBase = Solver.deduction(consistentKnowledge.union(knowledgeBase))
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
