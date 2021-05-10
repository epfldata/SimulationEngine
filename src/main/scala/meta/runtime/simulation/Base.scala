package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._
import meta.classLifting.SpecialInstructions.Time
import meta.runtime.Actor.AgentId
import scala.collection.immutable.ListMap

class Base(val config: SimulationConfig) extends Simulation {

  var actors: MutMap[AgentId, Actor] = MutMap() ++ config.actors.map(x => (x.id, x)).toMap
  currentTurn = config.startTurn
  currentTime = config.startTime

  var init = () => initLabelVals()

  var collect = () => {
    newActors.map(x => {actors += (x.id -> x)})
    newActors.clear()
    meta.Util.warning(s"Total agents ${actors.size}")
  }

  var proceed: () => Unit = () => {
    proceedGroups()
    currentTurn += proceedLabel("turn").asInstanceOf[Int]
  }

  val events: ListBuffer[()=> Unit] = new ListBuffer()
  events.append(
    () => { println(util.displayTime(currentTurn, currentTime)) }
  )
  events.append(() => collect())
  // If new actors are added, time takes them into account as well
  // events.append(() => registerLabel(Time, actors.size))
  events.append(() => {
    val msgs: List[Message] = actors.flatMap(_._2.getSendMessages).toList
    val mx = msgs.groupBy(_.receiverId)
    
    actors = actors.filterNot(x => x._2.deleted).map(a =>
    {(a._1, {
      val targetMessages: List[Message] = a._2.getProxyIds.flatMap(id => mx.getOrElse(id, List()))
      a._2.cleanSendMessage
        .addReceiveMessages(Random.shuffle(targetMessages))
        .run()})
    })
  })
  events.append(() => proceed())

  var takeSnapshot = () => {
    SimulationSnapshot(actors.map(x => x._2).toList, currentTurn, currentTime)
  }
}