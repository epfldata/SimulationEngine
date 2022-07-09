package generated.example.test

import meta.API._
import java.io._

object benchmarkDistTest {
    def main(args: Array[String]): Unit = {
        val hostRole = args(0)
        val totalMachines: Int = args(1).toInt
        val machineSeq: Int = args(2).toInt
        val latency: Int = args(3).toInt
        var totalTurns: Int = args(4).toInt
        val container: Int = args(5).toInt
        val name: String = args(6)
        
        val hostPort: Int = 25251
        val output: String = f"${name}_dist.csv"
        val pw = new PrintWriter(new FileOutputStream(new File(output),false))

        val start_initialization: Long = System.currentTimeMillis()
        val agents = name match {
            case "gameOfLife10k" => generated.example.gameOfLife.InitData(1000, 10*totalMachines, 1)
            case "stockMarket10k" => generated.example.stockMarket.InitData(10000*totalMachines)
            case "transportation10k" => generated.example.transportation.InitData(100*totalMachines, 9000*totalMachines, 900*totalMachines)
            case _ => throw new Exception(f"Invalid example name ${name}!")
        }
        val end_initialization: Long = System.currentTimeMillis()
        val time_generating_agents = end_initialization - start_initialization
        pw.write(f"Generating agents take ${time_generating_agents} ms\n")

        val c = (new DistSimulationConfig(agents, totalTurn = totalTurns, totalMachines, machineSeq, latencyBound=latency, role=hostRole, port=hostPort)).getConfig()
            
        val avgTime = if (container == 0){
            meta.runtime.simulation.SimExperiment.totalAgents = agents.size
            StartSimulation.benchAvg[AkkaMessagingLayer.type](c)
        } else {
            meta.runtime.simulation.SimExperiment.totalAgents = container * totalMachines
            val containerConfig = c.staticPartition(container)(BoundedLatency)
            StartSimulation.benchAvg[AkkaMessagingLayer.type](containerConfig)
        }
        println(f"Average time ${avgTime}")
        pw.write(f"${name},${totalMachines},${container},${latency},${avgTime}")
        pw.close()
    }
}

object ResetDistTest {
    def main(args: Array[String]): Unit = {
        val hostRole = args(0)
        val totalMachines: Int = args(1).toInt
        val machineSeq: Int = args(2).toInt
        var latency: Int = args(3).toInt
        val totalTurns: Int = args(4).toInt
        val name: String = args(5)

        val margs: Array[String] = args.drop(6)

        val hostPort: Int = 25251        
        val output: String = f"${name}_dist.csv"
        val pw = new PrintWriter(new FileOutputStream(new File(output),false))

        val agents = name match {
          case "epidemicSBM10k" => generated.example.epidemic.evalNPI.InitData(Range(0, 10*totalMachines).map(x => 1000).toList, margs(0).asInstanceOf[Double], true)
          case "epidemicERM10k" => generated.example.epidemic.evalNPI.InitData(Range(0, 10*totalMachines).map(x => 1000).toList, margs(0).asInstanceOf[Double], false)
          case _ => throw new Exception(f"Invalid example name ${name}!")
        }
        
        for (container <- List(0, 10, 100)) {
          agents.foreach(a => {
            a match {
              case i: generated.example.epidemic.evalNPI.Person => i.SimReset(Set("connectedAgents", "country"))
              case i: generated.example.epidemic.evalNPI.Country => i.SimReset(Set("connectedAgents", "citizens", "otherCountries"))
              case _ =>
          }})
          val c = (new DistSimulationConfig(agents, totalTurn = totalTurns, totalMachines, machineSeq, latencyBound=latency, role=hostRole, port=hostPort)).getConfig()
              
          val avgTime = if (container == 0){
              meta.runtime.simulation.SimExperiment.totalAgents = agents.size
              StartSimulation.benchAvg[AkkaMessagingLayer.type](c)
          } else {
              meta.runtime.simulation.SimExperiment.totalAgents = container * totalMachines
              val containerConfig = c.staticPartition(container)(BoundedLatency)
              StartSimulation.benchAvg[AkkaMessagingLayer.type](containerConfig)
          }
          println(f"Average time ${avgTime}")
          pw.write(f"${name},${totalMachines},${container},${latency},${margs},${avgTime}\n")
        }
        pw.close()
    }
}