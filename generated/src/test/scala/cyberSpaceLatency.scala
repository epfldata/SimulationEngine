package generated.example.test

import meta.API._
import java.io._
import scala.collection.mutable.{StringBuilder}

class cyberSpaceLatencyTests extends org.scalatest.FlatSpec {

    val example: String ="Cyber space"
    val output: String = "cyber_space.csv"

    val logger: StringBuilder = new StringBuilder()
    val population: Int = 1000
    val totalTurns: Int = 100
    // Assume one server per container
    val containers: Set[Int] = Set(50, 100)
    val boundLatency: Set[Int] = Set(1, 10, 30, 50)

    val pw = new PrintWriter(new FileOutputStream(new File(output),true))

    for (container <- containers) {
        for (latency <- boundLatency) {
            f"${example} example with ${population} agents and ${container} containers with latency bound ${latency}" should "run" in {
                val agents = generated.example.cyberSpace.InitData(population, container, 10)

                val c = new SimulationConfig(agents, totalTurns, true, latency)
                val run1 = {
                    if (container == 0){
                        StartSimulation.benchAvg[AkkaMessagingLayer.type](c)(logger)
                    }else {
                        logger ++= c.toString()
                        val containerConfig = c.staticPartition(container)(BoundedLatency)
                        StartSimulation.benchAvg[AkkaMessagingLayer.type](containerConfig)(logger)
                    }
                } 
                pw.write(f"${example},${population},${container},${latency},${run1}\n")
            }
        }
    }

    f"${example} example" should "run successfully and log the output" in {
        pw.close()
    }
}

