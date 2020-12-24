package generated.example
package test

class asyncMsgTest extends FlatSpec {

  "Rumor spread" should "grow exponentially" in {
    import rumor._
    val Sim = new Simulation(new Config(InitData.initActors, totalTurn = 40))
    Sim.run()
  }

  "Group chat" should "gets repsonses in the next epoch" in {
    import meeting_example._
    val Sim: Simulation = new Simulation(new Config(InitData.initActors, totalTurn = 15))
    Sim.run()
  }

  "nb-method" should "compile and run" in {
    import nb_methods_example._
    val Sim = new Simulation(new Config(InitData.initActors, totalTurn = 15))
    Sim.run()
  }
}