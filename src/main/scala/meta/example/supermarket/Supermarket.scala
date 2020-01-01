package meta.example.supermarket

import meta.example.supermarket.goods.Item
import scala.collection.mutable.{Map, Queue}

//case class Warehouse(var Vegetable: Map[String, ItemDeque] = Map[String, ItemDeque](),
//                     var Meat: Map[String, ItemDeque] = Map[String, ItemDeque](),
//                     var Snack: Map[String, ItemDeque] = Map[String, ItemDeque](),
//                     var Grain: Map[String, ItemDeque] = Map[String, ItemDeque](),
//                     var Dairy: Map[String, ItemDeque] = Map[String, ItemDeque]())

class Supermarket extends SummaryTrait {
  val warehouse: Map[String, ItemDeque] = Map[String, ItemDeque]()
  val isInvalids: Queue[Long] = new Queue()

  val vegetables: List[String] = categories.getArticleNames("Vegetable")
  val meats: List[String] = categories.getArticleNames("Meat")
  val snacks: List[String] = categories.getArticleNames("Snack")
  val grains: List[String] = categories.getArticleNames("Grain")
  val dairys: List[String] = categories.getArticleNames("Dairy")

  def recordWaste(category: String, priceUnit: Int, isSold: Boolean): Unit ={
    updateWasteSummary(category, priceUnit, isSold)
  }

  def getItemDeque(item: String): ItemDeque = {
    warehouse.get(item).get
  }

  def initializeItemDeque(item: Item): Unit = {
    warehouse += (item.name -> new ItemDeque(item))
  }

  def initializeItemDeque(item: List[Item]): Unit = {
    assert(item.size > 0)
    warehouse += (item(0).name -> new ItemDeque(item))
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
}

object Supermarket {
  val store: Supermarket = new Supermarket
}