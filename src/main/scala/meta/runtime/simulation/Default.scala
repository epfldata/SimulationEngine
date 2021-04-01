package meta.runtime
package simulation

import scala.collection.mutable.{ListBuffer, Map => MutMap}
import scala.util.Random
import SimRuntime._
import meta.classLifting.SpecialInstructions.Time

class Default(val config: SimulationConfig) extends Simulation {

  private var actors: MutMap[Actor.AgentId, Actor] = MutMap() ++ config.actors.map(x => (x.id, x)).toMap
  private var currentTurn: Int = config.startTurn
  private var currentTime: Double = config.startTime
  private val mergeFrequency: Int = 5

  // Can be overridden in an inherited class. Same for scheduleEvents
  def init(): List[()=> Unit] = {
    initLabelVals()
    scheduleEvents()
  }

  def collect(): Unit = {
    newActors.map(x => {actors += (x.id -> x)})
    // actors = actors ::: newActors.map(x => (x.id, x)).toList
    newActors.clear()
  }

  def proceed(): Unit = {
    proceedGroups()
    currentTurn += proceedLabel("turn").asInstanceOf[Int]
  }

  def scheduleEvents(): List[()=> Unit] = {
    val events: ListBuffer[()=> Unit] = new ListBuffer()
    events.append(
      () => { println(util.displayTime(currentTurn, currentTime)) }
    )
    events.append(() => collect())
    // If new actors are added, time takes them into account as well
    events.append(() => registerLabel(Time, actors.size))
    events.append(() => {
      val mx = actors.flatMap(_._2.getSendMessages).groupBy(_.receiverId)
      // Record the communication pattern
      if (meta.compile.Optimization.runtimeMerging){
        mx.foreach(x => {
          x._2.foreach(m => MessagingStats.recordMessage(m.senderId, m.receiverId))
        })
      }
      
      actors = actors.filterNot(x => x._2.deleted).map {a =>
      {(a._1, {
        val targetMessages: List[Message] = a._2.proxyIds.flatMap(id => mx.getOrElse(id, List()))
        a._2.cleanSendMessage
          .addReceiveMessages(Random.shuffle(targetMessages))
          .run()})
      }}
    })
    events.append(() => proceed())
    if (meta.compile.Optimization.runtimeMerging) {
      events.append(() => {
        if (currentTurn % mergeFrequency == 0) {
          val candidates = MessagingStats.getMergeCandidates
          meta.Util.warning(s"Merge agents: ${candidates}")
          candidates.foreach(x => {
            val c1 = new Container()
            c1.addAgents(x.toList.map(a => {
              assert(actors.get(a).isDefined)
              actors.remove(a).get
            }))
            newActors.append(c1)
          })
          MessagingStats.clearMergeCandidates
        }
      })
    }
    events.toList
  }

  def run(): SimulationSnapshot = {

    val events: List[()=> Unit] = init()

    util.bench {
      while (currentTurn <= config.totalTurn && currentTime <= config.totalTime) {
        events.foreach(_())
      }
    }

    SimulationSnapshot(actors.map(x => x._2).toList, currentTurn, currentTime)
  }
}