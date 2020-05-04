package meta.example.actor_merge_example.no_communication

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val ob1: object1 = new object1("Alice")
    val ob2: object2 = new object2("Bob")
    List(ob2, ob1)
  }
}