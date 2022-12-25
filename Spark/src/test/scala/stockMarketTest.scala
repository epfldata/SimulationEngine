package simulation.spark
package test

object stockMarketTest {
    def main(args: Array[String]): Unit = {
        val totalMarkets = args(0).toInt
        val tradersPerMarket: Int = args(1).toInt
        val totalTurns: Int = args(2).toInt
        val mode: Int = args(3).toInt
        apply(totalMarkets, tradersPerMarket, totalTurns, mode)
    }

    def apply(totalMarkets: Int, tradersPerMarket: Int, totalTurns: Int, mode: Int): Unit = {
        mode match {
            case 1 => {
                // callAndForget
                val agents = generated.example.stockMarket.v1.InitData(totalMarkets, tradersPerMarket)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }

            case 2 => {
                // v2, cfreq
                val agents = generated.example.stockMarket.v2.InitData(totalMarkets, tradersPerMarket, 0)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }
        }
    }
}
