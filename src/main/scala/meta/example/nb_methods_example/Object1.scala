package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.{lift, dbg_lift}

@lift
class Object1(var n1: Object2, var n2: Object3) extends Actor {

  def hello(name: String): String = {
    println("Name is " + name)
    "hello! " + name
  }

  def main(): Unit = {
    while (true) {
      n1.get(30, 30)
      n1.get(0, 0)

      val msg1 = ()=> n1.get(10, 15)
      val msg2 = ()=> n2.get()
      batchMessages(msg1, msg2)
      waitTurns(1)
    }
  }
}
