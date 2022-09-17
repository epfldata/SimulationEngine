package simulation.akka
package test

import meta.API._
import org.scalatest.{FlatSpec}

class gameOfLife extends FlatSpec {
    import meta.deep.IR.Predef._

    "Game of life example" should "run" in {
        val agents = generated.example.gameOfLife.InitData(5, 5)
        val snapshot1 = AkkaRun(agents, 10)
    }
}