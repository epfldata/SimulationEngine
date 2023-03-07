package simulation.spark
package examples

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

            case 3 => {
                // communication frequency
                val cfreq: Int = args(4).toInt
                val agents = generated.example.gameOfLifeCommFreq.InitData(width, height, cfreq)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }
        }
    }
}