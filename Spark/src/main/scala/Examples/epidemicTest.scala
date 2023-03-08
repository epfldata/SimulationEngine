package simulation.spark
package examples

object epidemic {
    def main(args: Array[String]): Unit = {
        val population: Int = args(0).toInt
        val p: Double = 0.01
        val isSBM: Boolean = (args(1).toInt == 1)
        val blocks: Int = args(2).toInt
        val totalTurns: Int = args(3).toInt
        val mode: Int = args(4).toInt

        mode match {
            case 1 => {
                // v1
                val agents = generated.example.epidemic.v1.InitData(population, p, isSBM, blocks)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }

            case 2 => {
                // Use v5 as default comm.freq (message-passing)
                val cfreq: Int = args(5).toInt
                val agents = generated.example.epidemic.v5.InitData(population, p, isSBM, blocks, cfreq)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }

            case 3 => {
                // v3
                val agents = generated.example.epidemic.v3.InitData(population, p, isSBM, blocks)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }

            case 4 => {
                // comp. interval
                val interval: Int = args(5).toInt
                val agents = generated.example.epidemic.v6.InitData(population, p, isSBM, blocks, interval)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }
        }
    }
}
