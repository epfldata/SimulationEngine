import org.scalatest.FlatSpec

class timeExampleTest extends FlatSpec {

  import _root_.generated.simulation.{SimulationConfig => Config, _}
  import generated.meta.example.time_example._
  import meta.example.recordLogEvents
  import ch.qos.logback.classic.Level._
  import meta.example.time_example.logInfo

  "time example" should "interrupt at real-time 5" in {
    val time5 = recordLogEvents.record({
      Simulation.run(Config(InitData.initActors, 0, 100, 0, 5.0))
    }).filter(e => e.getLevel.equals(INFO))

    assert(time5.map(e => e.getFormattedMessage).contains(logInfo.interruptMsg))
  }

  "time example" should "interrupt at real-time 5, spark implementation" in {
    val time5 = recordLogEvents.record({
      SimulationSpark.run(Config(InitData.initActors, 0, 100, 0, 5.0))
    }).filter(e => e.getLevel.equals(INFO))
    assert(time5.map(e => e.getFormattedMessage).contains(logInfo.interruptMsg))
  }
}