package meta.example.supermarket

import meta.example.supermarket.goods.{Item, categories}

import scala.collection.mutable.Map
import scala.collection.mutable.PriorityQueue

case class Warehouse(var Vegetable: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq),
                     var Meat: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq),
                     var Snack: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq),
                     var Grain: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq),
                     var Dairy: Map[String, PriorityQueue[Item]] = Map[String, PriorityQueue[Item]]().withDefaultValue(new priceOrderedPQ().pq))

class Supermarket extends SummaryTrait {

  val warehouse: Warehouse = Warehouse()
  val categories: categories = new categories

  val vegetables: List[String] = categories.getArticleNames("Vegetable")
  val meats: List[String] = categories.getArticleNames("Meat")
  val snacks: List[String] = categories.getArticleNames("Snack")
  val grains: List[String] = categories.getArticleNames("Grain")
  val dairys: List[String] = categories.getArticleNames("Dairy")

  def recordWaste(category: String, priceUnit: Int, isSold: Boolean): Unit ={
    updateWasteSummary(category, priceUnit, isSold)
  }

  def getItemQueue(category: String, item: String): PriorityQueue[Item] = {
    category.capitalize match {
      case "Vegetable" => warehouse.Vegetable(item)
      case "Meat" => warehouse.Meat(item)
      case "Dairy" => warehouse.Dairy(item)
      case "Snack" => warehouse.Snack(item)
      case "Grain" => warehouse.Grain(item)
      case _ => {println("Unrecognized food category name!"); throw new Exception}
    }
  }

  def checkQueueSize(requested: PriorityQueue[Item], item: String): Unit = {
    if (requested.size==0) { println("Item not found :( " + item); throw new Exception }
  }

  def sell(category: String, item: String): Item = {
//    println("Sell req: category " + category + item)
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
    getItemQueue(item.category, item.name).enqueue(item)
  }
}

object Supermarket {
  val store: Supermarket = new Supermarket
}