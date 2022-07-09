package meta.runtime

import Actor.AgentId
import meta.classLifting.SpecialInstructions._
import scala.collection.mutable.{ListBuffer, Map => MutMap}

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
    
    protected val internalMessages: MutMap[AgentId, ListBuffer[Message]] = MutMap[AgentId, ListBuffer[Message]]()

    // Dynamically add agents to a container at run time
//   def addAgents(sims: Seq[Actor]): Unit = {
//     val simsMessages = sims.flatMap(_.getSendMessages)
//     sendMessages.appendAll(simsMessages)
//     containedAgents ++= sims.map(_.cleanSendMessage).map(x => (x.id, x)).toMap
//     addProxyIds(sims.flatMap(x => x.proxyIds))
//     sims.foreach(s => {
//       s._container = this
//     })
//   }

    def setKBound(bound: Int): Unit = {
        kbound = bound
    }
    override def run(msg: List[Message]): (List[Message], Int) = {
        var localTurns: Int = 0
        val newMsgs = msg.groupBy(_.receiverId)

        sendMessages.clear()
        do {
            val sentMessages = containedAgents.flatMap(a => {
                a._2.run(
                    a._2.proxyIds.flatMap(
                        id => internalMessages.remove(id).getOrElse(List()) ++ newMsgs.getOrElse(id, List())
                    ))._1
            })

            val separated_msgs = sentMessages.partition(x => (proxyIds.contains(x.receiverId)))

            sendMessages.appendAll(separated_msgs._2)
            separated_msgs._1.foreach(m => {
                internalMessages.getOrElseUpdate(m.receiverId, ListBuffer()).append(m)
            })
            localTurns += 1
        } while (!internalMessages.isEmpty && localTurns<kbound)
        (sendMessages.toList, localTurns)
    }
}