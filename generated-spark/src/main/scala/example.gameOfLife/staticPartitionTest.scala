package generated.example.gameOfLife

import meta.API._
import scala.collection.mutable.{Map}
import java.io._
import meta.runtime.Actor

object Util {
    def crossJoin[T](list: Traversable[Traversable[T]]): Traversable[Traversable[T]] =
        list match {
            case xs :: Nil => xs map (Traversable(_))
            case x :: xs => for {
                i <- x
                j <- crossJoin(xs)
            } yield Traversable(i) ++ j
    }

    // Comma separated list with just values
    def csList(list: Traversable[Any]): String = {
        list.mkString(",")
    }
}


class StaticPartitionTest[T: SimsRunner](name: String, 
                    totalTurns: Int,
                    latencys: Set[Int],
                    containers: Set[Int],
                    parameterss: Traversable[Traversable[Any]], 
                    init: => List[Any] => List[Actor], 
                    schemaWriter: => PrintWriter => Unit) {
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
}

object gameOfLifeStaticTestSpark extends StaticPartitionTest[SparkMessagingLayer.type](
    "gameOfLifeStaticTestSpark", 5, Set(1), Set(0), 
    List(Set(10), Set(100), Set(1)), 
    generated.example.gameOfLife.InitData.wrapper, 
    generated.example.gameOfLife.InitData.writeSchema) {
      def main(args: Array[String]) = {
        run()
      }
}