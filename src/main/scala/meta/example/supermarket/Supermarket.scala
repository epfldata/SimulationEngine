package meta.example.supermarket

import meta.example.supermarket.goods.Item
import meta.example.supermarket.utils.randElement
import scala.collection.mutable.{Map, Queue}

class Supermarket extends SummaryTrait {
  val warehouse: Map[String, ItemDeque] = Map[String, ItemDeque]()
  val isInvalids: Queue[Long] = new Queue()

  val vegetables: Vector[String] = categories.getArticleNames("Vegetable")
  val meats: Vector[String] = categories.getArticleNames("Meat")
  val snacks: Vector[String] = categories.getArticleNames("Snack")
  val grains: Vector[String] = categories.getArticleNames("Grain")
  val dairys: Vector[String] = categories.getArticleNames("Dairy")

  def recordWaste(category: String, priceUnit: Int, isSold: Boolean): Unit ={
    updateWasteSummary(category, priceUnit, isSold)
  }

  def getItemDeque(item: String): ItemDeque = {
    warehouse.get(item).get
  }

  def initializeItemDeque(item: Item): Unit = {
    warehouse += (item.name -> new ItemDeque(item))
  }

  def initializeItemDeque(item: Vector[Item]): Unit = {
    item.groupBy(_.name).foreach(pair =>
      warehouse += (pair._1 -> new ItemDeque(pair._2))
    )
  }

  def checkQueueSize(requested: ItemDeque, item: String): Unit = {
    if (requested.size==0) { println("Item not found :( " + item); throw new NoSuchElementException }
  }

  def sell(item: String): Item = {
    val requested: ItemDeque = getItemDeque(item)
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

  def getRandFood(category: String): String = {
    category.capitalize match {
      case "Vegetable" => randElement(vegetables)
      case "Meat" => randElement(meats)
      case "Dairy" => randElement(dairys)
      case "Snack" => randElement(snacks)
      case "Grain" => randElement(grains)
      case _ => {println("Unrecognized food category name for generating food! Category is " + category); throw new IllegalArgumentException}
    }
  }
}

object Supermarket {
  val store: Supermarket = new Supermarket
}