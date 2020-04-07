package meta.example.nb_methods_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.{lift, dbg_lift}

@lift
class Object1(var n1: Object2, var n2: Object3) extends Actor {

  def hello(name: String): Unit = {
    println("Hello " + name)
//    waitTurns(1)
  }

  def main(): Unit = {
    while (true) {

      val msg1 = ()=> n1.get(10, 15)
      val msg2 = ()=> n2.get()
      val msg3 = ()=> hello("world") // local msg
//      batchMessages(msg3, msg1, msg2)
      batchMessages(msg1, msg2, msg3)

      // Example for without batching
//      n1.get(10, 15)
//      n2.get()
//      hello("world")
      waitTurns(1)
    }
  }
}
