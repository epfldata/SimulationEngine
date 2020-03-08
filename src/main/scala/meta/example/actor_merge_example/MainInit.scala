package meta.example.actor_merge_example

import meta.deep.runtime.Actor
import squid.quasi.lift

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val ob1: object1 = new object1
    val ob2: object2 = new object2
    List(ob2, ob1)
  }
}