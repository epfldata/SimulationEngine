package lib.Bot

import meta.classLifting.SpecialInstructions._
import meta.runtime.Future
import squid.quasi.lift

@lift
class MessengerBot() extends Actor {

  var deleted: Boolean = false

  def waitUntilAllReceive(future_objs: List[Option[Future[Any]]]): Unit = {
    while (!(future_objs.nonEmpty && future_objs.forall(x => SimRuntime.isCompleted(x.get)))) {
      waitLabel("turn",1)
    }
    future_objs.foreach(o => SimRuntime.clearFutureObj(o.get))
    deleted = true
  }

  def waitUntilAllReply(future_objs: List[Option[Future[Any]]]): List[Any] = {
    while (!(future_objs.nonEmpty && future_objs.forall(x => SimRuntime.isCompleted(x.get)))) {
      waitLabel("turn",1)
    }
    val ans: List[Any] = future_objs.map(o => SimRuntime.getFutureValue[Any](o.get))
    future_objs.foreach(o => SimRuntime.clearFutureObj(o.get))
    deleted = true
    ans
  }

  def main(): Unit = {
    while (!deleted) {
      handleMessages()
      waitLabel("turn", 1)
    }
  }
}