package generated.example.test

import meta.API._
import meta.runtime.{Actor}
import java.io._

class predatorPreyTest extends org.scalatest.FlatSpec {
    "Compiled shortest path example with 10 nodes" should "run" in {
        val agents = generated.example.predatorPrey.InitData(10, 10, 5)

        // (time, score)
        // (#players, #npcs)
        type MapperOut = Option[(Int, Int)]
        type ReducerOut = (Int, Int)

        // record the state of players
        val mapper: Actor => MapperOut = (agent) => {
            val player = agent.asInstanceOf[generated.example.predatorPrey.Cell].currentPlayer
            if (player.isDefined){
                val c = player.get
                if (c.isNPC) {
                    Some((0, 1))
                } else {
                    Some((1, 0))
                }
            } else {
                None
            }
        }

        val reducer: List[MapperOut] => ReducerOut = (x) => x.filter(_.isDefined).foldLeft(0, 0)((x, y) => (x._1+y.get._1, x._2+y.get._2))

        val c = new SimulationConfig(agents, 1000)
        // val containerConfig = c.staticPartition(10)(BoundedLatency)
        val results = StartSimulation.runAndReduce[AkkaMessagingLayer.type, MapperOut, ReducerOut](c)(mapper, reducer)

        // val results = StartSimulation[AkkaMessagingLayer.type](c)
        val pw = new PrintWriter(new FileOutputStream(new File("predatorPrey.log"),false))
        results.foreach(x => pw.write(x + "\n"))
        pw.close()
    }
}