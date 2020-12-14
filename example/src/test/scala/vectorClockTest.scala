import example.epistemicLogicVC.VCHelper.ProcessTime
import lib.EpistemicLogic.Sentence.P
import org.scalatest.FlatSpec

class vectorClockTest extends FlatSpec {
  import example.epistemicLogicVC.VectorClock

  val p1: ProcessTime = ProcessTime(1, 1)
  val p2: ProcessTime = ProcessTime(1, 2)
  val p3: ProcessTime = ProcessTime(1, 3)

  val vectorClock: VectorClock = new VectorClock()
  vectorClock.default()
  vectorClock.addConstraints(x => {
    x.isInstanceOf[P[ProcessTime]] &&
      vectorClock.timingRule(x.asInstanceOf[P[ProcessTime]])
  })

  "Timing constraint of VectorClock" should "prevent smaller value from overwriting the existing time info" in {
    vectorClock.remember(Set(P(p3)))
    assert(vectorClock.learn(Set(P(p1))).isEmpty)
  }

  "Learning a time larger than existing one" should "succeed" in {
    vectorClock.forgetAll()
    vectorClock.remember(Set(P(p2)))
    assert(vectorClock.learn(Set(P(p3))) == Set(P(p3)))
  }

  "UpdateTimingInfo" should "forget the outdated value and learn the new one" in {
    vectorClock.forgetAll()
    vectorClock.remember(Set(P(p2)))
    vectorClock.updateTimingInfo(P(p3))
    assert(vectorClock.know(P(p3)))
    assert(!vectorClock.know(P(p2)))
  }
}
