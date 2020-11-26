package example.muddy_children

import meta.classLifting.SpecialInstructions._
import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer
import scala.util.Random

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val l: ListBuffer[Actor] = new ListBuffer[Actor]()
    val lc: ListBuffer[MuddyChild] = new ListBuffer[MuddyChild]()

    val muddyChildren: Int = 3
    val totalChildren: Int = 5

    (1 to muddyChildren).foreach(_ =>
      lc.append(new MuddyChild(true))
    )

    (1 to (totalChildren - muddyChildren)).foreach(_ =>
      lc.append(new MuddyChild(false))
    )

    lc.foreach(c => {
      c.env = lc.toList.filterNot(p => p==c)
    })

    val parent: Parent = new Parent(lc.toList)
    
    l.appendAll(lc)
    l.append(parent)

    l.toList 
  }
}

object muddyChildrenExample extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.compile.compileSims

  val cls1: ClassWithObject[MuddyChild] = MuddyChild.reflect(IR)
  val cls2: ClassWithObject[Parent] = Parent.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2), mainClass)
}

