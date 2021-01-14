package example
package spanningTree.broadcast

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

/**
  * Channel is bi-directional
  * Used only for discovering neighbors (though it has the potential to capture all communication)
  * @param sender
  * @param receiver
  */ 
@lift
class Channel(val sender: TreeNode,
              val receiver: TreeNode) extends Actor {

  def getNeighbor(x: Actor.AgentId): Option[TreeNode] = {
    if (sender.id == x) {
      Some(receiver)
    } else if (receiver.id == x) {
      Some(sender)
    } else {
      println(s"Invalid channel for $x!")
      None 
    }
  }

  def main(): Unit = {
    while(true){
      handleMessages()
      waitLabel(Turn, 1)
    }
  }
}
