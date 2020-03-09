package meta.example.actor_merge_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class object1(val name: String) extends Actor {
//  val name: String = "object 1"
  var age: Int = 5

  def introduction: Unit = {
    println(s"Je m'appelle ${name}. J'ai ${age} ans.")
  }

  def main(): Unit = {
    while(true){
      introduction
      age = age + 1
      waitTurns(1)
    }
  }
}