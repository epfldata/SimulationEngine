package example
package epistemicLogicExamples
package MuddyChildren_v1

import squid.quasi.lift

object Example extends App {
  import meta.deep.IR.Predef._ 
  import lib.Bot.MessengerBot

  val init = code"""    
    val lc: ListBuffer[Child] = new ListBuffer[Child]()

    val muddyChildren: Int = 3
    val totalChildren: Int = 5

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
  """

  val cls2: ClassWithObject[Adult] = Adult.reflect(IR)
  val cls1: ClassWithObject[Child] = Child.reflect(IR)
  val cls3: ClassWithObject[MessengerBot] = MessengerBot.reflect(IR)
  // val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls2, cls1, cls3), 
  mainInit = Some(init), 
  initPkgName = this.getClass.getPackage.getName)
}