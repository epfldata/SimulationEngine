package meta.example.stateless_server

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}
import squid.quasi.lift

@lift
class RandomPrinter(val server: RandomNumberServer) extends Actor {
  var i: Int = 0

  def printNum(): Unit = {
    println(i, server.getNumber())
  }

  def main(): Unit = {
    while (true) {
      printNum()
      waitTurns(1)
      handleMessages()
    }
  }
}
