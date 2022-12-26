package simulation.spark
package examples

// sbt -mem 100000 "project akka; test:runMain simulation.akka.test.gameOfLifeBench 100 100 50 1"

object gameOfLife {
    def main(args: Array[String]): Unit = {
        val width = args(0).toInt
        val height: Int = args(1).toInt
        val totalTurns: Int = args(2).toInt
        val mode: Int = args(3).toInt

        mode match {
            case 1 => {
                // Messaging
                val agents = generated.example.gameOfLife.InitData(width, height)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }
            case 2 => {
                // callAndForget
                val agents = generated.example.gameOfLifeRPCOneSide.InitData(width, height)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }

            // case 3 => {
            //     // Direct method call, double buffer
            //     val agents = generated.example.gameOfLifeRPCOneSideDoubleBuffer.InitData(width, height)
            //     API.OptimizationConfig.directMethodCall()
            //     val snapshot1 = API.Simulate(agents, 2*totalTurns)
            // }

            // case 4 => {
            //     // Direct method call, multi-version
            //     val agents = generated.example.gameOfLifeRPCOneSideMultiversion.InitData(width, height)
            //     API.OptimizationConfig.directMethodCall()
            //     val snapshot1 = API.Simulate(agents, totalTurns)
            // }


            // case 5 => {
            //     // asyncCall
            //     val agents = generated.example.gameOfLifeRPC.InitData(width, height)
            //     API.OptimizationConfig.mergedWorker()
            //     val snapshot1 = API.Simulate(agents, totalTurns)
            // }
        }
    }
}

object gameOfLifeCommFreq {
    val totalTurns: Int = 200
    val width: Int = 10
    val height: Int = 100

    def main(args: Array[String]): Unit = {
        val cfreq: Int = args(0).toInt
        val agents = generated.example.gameOfLifeCommFreq.InitData(width, height, cfreq)
        API.Simulate(agents, totalTurns)
    }
}
