package example
package distributedGraph 
package networkTraversal 
package BFSpanningTreeWave

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import Actor.AgentId
import lib.Bot.MessengerBot

/**
 * Build a spanning tree using BFS w/ centralized control, i.e. process knows their values are final 
 * Worst case: messages: O(n^2), time: O(n^2)
 */
@lift
class Node(val root: Boolean) extends TreeNode {

  var channels: List[Channel] = List()      // for dependency injection
  
  private var neighbors: Set[Node] = Set()
  private var parent: Option[Node] = None
  private var children: List[Node] = List()

  // write-once variable, record the distance to root 
  var distance: Int = 0

  // neighbors to whom the node needs to propagate the wave 
  var to_send: Set[Node] = Set()
  // manage the return of the current wave to its parent 
  var waiting_from: Set[Node] = Set()

  // responses, input to back()
  val NO: Int = 0
  val STOP: Int = 1 
  val CONTINUE: Int = 2

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

 def start(): Unit = {
   to_send = neighbors
   parent = Some(this)
   val getAround = (List(this), distance)

   to_send.toList.foreach(x => asyncMessage(() => x.go(getAround)))
   waiting_from = neighbors
 }

 // Broadcast: one-to-many 
 def go(msg: (List[Node], Int)): Unit = {
   val sender: Node = msg._1.head  
   val dlevel: Int = msg._2 

   println(s"$id receives go message from ${sender.id} level $dlevel")

   var getAround = (List(this), NO)

   if (!parent.isDefined) {
      parent = Some(sender) 
      distance = dlevel + 1     // write-once 
      to_send = neighbors.toList.filter(x => x!=sender).toSet[Node]

      if (to_send.isEmpty) {
        getAround = (List(this), STOP)
        asyncMessage(() => sender.back(getAround))
      } else {
        getAround = (List(this), CONTINUE)
        asyncMessage(() => sender.back(getAround))
      }
    } else if (parent.get == sender) {
      // launch the wave. Add d+1 nodes to the tree 
      getAround = (List(this), distance)

      to_send.toList.foreach(n => {
        asyncMessage(() => n.go(getAround))
      })

      waiting_from = to_send 
    } else {
      // return No 
      asyncMessage(() => sender.back(getAround))
    }
 }

 // Convergecast: many-to-one 
 // response: 0 - no, 1 - stop, 2 - continue 
 def back(msg: (List[Node], Int)): Unit = {
    val cs: List[Node] = msg._1 
    val resp = msg._2 

    var getAround = msg 

    println(s"$id receives back message from ${cs.map(x => x.id)} Response: $resp")

    waiting_from = waiting_from.diff(Set(cs.head))

    // Add to children 
    if (resp != NO) {
      children = cs ::: children
    }

    // No longer sends msg 
    if (resp != CONTINUE) {
      to_send = to_send.diff(Set(cs.head))
    }

    if (to_send.isEmpty) {
      if (parent.get == this) {
        println("Broadcast completed!")
      } else {
        getAround = (this :: children, STOP)
        parent.get.back(getAround)
      } 
    } else {
      if (waiting_from.isEmpty) {
        if (parent.get == this) {
          getAround = (List(this), distance)

          to_send.toList.foreach(n => {
            asyncMessage(() => n.go(getAround))
          })
          waiting_from = to_send 
        } else {
          getAround = (this :: children, CONTINUE)

          parent.get.back(getAround)
        }   
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
