package generated.example.test

import meta.API._
import java.io._

object gameOfLifeDist {

    def main(args: Array[String]): Unit = {
        
        val name: String = "gameOfLife"
        val hostRole = args(0)
        val totalMachines: Int = args(1).toInt
        val machineSeq: Int = args(2).toInt
        val hostPort: Int = args(3).toInt
        var container: Int = args(4).toInt
        val height: Int = args(5).toInt
        val latency: Int = args(6).toInt

        val width: Int = 10
        val agents = generated.example.gameOfLife.InitData(width, height, 1)
        val output: String = "gameOfLife_dist.csv"
        val pw = new PrintWriter(new FileOutputStream(new File(output),false))

        pw.write("Experiment,ContainersPerMachine,K,AvgTime")
        generated.example.gameOfLife.InitData.writeSchema(pw)
        pw.write("\n")

        val c = (new DistSimulationConfig(agents, totalTurn = 50, totalMachines, machineSeq, latencyBound=latency, role=hostRole, port=hostPort)).getConfig()
            
        val avgTime = if (container == 0){
            meta.runtime.simulation.SimExperiment.totalAgents = agents.size
            StartSimulation.benchAvg[AkkaMessagingLayer.type](c)
        } else {
            meta.runtime.simulation.SimExperiment.totalAgents = container * totalMachines
            val containerConfig = c.staticPartition(container)(BoundedLatency)
            StartSimulation.benchAvg[AkkaMessagingLayer.type](containerConfig)
        }
        println(f"Average time ${avgTime}")
        pw.write(f"\n${name},${container},${latency},${avgTime},${width},${height}")
        pw.close()
    }
}

