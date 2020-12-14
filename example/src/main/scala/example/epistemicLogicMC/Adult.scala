package example.epistemicLogicMC

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}

import scala.collection.mutable.ListBuffer
import squid.quasi.lift
import MCHelper._
//import lib.Broadcast._
import lib.EpistemicLogic.KnowledgeBase
import lib.EpistemicLogic.Sentence._

@lift
class Adult(val children: List[Child]) extends Actor {

  val knowledgeBase: KnowledgeBase = new KnowledgeBase()
  knowledgeBase.default()

  var future_objs: ListBuffer[Option[Future[Unit]]] = new ListBuffer[Option[Future[Unit]]]()
  var future_objs1: ListBuffer[Option[Future[P[ChildStatus]]]] = new ListBuffer[Option[Future[P[ChildStatus]]]]()

  val allChildrenMuddy: ListBuffer[EpistemicSentence] = new ListBuffer[EpistemicSentence]()
  val allChildrenUnaware: ListBuffer[EpistemicSentence] = new ListBuffer[EpistemicSentence]()

  def see(): Unit = {
    knowledgeBase.forgetAll()
    children.foreach(c =>
      future_objs1.append(asyncMessage(() => c.tell()))
    )
    while (!future_objs1.toList.forall(x => isCompleted(x.get))) {
      waitTurns(1)
    }
    val ans: ListBuffer[P[ChildStatus]] = new ListBuffer[P[ChildStatus]]()

    future_objs1.toList.foreach(o => ans.append(getFutureValue[P[ChildStatus]](o.get)))
    future_objs1.toList.foreach(o => clearFutureObj(o.get))
    future_objs1.clear()

    ans.toList.foreach(c => {
      val f: EpistemicSentence = P(ChildMuddy(c.proposition.id, c.proposition.isMuddy))
      if (c.proposition.isForward){
        knowledgeBase.remember(Set(f, Ka(c.proposition.id, f)))
      } else {
        knowledgeBase.remember(Set(f, NotE(Ka(c.proposition.id, f))))
      }
    })
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
    children.foreach(c => {
      allChildrenMuddy.append(P(ChildMuddy(c.id)))
      allChildrenUnaware.append(NotE(Ka(c.id, P(ChildMuddy(c.id, c.isMuddy)))))
    })

    while (true) {
      see()
      if (knowledgeBase.knowAny(allChildrenMuddy.toSet)) {
        while (knowledgeBase.knowAny(allChildrenUnaware.toSet)) {
          println("There is at least one muddy child.")
          println("Step up if you know whether you are clean or muddy")
          ask()
          see()
        }
      }
      waitTurns(1)
    }
  }
}
