package generated.simulation

import meta.deep.runtime.{Actor, Monitor}
import meta.deep.runtime.Actor._
import scala.util.Random

object Simulation {

  def run(config: SimulationConfig): SimulationSnapshot = {

    var actors: List[Actor] = config.actors
    var currentTurn: Int = config.startTurn
    var currentTime: Double = config.startTime
    val totalTurn: Int = config.totalTurn
    val totalTime: Double = config.totalTime
    val monitorEnabled: Boolean = config.monitorEnabled

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
      actors.map(i => {
        i.currentTime = currentTime
        i.currentTurn = currentTurn
      })
    }

    initLabelVals()

    val start = System.nanoTime()
    println("Monitor is enabled: " + monitorEnabled)
    while (currentTurn <= totalTurn && currentTime <= totalTime) {
      util.displayTime(currentTurn, currentTime)
      collect(currentTurn)
      waitLabels("time") = actors.length

      val mx = actors.flatMap(_.getSendMessages).groupBy(_.receiverId)

      actors = actors.map { a =>
        {
          a.cleanSendMessage
            .addInterrupts(currentTime)
            .addReceiveMessages(Random.shuffle(mx.getOrElse(a.id, List())))
            .run_until(currentTurn)
        }
      }

      if (monitorEnabled) Monitor.eachIteration(()=>())
      proceed()
    }

    val end = System.nanoTime()
    val consumed = end - start
    if (monitorEnabled) Monitor.onCompletion()
    println("Time consumed", consumed)
    SimulationSnapshot(actors, currentTurn, currentTime, monitorEnabled)
  }
}
