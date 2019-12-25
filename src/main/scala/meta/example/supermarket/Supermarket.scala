package meta.example.supermarket

import meta.example.supermarket.goods.Item

import scala.collection.mutable.Map
import scala.collection.mutable.PriorityQueue

case class Warehouse(var Vegetable: PriorityQueue[Item] = new priceOrderedPQ().pq,
                     var Meat: PriorityQueue[Item] = new priceOrderedPQ().pq,
                     var Snack: PriorityQueue[Item] = new priceOrderedPQ().pq,
                     var Grain: PriorityQueue[Item] = new priceOrderedPQ().pq,
                     var Dairy: PriorityQueue[Item] = new priceOrderedPQ().pq)

class Supermarket extends SummaryTrait {
  import Supermarket._

  def recordWaste(category: String, priceUnit: Int, isSold: Boolean): Unit ={
    updateWasteSummary(category, priceUnit, isSold)
  }

  def getQueue(category: String): PriorityQueue[Item] = {
    var requested: PriorityQueue[Item] = new priceOrderedPQ().pq
    category.toLowerCase match {
      case "vegetable" => requested = warehouse.Vegetable
      case "meat" => requested = warehouse.Meat
      case "dairy" => requested = warehouse.Dairy
      case "snack" => requested = warehouse.Snack
      case "grain" => requested = warehouse.Grain
      case _ => throw new Exception
    }
    requested
  }

  val warehouse: Warehouse = Warehouse()

  // randomly select an option based on the category
  def sell(category: String): Item = {
    val soldItem: Item = getQueue(category).dequeue()
    soldItem.purchase
    soldItem
  }

  def sell(category: String, item: String): Item = {
    getQueue(category).find(el => el.name.toLowerCase==item.toLowerCase && !el.state.isPurchased) match {
      case Some(el) => {el.purchase; println("purchased element " + el.id); el}
      case None => {println("No such item found!"); throw new Exception}
    }
  }

  def add(itemList: List[Item]): Unit =  {
    for (item <- itemList){
      add(item)
    }
  }

  def add(item: Item): Unit = {
    getQueue(item.category).enqueue(item)
  }
}

object Supermarket {
  val store: Supermarket = new Supermarket
}