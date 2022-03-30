package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import meta.runtime.Actor.AgentId
import meta.API.{SimulationSnapshot, SimulationConfig}

class Base(var actors: List[Actor], val totalTurn: Int, val messages: List[Message]) extends Serializable {

    var currentTurn: Int = 0
    var collectedMessages: List[Message] = messages

    def proceed(elapsedTurn: Int = 1) = {
      currentTurn += elapsedTurn
    }

    // discover the newly generated agents
    def collect() = {
      actors = SimRuntime.newActors.toList ::: actors
      SimRuntime.newActors.clear()
    }

    def run(): SimulationSnapshot = {
      while (currentTurn < totalTurn) {
        // println(util.displayTime(currentTurn))
        val mx = collectedMessages.groupBy(_.receiverId)
        val res = actors.filterNot(_.deleted).map(a => {
          val targetMessages: List[Message] = a.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
          a.run(targetMessages)
        }).foldLeft((List[Message](), 1))((a, b) => ((a._1 ::: b._1), if (a._2 > b._2) a._2 else b._2))
        collect()
        collectedMessages = res._1
        proceed(res._2)
      }
      SimulationSnapshot(actors, collectedMessages)
    }
}

class BaseWithEval(c: SimulationConfig) extends Base(c.actors, c.totalTurn, c.messages) {
  def run[T](filter:(List[Actor], List[Message]) => T): List[T] = {
      val ans: ListBuffer[T] = new ListBuffer[T]()
      while (currentTurn < totalTurn) {
        // println(util.displayTime(currentTurn))
        val mx = collectedMessages.groupBy(_.receiverId)
        val res = actors.filterNot(_.deleted).map(a => {
          val targetMessages: List[Message] = a.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
          a.run(targetMessages)
        }).foldLeft((List[Message](), 1))((a, b) => ((a._1 ::: b._1), if (a._2 > b._2) a._2 else b._2))
        collect()
        collectedMessages = res._1
        proceed(res._2)
        ans.append(filter(actors, collectedMessages))
      }

      // Actor.reset
      ans.toList
    }
}

class BaseWithReducer(c: SimulationConfig) extends Base(c.actors, c.totalTurn, c.messages) {
  def run[K, T](mapper: Actor => K, reducer: List[K] => T): List[T] = {
      val ans: ListBuffer[T] = new ListBuffer[T]()
      while (currentTurn < totalTurn) {
        // println(util.displayTime(currentTurn))
        val mx = collectedMessages.groupBy(_.receiverId)
        val collectAll = actors.filterNot(_.deleted).map(a => {
          val targetMessages: List[Message] = a.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
          a.runAndEval(targetMessages, mapper)
        })
        val res = collectAll.map(_._1).foldLeft((List[Message](), 1))((a, b) => ((a._1 ::: b._1), if (a._2 > b._2) a._2 else b._2))
        collect()
        collectedMessages = res._1
        proceed(res._2)
        ans.append(reducer(collectAll.map(_._2)))
      }

      // Actor.reset
      ans.toList
    }
}