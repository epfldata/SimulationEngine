package example
package groupMessage

import squid.quasi.lift

@lift
class MainInit {
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

  val cls1: ClassWithObject[Person] = Person.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1), mainClass)
}

