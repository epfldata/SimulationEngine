package example
package spanningTree.broadcast
package v1

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import Actor.AgentId
import lib.Bot.MessengerBot

/**
 * Build a spanning tree from list of neighbors
 */
@lift
class Node(val root: Boolean) extends TreeNode {

  var channels: List[Channel] = List()      // for dependency injection
  
  private var neighbors: List[Node] = List()
  private var parent: Option[Node] = None
  private var children: List[Node] = List()

  // the number of msg waiting for from its children before sending a message Back to its parent
  var expected_msg: Int = 0

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
   parent = Some(this)
   expected_msg = neighbors.length

   neighbors.foreach(n => {
     asyncMessage(() => n.go(this))
   })
 }

 // Broadcast: one-to-many 
 def go(sender: Node): Unit = {
   println(s"$id receives go message from ${sender.id}")

   if (!parent.isDefined) {
     parent = Some(sender)
     expected_msg = neighbors.length - 1
     if (expected_msg == 0) {
       println(s"Node $id is done!")
       asyncMessage(() => sender.back(List(this)))
     } else {
       neighbors.filter(x => x!=sender).foreach(x => asyncMessage(() => x.go(this)))
     }
   } else {
     // not a child node of the sender
     asyncMessage(() => sender.back(Nil))
   }
 }

 // Convergecast: many-to-one 
 def back(cs: List[Node]): Unit = {
   println(s"$id receives back message of ${cs.map(x => x.id)}")
   expected_msg = expected_msg - 1
   if (!cs.isEmpty) {
     children = cs ::: children
   }
   if (expected_msg == 0) {
     if (parent.get != this) {
       parent.get.back(this :: children)
     } else {
       println("Broadcast completed!")
     }
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
