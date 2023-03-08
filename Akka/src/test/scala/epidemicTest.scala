package simulation.akka
package test

object epidemic {

    def main(args: Array[String]): Unit = {
        val population: Int = args(0).toInt
        val p: Double = args(1).toDouble
        val isSBM: Boolean = (args(2).toInt == 1)
        val blocks: Int = args(3).toInt
        val totalTurns: Int = args(4).toInt
        val mode: Int = args(5).toInt
        var role: String = "Standalone"
        var port: Int = 25251

        if (args.size > 7) {
            role = args(6)
            port = args(7).toInt
        }

        mode match {
            case 1 => {
                // v1
                val agents = generated.example.epidemic.v1.InitData(population, p, isSBM, blocks)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            case 2 => {
                // v5, cfreq with message-passing
                val cfreq: Int = args(6).toInt
                val agents = generated.example.epidemic.v5.InitData(population, p, isSBM, blocks, cfreq)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            case 3 => {
                // v3,
                val agents = generated.example.epidemic.v3.InitData(population, p, isSBM, blocks)
                API.OptimizationConfig.mergedWorker()
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            case 4 => {
                // v4, partial materialized workers
                if (role == "Driver") {
                    API.Simulate.driver(totalTurns)
                } else if (role.startsWith("Machine-")){
                    val mid = role.stripPrefix("Machine-").toInt
                    val totalMachines = blocks
                    meta.runtime.Actor.lastAgentId = mid * population
                    val agents = generated.example.epidemic.v4.InitData(population, p, isSBM, totalMachines)
                    API.OptimizationConfig.mergedWorker()
                    API.Simulate.machine(mid, agents, totalTurns)
                }
            }
        }
    }
}
