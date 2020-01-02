package meta.example.supermarket

import meta.example.supermarket.goods.Item

import scala.collection.mutable.{ArrayBuffer}

class ItemDeque(var item: Item, var itemList: Vector[Item]) {
  private val itemDeque = ArrayBuffer[Item]()

  def this(item: Item){
    this(item, itemList=null)
    itemDeque += item
  }

  def this(itemList: Vector[Item]){
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

  def +=(itemList: Vector[Item]): Unit = {
    itemDeque ++= itemList
  }

  def popLeft: Item = {
    if (size == 0) throw new NoSuchElementException
    itemDeque.remove(0)
  }

  def popRight: Item = {
    if (size == 0) throw new NoSuchElementException
    itemDeque.remove(size-1)
  }

  def peak: Item = {
    itemDeque(0)
  }
}