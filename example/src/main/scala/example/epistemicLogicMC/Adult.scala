package example.epistemicLogicMC

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}
import scala.collection.mutable.ListBuffer
import squid.quasi.lift

@lift
class Adult(val children: List[Child]) extends Actor {

  var future_objs: ListBuffer[Option[Future[Unit]]] = new ListBuffer[Option[Future[Unit]]]()

  def observe(): Boolean = {
     children.exists(c => !c.isAware)
//     !children.forall(c => c.isAware)
  }

  // Ask all children simultaneously, and wait for all children to answer
  def ask(): Unit = {
    println("There is at least one muddy child. Speak out if you know whether you are clean or muddy")
    children.foreach(c => future_objs.append(asyncMessage(() => c.answer())))
    while (!(future_objs.nonEmpty && future_objs.toList.forall(x => isCompleted(x.get)))) {
      waitTurns(1)
    }
    future_objs.toList.foreach(o => clearFutureObj(o.get))
    future_objs.clear()
  }

  def main(): Unit = {
    while (true) {
      if (observe()) {
        ask()
      }
      waitTurns(1)
    }
  }
}
