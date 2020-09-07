package generated.simulation

import meta.deep.runtime.{Actor, Message, Monitor}
import meta.deep.runtime.Actor._
import scala.util.Random

object Simulation {

  def run(config: SimulationConfig): Unit = {

    var actors: List[Actor] = config.actors
    var messages: List[Message] = List()
    var currentTurn: Int = config.startTurn
    var currentTime: Double = config.startTime
    val totalTurn: Int = config.totalTurn
    val totalTime: Double = config.totalTime
    val monitor_enabled: Boolean = config.monitorEnabled

    def collect(currentTurn: Int): Unit = {
      newActors.map(i => i.currentTurn = currentTurn)
      actors = actors ::: newActors.toList
      newActors.clear()
    }

    def proceed(): Unit = {
      proceedGroups()
      currentTurn += minTurn()
      currentTime += proceedLabel("time")

      // update the turn counter for Sims
      actors.map(i => {
        i.currentTime = currentTime
        i.currentTurn = currentTurn
      })
      waitTurnList.clear()
    }

    initLabelVals()

    val start = System.nanoTime()
    println("Monitor is enabled: " + monitor_enabled)
    while (currentTurn <= totalTurn && currentTime <= totalTime) {
      println("(Time " + BigDecimal(currentTime).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble
        + " Turn " + currentTurn + ")" )
      collect(currentTurn)
      waitLabels("time") = actors.length

      val mx = messages.groupBy(_.receiverId)

      actors = actors.map { a =>
        {
          a.cleanSendMessage
            .checkInterrupts(currentTime)
            .addReceiveMessages(Random.shuffle(mx.getOrElse(a.id, List())))
            .run_until(currentTurn)
        }
      }
      messages = actors.flatMap(_.getSendMessages).toList
      if (monitor_enabled) Monitor.eachIteration(()=>())
      proceed()
    }

    val end = System.nanoTime()
    val consumed = end - start
    if (monitor_enabled) Monitor.onCompletion()
    println("Time consumed", consumed)
  }
}
