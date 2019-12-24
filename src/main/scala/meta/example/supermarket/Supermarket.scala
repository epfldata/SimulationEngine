package meta.example.supermarket

import meta.example.supermarket.goods.{Item}

import scala.collection.mutable.PriorityQueue

class Supermarket extends SummaryTrait {
  import Supermarket._

  def recordWaste(category: String, priceUnit: Int, isSold: Boolean): Unit ={
    updateWasteSummary(category, priceUnit, isSold)
  }

  def sell(category: String, item: String): Item = {
    println("Purchase item! Current size of the queue is: " + vegetables.size)
    val soldItem: Item = vegetables.dequeue()
    soldItem.purchase
    soldItem
  }

  var vegetables: PriorityQueue[Item] = null
}

object Supermarket {
  val store: Supermarket = new Supermarket
}