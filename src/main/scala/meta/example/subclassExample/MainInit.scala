package meta.example.subclassExample
import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

@lift 
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()

    val subclass1 = new Subclass1
    val subclass2 = new Subclass2
    l.append(subclass1, subclass2)

    l.toList
  }
}
