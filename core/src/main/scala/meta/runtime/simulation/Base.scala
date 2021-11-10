package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import meta.runtime.Actor.AgentId

class Base(var actors: List[Actor], val totalTurn: Int) extends Simulation {

  var collectedMessages: List[Message] = List()

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
}