package meta.example.TwoPL_example
import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import meta.deep.runtime.Actor.AgentId
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

@lift
class Transaction() extends Actor{
  private val secret: List[Int] = List(1, 2)
  var shared: List[SharedObject] = null

  def read(): String = {
    shared(0).get() + shared(1).get()
  }

  def main(): Unit = {
    while (true) {
      println("The secret words are " + read())
      waitTurns(1)
    }
  }
}