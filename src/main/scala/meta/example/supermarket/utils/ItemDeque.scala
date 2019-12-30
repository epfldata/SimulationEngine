package meta.example.supermarket

import meta.example.supermarket.goods.Item

import scala.collection.mutable.{ArrayBuffer}

class ItemDeque(var item: Item = null) {
  private val itemDeque = ArrayBuffer[Item]()
  if (item!=null) itemDeque += item

  def size: Int = {
    itemDeque.size
  }

  def +=(item: Item): Unit = {
    itemDeque += item
  }

  def popLeft: Item = {
    itemDeque.remove(0)
  }

  def popRight: Item = {
    itemDeque.remove(size-1)
  }
}