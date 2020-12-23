package example
package codegen_example

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Market extends Actor {
  var goods: List[String] = List[String]()

  def sell(unit: Int): Unit = {
    println("Market sells: " + unit)
  }

  def sell2(unit: Int): Int = {
    println("Market sells: " + unit)
    42
  }

  def recursiveTest(l: List[Int]): Unit = {
    if (l.isEmpty) {} else {
      recursiveTest(l.tail)
      println(l.head)
    }
  }

  def main(): Unit = {
    var x = 21
    x = x + 1
    var y = 11
    var z = x + y
    println(z)

    while (true) {
//      handleMessages()
      //x = x + 1
      //println("Binding test:", x)
      sell(10)
      recursiveTest(List(10, 20, 30))
      waitLabel("turn",1)
    }
  }

}
