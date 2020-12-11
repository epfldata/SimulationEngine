package example.epistemicLogicMC

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}

import scala.collection.mutable.ListBuffer
import squid.quasi.lift
import MCHelper._
import library.Broadcast._
import library.EpistemicLogic.KnowledgeBase
import library.EpistemicLogic.Sentence._

@lift
class Adult(val children: List[Child]) extends Actor {

  val knowledgeBase: KnowledgeBase = new KnowledgeBase()
  knowledgeBase.default()

  var future_objs: ListBuffer[Option[Future[Unit]]] = new ListBuffer[Option[Future[Unit]]]()
  var future_objs1: ListBuffer[Option[Future[ChildStatus]]] = new ListBuffer[Option[Future[ChildStatus]]]()

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
    val ans: ListBuffer[ChildStatus] = new ListBuffer[ChildStatus]()

    future_objs1.toList.foreach(o => ans.append(getFutureValue[ChildStatus](o.get)))
    future_objs1.toList.foreach(o => clearFutureObj(o.get))
    future_objs1.clear()

    ans.toList.foreach(c => {
      val f: EpistemicSentence = schema(c.id, c.isMuddy)
      if (c.isForward){
        knowledgeBase.learn(Set(f, Ka(c.id, f)))
      } else {
        knowledgeBase.learn(Set(f, NotE(Ka(c.id, f))))
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
      allChildrenMuddy.append(schema(c.id))
      allChildrenUnaware.append(NotE(Ka(c.id, schema(c.id, c.isMuddy))))
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
