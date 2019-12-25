import meta.deep.runtime.Message
import meta.deep.runtime.{Actor, Message}
import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods.Item
import meta.example.supermarket.priceOrderedPQ

object Simulation extends App {

  var actors: List[Actor] = List()
  var messages: List[Message] = List()
  var timer = 0
  var until = 7
  val supplyThreshold = 5

  def init(): Unit = {
    actors = generated.InitData.initActors
  }

  def addSupply: Unit = {
    if (Supermarket.store.warehouse.Vegetable.size < supplyThreshold) {
      val new_actor: generated.Item3 = new generated.Item3()
      new_actor.timeVar = timer
      Supermarket.store.warehouse.Vegetable.enqueue(new_actor.asInstanceOf[Item])
      actors = actors :+ new_actor.asInstanceOf[meta.deep.runtime.Actor]
    }
  }

  def main(): Unit = {
    init()
    val start = System.nanoTime()

    while (timer <= until) {
      println("TIMER", timer)
      val mx = messages.groupBy(_.receiverId)
      addSupply
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
