package generated.example.test

import meta.API._
import scala.collection.mutable.{Map}
import java.io._
import meta.runtime.{Actor, Message}

// No container, latency 1
class TimeseriesTest[T: SimsRunner, E](name: String, 
                    totalTurns: Int,
                    parameterss: Traversable[Traversable[Any]], 
                    eval: (List[Actor], List[Message]) => E,
                    init: => List[Any] => List[Actor], 
                    schemaWriter: => PrintWriter => Unit) extends org.scalatest.FlatSpec {

    def run(): Unit = {
        val output: String = f"${name}.csv"
        val pw = new PrintWriter(new FileOutputStream(new File(output),false))

        pw.write("Experiment,AvgTime,")
        schemaWriter(pw)
        pw.write("\n")

        Util.crossJoin(parameterss).foreach(x => {
            val agents = init(x.toList)
            val c = new SimulationConfig(agents, totalTurns, true, 1)

            val avgTime = meta.runtime.simulation.util.bench {
                StartSimulation.runAndEval[T, E](c)(eval)
                // println(x)
            } / totalTurns.toDouble

            pw.write(f"\n${name},${avgTime},${Util.csList(x)}")
            pw.flush()
        })

        pw.close()
    }

    f"${name} timeseries example" should "run" in {
        run()
    }
}

class TimeseriesDeforestationTest[T: SimsMapReduceRecorder, K, R](name: String, 
                    totalTurns: Int,
                    parameterss: Traversable[Traversable[Any]], 
                    mapper: Actor => K,
                    reducer: List[K] => R,
                    init: => List[Any] => List[Actor], 
                    schemaWriter: => PrintWriter => Unit) extends org.scalatest.FlatSpec {

    def run(): Unit = {
        val output: String = f"${name}.csv"
        val pw = new PrintWriter(new FileOutputStream(new File(output),false))

        pw.write("Experiment,AvgTime,")
        schemaWriter(pw)
        pw.write("\n")

        Util.crossJoin(parameterss).foreach(x => {
            val agents = init(x.toList)
            val c = new SimulationConfig(agents, totalTurns, true, 1)

            val avgTime = meta.runtime.simulation.util.bench {
                StartSimulation.runAndReduce[T, K, R](c)(mapper, reducer)
                // println(x)
            } / totalTurns.toDouble

            pw.write(f"\n${name},${avgTime},${Util.csList(x)}")
            pw.flush()
        })

        pw.close()
    }

    f"${name} timeseries example with deforestation" should "run" in {
        run()
    }
}