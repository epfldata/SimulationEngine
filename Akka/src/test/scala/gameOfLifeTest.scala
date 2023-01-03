package simulation.akka
package test

object gameOfLife {
    def main(args: Array[String]): Unit = {
        val width = args(0).toInt
        val height: Int = args(1).toInt
        val totalTurns: Int = args(2).toInt
        val mode: Int = args(3).toInt
        var role: String = "Standalone"
        var port: Int = 25251
        if (args.size > 4) {
            role = args(4)
            port = args(5).toInt
        }

        mode match {
            case 1 => {
                // Messaging
                val agents = generated.example.gameOfLife.InitData(width, height)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }
            case 2 => {
                // callAndForget
                val agents = generated.example.gameOfLifeRPCOneSide.InitData(width, height)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            case 3 => {
                // Direct method call, double buffer
                val agents = generated.example.gameOfLifeRPCOneSideDoubleBuffer.InitData(width, height)
                API.OptimizationConfig.directMethodCall()
                val snapshot1 = API.Simulate(agents, 2*totalTurns, role, port)
            }

            case 4 => {
                // Direct method call, multi-version
                val agents = generated.example.gameOfLifeRPCOneSideMultiversion.InitData(width, height)
                API.OptimizationConfig.directMethodCall()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }


            case 5 => {
                // asyncCall
                val agents = generated.example.gameOfLifeRPC.InitData(width, height)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            case 6 => {
                // Messaging, concurrent
                val agents = generated.example.gameOfLife.InitData(width, height)
                API.OptimizationConfig.concurrentWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }
        }
    }
}

object gameOfLifeCommFreq {
    val totalTurns: Int = 50
    val width: Int = 10
    val height: Int = 100

    def main(args: Array[String]): Unit = {
        val cfreq: Int = args(0).toInt
        val agents = generated.example.gameOfLifeCommFreq.InitData(width, height, cfreq)
        API.OptimizationConfig.mergedWorker()
        API.Simulate(agents, totalTurns)
    }
}
