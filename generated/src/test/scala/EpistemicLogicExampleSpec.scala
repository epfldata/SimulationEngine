package generated.example
package test

class EpistemicLogicExampleSpec extends FlatSpec {

  "Muddy children (5, 3)" should "muddy children realize after two rounds, clean one in the following" in {
    import epistemicLogicMC._
    val Sim = new Simulation(new Config(InitData.initActors, totalTurn = 35))
    Sim.run()
  }

  "Vector clock example" should "always has consistent view of global clock" in {
    import epistemicLogicVC._
    val Sim = new Simulation(new Config(InitData.initActors, totalTurn = 10))
    Sim.run()
  }
}
