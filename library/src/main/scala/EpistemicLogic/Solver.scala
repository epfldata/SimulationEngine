package lib.EpistemicLogic

import EpistemicLogicCommon._

object Solver {

  // add to the set knowledge base new inferred knowledge axioms
  private def applyKnowledgeAxiom(kb: Set[EpistemicSentence]): Set[EpistemicSentence] = {

    val inferredKB: Set[EpistemicSentence] = kb.flatMap(x => {
      x match {
        case Ka(id, e1) => Set[EpistemicSentence](e1)
        case _ => Set[EpistemicSentence]()
      }
    })

    inferredKB.toList match {
      case Nil => kb
      case _ => kb ++ inferredKB ++ applyKnowledgeAxiom(inferredKB)
    }
  }

  private def commutation(kb: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    val inferredKB: Set[EpistemicSentence] = kb.flatMap(x => {
      x match {
        // commutation: p or q = q or p
        case Ka(i, ImpE(NotE(e1), e2)) => Set[EpistemicSentence](Ka(i, ImpE(NotE(e2), e1)))
        case ImpE(NotE(e1), e2) => Set[EpistemicSentence](ImpE(NotE(e2), e1))
        // p and q = q and p
        case Ka(i, NotE(ImpE(e1, NotE(e2)))) => Set[EpistemicSentence](Ka(i, NotE(ImpE(NotE(e2), e1))))
        case NotE(ImpE(e1, NotE(e2))) => Set[EpistemicSentence](NotE(ImpE(NotE(e2), e1)))
        case _ => Set[EpistemicSentence]()
      }
    })

    kb ++ inferredKB
  }

  // eliminate conjunction
  private def elimConjunction(kb: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    kb ++ (kb.filter(x => x.isInstanceOf[AndE])
      .flatMap(x => Set(x.asInstanceOf[AndE].e1, x.asInstanceOf[AndE].e2)))
  }

  // eliminate conjunction, apply commutation, apply knowledge axiom
  private def preproc(kb: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    applyKnowledgeAxiom(commutation(elimConjunction(kb)))
  }

  // EpistemicSentence rather than Set[EpistemicSentence]: recursively deduce
  def deduce(target: EpistemicSentence, kb: Set[EpistemicSentence]): EpistemicSentence = {
    target match {
      case NotE(NotE(e)) => e

      case Ka(i, ImpE(e1, e2)) if kb.contains(Ka(i, e1)) => Ka(i, e2)
      case ImpE(e1, e2) if kb.contains(e1) => e2

      case Ka(i, AndE(a1, a2)) => AndE(Ka(i, a1), Ka(i, a2))
      case Ka(i, ImpE(NotE(x), AndE(a1, a2))) if kb.contains(Ka(i, deduce(NotE(a1), kb))) || kb.contains(Ka(i, deduce(NotE(a2), kb))) => Ka(i, x)
      case Ka(i, ImpE(NotE(AndE(a1, a2)), x)) if kb.contains(Ka(i, deduce(NotE(a1), kb))) || kb.contains(Ka(i, deduce(NotE(a2), kb))) => Ka(i, x)

      // x or y, where y = AndE()
      case ImpE(NotE(x), AndE(a1, a2)) if kb.contains(deduce(NotE(a1), kb)) || kb.contains(deduce(NotE(a2), kb)) => x
      case ImpE(NotE(AndE(a1, a2)), x) if kb.contains(deduce(NotE(a1), kb)) || kb.contains(deduce(NotE(a2), kb)) => x

      case Ka(i, ImpE(e1, e2)) if kb.contains(Ka(i, deduce(NotE(e2), kb))) => Ka(i, deduce(NotE(e1), kb))
      case ImpE(e1, e2) if kb.contains(deduce(NotE(e2), kb)) => deduce(NotE(e1), kb)

      case Ka(i, NotE(ImpE(e1, e2))) => AndE(Ka(i, e1), Ka(i, deduce(NotE(e2), kb)))
      case NotE(ImpE(e1, e2)) => AndE(e1, deduce(NotE(e2), kb))

      case Ka(i, e1) => Ka(i, deduce(e1, kb))
      case ImpE(x, y) => ImpE(deduce(x, kb), deduce(y, kb))

      case _ => target
    }
  }

  // deduce the knowledge base until no more to add
  def deduction(kb: Set[EpistemicSentence]): Set[EpistemicSentence] = {

    var base: Set[EpistemicSentence] = Set[EpistemicSentence]()
    base = preproc(kb)
    var deduced: Set[EpistemicSentence] = base.map(t => deduce(t, base)).diff(base)
    deduced = preproc(deduced)

    while (!deduced.isEmpty) {
      base = base.union(deduced)
      deduced = base.map(t => deduce(t, base)).diff(base)
      deduced = preproc(deduced)
    }
    base
  }

  def speculate(hypothesis: Set[EpistemicSentence], knowledgeBase: Set[EpistemicSentence]): Set[EpistemicSentence] = {
    deduction(hypothesis ++ knowledgeBase).diff(knowledgeBase)
  }
}
