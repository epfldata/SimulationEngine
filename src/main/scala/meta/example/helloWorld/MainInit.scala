package meta.example.helloWorld

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val p1 = new TimidPerson("Alice")
    val p2 = new OutgoingPerson("Bob")
    val p3 = new TimidPerson("Eve")

    p1.friendList.append(p2)
    p2.friendList.append(p1, p3)
    p3.friendList.append(p2)
    List(p1, p2, p3)
  }
}