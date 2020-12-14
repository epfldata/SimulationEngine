package example.epistemicLogicMC

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}
import meta.deep.runtime.Actor.AgentId
import scala.collection.mutable.ListBuffer

import squid.quasi.lift
import library.EpistemicLogic.Sentence._
import library.EpistemicLogic.KnowledgeBase

import MCHelper._

@lift
class Child(val isMuddy: Boolean) extends Actor {

  var neighbors: List[Child] = Nil
  var neighborIds: ListBuffer[AgentId] = new ListBuffer[AgentId]()

  var epoch: Int = 0
  var isForward: Boolean = false

  val knowledgeBase: KnowledgeBase = new KnowledgeBase()
  knowledgeBase.default()

  // RPC
  def tell(): ChildStatus = {
    ChildStatus(id, isMuddy, isForward, epoch)
  }

  // Child won't answer if there are neighbors they haven't seen
  // Children answers simultaneously; can't change one's answer based on observing the same round behaviour
  // RPC
  def answer(): Unit = {
    if (knowledgeBase.know(seeAllNeighbor())) {
      epoch = epoch + 1
      knowledgeBase.recordLearning(epoch, Set(hearParent(epoch)))
      if (!isForward) {        // child only answers once
        knowledgeBase.recordLearning(epoch, Set(announce((neighborIds ++ List(id)).toList)))
        val fact: EpistemicSentence = childMuddy(id, isMuddy)
        if (knowledgeBase.know(fact)  || knowledgeBase.know(Ka(id, fact))) {
          isForward = true
          println("Child " + id + " steps forward!")
        } else {
          println("Child " + id + " stays still!")
        }
      }
    }
  }

  // non-RPC
  def think(c: ChildStatus): Unit = {
//      println("Child thinks " + c + " epoch " + epoch)
    val announce: EpistemicSentence = hearParent(epoch)
    // A child thinks only after hearing from the parent in this epoch
    // If the observation has an earlier epoch, than ignore it because already seen it
    if (knowledgeBase.know(announce) && !isForward && c.epoch == epoch) {
      val ans: Set[EpistemicSentence] = if (c.isForward) {
        val f: EpistemicSentence = childMuddy(c.id, c.isMuddy)
        knowledgeBase.recordLearning(epoch, Set(Ka(c.id, f)))
        counterExampleLearning(knowledgeBase.speculate(Set(Ka(c.id, NotE(f)))))
      } else {
        inferOtherAgent(c.id, (id :: neighborIds.toList).filterNot(x => x == c.id), epoch)
      }
      knowledgeBase.recordLearning(epoch, ans)
    }
  }

  val future_objs: ListBuffer[Option[Future[ChildStatus]]] = new ListBuffer[Option[Future[ChildStatus]]]()

  // non-RPC
  def see(): List[ChildStatus] = {
    neighbors.foreach(n =>
      future_objs.append(asyncMessage(() => n.tell())))
    waitTurns(1)
    handleMessages()
    // see all neighbors
    while (!future_objs.toList.forall(x => isCompleted(x.get))) {
      waitTurns(1)
      handleMessages()
    }

    knowledgeBase.remember(Set(seeAllNeighbor()))

    val ans: ListBuffer[ChildStatus] = new ListBuffer[ChildStatus]()
    future_objs.toList.foreach(o => ans.append(getFutureValue[ChildStatus](o.get)))
    future_objs.toList.foreach(o => clearFutureObj(o.get))
    future_objs.clear()

    assert(ans.size == neighbors.size)

    // remember what they see
    ans.toList.foreach(c => {
      val f: EpistemicSentence = childMuddy(c.id, c.isMuddy)
      knowledgeBase.recordLearning(epoch, Set(Ka(id, f)))
    })

    ans.toList
  }

  def main(): Unit = {
    neighbors.foreach(n => neighborIds.append(n.id))
    while (true) {
      see().foreach(s => {
        think(s)
      })
//      println("==== Agent " + id + " knows epochs " + knowledgeBase.learningProcess.keys + " ==== ")
//      knowledgeBase.printLearningProcess(epoch)
      handleMessages()
    }
  }
}

