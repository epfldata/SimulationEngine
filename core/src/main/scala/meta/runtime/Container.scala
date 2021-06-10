package meta.runtime

import Actor.AgentId

import custMacros.Sim
import meta.classLifting.SpecialInstructions._
import scala.collection.mutable.ListBuffer
import org.coroutines._

/**
 * A container agent holds a collection of agents. 
 * The internal messages among the agents are non-blocking. 
 */ 

class Container extends Actor {
    protected var position: Int = 0

    // Use a list buffer to remove agents
    val containedAgents: scala.collection.mutable.Map[AgentId, Actor] = scala.collection.mutable.Map[AgentId, Actor]()
    
    protected var internalMessages: ListBuffer[Message] = ListBuffer[Message]()

    // Coroutine instances
    protected val containedAgentInstances: ListBuffer[org.coroutines.Coroutine.Instance[List[meta.runtime.Message],Unit]] = ListBuffer[org.coroutines.Coroutine.Instance[List[meta.runtime.Message],Unit]]()

    def initAddAgents(sims: Seq[Actor], mode: String): Unit = {
        containedAgents ++= sims.map(x => (x.id, x)).toMap

        addProxyIds(sims.flatMap(x => {
            x._container = this
            x.getProxyIds
        }))

        mode match {
          case "Staged" => containedAgentInstances.appendAll(
                            sims.map(x => call (x.run()())))
          case _ =>
        }
    }

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

    // Group messages by receiver id
    protected var mx = receivedMessages.toList.groupBy(_.receiverId)
    protected var messageBuffer: List[Message] = List()

    protected var unblockAgents: Set[Actor.AgentId] = Set()

    override def run(msg: List[Message]): List[Message] = {
        // meta.runtime.simulation.util.bench {
            mx = (internalMessages ++ msg).toList.groupBy(_.receiverId)

            sendMessages.clear()
            internalMessages.clear()

            val sentMessages = containedAgents.flatMap(a => {
                    a._2.run(
                        a._2.getProxyIds.toList.flatMap(
                            id => mx.getOrElse(id, List())
                        ))
                })

            sendMessages.appendAll(sentMessages)

            internalMessages = sendMessages.filter(x => (proxyIds.contains(x.receiverId)))

            sendMessages --= internalMessages
        // }
        sendMessages.toList
    }

    override def run() = org.coroutines.coroutine((() => while (true) 
    {
        // meta.runtime.simulation.util.bench {
            mx = (internalMessages ++ receivedMessages).toList.groupBy(_.receiverId)

            receivedMessages.clear()
            internalMessages.clear()

            containedAgents.foreach(a => {
                a._2.addReceiveMessages(
                    a._2.getProxyIds.toList.flatMap(
                        id => mx.getOrElse(id, List())
                    ))
            })

            containedAgentInstances.map(x =>x.resume)

            sendMessages.appendAll(containedAgentInstances.flatMap(a => a.value))

            internalMessages = sendMessages.filter(x => (proxyIds.contains(x.receiverId)))

            sendMessages --= internalMessages
        // }
        org.coroutines.yieldval(sendMessages.toList);
        sendMessages.clear()
    }))
}