package simulation.akka
package test

import org.scalatest.{FlatSpec}

class gameOfLife extends FlatSpec {
    import meta.deep.IR.Predef._

    val width: Int = 100
    val height: Int = 100
    val totalTurns: Int = 50

    "Game of life RPC-oneside example concurrent" should "run" in {
        val agents = generated.example.gameOfLifeRPCOneSide.InitData(width, height)
        API.OptimizationConfig.concurrentWorker()
        val snapshot1 = API.Simulate(agents, totalTurns)
    }

    "Game of life RPC-oneside example direct method calls" should "run" in {
        val agents = generated.example.gameOfLifeRPCOneSideMultiversion.InitData(width, height)
        API.OptimizationConfig.directMethodCall()
        val snapshot1 = API.Simulate(agents, totalTurns)
    }

    "Game of life example messaging" should "run" in {
        val agents = generated.example.gameOfLife.InitData(width, height)
        API.OptimizationConfig.mergedWorker()
        val snapshot1 = API.Simulate(agents, totalTurns)
    }
}