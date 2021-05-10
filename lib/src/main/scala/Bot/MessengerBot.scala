package lib
package Bot

import meta.classLifting.SpecialInstructions._
import meta.runtime.Future
import squid.quasi.lift

@lift
class MessengerBot() extends Actor {

  def waitUntilAllReceive[T](future_objs: List[Future[T]]): Unit = {
    while (!(future_objs.nonEmpty && future_objs.forall(x => x.isCompleted))) {
      waitLabel(Turn,1)
    }
    future_objs.foreach(o => o.popValue)
    deleted = true
  }

  def waitUntilAllReply[T](future_objs: List[Future[T]]): List[T] = {
    while (!(future_objs.nonEmpty && future_objs.forall(x => x.isCompleted))) {
      waitLabel(Turn,1)
    }
    val ans = future_objs.map(o => o.popValue.get.asInstanceOf[T])
    deleted = true
    ans
  }

  def main(): Unit = {
    while (!deleted) {
      handleMessages()
      waitLabel(Turn, 1)
    }
  }
}