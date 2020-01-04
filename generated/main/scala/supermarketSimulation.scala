import java.io.{File, PrintWriter}

import meta.deep.runtime.Message
import meta.deep.runtime.{Actor, Message}
import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods.{Item, newItem, newItemsMap}
import com.typesafe.scalalogging.Logger
import org.apache.log4j.BasicConfigurator

import scala.collection.mutable.ListBuffer

object supermarketSimulation extends App {

  var actors: Array[Actor] = Array()
  var messages: List[Message] = List()
  var timer: Int = 0
  var until: Int = 10
  val shelfCapacity: Int = 1
  val unitLoad: Int = 5 //

  //  val memUnit: Int = 1024 // KB
  //  val runtime = Runtime.getRuntime

  BasicConfigurator.configure()
  val logger = Logger("Root")

  def init(): Unit = {
    actors = generated.InitData.initActors.to[Array]
  }

  def fillShelf(item: String): Int = {
//    println("Fill the shelf for item " + item + " amount: " + (shelfCapacity - Supermarket.store.warehouse(item).size))
    shelfCapacity - Supermarket.store.warehouse(item).size
  }

  private def genNewItem(itemId: String): newItem = {
    itemId match {
      case "Item1" => new generated.Item1()
      case "Item2" => new generated.Item2()
      case "Item3" => new generated.Item3()
      case "Item4" => new generated.Item4()
      case "Item5" => new generated.Item5()
      case "Item6" => new generated.Item6()
      case "Item7" => new generated.Item7()
      case "Item8" => new generated.Item8()
      case "Item9" => new generated.Item9()
      case "Item10" => new generated.Item10()
      case "Item11" => new generated.Item11()
      case "Item12" => new generated.Item12()
      case "Item13" => new generated.Item13()
      case "Item14" => new generated.Item14()
      case "Item15" => new generated.Item15()
      case "Item16" => new generated.Item16()
      case "Item17" => new generated.Item17()
      case "Item18" => new generated.Item18()
      case "Item19" => new generated.Item19()
      case "Item20" => new generated.Item20()
      case "Item21" => new generated.Item21()
      case "Item22" => new generated.Item22()
      case "Item23" => new generated.Item23()
      case "Item24" => new generated.Item24()
      case "Item25" => new generated.Item25()
      case "Item26" => new generated.Item26()
      case "Item27" => new generated.Item27()
      case "Item28" => new generated.Item28()
      case "Item29" => new generated.Item29()
      case "Item30" => new generated.Item30()
      case "Item31" => new generated.Item31()
      case _ => throw new IllegalArgumentException
    }
  }

  def addSupply: Unit = {
    newItemsMap.itemMap.keys.foreach(
      item => 1.to(fillShelf(item)).foreach(_ => {
        val new_item: newItem = genNewItem(newItemsMap.itemMap(item))
        new_item.timeVar = timer
        Supermarket.store.warehouse(item) += (new_item.asInstanceOf[Item])
        actors = actors :+ new_item.asInstanceOf[meta.deep.runtime.Actor]
        println("Add new actor! name: " + item)
      })
    )
  }

  //  val writer = new PrintWriter(new File("output.log"))

  def main(): Unit = {
    //    println(this.getClass.getClassLoader)
    init()
    val start = System.nanoTime()

    while (timer <= until) {
      println("TIMER", timer)

      //      writer.write("Used Memory:  " + (runtime.totalMemory - runtime.freeMemory) / memUnit + "\n")
      val mx = messages.groupBy(_.receiverId)
      // remove invalid actors
      while (Supermarket.store.isInvalids.size>0){
        val toRemove = Supermarket.store.isInvalids.dequeue()
        actors = actors.filter(_.id!=toRemove)
      }

      addSupply

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

    //    writer.close()
    val end = System.nanoTime()
    val consumed = end - start
    println("Time consumed", consumed)
  }

  main()

}
