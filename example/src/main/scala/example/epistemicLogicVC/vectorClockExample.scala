package example.epistemicLogicVC

import meta.deep.runtime.Actor
import squid.quasi.lift
import scala.collection.mutable.ListBuffer

@lift
class MainInit extends Actor {
  def main(): List[Actor] = {
    val l: ListBuffer[Actor] = new ListBuffer[Actor]()
    val lc: ListBuffer[Process] = new ListBuffer[Process]()

    val total: Int = 3

    (1 to total).foreach(_ =>
      lc.append(new Process())
    )

    lc.foreach(c => {
      c.others = lc.toList.filterNot(p => p==c)
    })

    l.appendAll(lc)

    l.toList
  }
}

object epistemicLogicVCExample extends App {
  import meta.deep.IR
  import meta.deep.IR.TopLevel.ClassWithObject
  import meta.compile.compileSims

  val cls1: ClassWithObject[Process] = Process.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1), mainClass)
}
