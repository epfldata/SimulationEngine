import meta.deep.member.{Actor, Message}

object Simulation extends App {

  var actors: List[Actor] = List()
  var messages: List[Message] = List()
  var timer = 0
  var until = 100

  def init(): Unit = {
    actors = generated.InitData.initActors
  }

  def main(): Unit = {
    init()
    val start = System.nanoTime()

    while (timer <= until) {
      println("TIMER", timer)
      val mx = messages.groupBy(_.receiverId)
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
