package example
package nb_methods_example

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Object2 extends Actor{

  val deposit: Int = 500

  def get(simId: Actor.AgentId, randArg: Double, randArg2: Double): Int = {
//    println("Async msg" + msgId + ": remote method with wait 1 " +
//      " arg: " + randArg +  " arg: " + randArg2)
    println("Async msg received from " + simId)
    waitLabel("turn",1)
//    println("Finished processing " + simId)
    deposit
  }

  def main(): Unit = {
    while (true) {
      handleMessages()
      waitLabel("turn",1)
    }
  }
}
