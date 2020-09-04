import org.scalatest.FlatSpec

class statelessServerTest extends FlatSpec {

    import generated.meta.example.stateless_server._
    import generated.simulation.{Simulation, SimulationSpark, SimulationConfig => Config}

  "Simulation" should "run" in {
    Simulation.run(Config(InitData.initActors, totalTurn = 10))
  }

  "Spark simulation" should "work fine" in {
    SimulationSpark.run(Config(InitData.initActors, totalTurn = 10))
  }
}
