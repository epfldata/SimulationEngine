package simulation.spark
package examples

object epidemicTest {
    val totalTurns: Int = 50

    def main(args: Array[String]): Unit = {
        val population: Int = args(0).toInt
        val p: Double = args(1).toDouble
        val isSBM: Boolean = (args(2).toInt == 1)
        val blocks: Int = args(3).toInt
        val mode: Int = args(4).toInt

        mode match {
            case 1 => {
                // v1
                val agents = generated.example.epidemic.v1.InitData(population, p, isSBM, blocks)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }

            case 2 => {
                // v2
                val cfreq: Int = args(5).toInt
                val agents = generated.example.epidemic.v2.InitData(population, p, isSBM, blocks, cfreq)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }

            case 3 => {
                // v3
                val agents = generated.example.epidemic.v3.InitData(population, p, isSBM, blocks)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }
        }
    }
}
