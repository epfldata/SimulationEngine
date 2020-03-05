package meta.example.TwoPL_example
import meta.deep.runtime.Actor
import squid.quasi.lift

import scala.collection.mutable.ListBuffer

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()
    val sharedData: List[String] = List("1 China", "2 Switzerland", "3 France", "4 Spain", "5 Italy")
    val shared: ListBuffer[SharedObject] = ListBuffer[SharedObject]()

    sharedData.foreach(word => {
      val shared_object: SharedObject = new SharedObject()
      shared_object.secret = word
      shared += shared_object
      l += shared_object
    })

    val newTx: Transaction = new Transaction()
    newTx.shared = shared.toList
    l += newTx

    l.toList
  }
}