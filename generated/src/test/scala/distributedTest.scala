package generated.example.test

import meta.API._

object gameOfLifeDist {

    def main(args: Array[String]): Unit = {
        
        val agents = generated.example.gameOfLife.InitData(10, 10, 1)

        val hostRole = args(0)
        val hostPort: Int = args(1).toInt

        val c = new SimulationConfig(agents, totalTurn = 50, role=hostRole, port=hostPort)

        val results = StartSimulation[AkkaMessagingLayer.type](c)
    }
}