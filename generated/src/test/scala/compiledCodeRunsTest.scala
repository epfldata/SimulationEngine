package generated.example
package test

class compiledCodeRunsTest extends FlatSpec {

 "Codegen example" should "run for both Simulation and SparkSimulation" in {
   import codegen_example._
   val c: Config = new Config(InitData.initActors, 0, 10, 0, 1)
   new Default(c).run()
 }

  "latency example" should "run for default" in {
    import latency._
    val c: Config = new Config(InitData.initActors, 0, 100, 0, 10)
    new Default(c).run()
    // time: 1654734773
  }

 "latency example" should "run for SparkSimulation" in {
   import latency._
   val c: Config = new Config(InitData.initActors, 0, 100, 0, 10)
   new SimulationSpark(c).run()
   // time: 45831111052
 }

// "meeting example" should "run for both Simulation and SparkSimulation" in {
//   runBoth(meeting_example.InitData.initActors, 10, 1)
// }
//
// "Monitor example agent" should "run for both Simulation and SparkSimulation" in {
//   runBoth(monitor_example.agent_monitor.InitData.initActors, 10, 1, false)
// }
//
// "Monitor example built-in" should "run for both Simulation and SparkSimulation" in {
//   runBoth(monitor_example.builtin_monitor.InitData.initActors, 10, 1, true)
// }
//
// "segregation example" should "run for both Simulation and SparkSimulation" in {
//   runBoth(segregation.InitData.initActors, 50, 5)
// }
//
// "server communication example" should "run for both Simulation and SparkSimulation" in {
//   runBoth(server_communication_merged.InitData.initActors, 10, 1)
// }
//
// "stateless server example" should "run for both Simulation and SparkSimulation" in {
//   runBoth(stateless_server.InitData.initActors, 30, 1)
// }
//
// "time example" should "run for both Simulation and SparkSimulation" in {
//   runBoth(time_example.InitData.initActors, 100, 10)
// }
}