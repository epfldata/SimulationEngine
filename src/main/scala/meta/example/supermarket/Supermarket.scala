package meta.example.supermarket

import meta.example.supermarket.goods.Item

import scala.collection.mutable.Map
import scala.collection.mutable.PriorityQueue

case class Warehouse(var Vegetable: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq),
                     var Meat: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq),
                     var Snack: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq),
                     var Grain: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq),
                     var Dairy: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq))

class Supermarket extends SummaryTrait {

  def recordWaste(category: String, priceUnit: Int, isSold: Boolean): Unit ={
    updateWasteSummary(category, priceUnit, isSold)
  }

  def getQueue(category: String, item: String): PriorityQueue[Item] = {
    category.toLowerCase match {
      case "vegetable" => warehouse.Vegetable(item)
      case "meat" => warehouse.Meat(item)
      case "dairy" => warehouse.Dairy(item)
      case "snack" => warehouse.Snack(item)
      case "grain" => warehouse.Grain(item)
      case _ => {println("Unrecognized food category name!"); throw new Exception}
    }
  }

  val warehouse: Warehouse = Warehouse()

  def checkQueueSize(requested: PriorityQueue[Item], item: String): Unit = {
    if (requested.size==0) { println("Item not found :( " + item); throw new Exception }
  }

  def sell(category: String, item: String): Item = {
    val requested: PriorityQueue[Item] = getQueue(category, item)

    checkQueueSize(requested, item)

    var soldItem: Item = requested.dequeue()
    while (soldItem.state.isDiscarded){
      checkQueueSize(requested, item)
      soldItem = requested.dequeue()
    }

    assert(!soldItem.state.isDiscarded)
    soldItem.purchase

    println(s"Item ${soldItem.name} sold! " + soldItem.id)
    soldItem
  }

  def add(itemList: List[Item]): Unit =  {
    for (item <- itemList){
      add(item)
    }
  }

  def add(item: Item): Unit = {
    getQueue(item.category, item.name).enqueue(item)
  }
}

object Supermarket {
  val store: Supermarket = new Supermarket
}