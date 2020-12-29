package example
package stateless_server


import meta.runtime.{Actor, Future}
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class RandomPrinter() extends Actor {

  var server: RandomNumberServer = null
  var i: Int = 0

  def printNum(): Unit = {
    println(i, server.getNumber())
  }

  def main(): Unit = {
    while (true) {
      printNum()
      waitLabel(Turn,1)
      handleMessages()
    }
  }
}
