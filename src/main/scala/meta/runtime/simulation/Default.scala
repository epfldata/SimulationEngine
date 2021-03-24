package meta.runtime
package simulation

import scala.collection.mutable.ListBuffer
import scala.util.Random
import SimRuntime._
import meta.classLifting.SpecialInstructions.Time

class Default(val config: SimulationConfig) extends Simulation {

  private var actors: List[Actor] = config.actors
  private var currentTurn: Int = config.startTurn
  private var currentTime: Double = config.startTime

  // Can be overridden in an inherited class. Same for scheduleEvents
  def init(): List[()=> Unit] = {
    initLabelVals()
    scheduleEvents()
  }

  def collect(): Unit = {
    actors = actors ::: newActors.toList
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
    events.append(() => registerLabel(Time, actors.length))
    events.append(() => {
      val mx = actors.flatMap(_.getSendMessages).groupBy(_.receiverId)
      actors = actors.filterNot(x => x.deleted).map { a =>
      {
        a.cleanSendMessage
          .addInterrupts(currentTime)
          .addReceiveMessages(Random.shuffle(a.proxyIds.flatMap(id => mx.getOrElse(id, List()))))
          .run()
      }}
    })
    events.append(() => proceed())
    events.toList
  }

  def run(): SimulationSnapshot = {

    val events: List[()=> Unit] = init()

    util.bench {
      while (currentTurn <= config.totalTurn && currentTime <= config.totalTime) {
        events.foreach(_())
      }
    }

    SimulationSnapshot(actors, currentTurn, currentTime)
  }
}