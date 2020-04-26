package meta.example.actor_merge_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object2(val name: String) extends Actor {
//  val name: String = "object 2"
  var age: Int = 5

  def introduction: Unit = {
    println(s"My name is ${name}. I am ${age}")
  }

  def main(): Unit = {
    while(true){
      introduction
      age = age + 5
      waitTurns(1)
    }
  }
}
