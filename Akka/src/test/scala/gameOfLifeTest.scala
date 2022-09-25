package simulation.akka
package test

import org.scalatest.{FlatSpec}

class gameOfLife extends FlatSpec {
    import meta.deep.IR.Predef._
    // "Game of life RPC example" should "run" in {
    //     val agents = generated.example.gameOfLifeRPC.InitData(100, 100)
    //     val snapshot1 = AkkaRun(agents, 7)
    // }

    "Game of life RPC-oneside example" should "run" in {
        val agents = generated.example.gameOfLifeRPCOneSide.InitData(100, 1000)
        // val snapshot1 = AkkaRun(agents, 7)
        val snapshot1 = API.Simulate(agents, 7, true)
    }

    // "Game of life example" should "run" in {
    //     val agents = generated.example.gameOfLife.InitData(10, 10)
    //     val snapshot1 = AkkaRun(agents, 7)
    // }
}