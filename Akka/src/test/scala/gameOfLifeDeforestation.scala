package simulation.akka
package test

import simulation.akka.API._
import meta.runtime.Actor

object gameOfLifeDeforestation {
    def main(args: Array[String]): Unit = {
        val width = args(0).toInt
        val height: Int = args(1).toInt
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
                // timeseries
                val agents = generated.example.gameOfLife.InitData(width, height)
                API.OptimizationConfig.mergedWorker()
                // default to time series
                val snapshot1 = Simulate(agents, totalTurns, role, port)
                // Simulate.log.log.foreach(x => println(x._2.map(i => i.asInstanceOf[generated.example.gameOfLife.Cell].alive)))
            }

            case 2 => {
                // Q2 snapshot
                val agents = generated.example.gameOfLife.InitData(width, height)
                API.OptimizationConfig.mergedWorker()
                Simulate.log = null
                val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            }

            // For the following experiments, need to change the default value of log variable in 
            // /Akka/src/main/scala/API/Simulate.scala to match the type of Simulate.log, such as GoLQ1Timeseries
            // case 3 => {
            //     // q1
            //     // (logicalClock, Iterable(local_sims.filter(i => i._2.asInstanceOf.alive==1).size))
            //     val agents = generated.example.gameOfLife.InitData(width, height)
            //     API.OptimizationConfig.mergedWorker()
            //     Simulate.log = GoLQ1Timeseries
            //     val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            //     Simulate.log.log.foreach(x => println(x._2))
            // }

            // case 4 => {
            //     // q3
            //     // Need to change 
            //     // (logicalClock, Iterable(local_sims.filter(i => i._2.asInstanceOf.alive==1).size))
            //     val agents = generated.example.gameOfLife.InitData(width, height)
            //     API.OptimizationConfig.mergedWorker()
            //     Simulate.log = GoLQ3Timeseries
            //     val snapshot1 = API.Simulate(agents, totalTurns, role, port)
            //     Simulate.log.log.foreach(x => println(x._2.map(i => i.asInstanceOf[generated.example.gameOfLife.Cell].alive)))
            // }
        }

    }
}