package example.epistemicLogicMC

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}

import scala.collection.mutable.ListBuffer
import squid.quasi.{lift}
import MCHelper._
import library.Broadcast._

@lift
class Adult(val children: List[Child]) extends Actor {

  var future_objs: ListBuffer[Option[Future[Unit]]] = new ListBuffer[Option[Future[Unit]]]()
  var future_objs1: ListBuffer[Option[Future[ChildStatus]]] = new ListBuffer[Option[Future[ChildStatus]]]()

  def observe(): List[ChildStatus] = {
    children.foreach(c => future_objs1.append(asyncMessage(() => c.seen())))
    while (!(future_objs1.nonEmpty && future_objs1.toList.forall(x => isCompleted(x.get)))) {
      waitTurns(1)
    }
    val ans: ListBuffer[ChildStatus] = new ListBuffer[ChildStatus]()

    future_objs1.toList.foreach(o => ans.append(getFutureValue[ChildStatus](o.get)))
    future_objs1.toList.foreach(o => clearFutureObj(o.get))
    future_objs1.clear()
    ans.toList
  }

  def atLeastOneMuddy(observation: List[ChildStatus]): Boolean = {
    observation.exists(c => c.isMuddy)
  }

  def atLeastOneUnaware(observation: List[ChildStatus]): Boolean = {
    observation.exists(c => !c.isForward)
  }

  // Ask all children simultaneously, and wait for all children to answer
  def ask(): Unit = {
//    val broadcast: Broadcast[Child, Unit] = new Broadcast[Child, Unit](this, children, child => child.answer())
//    broadcast.waitUntilAllReceived

    children.foreach(c => future_objs.append(asyncMessage(() => c.answer())))
    while (!(future_objs.nonEmpty && future_objs.toList.forall(x => isCompleted(x.get)))) {
      waitTurns(1)
    }
    future_objs.toList.foreach(o => clearFutureObj(o.get))
    future_objs.clear()
  }

  def main(): Unit = {
    while (true) {
      var observations: List[ChildStatus] = observe()
      if (atLeastOneMuddy(observations) && atLeastOneUnaware(observations)) {
        println("There is at least one muddy child")
        var unaware: Boolean = atLeastOneUnaware(observations)
        while (unaware) {
          println("Step up if you know whether you are clean or muddy")
          ask()
          unaware = atLeastOneUnaware(observe())
        }
        observations = observe()
      }
      waitTurns(1)
    }
  }
}
