package example.groupMessage

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val l: ListBuffer[Actor] = new ListBuffer[Actor]()
    val lc: ListBuffer[Person] = new ListBuffer[Person]()

    val groupSize: Int = 7

    (1 to groupSize).foreach(i =>
      lc.append(new Person("Member " + i))
    )

    lc.toList.foreach(m => m.group = lc.filter(n => n!=m).toList)
    lc.toList
  }
}

object groupMessageExample extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.compile.compileSims

  val cls1: ClassWithObject[Person] = Person.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1), mainClass)
}

