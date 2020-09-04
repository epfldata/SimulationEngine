package meta.example.meeting_example

import meta.deep.runtime.Actor
import meta.classLifting.SpecialInstructions.{handleMessages, waitLabel, waitTime, waitTurns}
import squid.quasi.lift

@lift
class Person(var isBoss: Boolean) extends Actor {

  def main(): Unit = {
    while (true) {
      println("Meeting attendee: " + id)
      if (isBoss) {
        waitLabel("MeetingGroup", 2)
      } else {
        waitLabel("MeetingGroup", 1)
      }
      handleMessages()
    }
  }
}