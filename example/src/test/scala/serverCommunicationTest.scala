import org.scalatest.FlatSpec

class serverCommunicationTest extends FlatSpec {

  import generated.serverCommunication._
  import generated.simulation.{Simulation, SimulationSpark, SimulationConfig => Config}

  "Backend server's heartbeat message" should "appear at each turn" in {
    Simulation.run(Config(InitData.initActors, totalTurn = 5))
  }

  "Spark simulation" should "work fine" in {
    SimulationSpark.run(Config(InitData.initActors, totalTurn = 5))
  }
}
