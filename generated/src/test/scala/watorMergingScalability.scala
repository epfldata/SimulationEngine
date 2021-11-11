package generated.example.test

import meta.API._
import scala.collection.mutable.{Map, StringBuilder}
import java.io._

class watorMergingScalability extends org.scalatest.FlatSpec {

    val example: String = "wator"
    val output: String = "wator.csv"

    val logger: StringBuilder = new StringBuilder()
    val widths: Set[Int] = Set(10, 100)
    // val widths: Set[Int] = Set(10, 100, 1000)
    val height: Int = 100

    val totalTurns: Int = 100

    val containers: Set[Int] = Set(0, 50, 100)
    // assume fixed model
    val boundLatency: List[Int] = List(1)

    val pw = new PrintWriter(new FileOutputStream(new File(output),true))

    for (width <- widths) {
        for (container <- containers) {
            for (latency <- boundLatency) {
                f"${example} example with ${width * height} agents and ${container} containers with latency bound ${latency}" should "run" in {
                    val agents = generated.example.cellularAutomata.wator.InitData(width, height)

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
                    pw.write(f"${example},${width*height},${container},${latency},${run1}\n")
                }
            }
        }
    }

    f"${example} example" should "run successfully and log the output" in {
        pw.close()
    }
}

