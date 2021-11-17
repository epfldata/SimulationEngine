package generated.example.test

import meta.API._
import scala.collection.mutable.{Map}
import java.io._

class epidemicMergingScalability extends org.scalatest.FlatSpec {

    val example: String = "epidemic"
    val output: String = "epidemic.csv"
    val populations: Set[Int] = Set(1000, 10000, 100000)

    val totalTurns: Int = 100

    val containers: Set[Int] = Set(0, 50, 100)
    // assume fixed model
    val boundLatency: List[Int] = List(1)

    val pw = new PrintWriter(new FileOutputStream(new File(output),true))

    for (population <- populations) {
        for (container <- containers) {
            for (latency <- boundLatency) {
                f"${example} example with ${population} agents and ${container} containers with latency bound ${latency}" should "run" in {
                    val agents = generated.example.epidemic.InitData(population)

                    val c = new SimulationConfig(agents, totalTurns, true, latency)
                    val run1 = {
                        if (container == 0){
                            StartSimulation.benchAvg[AkkaMessagingLayer.type](c)
                        }else {
                            val containerConfig = c.staticPartition(container)(BoundedLatency)
                            StartSimulation.benchAvg[AkkaMessagingLayer.type](containerConfig)
                        }
                    } 
                    pw.write(f"${example},${population},${container},${latency},${run1}\n")
                }
            }
        }
    }

    f"${example} example" should "run successfully and log the output" in {
        pw.close()
    }
}

