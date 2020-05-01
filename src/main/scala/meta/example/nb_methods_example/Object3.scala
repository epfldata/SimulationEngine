package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions.waitTurns
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Object3 extends Actor{

  def get(msgId: Int): Unit = {
    println("Async msg" + msgId)
  }

  def getWR(msgId: Int): String = {
    println("Async msg" + msgId)
    "Object3"
  }

  def main(): Unit = {
    while (true) {
      waitTurns(1)
    }
  }
}