import org.scalatest.FlatSpec

class codegenTest extends FlatSpec {

  import generated.meta.example.codegen_example._
  import _root_.generated.simulation.{Simulation, SimulationSpark, SimulationConfig => Config}

  "Codegen" should "run" in {
    Simulation.run(Config(InitData.initActors, totalTurn = 10))
  }

  "Spark simulation" should "work fine" in {
    SimulationSpark.run(Config(InitData.initActors, totalTurn = 6))
  }
}

