package example
package distributedGraph

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import lib.Bot.MessengerBot
import Actor.AgentId

/**
  * Service that returns neighbors for the sender as a list of tree node
  */ 
@lift
class DiscoverNeighborWithWeightService() extends Actor {

    def getNeighbor(senderId: AgentId, channels: List[WeightedChannel]): List[(TreeNode, Int)] = {
        val mBot = new MessengerBot()
        val ns: List[Option[Future[Option[(TreeNode, Int)]]]] = channels
            .map(c => asyncMessage(() => c.getNeighbor(senderId)))
        mBot.waitUntilAllReply(ns)
            .map(x => x.asInstanceOf[Option[(TreeNode, Int)]])
            .filter(x => x.isDefined)
            .map(x => x.get) 
    }

    def main(): Unit = {
        while(true){
            handleMessages()
            waitLabel(Turn, 1)
        }
    }
}