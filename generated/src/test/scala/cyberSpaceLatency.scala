package generated.example.test

import meta.API._
import java.io._

class cyberSpaceLatencyTests extends org.scalatest.FlatSpec {

    val example: String ="Cyberspace"
    val output: String = "cyberspace.csv"

    val population: Int = 10000
    val totalTurns: Int = 600
    // Assume one server per container
    val containers: Set[Int] = Set(50)
    val boundLatency: Set[Int] = Set(1, 100, 200)

    val pw = new PrintWriter(new FileOutputStream(new File(output),true))

    for (container <- containers) {
        for (latency <- boundLatency) {
            f"${example} example with ${population} agents and ${container} containers with latency bound ${latency}" should "run" in {
                val agents = generated.example.cyberSpace.InitData(population, container, 200)

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

    f"${example} example" should "run successfully and log the output" in {
        pw.close()
    }
}

