import java.io.{File, PrintWriter}

import meta.deep.runtime.Message
import meta.deep.runtime.{Actor, Message}
import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods.Item
import com.typesafe.scalalogging.Logger
import org.apache.log4j.BasicConfigurator

import scala.collection.mutable.ListBuffer

object Simulation extends App {

  var actors: Array[Actor] = Array()
  var messages: List[Message] = List()
  var timer = 0
  var until = 300
//  val supplyThreshold = 5

  val memUnit = 1024 // KB
  val runtime = Runtime.getRuntime

  BasicConfigurator.configure()
  val logger = Logger("Root")

  def init(): Unit = {
    actors = generated.InitData.initActors.to[Array]
//    actors = generated.InitData.initActors
  }

  def addSupply: Unit = {
//    if (Supermarket.store.warehouse.Vegetable.size < supplyThreshold) {
      val new_actor: generated.Item1 = new generated.Item1()
      new_actor.timeVar = timer
      Supermarket.store.warehouse.Vegetable.get("Eggplant").get.enqueue(new_actor.asInstanceOf[Item])
      actors = actors :+ new_actor.asInstanceOf[meta.deep.runtime.Actor]
      val new_actor2: generated.Item2 = new generated.Item2()
      new_actor2.timeVar = timer
      Supermarket.store.warehouse.Vegetable.get("Onion").get.enqueue(new_actor2.asInstanceOf[Item])
      actors = actors :+ new_actor2.asInstanceOf[meta.deep.runtime.Actor]
      val new_actor3: generated.Item3 = new generated.Item3()
      new_actor3.timeVar = timer
      Supermarket.store.warehouse.Vegetable.get("Potato").get.enqueue(new_actor3.asInstanceOf[Item])
      actors = actors :+ new_actor3.asInstanceOf[meta.deep.runtime.Actor]
//    }
  }

  val writer = new PrintWriter(new File("output.log"))

  def main(): Unit = {
//    println(this.getClass.getClassLoader)
    init()
    val start = System.nanoTime()

    while (timer <= until) {
      println("TIMER", timer)
      writer.write("Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / memUnit + "\n")
      val mx = messages.groupBy(_.receiverId)
      addSupply

      // remove invalid actors
      while (Supermarket.store.isInvalids.size>0){
        val toRemove = Supermarket.store.isInvalids.dequeue()
        actors = actors.filter(actors=>actors.id!=toRemove)
      }

      actors = actors.map { a =>
        {
          a.cleanSendMessage
            .addReceiveMessages(mx.getOrElse(a.id, List()))
            .run_until(timer)
        }
      }
      messages = actors.flatMap(_.getSendMessages).toList
      timer += 1
//      println(Supermarket.store.isInvalids)
    }

    writer.close()
    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
  }

  main()

}
