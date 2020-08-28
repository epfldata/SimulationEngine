import meta.deep.runtime.{Actor, Message, Monitor}
import meta.deep.runtime.Actor._

import scala.util.Random

object Simulation extends App {

  var actors: List[Actor] = List()
  var messages: List[Message] = List()
  // Consider just have time, and remove total turn
  var currentTurn: Int = 0
  var totalTurn: Int = 100
  var currentTime: Double = 0
  var totalTime: Double = 4

  var monitor_enabled: Boolean = false

  def init(): Unit = {
    actors = generated.InitData.initActors
    initLabelVals()
  }

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

  def main(): Unit = {
    init()
    val start = System.nanoTime()
    println("Monitor is enabled: " + monitor_enabled)
    while (currentTurn <= totalTurn && currentTime <= totalTime) {
      println("(Time " + BigDecimal(currentTime).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble + " Turn " + currentTurn + ")" )
      collect(currentTurn)
      waitLabels("time") = actors.length

      val mx = messages.groupBy(_.receiverId)

      actors = actors.map { a =>
        {
          a.cleanSendMessage
            .addReceiveMessages(Random.shuffle(mx.getOrElse(a.id, List())))
            .run_until(currentTurn)
        }
      }
      messages = actors.flatMap(_.getSendMessages).toList
      if (monitor_enabled) Monitor.eachIteration(()=>())
      proceed()
//      currentTurn = currentTurn + 1
//      actors.map(i => {
//        i.currentTurn = currentTurn
//      })
    }

    val end = System.nanoTime()
    val consumed = end - start
    if (monitor_enabled) Monitor.onCompletion()
    println("Time consumed", consumed)
  }

  main()

}
