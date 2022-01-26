package generated.example.test

import meta.API._
import scala.collection.mutable.{Map}
import java.io._
import meta.runtime.Actor

class StaticPartitionTest(name: String, 
                    totalTurns: Int,
                    latencys: Set[Int],
                    containers: Set[Int],
                    parameterss: Traversable[Traversable[Int]], 
                    init: => List[Int] => List[Actor], 
                    schemaWriter: => PrintWriter => Unit) extends org.scalatest.FlatSpec {

    def run(): Unit = {
        val output: String = f"${name}.csv"
        val pw = new PrintWriter(new FileOutputStream(new File(output),false))

        pw.write("Experiment,Containers,K,AvgTime,")
        schemaWriter(pw)

        for (container <- containers; latency <- latencys){
            Util.crossJoin(parameterss).foreach(x => {
                val agents = init(x.toList)
                val c = new SimulationConfig(agents, totalTurns, true, latency)

                val avgTime = {
                    if (container == 0){
                        StartSimulation.benchAvg[AkkaMessagingLayer.type](c)
                    }else {
                        val containerConfig = c.staticPartition(container)(BoundedLatency)
                        StartSimulation.benchAvg[AkkaMessagingLayer.type](containerConfig)
                    }
                } 
                pw.write(f"\n${name},${container},${latency},${avgTime},${Util.csList(x)}")
                pw.flush()
            })
        }

        pw.close()
    }

    f"${name} example with ${containers} containers with latency bound ${latencys}" should "run" in {
        run()
    }
}

class gameOfLifeStaticTest extends StaticPartitionTest(
    "gameOfLife", 100, Set(1), Range(0, 101, 10).toSet, 
    List(Set(1000), Set(100), Set(1).union(Range(5, 31, 5).toSet)), 
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema) {
}

class watorStaticTest extends StaticPartitionTest(
    "wator", 100, Set(1), Range(0, 101, 10).toSet, 
    List(Set(1000), Set(100), Set(1).union(Range(5, 31, 5).toSet)), 
    generated.example.cellularAutomata.wator.InitData.wrapper, 
    generated.example.cellularAutomata.wator.InitData.writeSchema) {
}

class cyberspaceStaticTest extends StaticPartitionTest(
    "cyberspace", 600, Set(1, 100, 200), Set(50), 
    List(Set(10000), Set(50), Set(200)), 
    generated.example.cyberspace.InitData.wrapper, 
    generated.example.cyberspace.InitData.writeSchema) {
}

class epidemicStaticTest extends StaticPartitionTest(
    "epidemic", 100, Set(1), Set(0, 50, 100), 
    List(Set(1000, 10000, 100000)), 
    generated.example.epidemic.InitData.wrapper, 
    generated.example.epidemic.InitData.writeSchema) {
}
