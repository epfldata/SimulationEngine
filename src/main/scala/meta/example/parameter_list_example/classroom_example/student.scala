package meta.example.parameter_list_example.classroom_example

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.util.Random

@lift
class student(val name: String, val advisor: teacher) extends Actor {

  val question: String = Random.nextString(5)

  def introduction(): Unit = {
//    println(s"Student: My name is ${name} and my advisor is ${advisor.name}")
    println(s"Student: My name is ${name} and my advisor is ${advisor.getName()}")
  }

  def ask(): Unit = {
    println("Q: What is " + question + "?")
    println(advisor.answer(question))
  }

  def main(): Unit = {
    introduction()
    while (true) {
      ask()
      waitTurns(1)
    }
  }
}