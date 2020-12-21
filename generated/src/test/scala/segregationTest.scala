import breeze.numerics.abs
import org.scalatest.{FlatSpec, Matchers}

class segregationTest extends FlatSpec with Matchers {

  import generated.simulation.{SimulationConfig => Config, _}
  import generated.example.segregation
  import meta.example.recordLogEvents
  import ch.qos.logback.classic.Level._

  "The similarity level of the segregation model" should "converge to around 0.85" in {
    val totalTurns: Int = 70
    val Sim = new Simulation(Config(segregation.InitData.initActors, 0, totalTurns, 0, 0))
    val last10 = recordLogEvents.record({
      Sim.run()
    }).filter(e => e.getLevel().equals(INFO)).map(i => i.getFormattedMessage.stripMargin.toDouble).drop(totalTurns-10)
    assert(abs(last10.sum / last10.length - 0.85) < 0.03)
  }

//  "The similarity level of the segregation model" should "converge to around 0.85, Spark" in {
//    val totalTurns: Int = 70
//    val last10 = recordLogEvents.record({
//      SimulationSpark.run(Config(segregation.InitData.initActors, 0, totalTurns, 0, 0))
//    }).filter(e => e.getLevel().equals(INFO)).map(i => i.getFormattedMessage.stripMargin.toDouble).drop(totalTurns-10)
//    assert(abs(last10.sum / last10.length - 0.85) < 0.03)
//  }
}
