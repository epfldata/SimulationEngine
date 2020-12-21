import org.scalatest.FlatSpec

class EpistemicLogicExampleSpec extends FlatSpec {

  import generated.simulation.{SimulationConfig => Config, _}

  "Muddy children (5, 3)" should "muddy children realize after two rounds, clean one in the following" in {
    import generated.example.epistemicLogicMC._
    val Sim = new Simulation(Config(InitData.initActors, totalTurn = 35))
    Sim.run()
  }

  "Vector clock example" should "always has consistent view of global clock" in {
    import generated.example.epistemicLogicVC._
    val Sim = new Simulation(Config(InitData.initActors, totalTurn = 10))
    Sim.run()
  }
}
