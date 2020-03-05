package meta.example.new_instance_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

@lift
class object2 extends Actor {
  val object_queue = ListBuffer[object1]()

  def addObject1(): Unit = {
    object_queue.append(new object1)
  }

  def main(): Unit = {
    while(true){
      addObject1()
      waitTurns(1)
    }
  }
}