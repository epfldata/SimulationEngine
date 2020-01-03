package meta.example.supermarket

import meta.example.supermarket.utils.{divCeil, randElement}
import meta.example.supermarket.categories.{articleName, gram}
import meta.example.supermarket.goods.Item
import scala.collection.mutable.Map

class Fridge {
  val amountMap: Map[articleName, gram] = Map().withDefaultValue(0)
  val storage: Map[articleName, ItemDeque] = Map()
  val opened: Map[articleName, gram] = Map().withDefaultValue(0)

  def isEmpty: Boolean = {
    amountMap.size + storage.size == 0
  }

  def add(item: Item): Unit = {
    getAmount(item.name) match {
      case 0 => storage += (item.name -> new ItemDeque(item))
      case _ => storage.get(item.name).get += item
    }
    val newAmount: Int = amountMap(item.name) + item.priceUnit
    amountMap += (item.name -> newAmount)
    opened += (item.name -> item.priceUnit)
  }

  def getAmount(article: articleName): Int = {
    amountMap(article)
  }

  def getAvailFood: Vector[articleName] = {
    opened.filterKeys(opened(_)!=0).keys.toVector
  }

  def rmExpired(item: Item): Unit = {
    storage.get(item.name).get.popLeft
    amountMap += (item.name -> (amountMap(item.name)-opened(item.name)))
    item.cleanExpired(opened(item.name))
    opened += (item.name -> 0)
  }

  def consume(article: articleName, amount: gram): Int = {
    val currentAmount: Int = getAmount(article)
    currentAmount match {
      case 0 => 0
      case _ => {
        val targetItem: Item = storage.get(article).get.peak
        val targetUnit: Int = targetItem.priceUnit
        (currentAmount <= amount) match {
          case true => {
            consumeAll(article)
            currentAmount
          }
          case false => {
            if (amount > opened(article)) {
              set2Consume(article, divCeil(amount - opened(article), targetUnit))
              opened += (article -> (opened(article) - (amount - opened(article))%targetUnit))
            } else {
              opened += (article -> (opened(article)-amount))
            }
            if (opened(article)==0){
              set2Consume(article, 1)
              opened += (article -> targetUnit)
            }
            amountMap += (article -> (amountMap(article)-amount))
            amount
          }
        }
      }
    }
  }

  def consumeAll(article: articleName): Unit = {
    set2Consume(article, storage.get(article).get.size)
    amountMap += (article -> 0)
    opened += (article -> 0)
  }

  // remove count number of instances of given article
  private def set2Consume(article: articleName, count: Int): Unit = {
    1.to(count).foreach(
      _ => storage.get(article).get.popLeft.consume
    )
  }
}

