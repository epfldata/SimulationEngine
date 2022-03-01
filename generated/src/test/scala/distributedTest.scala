package generated.example.test

import meta.API._
import java.io._

object gameOfLifeDist {

    def main(args: Array[String]): Unit = {
        
        val name: String = "gameOfLife"
        val latency: Int = 1
        val hostRole = args(0)
        val hostPort: Int = args(1).toInt
        val container: Int = args(2).toInt
        
        val agents = generated.example.gameOfLife.InitData(100, 1000, 1)
        val output: String = "gameOfLife_dist.csv"
        val pw = new PrintWriter(new FileOutputStream(new File(output),false))

        pw.write("Experiment,Containers,K,AvgTime")
        pw.write("\n")

        val c = new SimulationConfig(agents, totalTurn = 5, latencyBound = latency, role=hostRole, port=hostPort)
        
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

