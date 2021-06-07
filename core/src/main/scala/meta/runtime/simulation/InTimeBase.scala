package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._
import meta.runtime.Actor.AgentId
import org.coroutines._

class InTimeBase(val config: SimulationConfig) extends Simulation {

  var actors: List[Actor] = config.actors

  val coroutineAgents = actors.map(x => call (x.run()()))
  
  var sortedMessages: Map[Long, List[Message]] = Map()

  currentTurn = config.startTurn

  val collectedMessages: ListBuffer[Message] = new ListBuffer[Message]()

  var init = () => initLabelVals()

  var collect = () => {
    actors = newActors.toList ::: actors
    newActors.clear()
  }

  var proceed: () => Unit = () => {
    proceedGroups()
    currentTurn += 1
  }

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

  var takeSnapshot = () => {
    SimulationSnapshot(actors, currentTurn)
  }
}