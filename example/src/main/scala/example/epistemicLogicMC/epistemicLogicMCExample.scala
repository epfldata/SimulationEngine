package example.epistemicLogicMC

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val l: ListBuffer[Actor] = new ListBuffer[Actor]()
    val lc: ListBuffer[Child] = new ListBuffer[Child]()

    val muddyChildren: Int = 2
    val totalChildren: Int = 3

    (1 to muddyChildren).foreach(_ =>
      lc.append(new Child(true))
    )

    (1 to (totalChildren - muddyChildren)).foreach(_ =>
      lc.append(new Child(false))
    )

    lc.foreach(c => {
      c.neighbors = lc.toList.filterNot(p => p==c)
    })

    val adult: Adult = new Adult(lc.toList)

    l.appendAll(lc)

    l.append(adult)

    l.toList
  }
}

object epistemicLogicMCExample extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.compile.compileSims

  val cls2: ClassWithObject[Adult] = Adult.reflect(IR)
  val cls1: ClassWithObject[Child] = Child.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls2, cls1), mainClass)
}