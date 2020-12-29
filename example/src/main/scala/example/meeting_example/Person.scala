package example
package meeting_example

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Person(var isBoss: Boolean) extends Actor {

  def main(): Unit = {
    while (true) {
      println("Meeting attendee: " + id)
      if (isBoss) {
        waitLabel(Group("Meeting"), 2)
      } else {
        waitLabel(Group("Meeting"), 1)
      }
      handleMessages()
    }
  }
}