package example
package distributedGraph 
package networkTraversal
package DFSpanningTree 

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import Actor.AgentId
import lib.Bot.MessengerBot

/**
  * DFS construct a spanning tree (carry global information in visited)
  * O(n) msg complexity and time complexity 
  */
@lift
class Node(val root: Boolean) extends TreeNode {

  var channels: List[Channel] = List()      
  
  private var neighbors: Set[Node] = Set()
  private var parent: Option[Node] = None
  private var children: List[Node] = List()

  // the number of msg waiting for from its children before sending a message Back to its parent

  def init(): Unit = {
    // Nodes learn who their neighbors are at the same turn
    val mBot = new MessengerBot()
    val ns: List[Option[Future[Option[TreeNode]]]] = channels.map(c => asyncMessage(() => c.getNeighbor(id)))

    neighbors = mBot.waitUntilAllReply(ns)
    .map(x => x.asInstanceOf[Option[Node]])
    .filter(x => x.isDefined)
    .map(x => x.get)
    .toSet[Node]
  }

 // Only root node calls start 
 def start(): Unit = {
   parent = Some(this)

  // neighbors.map(n => n.go(this))
    var ns: Option[Future[Unit]] = None 
    val getAround = (this, Set(this))

    val child: Node = neighbors.head 

    asyncMessage(() => child.go(getAround))
    children = child :: children  
 }

 // Broadcast with global info (visited)
  def go(msg: (Node, Set[Node])): Unit = {
    val sender: Node = msg._1 
    val gvisited: Set[Node] = msg._2

   println(s"$id receives go message from ${sender.id}")
   if (!parent.isDefined) {
      parent = Some(sender)
      val visited = gvisited.union(Set(this))

      val getAround = (this, visited)
     // all neighbors are visited 
     val unvisitedNeighbors: Set[Node] = neighbors.diff(visited)

     if (unvisitedNeighbors.isEmpty) {
       asyncMessage(() => sender.back(visited))
     } else {
        val child: Node = unvisitedNeighbors.head 
        asyncMessage(() => child.go(getAround))
        children = child :: children  
     }
   } 
 }

 def back(visited: Set[Node]): Unit = {
    println(s"$id children: ${children.map(x => x.id)} visited: ${visited.map(x => x.id)}")
     val unvisitedNeighbors = neighbors.diff(visited)
     val getAround = (this, visited)

     if (unvisitedNeighbors.isEmpty) {
      if (parent.get != this) {
        parent.get.back(visited)
      } else {
        println("Broadcast completed!")
      }
    } else {
      val child: Node = unvisitedNeighbors.head 
      asyncMessage(() => child.go(getAround))
      children = child :: children  
    }
 }

  def main(): Unit = {
   init()
   println(s"Initialization completed! Neighbors of $id: ${neighbors.map(x => x.id)}")

   if (root) {
      start() 
   }

    while (true) {
      handleMessages()
      waitLabel(Turn, 1)
    }
  }
}
