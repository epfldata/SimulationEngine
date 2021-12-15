package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import meta.runtime.Actor.AgentId
import meta.API.SimulationSnapshot

class Base(var actors: List[Actor], val totalTurn: Int, val messages: List[Message]) extends Serializable {

    var currentTurn: Int = 0
    var collectedMessages: List[Message] = messages

    // discover the newly generated agents
    def collect() = {
      actors = SimRuntime.newActors.toList ::: actors
      SimRuntime.newActors.clear()
    }

    // go to the next state of the Sim
    def proceed() = {
      SimRuntime.proceedGroups()
      currentTurn += 1
      // currentTurn += proceedLabel("turn").asInstanceOf[Int]
    }

    def run(): SimulationSnapshot = {
      SimRuntime.initLabelVals()

      while (currentTurn < totalTurn) {
        println(util.displayTime(currentTurn))
        collect()
        val mx = collectedMessages.groupBy(_.receiverId)
        collectedMessages = actors.filterNot(_.deleted).flatMap(a => {
          val targetMessages: List[Message] = a.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
          a.cleanSendMessage
            .run(targetMessages)._1
        }).toList
        proceed()
      }

      Actor.reset
      SimulationSnapshot(actors, collectedMessages)
    }
}