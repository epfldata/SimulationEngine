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
class StaticPartitionTestReset[T: SimsRunner](name: String, 
                    preprocess: => List[Actor] => Unit,
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
                preprocess(agents)
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

class shortestPathStaticTestReset extends StaticPartitionTestReset[AkkaMessagingLayer.type](
    "shortestPathStaticTestReset", (x: List[Actor]) => Unit, 3, Set(1), Set(10, 50, 100, 500, 1000), 
    List(Set(1000, 10000, 100000), Set(0.001)), 
    generated.example.graphAlgorithm.shortestPath.InitData.wrapper, 
    generated.example.graphAlgorithm.shortestPath.InitData.writeSchema) 

class pageRankStaticTestReset extends StaticPartitionTestReset[AkkaMessagingLayer.type](
    "pageRankStaticTestReset", (x: List[Actor]) => Unit, 20, Set(1, 4), Set(10, 50, 100, 500, 1000), 
    List(Set(1000, 10000), Set(0.001)), 
    generated.example.graphAlgorithm.pageRank.InitData.wrapper, 
    generated.example.graphAlgorithm.pageRank.InitData.writeSchema) 

class epidemicSBM extends StaticPartitionTestReset[AkkaMessagingLayer.type](
    "epidemicSBM", (x: List[Actor]) => {x.foreach(a =>
        a match {
            case i: generated.example.epidemic.evalNPI.Person => i.SimReset(Set("country"))
            case i: generated.example.epidemic.evalNPI.Country => i.SimReset(Set("citizens", "otherCountries"))
            case _ =>
        }
    )}, 300, Set(1, 50), Set(0, 50, 100, 500, 1000), 
    List(Set(Range(0, 10).map(x => 1000).toList), Set(0.01, 0.05, 0.1), Set(true)), 
    generated.example.epidemic.evalNPI.InitData.wrapper, 
    generated.example.epidemic.evalNPI.InitData.writeSchema) 

class epidemicERM extends StaticPartitionTestReset[AkkaMessagingLayer.type](
    "epidemicERM", (x: List[Actor]) => {x.foreach(a =>
        a match {
            case i: generated.example.epidemic.evalNPI.Person => i.SimReset(Set("country"))
            case i: generated.example.epidemic.evalNPI.Country => i.SimReset(Set("citizens", "otherCountries"))
            case _ =>
        }
    )}, 300, Set(1, 50), Set(0, 50, 100, 500, 1000), 
    List(Set(Range(0, 10).map(x => 1000).toList), Set(0.0001, 0.0003, 0.001), Set(false)), 
    generated.example.epidemic.evalNPI.InitData.wrapper, 
    generated.example.epidemic.evalNPI.InitData.writeSchema) 