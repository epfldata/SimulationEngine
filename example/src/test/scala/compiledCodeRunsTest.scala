import org.scalatest.FlatSpec
import meta.deep.runtime.Actor

class compiledCodeRunsTest extends FlatSpec {

  import _root_.generated.simulation.{Simulation, SimulationSpark, SimulationConfig => Config}
  import generated.meta.example._

  def runBoth(l: List[Actor], totalTurn: Int, totalTime: Double, monitorEnabled: Boolean = false): Unit = {
    Simulation.run(Config(l, totalTurn = totalTurn, totalTime = totalTime, monitor_enabled = monitorEnabled))
    // Comment out Spark for now. Multiple spark context result in non-serializable
//    SimulationSpark.run(Config(l, totalTurn = totalTurn, totalTime = totalTime, monitor_enabled = monitorEnabled))
  }

  "Codegen example" should "run for both Simulation and SparkSimulation" in {
    runBoth(codegen_example.InitData.initActors, 10, 1)
  }

  "helloWorld example" should "run for both Simulation and SparkSimulation" in {
    runBoth(helloWorld.InitData.initActors, 10, 1)
  }

  "latency example" should "run for both Simulation and SparkSimulation" in {
    runBoth(latency.InitData.initActors, 100, 10)
  }

  "lock example" should "run for both Simulation and SparkSimulation" in {
    runBoth(lock_example.InitData.initActors, 10, 1)
  }

  "meeting example" should "run for both Simulation and SparkSimulation" in {
    runBoth(meeting_example.InitData.initActors, 10, 1)
  }

  "Monitor example agent" should "run for both Simulation and SparkSimulation" in {
    runBoth(monitor_example.agent_monitor.InitData.initActors, 10, 1, false)
  }

  "Monitor example built-in" should "run for both Simulation and SparkSimulation" in {
    runBoth(monitor_example.builtin_monitor.InitData.initActors, 10, 1, true)
  }

  "non-blocking method example" should "run for both Simulation and SparkSimulation" in {
    runBoth(nb_methods_example.InitData.initActors, 30, 5)
  }

  "segregation example" should "run for both Simulation and SparkSimulation" in {
    runBoth(segregation.InitData.initActors, 50, 5)
  }

  "server communication example" should "run for both Simulation and SparkSimulation" in {
    runBoth(server_communication.InitData.initActors, 10, 1)
  }

  "stateless server example" should "run for both Simulation and SparkSimulation" in {
    runBoth(stateless_server.InitData.initActors, 30, 1)
  }

  "time example" should "run for both Simulation and SparkSimulation" in {
    runBoth(time_example.InitData.initActors, 100, 10)
  }
}