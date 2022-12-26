package simulation.spark
package examples

object stockMarket {
    def main(args: Array[String]): Unit = {
        val totalMarkets = args(0).toInt
        val tradersPerMarket: Int = args(1).toInt
        val totalTurns: Int = args(2).toInt
        val mode: Int = args(3).toInt

        mode match {
            case 1 => {
                // callAndForget
                val agents = generated.example.stockMarket.v1.InitData(totalMarkets, tradersPerMarket)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }

            case 2 => {
                // v2, cfreq
                val cfreq: Int = args(4).toInt
                val agents = generated.example.stockMarket.v2.InitData(totalMarkets, tradersPerMarket, cfreq)
                val snapshot1 = API.Simulate(agents, totalTurns)
            }
        }
    }
}
