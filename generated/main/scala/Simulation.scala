import meta.deep.runtime.{Actor, Message, Monitor}

import scala.util.Random

object Simulation extends App {

  var actors: List[Actor] = List()
  var messages: List[Message] = List()
  var timer: Int = 0
  var until: Int = 10

  def init(): Unit = {
    actors = generated.InitData.initActors
  }

  def collect(current_time: Int): Unit = {
    meta.deep.runtime.Actor.newActors.map(i => i.timer = current_time)
    actors = actors ::: meta.deep.runtime.Actor.newActors.toList
    meta.deep.runtime.Actor.newActors.clear()
  }

  def main(): Unit = {
    init()
    val start = System.nanoTime()

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
      Monitor.eachIteration()
      timer += 1
    }

    val end = System.nanoTime()
    val consumed = end - start
    Monitor.onCompletion()
    println("Time consumed", consumed)
  }

  main()

}
