package meta.example

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val l: ListBuffer[Actor] = ListBuffer()

    l.toList
  }
}