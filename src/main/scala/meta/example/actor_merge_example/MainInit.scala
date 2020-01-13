package meta.example.actor_merge_example

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()

    val actor_1 = new actor1
    val actor_2 = new actor2

    l.append(actor_1, actor_2)

    l.toList
  }
}