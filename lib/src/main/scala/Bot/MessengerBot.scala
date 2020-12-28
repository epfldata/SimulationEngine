package lib.Bot

import meta.classLifting.SpecialInstructions._
import squid.quasi.lift

@lift
class MessengerBot(val sender: Actor) extends Actor {

  var deleted: Boolean = false

  def waitUntilAllReceived(future_objs: List[Option[Future[Unit]]]): Unit = {
    while (!(future_objs.nonEmpty && future_objs.forall(x => sender.isCompleted(x.get)))) {
      waitLabel("turn",1)
    }
    future_objs.foreach(o => sender.clearFutureObj(o.get))
    deleted = true
  }

  def main(): Unit = {
    while (!deleted) {
      handleMessages()
      waitLabel("turn", 1)
    }
  }
}