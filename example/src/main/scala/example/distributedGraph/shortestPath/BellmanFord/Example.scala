package example
package distributedGraph
package shortestPath
package BellmanFord


import squid.quasi.lift
import lib.Bot.MessengerBot

@lift
class MainInit {
  def main(): List[Actor] = {

    val service: DiscoverNeighborWithWeightService = new DiscoverNeighborWithWeightService() 

    val allPids: List[Long] = List(2, 3, 4, 5) 

    // val a: Node = new Node(true, allPids)  
    // val b: Node = new Node(false, allPids)
    // val c: Node = new Node(false, allPids)
    // val d: Node = new Node(false, allPids)

    val a: Node = new Node(true, allPids, service)  
    val b: Node = new Node(false, allPids, service)
    val c: Node = new Node(false, allPids, service)
    val d: Node = new Node(false, allPids, service)

    // connects the graph 
    // undirected weighted edges 
    val c1: WeightedChannel = new WeightedChannel(a, b, 1)
    val c2: WeightedChannel = new WeightedChannel(a, c, 5)
    val c3: WeightedChannel = new WeightedChannel(a, d, 2)
    val c4: WeightedChannel = new WeightedChannel(b, c, 3)
    val c5: WeightedChannel = new WeightedChannel(b, d, 1)
    val c6: WeightedChannel = new WeightedChannel(c, d, 1)

    a.channels = List(c1, c2, c3)
    b.channels = List(c1, c4, c5)
    c.channels = List(c2, c4, c6)
    d.channels = List(c3, c5, c6)

    // List(a, b, c, d, c1, c2, c3, c4, c5, c6, service)
    List() 
  }
}

object Example extends App {

  val cls1: ClassWithObject[Node] = Node.reflect(IR)
  val cls2: ClassWithObject[WeightedChannel] = WeightedChannel.reflect(IR)
  val cls3: ClassWithObject[MessengerBot] = MessengerBot.reflect(IR)
  val channelServ: ClassWithObject[DiscoverNeighborWithWeightService] = DiscoverNeighborWithWeightService.reflect(IR)
  val cls4: ClassWithObject[MainInit] = MainInit.reflect(IR)

  compileSims(List(cls1, cls2, cls3, channelServ), cls4)
}

