package example
package stateless_server



import scala.util.Random.nextInt

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class RandomNumberServer extends Actor {
  def getNumber(): Int = {
    nextInt(1000)
  }

  def getDelayedNumber(): Int = {
    waitLabel("turn",1)
    nextInt(50)
  }

  def main(): Unit = {
    while (true) {
      waitLabel("turn",1)
      handleMessages()
    }
  }
}
