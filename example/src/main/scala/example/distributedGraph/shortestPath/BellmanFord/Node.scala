package example
package distributedGraph
package shortestPath
package BellmanFord

import squid.quasi.lift
import meta.classLifting.SpecialInstructions._

import Actor.AgentId
import lib.Bot.MessengerBot

import scala.collection.mutable.Map 

/**
 * Initial knowledge: each process knows all process ids and its position on the communication graph (neighbors). Nothing else about the structure of the graph 
 * pid: an int between 1 and totalNodes 
 */
@lift
// class Node(val root: Boolean, 
//            val allPids: List[AgentId]) extends TreeNode {
class Node(val root: Boolean, 
           val allPids: List[AgentId], 
           val service: DiscoverNeighborWithWeightService) extends TreeNode {

    var neighbors: List[Node] = List() 
    var channels: List[WeightedChannel] = List() 

    // lengths[k]: shortest path from current node to k (pid, length)
    private var lengths: Map[Long, Int] = Map[Long, Int]() 

    // length associated with channel (i, j) where i is the current pid; 
    // j is the key of lg  
    // set during the initialization 
    private var lg: Map[Long, Int] = Map[Long, Int]() 

    // when terminate, routing_to[k]=j means j is a neighbor of current pid on the shortest paths to k 
    private var routing_to: Map[Long, Node] = Map[Long, Node]()

    private var updated: Boolean = false 

    // discover the neighbors and initialize lg  
    def init(): Unit = {
        val ans: List[(TreeNode, Int)] = service.getNeighbor(id, channels)

        ans.foreach(p => {
            neighbors = (p._1).asInstanceOf[Node] :: neighbors 
            lg(p._1.id) = p._2  
        })

        println("Neighbors discovered! ")
        // initial length inf max 
        allPids.foreach(x => {
            lengths(x) = Int.MaxValue
        })

        // length to itself is 0 
        lengths(id) = 0 
        if (root) start() 
    }

    def start(): Unit = {
        val outMsg = (this, lengths)

        neighbors.foreach(n => {
            asyncMessage(() => n.update(outMsg))
        })
    }

    // sender, length 
    def update(msg: (Node, Map[Long, Int])): Unit = {
        val sender: Node = msg._1  
        val senderLengths: Map[Long, Int] = msg._2 
        updated = false 
        
        // Check for MaxValue to avoid overflow error
        allPids.filter(x => x!=id).foreach(x => {
            if ((senderLengths(x)!=Int.MaxValue) && (lengths(x) > (lg(sender.id) + senderLengths(x)))) {
                lengths(x) = lg(sender.id) + senderLengths(x)
                routing_to(x) = sender
                updated = true 
            }
        })

        // If changed with any shorter path, inform all neighbors 
        val outMsg = (this, lengths)

        if (updated) {
            neighbors.foreach(n => {
                asyncMessage(() => n.update(outMsg))
            })
        }
    }

    def main() {
        init() 
        println(s"Channel distance to neighbors : $lg")

        while (true) {
            handleMessages()
            waitLabel(Turn, 1)
            println(s"$id: Lengths: $lengths")
        }
    }

}