package generated.simulation

import meta.deep.runtime.{Actor, Monitor}
import meta.deep.runtime.Actor._

import scala.collection.mutable.ListBuffer
import scala.util.Random

object Simulation {
  val eachIteration: ListBuffer[()=> Unit] = new ListBuffer()
}

class Simulation(config: SimulationConfig) {
  import Simulation._
  var actors: List[Actor] = config.actors
  var currentTurn: Int = config.startTurn
  var currentTime: Double = config.startTime
  val totalTurn: Int = config.totalTurn
  val totalTime: Double = config.totalTime

  def collect(currentTurn: Int): Unit = {
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

  // register events
  def addEvent(e: => ()=> Unit): Unit = {
    eachIteration.append(e)
  }

  // Can be overridden in an inherited class. Same for scheduleEvents
  def init(): Unit = {
    initLabelVals()
    scheduleEvents()
  }

  private var lastTurn: Int = -1

  def scheduleEvents(): Unit = {
    addEvent(() => {
      if (lastTurn != currentTurn) {
        println(util.displayTime(currentTurn, currentTime))
        lastTurn = currentTurn
      }
    })
    addEvent(() => collect(currentTurn))
    addEvent(() => waitLabels("time") = actors.length)
    addEvent(() => {
      val mx = actors.flatMap(_.getSendMessages).groupBy(_.receiverId)
      actors = actors.map { a =>
      {
        a.cleanSendMessage
//                  .addInterrupts(currentTime)
          .addReceiveMessages(Random.shuffle(mx.getOrElse(a.id, List())))
          .run_until(currentTurn)
      }}})
    addEvent(() => proceed())
  }

  def run(): SimulationSnapshot = {
    init()
    val start = System.nanoTime()
    while (currentTurn <= totalTurn && currentTime <= totalTime) {
      eachIteration.foreach(m => m())
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
    SimulationSnapshot(actors, currentTurn, currentTime)
  }
}
