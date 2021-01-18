package example
package rumor

import squid.quasi.lift
import lib.Bot.LoggerBotInt

@lift
class MainInit {
  def main(): List[Actor] = {
    val l: ListBuffer[Actor] = new ListBuffer[Actor]()
    var lp: List[Person] = List[Person]()

    // Use one logger to track the extent of the rumor spread
    val gossipLogger: LoggerBotInt = new LoggerBotInt("gossip", 3)

    val closeContacts: Int = 5
    val population: Int = 100

    val seed: Person = new Gossiper(gossipLogger, true, 0.5)

    lp = seed :: lp
    lp = lp ++ (1 to population).toList.map(i => new Gossiper(gossipLogger, false, 0.3))

    // Everyone is randomly connected to at most closeContacts
     lp.foreach(p =>
       p.network = p.network ++ (1 to closeContacts).toList.map(_ => Random.nextInt(population+1)).map(x => lp(x))
     )

    l.appendAll(lp)
    l.append(gossipLogger) 
    l.toList 
  }
}

object rumorExample extends App{

  import meta.deep.IR.TopLevel.ClassWithObject

  val cls1: ClassWithObject[Gossiper] = Gossiper.reflect(IR)
  val cls2: ClassWithObject[LoggerBotInt] = LoggerBotInt.reflect(IR)
  val mainClass: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2), Some(mainClass))
}