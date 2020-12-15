package example.meeting_example

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def init() = {
    val Sim1 = new Person(true)
    val Sim2 = new Person(false)
    val Sim3 = new Person(false)

    Actor.waitLabels("MeetingGroup") = 3

    List(Sim1, Sim2, Sim3)
  }
}