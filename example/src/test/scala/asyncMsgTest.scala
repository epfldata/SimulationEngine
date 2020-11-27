import org.scalatest.FlatSpec

class asyncMsgTest extends FlatSpec {

  import generated.simulation.{Simulation, SimulationSpark, SimulationConfig => Config}

  "Muddy children" should "run for both Simulation and SparkSimulation" in {
    import generated.example.muddy_children._
    Simulation.run(Config(InitData.initActors, totalTurn = 10))
    // SimulationSpark.run(Config(InitData.initActors))
  }

  "Rumor spread" should "run for both Simulation and SparkSimulation" in {
    import generated.example.rumor._
    Simulation.run(Config(InitData.initActors, totalTurn = 40))
    // SimulationSpark.run(Config(InitData.initActors))
  }

  "nb-method" should "run for both Simulation and SparkSimulation" in {
    import generated.example.nb_methods_example._
    Simulation.run(Config(InitData.initActors, totalTurn = 15))
    // SimulationSpark.run(Config(InitData.initActors))
  }
}