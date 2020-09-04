import org.scalatest.FlatSpec

class timeTest extends FlatSpec {

  import generated.meta.example.time_example._
  import generated.simulation.{Simulation, SimulationSpark, SimulationConfig => Config}

  "Interrupt" should "happen at time 5" in {
    Simulation.run(Config(InitData.initActors, totalTurn = 100, totalTime = 6))
  }

  "Spark simulation" should "work fine" in {
    SimulationSpark.run(Config(InitData.initActors, totalTime = 6))
  }
}

