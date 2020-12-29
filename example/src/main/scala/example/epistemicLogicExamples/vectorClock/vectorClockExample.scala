package example
package epistemicLogicVC

import squid.quasi.lift

@lift
class MainInit {
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

  val cls1: ClassWithObject[Process] = Process.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1), mainClass)
}
