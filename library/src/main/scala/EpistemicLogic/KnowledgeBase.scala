package library.EpistemicLogic
import Sentence._

object KnowledgeBase {
  type Rule = EpistemicSentence => Boolean
}

// Consider making EpistemicSentence a type parameter
class KnowledgeBase {
  import KnowledgeBase._
  var knowledgeBase: Set[EpistemicSentence] = Set()
  var constraints: List[EpistemicSentence => Boolean] = List()

  var learningProcess: Map[Int, Set[EpistemicSentence]] = Map[Int, Set[EpistemicSentence]]()

  def getKnowledgeBase: Set[EpistemicSentence] = knowledgeBase

  def addConstraints(x: => EpistemicSentence => Boolean): Unit = {
    constraints = x :: constraints
  }

  def default(): Unit = {
    // consistent
    addConstraints(x => !know(Solver.deduce(NotE(x), knowledgeBase)))
  }

  def recordLearning(epoch: Int, knowledge: Set[EpistemicSentence]): Unit = {
    val learnedKnowledge: Set[EpistemicSentence] = learn(knowledge)
    val learnt: Set[EpistemicSentence] = learningProcess.getOrElse(epoch, Set())
    learningProcess += (epoch -> learnt.union(learnedKnowledge))
    remember(learnedKnowledge)
  }

  // return what can be learned from the input
  def learn(newKnowledge: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    var filteredKnowledge: Set[EpistemicSentence] = newKnowledge
    for (c <- constraints) {
      filteredKnowledge = filteredKnowledge.filter(k => c(k))
    }
    Solver.deduction(filteredKnowledge.union(knowledgeBase)).diff(knowledgeBase)
  }

  // remember records the new knowledge to the knowledgeBase
  def remember(newKnowledge: Set[EpistemicSentence]): Unit = {
    knowledgeBase = newKnowledge.union(knowledgeBase)
  }

  // learn with a specified rule
  def ruleBasedLearn(newKnowledge: Set[EpistemicSentence], rule: Rule): Unit = {
    learn(newKnowledge.filter(k => rule(k)))
  }

  def know(sentence: EpistemicSentence): Boolean = {
    knowledgeBase.contains(sentence)
  }

  def ruleBasedKnow(rule: Rule): Set[EpistemicSentence] = {
    knowledgeBase.filter(e =>
      rule(e))
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

  def printLearningProcess(epoch: Int): Unit = {
    println("Epoch " + epoch + " learned: ")
    learningProcess.getOrElse(epoch, Set()).foreach(e =>
      println(e)
    )
  }
}