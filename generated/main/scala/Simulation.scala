import java.io.{File, PrintWriter}

import meta.deep.runtime.Message
import meta.deep.runtime.{Actor, Message}
import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods.Item
//import org.apache.log4j.BasicConfigurator
import scala.util.Random
import scala.collection.mutable.ListBuffer

object Simulation extends App {

  var actors: List[Actor] = List()
  var messages: List[Message] = List()
  var timer: Int = 0
  var until: Int = 10

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
            .addReceiveMessages(Random.shuffle(mx.getOrElse(a.id, List())))
            .run_until(timer)
        }
      }
      messages = actors.flatMap(_.getSendMessages).toList
      timer += 1
    }

    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
  }

  main()

}
