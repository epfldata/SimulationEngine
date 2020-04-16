package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions.waitTurns
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Object3 extends Actor{

  def get(): Unit = {
    println("Greeting from object3")
  }

  def getWR(): Int = {
    println("getWR is called from object3!")
    11
  }

  def main(): Unit = {
    while (true) {
      waitTurns(1)
    }
  }
}