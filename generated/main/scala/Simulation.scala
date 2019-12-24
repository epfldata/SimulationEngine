import meta.deep.runtime.Message
import meta.deep.runtime.{Actor, Message}
import meta.example.supermarket.Supermarket
import meta.example.supermarket.priceOrderedPQ

object Simulation extends App {

  var actors: List[Actor] = List()
  var messages: List[Message] = List()
  var timer = 0
  var until = 7

  def init(): Unit = {
    actors = generated.InitData.initActors
  }

  def main(): Unit = {
    init()
    val start = System.nanoTime()

    while (timer <= until) {
      println("TIMER", timer)
      val mx = messages.groupBy(_.receiverId)

//      println("Add new actor! Total actor count now: " + actors.size)
      val new_actor: generated.Item3 = new generated.Item3()
      new_actor.timeVar_5 = timer

      actors = actors :+ new_actor.asInstanceOf[meta.deep.runtime.Actor]

      actors = actors.map { a =>
        {
          a.cleanSendMessage
            .addReceiveMessages(mx.getOrElse(a.id, List()))
            .run_until(timer)
        }
      }
      messages = actors.flatMap(_.getSendMessages)
      timer += 1
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
  }

  main()

}
