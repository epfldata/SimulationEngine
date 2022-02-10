package generated.example.test

import meta.API._
import scala.collection.mutable.{Map}
import java.io._
import meta.runtime.Actor

class StaticPartitionTest[T: SimsRunner](name: String, 
                    totalTurns: Int,
                    latencys: Set[Int],
                    containers: Set[Int],
                    parameterss: Traversable[Traversable[Int]], 
                    init: => List[Any] => List[Actor], 
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
                        StartSimulation.benchAvg[T](c)
                    }else {
                        val containerConfig = c.staticPartition(container)(BoundedLatency)
                        StartSimulation.benchAvg[T](containerConfig)
                    }
                } 
                pw.write(f"\n${name},${container},${latency},${avgTime},${Util.csList(x)}")
                pw.flush()
            })
        }

        pw.close()
    }

    f"${name} example with ${containers} containers with latency bound ${latencys} in Akka" should "run" in {
        run()
    }
}

class gameOfLifeStaticTestSpark extends StaticPartitionTest[SparkMessagingLayer.type](
    "gameOfLife", 100, Set(1), Set(0), 
    List(Set(10), Set(100), Set(1)), 
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema) {
}

class gameOfLifeStaticTestAkka extends StaticPartitionTest[AkkaMessagingLayer.type](
    "gameOfLife", 100, Set(1), Set(0), 
    List(Set(10), Set(100), Set(1)), 
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema) {
}

class watorStaticTest extends StaticPartitionTest[AkkaMessagingLayer.type](
    "wator", 100, Set(1), Range(0, 101, 10).toSet, 
    List(Set(1000), Set(100), Set(1).union(Range(5, 31, 5).toSet)), 
    generated.example.cellularAutomata.wator.InitData.wrapper, 
    generated.example.cellularAutomata.wator.InitData.writeSchema) {
}

class cyberspaceStaticTest extends StaticPartitionTest[AkkaMessagingLayer.type](
    "cyberspace", 600, Set(1, 100, 200), Set(50), 
    List(Set(10000), Set(50), Set(200), Range(1, 1000, 100).toSet), 
    generated.example.cyberspace.InitData.wrapper, 
    generated.example.cyberspace.InitData.writeSchema) {
}

// class epidemicStaticTest extends StaticPartitionTest(
//     "epidemic", 100, Set(1), Set(0, 50, 100), 
//     List(Set(1000, 10000, 100000)), 
//     generated.example.epidemic.InitData.wrapper, 
//     generated.example.epidemic.InitData.writeSchema) {
// }
