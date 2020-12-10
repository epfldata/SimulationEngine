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
  var action: Option[StepForward] = None

  val knowledgeBase: KnowledgeBase = new KnowledgeBase()
  knowledgeBase.default()

  val neighborObservations: KnowledgeBase = new KnowledgeBase()
  neighborObservations.default()

  var lookingAround: Boolean = true

  // other agents are able to see the real state of the agent
  def state(): EpistemicSentence = {
    if (isMuddy) {
      P("Child " + id + " is muddy")
    } else {
      NotE(P("Child " + id + " is muddy"))
    }
  }

  // isMuddy, isForward
  def seen(): (Boolean, Boolean) = {
    (isMuddy, action.isDefined)
  }

  def answer(): Unit = {
    // children won't answer if there are still neighbors they haven't seen
    if (!lookingAround) {
      epoch = epoch + 1
      println("Child " + id + " hears!")
      if (!action.isDefined) {  // child only answers once
        knowledgeBase.learn(Set(announce((neighborIds ++ List(id)).toList)))
        val fact: EpistemicSentence = state()

        if (knowledgeBase.know(fact)  || knowledgeBase.know(Ka(id, fact))) {
          action = Some(StepForward(fact))
          println("Child " + id + " steps forward!")
        }

        neighbors.foreach(n => asyncMessage(() => n.see(action, id, epoch)))
      }
    }
  }

  def see(action: Option[StepForward], i: AgentId, it: Int): Unit = {
    val ans: Set[EpistemicSentence] = if (action.isDefined) {
      val f: EpistemicSentence = action.get.f
      knowledgeBase.learn(Set(Ka(i, f)))
      counterExampleLearning(knowledgeBase.speculate(Set(Ka(i, NotE(f)))))
    } else {
      inferOtherAgent(i, (id :: neighborIds.toList).filterNot(x => x == i), it)
    }
    knowledgeBase.learn(ans)
  }

  def seeNeighbor(i: AgentId, muddy: Boolean): Unit = {
    if (muddy) {
      neighborObservations.learn(Set[EpistemicSentence](pTemplate(i)))
    } else {
      neighborObservations.learn(Set[EpistemicSentence](NotE(pTemplate(i))))
    }
  }

  def bootstrap(): Unit = {
    neighbors.foreach(n => neighborIds.append(n.id))
    neighbors.foreach(n => asyncMessage(() => n.seeNeighbor(id, isMuddy)))
    waitTurns(1)
    handleMessages()
    lookAround()
    knowledgeBase.learn(neighborObservations.getKnowledgeBase)
  }

  def lookAround(): Boolean = {
    lookingAround = (neighborObservations.getKnowledgeBase.size != neighbors.size)
    lookingAround
  }

  def main(): Unit = {
    bootstrap()
    while (true) {
      waitTurns(1)
      handleMessages()
    }
  }
}

