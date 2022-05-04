package generated.example.test

import meta.API._
import scala.collection.mutable.{Map}
import java.io._
import meta.runtime.Actor

/**
* Reset objects between benchmark phases. 
* Can trigger unexpected JIT optimization for long-running workloads. 
* Only apply for graph anayltics workload which run for few iterations
*/
class StaticPartitionTestWJIT[T: SimsRunner](name: String, 
                    totalTurns: Int,
                    latencys: Set[Int],
                    containers: Set[Int],
                    parameterss: Traversable[Traversable[Any]], 
                    init: => List[Any] => List[Actor], 
                    schemaWriter: => PrintWriter => Unit) extends org.scalatest.FlatSpec {

    def run(): Unit = {
        val output: String = f"${name}.csv"
        val pw = new PrintWriter(new FileOutputStream(new File(output),false))

        pw.write("Experiment,Containers,K,AvgTime,")
        schemaWriter(pw)
        pw.write("\n")

        Util.crossJoin(parameterss).foreach(x => {
            val agents = init(x.toList)

            for (container <- containers; latency <- latencys){
                agents.foreach(x => x.SimReset)
                val c = new SimulationConfig(agents, totalTurns, true, latency)
                val avgTime = {
                    if (container == 0){
                        StartSimulation.benchAvg[T](c)
                    }else {
                        val containerConfig = c.staticPartition(container)(BoundedLatency)
                        StartSimulation.benchAvg[T](containerConfig)
                    }
                } 
                pw.write(f"\n${name},${container},${latency},${avgTime},${Util.csList(x)}")
                pw.flush()
            }
        })
        pw.close()
    }

    f"${name} example with ${containers} containers with latency bound ${latencys} in Akka" should "run" in {
        run()
    }
}

class shortestPathStaticTestWJITAkka extends StaticPartitionTestWJIT[AkkaMessagingLayer.type](
    "shortestPath", 3, Set(1), Set(10, 50, 100, 500, 1000), 
    List(Set(1000, 10000, 100000), Set(0.001)), 
    generated.example.graphAlgorithm.shortestPath.InitData.wrapper, 
    generated.example.graphAlgorithm.shortestPath.InitData.writeSchema) {
}

class pageRankStaticTestWJITAkka extends StaticPartitionTestWJIT[AkkaMessagingLayer.type](
    "pageRank", 20, Set(1, 4), Set(10, 50, 100, 500, 1000), 
    List(Set(1000, 10000), Set(0.001)), 
    generated.example.graphAlgorithm.pageRank.InitData.wrapper, 
    generated.example.graphAlgorithm.pageRank.InitData.writeSchema) {
}