import org.scalatest.FlatSpec

class helloWorldExampleTest extends FlatSpec {

  import _root_.generated.simulation.{SimulationConfig => Config, _}
  import generated.meta.example.helloWorld

  import meta.example.recordLogEvents
  import ch.qos.logback.classic.Level._
  import meta.example.helloWorld.Person

  "Alice" should "greet at turn 3" in {
    val turn3 = recordLogEvents.record({
      Simulation.run(Config(helloWorld.InitData.initActors, 0, 3, 0, 0, false))
    }).filter(e => e.getLevel().equals(INFO))
    assert(turn3.map(i => i.getFormattedMessage).contains(Person.selfIntroduction("Alice")))
  }

  "Alice" should "greet at turn 3, Spark" in {
    val turn3 = recordLogEvents.record({
      SimulationSpark.run(Config(helloWorld.InitData.initActors, 0, 3, 0, 0, false))
    }).filter(e => e.getLevel().equals(INFO))
    assert(turn3.map(i => i.getFormattedMessage).contains(Person.selfIntroduction("Alice")))
  }

  "Eve and Alice" should "greet each other after Alice's introduction" in {
    val turn4 = recordLogEvents.record({
      Simulation.run(Config(helloWorld.InitData.initActors, 0, 4, 0, 0, false))
    }).filter(e => e.getLevel().equals(INFO))
    assert(turn4.map(i => i.getFormattedMessage).contains(Person.greetMessage("Alice", "Eve")))
    assert(turn4.map(i => i.getFormattedMessage).contains(Person.greetMessage("Eve", "Alice")))
  }

  "Eve and Alice" should "greet each other after Alice's introduction, Spark" in {
    val turn4 = recordLogEvents.record({
      SimulationSpark.run(Config(helloWorld.InitData.initActors, 0, 4, 0, 0, false))
    }).filter(e => e.getLevel().equals(INFO))
    assert(turn4.map(i => i.getFormattedMessage).contains(Person.greetMessage("Alice", "Eve")))
    assert(turn4.map(i => i.getFormattedMessage).contains(Person.greetMessage("Eve", "Alice")))
  }
}