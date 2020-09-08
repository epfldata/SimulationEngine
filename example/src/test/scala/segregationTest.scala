import breeze.numerics.abs
import org.scalatest.{FlatSpec, Matchers}
import meta.example.segregation.Torus2D

class segregationTest extends FlatSpec with Matchers {

  case class torusTest(var width: Int, var height: Int) extends Torus2D

  val w1: Int = 3
  val h1: Int = 3

  an [IndexOutOfBoundsException] should be thrownBy torusTest(w1, h1).neighborCells(w1 * h1, 1)

  "neighborCells" should "return the neighbors of the given radius" in {
    // neighbor 4
//    torusTest(w1, h1).neighborCells(0, 1) shouldBe Set(1, 2, 3, 6)
//    torusTest(w1, h1).neighborCells(4, 1) shouldBe Set(1, 3, 5, 7)
//    torusTest(5, 3).neighborCells(7, 2) shouldBe Set(2, 6, 7, 8, 12, 1, 5, 11, 13, 3, 9)
      torusTest(w1, h1).neighborCells(0, 1) shouldBe Set(1, 2, 3, 4, 5, 6, 7, 8)
      torusTest(w1, h1).neighborCells(4, 1) shouldBe Set(0, 1, 2, 3, 5, 6, 7, 8)
  }

  import _root_.generated.simulation.{SimulationConfig => Config, _}
  import generated.meta.example.segregation
  import meta.example.recordLogEvents
  import ch.qos.logback.classic.Level._

  "The similarity level of the segregation model" should "converge to around 0.85" in {
    val totalTurns: Int = 70
    val last10 = recordLogEvents.record({
      Simulation.run(Config(segregation.InitData.initActors, 0, totalTurns, 0, 0, false))
    }).filter(e => e.getLevel().equals(INFO)).map(i => i.getFormattedMessage.stripMargin.toDouble).drop(totalTurns-10)
    assert(abs(last10.sum / last10.length - 0.85) < 0.03)
  }

  "The similarity level of the segregation model" should "converge to around 0.85, Spark" in {
    val totalTurns: Int = 70
    val last10 = recordLogEvents.record({
      SimulationSpark.run(Config(segregation.InitData.initActors, 0, totalTurns, 0, 0, false))
    }).filter(e => e.getLevel().equals(INFO)).map(i => i.getFormattedMessage.stripMargin.toDouble).drop(totalTurns-10)
    assert(abs(last10.sum / last10.length - 0.85) < 0.03)
  }
}