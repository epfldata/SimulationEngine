package simulation.akka
package test

object stockMarket {
    def main(args: Array[String]): Unit = {
        val totalMarkets = args(0).toInt
        val tradersPerMarket: Int = args(1).toInt
        val totalTurns: Int = args(2).toInt
        val mode: Int = args(3).toInt
        var role: String = "Standalone"
        var port: Int = 25251
        if (args.size > 5) {
            role = args(4)
            port = args(5).toInt
        }

        mode match {
            case 1 => {
                // v1
                val agents = generated.example.stockMarket.v1.InitData(totalMarkets, tradersPerMarket)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            case 2 => {
                // v2
                val cfreq: Int = args(4).toInt
                val agents = generated.example.stockMarket.v2.InitData(totalMarkets, tradersPerMarket, cfreq)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            case 3 => {
                // v3
                val agents = generated.example.stockMarket.v3.InitData(totalMarkets, tradersPerMarket)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }
        }
    }
}
