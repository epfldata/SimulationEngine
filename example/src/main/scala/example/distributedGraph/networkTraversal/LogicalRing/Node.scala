package example
package distributedGraph.networkTraversal
package LogicalRing 

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import Actor.AgentId
import lib.Bot.MessengerBot

import scala.collection.mutable.Map 
/**
  * DFS construct a spanning tree (carry global information in visited)
  * O(n) msg complexity and time complexity 
  */
@lift
class Node(val root: Boolean) extends TreeNode {

  var channels: List[Channel] = List()      
  
  private var neighbors: Set[Node] = Set()
  private var parent: Option[Node] = None

  // the successor of the node on the logical ring 
  private var succ: Option[Node] = None 
  // routing table; the destination for the given node 
  private val routing: Map[AgentId, Actor] = Map[AgentId, Actor]()
  private var first: AgentId = -1 

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

    var ns: Option[Future[Unit]] = None 
    val getAround = (this, Set(this), this)

    val child: Node = neighbors.head 
    first = child.id 
    asyncMessage(() => child.go(getAround))
 }

 // sender, visited, last visited 
  def go(msg: (Node, Set[Node], Node)): Unit = {
    val sender: Node = msg._1 
    val gvisited: Set[Node] = msg._2
    val last: Node = msg._3 

    println(s"$id receives go message from ${sender.id}")

    if (!parent.isDefined) {
        parent = Some(sender)
        succ = Some(last)

        val visited = gvisited.union(Set(this))
        // all neighbors are visited 
        val unvisitedNeighbors: Set[Node] = neighbors.diff(visited)

        val goMsg = (this, visited, this)
        
        if (unvisitedNeighbors.isEmpty) {
          asyncMessage(() => sender.back(goMsg))
          routing(sender.id) = sender 
        } else {
            val child: Node = unvisitedNeighbors.head 
            asyncMessage(() => child.go(goMsg))
            routing(child.id) = sender 
        }
      } 
 }

 // sender, visited, last 
 def back(msg: (Node, Set[Node], Node)): Unit = {
    val sender: Node = msg._1
    val visited: Set[Node] = msg._2
    val last: Node = msg._3 

    println(s"$id visited: ${visited.map(x => x.id)}")
     val unvisitedNeighbors = neighbors.diff(visited)
     val backMsg = (this, visited, last)

     if (unvisitedNeighbors.isEmpty) {
      if (parent.get != this) {
        parent.get.back(backMsg)
        routing(parent.get.id) = sender
      } else {
        succ = Some(last)
        routing(first) = sender
        println("Ring completed!")
      }
    } else {
      val child: Node = unvisitedNeighbors.head 
      asyncMessage(() => child.go(backMsg))
      routing(child.id) = sender
    }
 }

  def main(): Unit = {
   init()
  //  println(s"Initialization completed! Neighbors of $id: ${neighbors.map(x => x.id)}")

   if (root) {
      start() 
   }

    while (true) {
      handleMessages()
      waitLabel(Turn, 1)
    }
  }
}
