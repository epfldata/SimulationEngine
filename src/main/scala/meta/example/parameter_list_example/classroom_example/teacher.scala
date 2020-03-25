package meta.example.parameter_list_example.classroom_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class teacher(val name: String) extends Actor {

  val school: String = "Princeton"

  def getName(): String = {
    name
  }

  def introduction(): Unit = {
    println(s"Professor: Moi, c'est ${name} et Je travaille à ${school}")
  }

  def answer(question: String): String = {
    s"A: ${question} is ${question}"
  }

  def main(): Unit = {
    introduction()
    while (true) {
      waitTurns(1)
    }
  }
}
