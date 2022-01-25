package generated.example.test

import meta.API._
import scala.collection.mutable.{Map}
import java.io._

class watorMergingScalability extends org.scalatest.FlatSpec {

    val example: String = "wator"
    val output: String = "wator.csv"

    val widths: Set[Int] = Set(1000)
    val height: Int = 100
    val totalTurns: Int = 100

    val containers: Set[Int] = Range(0, 101, 10).toSet
    // assume fixed model
    val boundLatency: List[Int] = List(1)
    val cfreqs: Set[Int] = Range(5, 31, 5).toSet

    val pw = new PrintWriter(new FileOutputStream(new File(output),true))

    for (width <- widths) {
        for (container <- containers) {
            for (latency <- boundLatency) {
                for (cfreq <- cfreqs) {
                    f"${example} example with ${width * height} agents and ${container} containers with latency bound ${latency} cfreq ${cfreq}" should "run" in {
                        val agents = generated.example.cellularAutomata.wator.InitData(width, height)(cfreq)

                        val c = new SimulationConfig(agents, totalTurns, true, latency)

                        val run1 = {
                            if (container == 0){
                                StartSimulation.benchAvg[AkkaMessagingLayer.type](c)
                            }else {
                                val containerConfig = c.staticPartition(container)(BoundedLatency)
                                StartSimulation.benchAvg[AkkaMessagingLayer.type](containerConfig)
                            }
                        } 
                        pw.write(f"${example},${width*height},${container},${latency},${run1},${cfreq}\n")
                        pw.flush()
                    }
                }
            }
        }
    }

    f"${example} example" should "run successfully and log the output" in {
        pw.close()
    }
}