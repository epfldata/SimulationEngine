package generated.example.test

import meta.API._
import java.io._

object gameOfLifeDist {

    def main(args: Array[String]): Unit = {
        
        val name: String = "gameOfLife"
        val latency: Int = 1
        val hostRole = args(0)
        val totalMachines: Int = args(1).toInt
        val machineSeq: Int = args(2).toInt
        val hostPort: Int = args(3).toInt
        val container: Int = args(4).toInt
        
        val agents = generated.example.gameOfLife.InitData(10, 100, 1)
        val output: String = "gameOfLife_dist.csv"
        val pw = new PrintWriter(new FileOutputStream(new File(output),false))

        pw.write("Experiment,Containers,K,AvgTime")
        pw.write("\n")

        val c = (new DistSimulationConfig(agents, totalTurn = 100, totalMachines, machineSeq, latencyBound = latency, role=hostRole, port=hostPort)).getConfig()
        
        val avgTime = if (container == 0){
            StartSimulation.benchAvg[AkkaMessagingLayer.type](c)
        } else {
            val containerConfig = c.staticPartition(container)(BoundedLatency)
            StartSimulation.benchAvg[AkkaMessagingLayer.type](containerConfig)
        }
        println(f"Average time ${avgTime}")
        pw.write(f"\n${name},${container},${latency},${avgTime}")
        pw.close()
    }
}

