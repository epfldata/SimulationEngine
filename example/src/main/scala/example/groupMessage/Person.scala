package example
package groupMessage


import meta.runtime.{Actor, Future}
import scala.collection.mutable.Set
import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

@lift
class Person(var name: String) extends Actor {

  var future_objs: ListBuffer[Option[Future[Boolean]]] = new ListBuffer[Option[Future[Boolean]]]()
  var readByEveryone: Boolean = true
  var group: List[Person] = Nil

  // Wait until all have received before sending the next
  def groupChat(): Unit = {
    if (Random.nextBoolean() && readByEveryone) {
      println(name + " sends a group chat to friends!")
      group.foreach(x => future_objs.append(asyncMessage(() => x.receive())))
    }
  }

  def doThings(): Unit = {
    val r: Int = Random.nextInt(6)
    if (r < 3) {
      println(name + " works!")
      waitLabel("turn",3)
    }
    else if (r < 5) {
      println(name + " sleeps!")
      waitLabel("turn",2)
    }
    else {
      println(name + " eats and exercises!")
      waitLabel("turn",1)
    }
  }

  def receive(): Boolean = {
    println(name + " receives the message!")
    true
  }

  def checkResponse(): Unit = {
    if (!future_objs.toList.forall(x => isCompleted(x.get))) {
      readByEveryone = false
    } else {
      if (!future_objs.isEmpty){
        println(name + " message read by everyone")
        future_objs.toList.foreach(o => clearFutureObj(o.get))
        future_objs.clear()
      }
    }
  }

  def main(): Unit = {
    while(true){
      doThings()
      groupChat()
      checkResponse()
      handleMessages()
    }
  }
}