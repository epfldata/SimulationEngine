package example
package distributedGraph.networkTraversal
package BFSpanningTreeWave

import squid.quasi.lift
import lib.Bot.MessengerBot

@lift
class MainInit {
  def main(): List[Actor] = {

    val a: Node = new Node(true)  // root 
    val b: Node = new Node(false)
    val c: Node = new Node(false)
    val d: Node = new Node(false)

    // connects the graph 
    // only used during the initialization when building the communication graph
    val c1: Channel = new Channel(a, b)
    val c2: Channel = new Channel(a, c)
    val c3: Channel = new Channel(b, c)
    val c4: Channel = new Channel(d, c)

    a.channels = List(c1, c2)
    b.channels = List(c1, c3)
    c.channels = List(c2, c3, c4)
    d.channels = List(c4)

    List(c1, c2, c3, c4, a, b, c, d)
  }
}

object Example extends App {

  val cls1: ClassWithObject[Node] = Node.reflect(IR)
  val cls2: ClassWithObject[Channel] = Channel.reflect(IR)
  val cls3: ClassWithObject[MessengerBot] = MessengerBot.reflect(IR)
  val cls4: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2, cls3), cls4)
}
