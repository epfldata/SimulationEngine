package lib.EpistemicLogic

import EpistemicLogicCommon._

object KnowledgeBase {
  type Rule = EpistemicSentence => Boolean
}

class KnowledgeBase extends Serializable {
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

  // learn with a specified rule
  def ruleBasedLearn(newKnowledge: Set[EpistemicSentence], rule: Rule): Set[EpistemicSentence] = {
    learn(newKnowledge.filter(k => rule(k)))
  }

  // remember records the new knowledge to the knowledgeBase
  def remember(newKnowledge: Set[EpistemicSentence]): Unit = {
    knowledgeBase = newKnowledge.union(knowledgeBase)
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

  def knowAnyType[T](typeTemplate: String): Set[EpistemicSentence] = {
//    knowledgeBase.filter(p => {
//      println("P is " + p + " " + p.isInstanceOf[P[T]])
//      p.isInstanceOf[P[T]]
//    })
//    knowledgeBase.flatMap(p =>
//      p match {
//        case p: P[T] =>
//            println("P is " + p); Set(p)
//        case _: EpistemicSentence => Set()
//      }
//    )
    knowledgeBase.filter(p => p.toString.contains(typeTemplate))
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

  def printLearningProcess(epoch: Int): Unit = {
    println("Epoch " + epoch + " learned: ")
    learningProcess.getOrElse(epoch, Set()).foreach(e =>
      println(e)
    )
  }
}