package meta.example.supermarket.testCustomersExample

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

import meta.example.supermarket.Supermarket
import meta.example.supermarket.goods._
import meta.example.supermarket.people._

/* Auto generated from genExample*/

@lift
class MainInit {
  def main(): List[Actor] = {
    val l = ListBuffer[Actor]()
    val l_repeat = ListBuffer[Actor]()

    (1 to 1).foreach(_ => l_repeat.append(new Customer1))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 1).foreach(_ => l_repeat.append(new Customer77))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item1))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item2))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item3))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item4))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item5))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item6))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item7))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item8))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item9))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item10))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item11))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item12))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item13))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item14))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item15))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item16))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item17))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item18))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item19))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item20))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item21))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item22))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item23))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item24))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item25))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item26))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item27))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item28))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item29))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item30))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    (1 to 5).foreach(_ => l_repeat.append(new Item31))
    Supermarket.store.initializeItemDeque(l_repeat.toVector.map(_.asInstanceOf[Item]))
    l ++= l_repeat
    l_repeat.clear()

    l.toList
  }
}
