package example
package distributedGraph
package networkTraversal 
package BFSpanningTree

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import Actor.AgentId
import lib.Bot.MessengerBot
import breeze.numerics.exp

/**
 * Build a spanning tree using BFS w/o centralized control, i.e. process has no global information to know whether their values are final 
 * Worst case: messages: O(n^3), time: O(n)
 */
@lift
class Node(val root: Boolean) extends TreeNode {

  var channels: List[Channel] = List()      // for dependency injection
  
  private var neighbors: List[Node] = List()
  private var parent: Option[Node] = None
  private var children: List[Node] = List()

  var expected_msg: Int = 0 

  // the approximated distance to root 
  // Updated each time when it received go(), even if it has finalized (lack of global info. Only have local knowledge of neighbors, relying on root)
  var level: Int = 0

  def init(): Unit = {
    // Nodes learn who their neighbors are at the same turn
    val mBot = new MessengerBot()
    val ns: List[Option[Future[Option[TreeNode]]]] = channels.map(c => asyncMessage(() => c.getNeighbor(id)))
    neighbors = mBot.waitUntilAllReply(ns)
    .map(x => x.asInstanceOf[Option[Node]])
    .filter(x => x.isDefined)
    .map(x => x.get)
  }

 def start(): Unit = {
   expected_msg = neighbors.length
   parent = Some(this)
   val getAround = (List(this), level)

   neighbors.foreach(x => asyncMessage(() => x.go(getAround)))
 }

 // Broadcast: one-to-many 
 def go(msg: (List[Node], Int)): Unit = {
   val sender: Node = msg._1.head  
   val dlevel: Int = msg._2 

   println(s"$id receives go message from ${sender.id} level $dlevel")

   var getAround = (List(this), level)

   if (!parent.isDefined || level > (dlevel + 1)) {
      parent = Some(sender) 
      level = dlevel + 1 
      expected_msg = neighbors.length - 1

      getAround = (List(this), level)

      if (expected_msg == 0) {
        asyncMessage(() => sender.back(getAround))
      } else {
        neighbors.filter(x => x!=sender).foreach(x => asyncMessage(() => x.go(getAround)))
      }
    } else {
      getAround = (List(), dlevel+1)
      asyncMessage(() => sender.back(getAround))
    }
 }

 // Convergecast: many-to-one 
 def back(msg: (List[Node], Int)): Unit = {
    val cs: List[Node] = msg._1 
    val dlevel = msg._2 

    var getAround = msg 

    println(s"$id receives back message from ${cs.map(x => x.id)} level: $dlevel")

    if (level == dlevel - 1) {
      children = cs ::: children 
      expected_msg = expected_msg - 1
      if (expected_msg == 0) {
        if (parent.get != this) {
          getAround = (this :: getAround._1, level)
          parent.get.back(getAround)
        } else {
          println("Broadcast completed!")
        }
      }
    } else {
      println("Message discarded!")
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
