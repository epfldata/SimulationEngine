package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import meta.runtime.Actor.AgentId
import meta.API.SimulationSnapshot

class Base(var actors: List[Actor], val totalTurn: Int, val messages: List[Message]) extends Simulation {

  var collectedMessages: List[Message] = messages

  val events: ListBuffer[()=> Unit] = new ListBuffer()
  events.append(
    () => { println(util.displayTime(currentTurn)) }
  )

  events.append(() => collect())

  events.append(() => {
    val mx = collectedMessages.groupBy(_.receiverId)
    
    collectedMessages = actors.filterNot(_.deleted).flatMap(a => {
      val targetMessages: List[Message] = a.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
      a.cleanSendMessage
        .run(targetMessages)._1
    }).toList
  })

  events.append(() => proceed())

  override def run(): SimulationSnapshot = {
    init()

    while (currentTurn < totalTurn) {
      util.bench {
        events.foreach(_())
      }
    }

    Actor.reset
    SimulationSnapshot(actors, collectedMessages)
  }
}