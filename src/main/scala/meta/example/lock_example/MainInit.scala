package meta.example.lock_example

import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()

    val sharedObject: SharedObject = new SharedObject()

//    l.append(sharedObject)

    (1 to 5).foreach(i => {
      val competitor1: Competitor1 = new Competitor1()
      competitor1.sharedObj = sharedObject
      l.append(competitor1)
    })

    l.append(sharedObject)
    l.toList

  }
}
