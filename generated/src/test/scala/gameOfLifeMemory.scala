package generated.example.test

import meta.API._
import java.io._
import scala.collection.mutable.{Map, StringBuilder}

class gameOfLifeMemoryTests extends org.scalatest.FlatSpec {

    val example: String = "Game of life memory test"
    val output: String = "game_of_life_mem.csv"

    val logger: StringBuilder = new StringBuilder()
    val width: Int = 1000
    val height: Int = 1000
    val totalTurns: Int = 10
    val container: Int = 0

    val boundLatency: List[Int] = List(1)

    val pw = new PrintWriter(new FileOutputStream(new File(output),true))

    for (latency <- boundLatency) {
        f"${example} example with ${width * height} agents and ${container} containers with latency bound ${latency}" should "run" in {
            val agents = generated.example.gameOfLife.InitData(width, height)

            val c = new SimulationConfig(agents, totalTurns, true, latency)
            logger ++= c.toString()
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

    f"${example} example" should "run successfully" in {
        pw.close()
    }
}