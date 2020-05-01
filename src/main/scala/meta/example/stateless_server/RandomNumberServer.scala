package meta.example.stateless_server

import meta.deep.runtime.Actor
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import scala.util.Random.nextInt

@lift
class RandomNumberServer extends Actor {
  def getNumber(): Int = {
    nextInt(1000)
  }

  def getDelayedNumber(): Int = {
    waitTurns(1)
    nextInt(50)
    // println(nextInt(50))
  }

  def main(): Unit = {
    while (true) {
      waitTurns(1)
    }
  }
}
