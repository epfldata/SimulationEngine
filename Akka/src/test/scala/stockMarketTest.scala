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
        if (args.size > 4) {
            role = args(4)
            if (role == "Driver") {
                port = 25251
            } else {
                port = 0
            }
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
                val cfreq: Int = args(4).toInt
                val agents = generated.example.stockMarket.v3.InitData(totalMarkets, tradersPerMarket, cfreq)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            case 4 => {
                // v4
                if (role == "Driver") {
                    API.Simulate.driver(totalTurns)
                } else if (role.startsWith("Machine-")){
                    val mid = role.stripPrefix("Machine-").toInt
                    val totalMachines = args(5).toInt
                    val agents = generated.example.stockMarket.v4.InitData(tradersPerMarket, mid, totalMachines)
                    API.OptimizationConfig.mergedWorker()
                    API.Simulate.machine(mid, agents, totalTurns)
                }
            }
        }
    }
}
