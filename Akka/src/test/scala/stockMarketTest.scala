package simulation.akka
package test

object stockMarketTest {
    def main(args: Array[String]): Unit = {
        val totalMarkets = args(0).toInt
        val tradersPerMarket: Int = args(1).toInt
        val totalTurns: Int = args(2).toInt
        val mode: Int = args(3).toInt
        val role: String = args(4)
        val port: Int = args(5).toInt
        apply(totalMarkets, tradersPerMarket, totalTurns, mode, role, port)
    }

    def apply(totalMarkets: Int, tradersPerMarket: Int, totalTurns: Int, mode: Int, role: String, port: Int): Unit = {
        mode match {
            case 1 => {
                // v1
                val agents = generated.example.stockMarket.v1.InitData(totalMarkets, tradersPerMarket)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            case 2 => {
                // v2
                val agents = generated.example.stockMarket.v2.InitData(totalMarkets, tradersPerMarket, 10)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }
        }
    }
}
