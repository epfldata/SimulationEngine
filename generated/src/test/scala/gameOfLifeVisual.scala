package generated.example.test

import meta.API._
import lib.Grid.Grid2DSnapshot
import java.io._

object gameOfLifeVisual {

    def main(args: Array[String]): Unit = {
        val width: Int = 100
        val height: Int = 100
        val totalTurns: Int = 10
        val container: Int = 0
        
        val output: String = "GUI/experiments/gameOfLifeInit.txt"

        val agents = generated.example.gameOfLife.InitData(width, height, 1)

        val c = new SimulationConfig(agents, totalTurns, true, 1)

        val pw = new PrintWriter(new FileOutputStream(new File(output),true))

        val visualConfig = new Grid2DSnapshot(agents.map(x => 
            x.asInstanceOf[generated.example.gameOfLife.Cell].alive match {
                case true => 1
                case _ => 0
            }
        ), width, height, Map(1 -> "black"))
        
        pw.write(visualConfig.serialize())
        pw.close()
    }
}