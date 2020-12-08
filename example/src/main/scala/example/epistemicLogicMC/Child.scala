package example.epistemicLogicMC

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.{Actor, Future}
import meta.deep.runtime.Actor.AgentId
import scala.collection.mutable.ListBuffer
import scala.collection.Set

import squid.quasi.lift
import library.EpistemicLogic.Sentence._
import library.EpistemicLogic.Solver._
import MCHelper._

class ChildT extends Actor {

  var knowledgeBase: Set[EpistemicSentence] = Set[EpistemicSentence]()
  val isMuddy: Boolean = false

  // other agents are able to see the real state of the agent
  def state(): EpistemicSentence = {
    if (isMuddy) {
      P("Child " + id + " is muddy")
    } else {
      NotE(P("Child " + id + " is muddy"))
    }
  }

  def isAware: Boolean = {
    val fact: EpistemicSentence = state()
    val ans: Boolean = knowledgeBase.contains(Ka(id, fact)) || knowledgeBase.contains(fact)
    if (ans) println("Child " + id + " is aware: " + ans)
    ans
  }

  def learn(e: Set[EpistemicSentence]): Unit = {
    println("Child " + id + " learns " + e)
    knowledgeBase =  deduction(knowledgeBase.union(e))
  }

  def lookAround(neighbors: List[ChildT]): Unit = {
    var observations: Set[EpistemicSentence] = Set[EpistemicSentence]()

    neighbors.foreach(n => {
      observations = observations.union(Set(n.state()))
    })

    learn(observations)

    val neighborKnows: Set[EpistemicSentence] = observations.flatMap(x => {
      observations.flatMap(y => {
        if (getNeighborId(x.toString) != getNeighborId(y.toString)) {
          Set[EpistemicSentence](
            Ka(id, Ka(getNeighborId(x.toString), y)),
            Ka(id, Ka(getNeighborId(y.toString), x)),
//            Ka(id, Ka(getNeighborId(x.toString), Ka(id, y))),
//            Ka(id, Ka(getNeighborId(y.toString), Ka(id, x))),
          )
        } else {
          Set[EpistemicSentence]()
        }
      })
    })

    learn(neighborKnows)
  }

  // child speculates what-if based on observing others' actions
  // (id, isMuddy, isAware)
  def inspect(act: (AgentId, Boolean, Boolean), it: Int, neighbors: List[AgentId]): Set[EpistemicSentence] = {
    val fact: EpistemicSentence = if (act._2) {
      pTemplate(act._1)
    } else {
      NotE(pTemplate(act._1))
    }

    act match {
      case (i, _, false) =>
        inferOtherAgent(i, neighbors.filterNot(x => x == i) ++ List(id), it)
      case (i, _, true) =>
        learn(Set(Ka(i, fact)))
        inferOtherAgent(i, neighbors.filterNot(x => x == i) ++ List(id), it-1)
        speculate(Set(Ka(i, NotE(fact))), knowledgeBase).map(x => x match {
          case Ka(i, e) =>
//                        println("Learned forward case 1" + e);
            Ka(i, NotE(e))
          case _ => {
//                        println("Learned forward case 2 " + x);
            NotE(x)}
        })
    }
  }
}

// keyword override is not preserved after lifting
@lift
class Child(override val isMuddy: Boolean) extends ChildT {
  var neighbors: List[Child] = Nil
  var neighborIds: ListBuffer[AgentId] = new ListBuffer[AgentId]()
  var epoch: Int = 0

  def answer(): Unit = {
    println("Child " + id + " hears the parent!")
    epoch = epoch + 1
    learn(Set(announce((neighborIds ++ List(id)).toList)))
    neighbors.foreach(n => asyncMessage(() => n.hear(id, isMuddy, isAware, epoch)))
  }

  // map method results in List.Coll in generated code
  def hear(nid: AgentId, nIsMuddy: Boolean, nIsAware: Boolean, it: Int): Unit = {
    learn(inspect((nid, nIsMuddy, nIsAware), it, neighborIds.toList))
  }

  def main(): Unit = {
    // setIsMuddy()
    neighbors.foreach(n => neighborIds.append(n.id))
    lookAround(neighbors)
    while (true) {
      handleMessages()
      waitTurns(1)
    }
  }
}

