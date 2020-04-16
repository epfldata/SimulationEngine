package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions.waitTurns
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Object2 extends Actor{

  def get(age: Int, sibling_age: Int): Int = {
    println("object2!")
    age + sibling_age
//    waitTurns(1)
  }

  def getWR(): String = {
    println("getWR is called from object2!")
    "Greeting from object2 "
  }

  def main(): Unit = {
    while (true) {
      waitTurns(1)
    }
  }
}
