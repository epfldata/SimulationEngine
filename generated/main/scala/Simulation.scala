import meta.deep.runtime.{Actor, Message, Monitor}
import meta.deep.runtime.Actor.{newActors, minTurn, waitTurnList}
import scala.util.Random

object Simulation extends App {

  var actors: List[Actor] = List()
  var messages: List[Message] = List()
  var timer: Int = 0
  var until: Int = 20
  var monitor_enabled: Boolean = false

  def init(): Unit = {
    actors = generated.InitData.initActors
  }

  def collect(current_time: Int): Unit = {
    newActors.map(i => i.timer = current_time)
    actors = actors ::: newActors.toList
    newActors.clear()
  }

  def main(): Unit = {
    init()
    val start = System.nanoTime()
    println("Monitor is enabled: " + monitor_enabled)
    while (timer <= until) {
      println("TIMER", timer)
      collect(timer)
      val mx = messages.groupBy(_.receiverId)

      actors = actors.map { a =>
        {
          a.cleanSendMessage
            .addReceiveMessages(Random.shuffle(mx.getOrElse(a.id, List())))
            .run_until(timer)
        }
      }
      messages = actors.flatMap(_.getSendMessages).toList
      if (monitor_enabled) Monitor.eachIteration()
      timer += minTurn()
      actors.map(i => i.timer = timer)
      waitTurnList.clear()
    }

    val end = System.nanoTime()
    val consumed = end - start
    if (monitor_enabled) Monitor.onCompletion()
    println("Time consumed", consumed)
  }

  main()

}
