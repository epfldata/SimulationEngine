package generated.example.test

import meta.API._
import scala.collection.mutable.{Map}
import java.io._
import meta.runtime.Actor

class StaticPartitionTest[T: SimsRunner](name: String, 
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
    "gameOfLifeStaticTestSpark", 300, Set(1), Set(0), 
    List(Set(10), Set(100), Set(1)), 
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema) {
}

class gameOfLifeStaticTestAkka extends StaticPartitionTest[AkkaMessagingLayer.type](
    "gameOfLifeStaticTestAkka", 300, Set(1), Set(10, 50, 100, 500, 1000), 
    List(Set(1, 5, 10, 50, 100), Set(1000), Set(1)), 
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema)

class stockMarketStaticTestAkka extends StaticPartitionTest[AkkaMessagingLayer.type](
    "stockMarketStaticTestAkka", 300, Set(1, 20, 50), Set(10, 50, 100, 500, 1000), 
    List(Set(1000, 5000, 10000)), 
    generated.example.stockMarket.InitData.wrapper, 
    generated.example.stockMarket.InitData.writeSchema)

class transportation1kStaticTestAkka extends StaticPartitionTest[AkkaMessagingLayer.type](
    "transportation1kStaticTestAkka", 300, Set(1, 20, 50), Set(0), 
    List(Set(10), Set(900), Set(90)), 
    generated.example.transportation.InitData.wrapper, 
    generated.example.transportation.InitData.writeSchema)

class transportation5kStaticTestAkka extends StaticPartitionTest[AkkaMessagingLayer.type](
    "transportation5kStaticTestAkka", 300, Set(1, 20, 50), Set(0), 
    List(Set(50), Set(4500), Set(450)), 
    generated.example.transportation.InitData.wrapper, 
    generated.example.transportation.InitData.writeSchema)


class transportation10kStaticTestAkka extends StaticPartitionTest[AkkaMessagingLayer.type](
    "transportation10kStaticTestAkka", 300, Set(1, 20, 50), Set(0), 
    List(Set(100), Set(9000), Set(900)), 
    generated.example.transportation.InitData.wrapper, 
    generated.example.transportation.InitData.writeSchema)