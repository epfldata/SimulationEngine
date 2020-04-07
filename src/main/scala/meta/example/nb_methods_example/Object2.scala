package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions.waitTurns
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class Object2 extends Actor{

  def get(age: Int, sibling_age: Int): Unit = {
    println("Greeting from object2 " + age + " sibling's age is " + sibling_age)
  }

  def main(): Unit = {
    while (true) {
      waitTurns(2)
    }
  }
}
