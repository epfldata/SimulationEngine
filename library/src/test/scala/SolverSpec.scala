package lib.EpistemicLogic
package test

import org.scalatest.FlatSpec
import lib.EpistemicLogic.EpistemicLogicCommon._

class SolverSpec extends FlatSpec {
  import Solver.deduction

  val p1: EpistemicSentence = P("Child 1 is muddy")
  val p2: EpistemicSentence = P("Child 2 is muddy")
  val p3: EpistemicSentence = P("Child 3 is muddy")
  val p4: EpistemicSentence = P("Child 4 is muddy")

  // announcement: at least one is muddy
  val ann: EpistemicSentence = ImpE(NotE(p2),ImpE(NotE(p3), p1))

  "double negate" should "get removed" in {
    assert(deduction(Set(ImpE(p2, NotE(NotE(NotE(p1)))))).contains((ImpE(p2, NotE(p1)))))
    assert(deduction(Set(NotE(NotE(ImpE(p2, NotE(NotE(NotE(p1)))))))).contains((ImpE(p2, NotE(p1)))))
    assert(deduction(Set(Ka(1, NotE(NotE(ImpE(p2, NotE(NotE(NotE(p1))))))))).contains(Ka(1, (ImpE(p2, NotE(p1))))))
    assert(deduction(Set(NotE(NotE(Ka(1, ImpE(p2, NotE(NotE(NotE(p1))))))))).contains(Ka(1, (ImpE(p2, NotE(p1))))))
  }

  "not (a -> b)" should "return a and not b" in {
    assert(deduction(Set(NotE(ImpE(p2, NotE(NotE(NotE(p1))))))).contains(p2))
    assert(deduction(Set(NotE(ImpE(p2, NotE(NotE(NotE(p1))))))).contains(p1))
  }

  "not a or b" should " return not a and not b" in {
    assert(deduction(Set(NotE(ImpE(NotE(p1), p2)))).contains(NotE(p1)))
    assert(deduction(Set(NotE(ImpE(NotE(p1), p2)))).contains(NotE(p2)))
  }

  "a or b or c and not c" should "return a or b and b or a" in {
    assert(deduction(Set(ann, NotE(p1))).contains((ImpE(NotE(p2),p3))))
    assert(deduction(Set(ann, NotE(p1))).contains((ImpE(NotE(p3),p2))))
  }

  "a or b or c and not b" should "return a or c and c or a" in {
    assert(deduction(Set(ann, NotE(p3))).contains((ImpE(NotE(p2),p1))))
    assert(deduction(Set(ann, NotE(p3))).contains((ImpE(NotE(p1),p2))))
  }

  "a or b or c and not a" should "return b or c and c or b" in {
    assert(deduction(Set(ann, NotE(p2))).contains(ImpE(NotE(p3),p1)))
    assert(deduction(Set(ann, NotE(p2))).contains(ImpE(NotE(p1),p3)))
  }

  "a or b or c and not b and not c" should "return a" in {
    assert(deduction(Set(NotE(p2), NotE(p3), ann)).contains(p1))
    assert(deduction(Set(NotE(p1), NotE(p3), ann)).contains(p2))
    assert(deduction(Set(NotE(p3), NotE(p2), ann)).contains(p1))
    assert(deduction(Set(NotE(p1), NotE(p2), ann)).contains(p3))
  }

  "deduction Ka" should "apply the knowledge axiom and then deduce" in {
    assert(deduction(Set(Ka(1, NotE(p2)), Ka(1, ann))).contains(Ka(1, ImpE(NotE(p3),p1))))
    assert(deduction(Set(Ka(1, NotE(p2)), Ka(1, ann))).contains(Ka(1, ImpE(NotE(p1),p3))))
  }

  "Knowledge axiom" should "deduction arbitrary nested expression" in {
    assert(deduction(Set(Ka(1, Ka(2, NotE(p3))))).contains(NotE(p3)))
  }

  "Nested knowledge axiom and modus ponens" should "work" in {
    assert(deduction(Set(Ka(2, Ka(1, NotE(p2))), Ka(2, Ka(1, ann)))).contains(Ka(2, Ka(1, ImpE(NotE(p3),p1)))))
    assert(deduction(Set(Ka(1, Ka(2, NotE(p2))), Ka(1, Ka(2, ann)))).contains(Ka(1, Ka(2, ImpE(NotE(p3),p1)))))
  }
}

