package meta.runtime

import Actor.AgentId
import meta.classLifting.SpecialInstructions._
import scala.collection.mutable.ListBuffer

/**
 * A container agent holds a collection of agents. 
 * The internal messages among the agents are non-blocking. 
 */ 

class Container extends Actor {
    protected var position: Int = 0

    // Bound latency, default to 1
    var kbound: Int = 1
    // Use a list buffer to remove agents
    val containedAgents: scala.collection.mutable.Map[AgentId, Actor] = scala.collection.mutable.Map[AgentId, Actor]()
    
    protected var internalMessages: ListBuffer[Message] = ListBuffer[Message]()

    // Dynamically add agents to a container at run time
//   def addAgents(sims: Seq[Actor]): Unit = {
//     val simsMessages = sims.flatMap(_.getSendMessages)
//     sendMessages.appendAll(simsMessages)
//     containedAgents ++= sims.map(_.cleanSendMessage).map(x => (x.id, x)).toMap
//     addProxyIds(sims.flatMap(x => x.getProxyIds))
//     sims.foreach(s => {
//       s._container = this
//     })
//   }

    def setKBound(bound: Int): Unit = {
        kbound = bound
    }
    
    protected var mx = receivedMessages.toList.groupBy(_.receiverId)

    // vanilla compiled
    override def run(msg: List[Message]): (List[Message], Int) = {
        mx = msg.toList.groupBy(_.receiverId)

        sendMessages.clear()

        val sentMessages = containedAgents.flatMap(a => {
                a._2.run(a._2.getProxyIds.toList.flatMap(
                        id => mx.getOrElse(id, List())))._1
            })

        sendMessages.appendAll(sentMessages)
        (sendMessages.toList, 1)
    }
}