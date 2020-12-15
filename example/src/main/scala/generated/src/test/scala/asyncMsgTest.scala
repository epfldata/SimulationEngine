import org.scalatest.FlatSpec


class asyncMsgTest extends FlatSpec {

  import generated.simulation.{Simulation, SimulationConfig => Config}

  "Rumor spread" should "run for both Simulation and SparkSimulation" in {
    import generated.example.rumor._
    val Sim = new Simulation(Config(InitData.initActors, totalTurn = 40))
    Sim.run()
  }

  "Group chat" should "gets repsonses in the next epoch" in {
    import generated.example.groupMessage._
    val Sim: Simulation = new Simulation(Config(InitData.initActors, totalTurn = 15))
    Sim.run()
  }

  "nb-method" should "run for both Simulation and SparkSimulation" in {
    import generated.example.nb_methods_example._
    val Sim = new Simulation(Config(InitData.initActors, totalTurn = 15))
    Sim.run()
  }
}