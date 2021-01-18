package example 
package distributedGraph 

import squid.quasi.lift 
import lib.Bot.MessengerBot
import meta.classLifting.SpecialInstructions._ 

import Actor.AgentId 
/**
  * A weighted channel is bi-directional carrying a weight 
  * 
  * @param sender
  * @param receiver
  * @param weight: the weight of the edge 
  */ 
@lift
class WeightedChannel(val sender: TreeNode,
                    val receiver: TreeNode, 
                    val weight: Int) extends Actor {

  def getNeighbor(x: AgentId): Option[(TreeNode, Int)] = {
    if (sender.id == x) {
      Some((receiver, weight))
    } else if (receiver.id == x) {
      Some((sender, weight))
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
