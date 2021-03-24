package lib
package Bot

import meta.classLifting.SpecialInstructions._
import meta.runtime.Future
import squid.quasi.lift

@lift
class MessengerBot() extends Actor {

  def waitUntilAllReceive(future_objs: List[Future[Any]]): Unit = {
    while (!(future_objs.nonEmpty && future_objs.forall(x => x.isCompleted))) {
      waitLabel(Turn,1)
    }
    future_objs.foreach(o => o.popValue)
    deleted = true
  }

  def waitUntilAllReply(future_objs: List[Future[Any]]): List[Any] = {
    while (!(future_objs.nonEmpty && future_objs.forall(x => x.isCompleted))) {
      waitLabel(Turn,1)
    }
    val ans: List[Any] = future_objs.map(o => o.popValue.get)
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