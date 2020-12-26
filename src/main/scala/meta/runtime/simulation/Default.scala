package meta.runtime
package simulation

import scala.collection.mutable.ListBuffer
import scala.util.Random
import SimRuntime._

object Default extends Simulation {

  private var actors: List[Actor] = _
  private var currentTurn: Int = _
  private var currentTime: Double = _

  def apply(config: SimulationConfig): SimulationSnapshot = {
    actors = config.actors
    currentTurn = config.startTurn
    currentTime = config.startTime
    run(config)
  }

  // Can be overridden in an inherited class. Same for scheduleEvents
  def init(): List[()=> Unit] = {
    initLabelVals()
    scheduleEvents()
  }

  def collect(): Unit = {
    newActors.map(i => i.currentTurn = currentTurn)
    actors = actors ::: newActors.toList
    newActors.clear()
  }

  def proceed(): Unit = {
    proceedGroups()
    currentTurn += proceedLabel("turn").asInstanceOf[Int]
    currentTime += proceedLabel("time")

    // update the turn counter for Sims
    actors.foreach(i => {
      i.currentTime = currentTime
      i.currentTurn = currentTurn
    })
  }

  def scheduleEvents(): List[()=> Unit] = {
    val events: ListBuffer[()=> Unit] = new ListBuffer()
    events.append(
      () => { println(util.displayTime(currentTurn, currentTime)) }
    )
    events.append(() => collect())
    // If new actors are added, time takes them into account as well
    events.append(() => waitLabels("time") = actors.length)
    events.append(() => {
      val mx = actors.flatMap(_.getSendMessages).groupBy(_.receiverId)
      actors = actors.map { a =>
      {
        a.cleanSendMessage
          //                  .addInterrupts(currentTime)
          .addReceiveMessages(Random.shuffle(mx.getOrElse(a.id, List())))
          .run_until(currentTurn)
      }}})
    events.append(() => proceed())
    events.toList
  }

  def run(config: SimulationConfig): SimulationSnapshot = {
    val events: List[()=> Unit] = init()
    val start = System.nanoTime()
    while (currentTurn <= config.totalTurn && currentTime <= config.totalTime) {
      events.foreach(_())
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
    SimulationSnapshot(actors, currentTurn, currentTime)
  }
}