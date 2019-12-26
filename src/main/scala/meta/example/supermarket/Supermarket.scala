package meta.example.supermarket

import meta.example.supermarket.goods.{Item, categories}

import scala.collection.mutable
import scala.collection.mutable.{ListBuffer, Map, PriorityQueue}

case class Warehouse(var Vegetable: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]](),
                     var Meat: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]](),
                     var Snack: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]](),
                     var Grain: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]](),
                     var Dairy: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]())

class Supermarket extends SummaryTrait {

  val warehouse: Warehouse = Warehouse()
  val categories: categories = new categories
//  val isInvalids: ListBuffer[Long] = new ListBuffer() // possibly as a queue

  val isInvalids: mutable.Queue[Long] = new mutable.Queue()

  val vegetables: List[String] = categories.getArticleNames("Vegetable")
  val meats: List[String] = categories.getArticleNames("Meat")
  val snacks: List[String] = categories.getArticleNames("Snack")
  val grains: List[String] = categories.getArticleNames("Grain")
  val dairys: List[String] = categories.getArticleNames("Dairy")

  def recordWaste(category: String, priceUnit: Int, isSold: Boolean): Unit ={
    updateWasteSummary(category, priceUnit, isSold)
  }

  def checkItemQueue(category: String, item: String): Option[PriorityQueue[Item]] = {
    category.capitalize match {
      case "Vegetable" => warehouse.Vegetable.get(item)
      case "Meat" => warehouse.Meat.get(item)
      case "Dairy" => warehouse.Dairy.get(item)
      case "Snack" => warehouse.Snack.get(item)
      case "Grain" => warehouse.Grain.get(item)
      case _ => {println("Unrecognized category name!"); throw new Exception}
    }
  }

  def getItemQueue(category: String, item: String): PriorityQueue[Item] = {
    category.capitalize match {
      case "Vegetable" => warehouse.Vegetable.get(item).get
      case "Meat" => warehouse.Meat.get(item).get
      case "Dairy" => warehouse.Dairy.get(item).get
      case "Snack" => warehouse.Snack.get(item).get
      case "Grain" => warehouse.Grain.get(item).get
      case _ => {println("Unrecognized category name!"); throw new Exception}
    }
  }

  def initializeItemQueue(category: String, item: Item): Unit = {
    category.capitalize match {
      case "Vegetable" => {
        warehouse.Vegetable += (item.name -> new priceOrderedPQ().pq);
        warehouse.Vegetable.get(item.name).get.enqueue(item)}
      case "Meat" => {
        warehouse.Meat += (item.name -> new priceOrderedPQ().pq);
        warehouse.Meat.get(item.name).get.enqueue(item)}
      case "Dairy" => {
        warehouse.Dairy += (item.name -> new priceOrderedPQ().pq);
        warehouse.Dairy.get(item.name).get.enqueue(item)}
      case "Snack" => {
        warehouse.Snack += (item.name -> new priceOrderedPQ().pq);
        warehouse.Snack.get(item.name).get.enqueue(item)}
      case "Grain" => {
        warehouse.Grain += (item.name -> new priceOrderedPQ().pq);
        warehouse.Grain.get(item.name).get.enqueue(item)}
      case _ => {println("Unrecognized category name!"); throw new Exception}
    }
  }

  def checkQueueSize(requested: PriorityQueue[Item], item: String): Unit = {
    if (requested.size==0) { println("Item not found :( " + item); throw new Exception }
  }

  def sell(category: String, item: String): Item = {
    val requested: PriorityQueue[Item] = getItemQueue(category, item)

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
    checkItemQueue(item.category, item.name) match {
      case None => initializeItemQueue(item.category, item)
      case Some(itemQueue) => getItemQueue(item.category, item.name).enqueue(item)
    }
//    println("Elements in the queue is: " + getItemQueue(item.category, item.name))
  }
}

object Supermarket {
  val store: Supermarket = new Supermarket
}