package meta.example.supermarket

import meta.example.supermarket.goods.Item
import scala.collection.mutable.{Map, Queue}

case class Warehouse(var Vegetable: Map[String, ItemDeque] = Map[String, ItemDeque](),
                     var Meat: Map[String, ItemDeque] = Map[String, ItemDeque](),
                     var Snack: Map[String, ItemDeque] = Map[String, ItemDeque](),
                     var Grain: Map[String, ItemDeque] = Map[String, ItemDeque](),
                     var Dairy: Map[String, ItemDeque] = Map[String, ItemDeque]())

class Supermarket extends SummaryTrait {
  val warehouse: Warehouse = Warehouse()
  val isInvalids: Queue[Long] = new Queue()

  val vegetables: List[String] = categories.getArticleNames("Vegetable")
  val meats: List[String] = categories.getArticleNames("Meat")
  val snacks: List[String] = categories.getArticleNames("Snack")
  val grains: List[String] = categories.getArticleNames("Grain")
  val dairys: List[String] = categories.getArticleNames("Dairy")

  def recordWaste(category: String, priceUnit: Int, isSold: Boolean): Unit ={
    updateWasteSummary(category, priceUnit, isSold)
  }

  def checkItemDeque(category: String, item: String): Option[ItemDeque] = {
    category.capitalize match {
      case "Vegetable" => warehouse.Vegetable.get(item)
      case "Meat" => warehouse.Meat.get(item)
      case "Dairy" => warehouse.Dairy.get(item)
      case "Snack" => warehouse.Snack.get(item)
      case "Grain" => warehouse.Grain.get(item)
      case _ => {println("Unrecognized category name!"); throw new Exception}
    }
  }

  def getItemDeque(category: String, item: String): ItemDeque = {
    category.capitalize match {
      case "Vegetable" => warehouse.Vegetable.get(item).get
      case "Meat" => warehouse.Meat.get(item).get
      case "Dairy" => warehouse.Dairy.get(item).get
      case "Snack" => warehouse.Snack.get(item).get
      case "Grain" => warehouse.Grain.get(item).get
      case _ => {println("Unrecognized category name!"); throw new Exception}
    }
  }

  def initializeItemDeque(category: String, item: Item): Unit = {
    category.capitalize match {
      case "Vegetable" =>
        warehouse.Vegetable += (item.name -> new ItemDeque(item))
      case "Meat" =>
        warehouse.Meat += (item.name -> new ItemDeque(item))
      case "Dairy" =>
        warehouse.Dairy += (item.name -> new ItemDeque(item))
      case "Snack" =>
        warehouse.Snack += (item.name -> new ItemDeque(item))
      case "Grain" =>
        warehouse.Grain += (item.name -> new ItemDeque(item))
      case _ => {println("Unrecognized category name!"); throw new Exception}
    }
  }

  def initializeItemDeque(category: String, items: List[Item]): Unit = {
    assert(items.length>=1)
    category.capitalize match {
      case "Vegetable" =>
        warehouse.Vegetable += (items(0).name -> new ItemDeque(itemList = items))
      case "Meat" =>
        warehouse.Meat += (items(0).name -> new ItemDeque(itemList = items))
      case "Dairy" =>
        warehouse.Dairy += (items(0).name -> new ItemDeque(itemList = items))
      case "Snack" =>
        warehouse.Snack += (items(0).name -> new ItemDeque(itemList = items))
      case "Grain" =>
        warehouse.Grain += (items(0).name -> new ItemDeque(itemList = items))
      case _ => {println("Unrecognized category name!"); throw new Exception}
    }
  }

  def checkQueueSize(requested: ItemDeque, item: String): Unit = {
    if (requested.size==0) { println("Item not found :( " + item); throw new Exception }
  }

  def sell(category: String, item: String): Item = {
    val requested: ItemDeque = getItemDeque(category, item)

    checkQueueSize(requested, item)

    var soldItem: Item = requested.popLeft

    while (soldItem.state.isDiscarded){
      checkQueueSize(requested, item)
      soldItem = requested.popLeft
    }

    assert(!soldItem.state.isDiscarded)
    soldItem.purchase

    println(s"Item ${soldItem.name} sold! " + soldItem.id)
    soldItem
  }

  def add(itemList: List[Item], sameItems: Boolean = false): Unit =  {
    sameItems match {
      case true => {
        checkItemDeque(itemList(0).category, itemList(0).name) match {
          case None => initializeItemDeque(itemList(0).category, itemList)
          case Some(queue) => getItemDeque(itemList(0).category, itemList(0).name) += itemList
        }
      }
      case false => {
        for (item <- itemList){
          add(item)
        }
      }
    }

  }

  def add(item: Item): Unit = {
    checkItemDeque(item.category, item.name) match {
      case None => initializeItemDeque(item.category, item)
      case Some(queue) => getItemDeque(item.category, item.name) += item
    }
//    println("Elements in the queue is: " + getItemDeque(item.category, item.name))
  }
}

object Supermarket {
  val store: Supermarket = new Supermarket
}