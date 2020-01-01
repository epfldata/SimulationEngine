package meta.example.supermarket

import meta.example.supermarket.goods.Item

import scala.collection.mutable.{ArrayBuffer}

class ItemDeque(var item: Item, var itemList: List[Item]) {
  private val itemDeque = ArrayBuffer[Item]()

  def this(item: Item){
    this(item, itemList=null)
    itemDeque += item
  }

  def this(itemList: List[Item]){
    this(item=null, itemList)
    itemDeque ++= itemList
  }

  def this(){
    this(item=null, itemList=null)
  }

  def size: Int = {
    itemDeque.size
  }

  def +=(item: Item): Unit = {
    itemDeque += item
  }

  def +=(itemList: List[Item]): Unit = {
    itemDeque ++= itemList
  }

  def popLeft: Item = {
    itemDeque.remove(0)
  }

  def popRight: Item = {
    itemDeque.remove(size-1)
  }
}