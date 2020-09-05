package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions.{handleMessages, waitTurns}
import meta.deep.runtime.Actor
import squid.quasi.lift
import meta.deep.runtime.Actor.AgentId

@lift
class Object2 extends Actor{

  val deposit: Int = 500

  def get(simId: AgentId, randArg: Double, randArg2: Double): Int = {
//    println("Async msg" + msgId + ": remote method with wait 1 " +
//      " arg: " + randArg +  " arg: " + randArg2)
    println("Async msg received from " + simId)
    waitTurns(1)
//    println("Finished processing " + simId)
    deposit
  }

  def main(): Unit = {
    while (true) {
      handleMessages()
      waitTurns(1)
    }
  }
}
