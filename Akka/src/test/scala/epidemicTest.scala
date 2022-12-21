package simulation.akka
package test

object epidemicTest {
    def main(args: Array[String]): Unit = {
        val totalTurns: Int = 200

        val p: Double = args(0).toDouble
        val isSBM: Boolean = (args(1).toInt == 1)
        val blocks: Int = args(2).toInt

        val agents = generated.example.epidemic.v1.InitData(1000, p, isSBM, blocks)
        val snapshot = API.Simulate(agents, totalTurns)
    }
}
