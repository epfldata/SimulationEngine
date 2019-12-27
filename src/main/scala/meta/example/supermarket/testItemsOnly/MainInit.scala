package meta.example.supermarket.testItemsOnlyExample

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods._
@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()

    val supermarket: Supermarket = new Supermarket()

    val item1 = new Item1
    item1.supermarket = supermarket
    l.append(item1)

    val item2 = new Item2
    item2.supermarket = supermarket
    l.append(item2)

    val item3 = new Item3
    item3.supermarket = supermarket
    l.append(item3)

    val item4 = new Item4
    item4.supermarket = supermarket
    l.append(item4)

    val item5 = new Item5
    item5.supermarket = supermarket
    l.append(item5)

    val item6 = new Item6
    item6.supermarket = supermarket
    l.append(item6)

    val item7 = new Item7
    item7.supermarket = supermarket
    l.append(item7)

    val item8 = new Item8
    item8.supermarket = supermarket
    l.append(item8)

    val item9 = new Item9
    item9.supermarket = supermarket
    l.append(item9)

    val item10 = new Item10
    item10.supermarket = supermarket
    l.append(item10)

    val item11 = new Item11
    item11.supermarket = supermarket
    l.append(item11)

    val item12 = new Item12
    item12.supermarket = supermarket
    l.append(item12)

    val item13 = new Item13
    item13.supermarket = supermarket
    l.append(item13)

    val item14 = new Item14
    item14.supermarket = supermarket
    l.append(item14)

    val item15 = new Item15
    item15.supermarket = supermarket
    l.append(item15)

    val item16 = new Item16
    item16.supermarket = supermarket
    l.append(item16)

    val item17 = new Item17
    item17.supermarket = supermarket
    l.append(item17)

    val item18 = new Item18
    item18.supermarket = supermarket
    l.append(item18)

    val item19 = new Item19
    item19.supermarket = supermarket
    l.append(item19)

    val item20 = new Item20
    item20.supermarket = supermarket
    l.append(item20)

    val item21 = new Item21
    item21.supermarket = supermarket
    l.append(item21)

    val item22 = new Item22
    item22.supermarket = supermarket
    l.append(item22)

    val item23 = new Item23
    item23.supermarket = supermarket
    l.append(item23)

    val item24 = new Item24
    item24.supermarket = supermarket
    l.append(item24)

    val item25 = new Item25
    item25.supermarket = supermarket
    l.append(item25)

    l.toList
  }
}
