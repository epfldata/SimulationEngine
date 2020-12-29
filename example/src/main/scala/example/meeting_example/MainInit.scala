package example
package meeting_example

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class MainInit {
  def init() = {
    val Sim1 = new Person(true)
    val Sim2 = new Person(false)
    val Sim3 = new Person(false)

    SimRuntime.registerLabel(Group("Meeting"), 3)

    List(Sim1, Sim2, Sim3)
  }
}

object meetingExample extends App {
  val cls1: ClassWithObject[Person] = Person.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1), mainClass)
}