package simulation.akka
package test

// sbt -mem 100000 "project akka; test:runMain simulation.akka.test.gameOfLifeBench 100 100 50 1"

object gameOfLifeBench {
    def main(args: Array[String]): Unit = {
        val width = args(0).toInt
        val height: Int = args(1).toInt
        val totalTurns: Int = args(2).toInt
        val mode: Int = args(3).toInt
        apply(width, height, totalTurns, mode)
    }

    def apply(width: Int, height: Int, totalTurns: Int, mode: Int): Unit = {
        mode match {
            case 1 => {
                // Messaging
                val agents = generated.example.gameOfLife.InitData(width, height)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns)
            }
            case 2 => {
                // callAndForget
                val agents = generated.example.gameOfLifeRPCOneSide.InitData(width, height)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns)
            }
            case 3 => {
                // Direct method call
                val agents = generated.example.gameOfLifeRPCOneSideMultiversion.InitData(width, height)
                API.OptimizationConfig.directMethodCall()
                val snapshot1 = API.Simulate(agents, totalTurns)
            }

            case 4 => {
                // asyncCall
                val agents = generated.example.gameOfLifeRPC.InitData(width, height)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns)
            }
        }
    }
}
