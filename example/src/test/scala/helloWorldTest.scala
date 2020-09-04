import org.scalatest.FlatSpec

class helloWorldTest extends FlatSpec {

  import generated.meta.example.helloWorld._
  import generated.simulation.{Simulation, SimulationSpark, SimulationConfig => Config}

  "Introduce" should "happen" in {
    Simulation.run(Config(InitData.initActors, totalTurn = 6))
  }

  "Spark simulation" should "work fine" in {
    SimulationSpark.run(Config(InitData.initActors, totalTurn = 6))
  }
}

