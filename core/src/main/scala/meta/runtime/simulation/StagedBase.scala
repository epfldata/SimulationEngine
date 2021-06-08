package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._
import meta.runtime.Actor.AgentId
import org.coroutines._

class StagedBase(var actors: List[Actor], val totalTurn: Int) extends Simulation {

  val coroutineAgents = actors.map(x => call (x.run()()))
  
  var sortedMessages: Map[Long, List[Message]] = Map()

  val collectedMessages: ListBuffer[Message] = new ListBuffer[Message]()

  val events: ListBuffer[()=> Unit] = new ListBuffer()
  events.append(
    () => { println(util.displayTime(currentTurn)) }
  )

  events.append(() => collect())
  // If new actors are added, time takes them into account as well
  events.append(() => {

    sortedMessages = collectedMessages.toList.groupBy(x => x.receiverId).toMap

    collectedMessages.clear()

    actors.foreach(x => 
      x.asInstanceOf[Actor]
        .addReceiveMessages(x.getProxyIds.toList.flatMap(
          id => sortedMessages.getOrElse(id, List())))
    )

    coroutineAgents.map(x => x.resume)

    collectedMessages.appendAll(coroutineAgents.flatMap(x => x.value))
  })

  events.append(() => proceed())
}