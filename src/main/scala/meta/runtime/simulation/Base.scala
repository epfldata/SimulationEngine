package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._
import meta.runtime.Actor.AgentId

class Base(val config: SimulationConfig) extends Simulation {

  var actors: List[Actor] = config.actors
  currentTurn = config.startTurn
  var collectedMessages: List[Message] = List()

  var init = () => initLabelVals()

  var collect = () => {
    actors = newActors.toList ::: actors
    newActors.clear()
  }

  var proceed: () => Unit = () => {
    proceedGroups()
    currentTurn += proceedLabel("turn").asInstanceOf[Int]
  }

  val events: ListBuffer[()=> Unit] = new ListBuffer()
  events.append(
    () => { println(util.displayTime(currentTurn)) }
  )

  events.append(() => collect())
  // If new actors are added, time takes them into account as well
  // events.append(() => registerLabel(Time, actors.size))
  events.append(() => {
    val mx = collectedMessages.groupBy(_.receiverId)
    
    collectedMessages = actors.filterNot(_.deleted).flatMap(a => {
      val targetMessages: List[Message] = a.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
      a.cleanSendMessage
        .run(targetMessages)
    }).toList
  })
  events.append(() => proceed())

  var takeSnapshot = () => {
    SimulationSnapshot(actors, currentTurn)
  }
}