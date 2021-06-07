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

  // private val chattyAgents: ChattyAgents = new ChattyAgents(50)

  def initAddAgents(sims: Seq[Actor]): Unit = {
	containedAgents ++= sims.map(x => (x.id, x)).toMap

    addProxyIds(sims.flatMap(x => {
      x._container = this
      x.getProxyIds
    }))

    containedAgentInstances.appendAll(sims.map(x => call (x.run()())))
  }

  def addAgents(sims: Seq[Actor]): Unit = {
    val simsMessages = sims.flatMap(_.getSendMessages)
    sendMessages.appendAll(simsMessages)
    containedAgents ++= sims.map(_.cleanSendMessage).map(x => (x.id, x)).toMap
    addProxyIds(sims.flatMap(x => x.getProxyIds))
    sims.foreach(s => {
      s._container = this
    })
  }

  // Root can propose to migrate an agent based on profiling data. 
  // None if the agent is hot or warmup not completed, and we prefer to keep it internal
  // Otherwise return the agent to the root, and remove it. Upon removal, we also reset the chattyAgent monitor to clear the proposed merged agents
  // def migrateAgent(a: AgentId): Option[Actor] = {
  //   var silentAgents: List[AgentId] = List()
  //   // if no merge candidates, then stil in the warm-up phase
  //   val hotAgents = chattyAgents.getPairedMergeCandidates.flatten
  //   if (hotAgents.nonEmpty) {
  //     silentAgents = getProxyIds.filterNot(_ == id).diff(hotAgents)
  //   }
  //   // println(s"Silent agents of the turn: ${silentAgents}")

  //   silentAgents.contains(a) match {
  //     case true => removeAgent(a)
  //     case false => None
  //   }
  // }

  // If an agent communicates much more frequently with an external container, then offer it to that container; if an agent doesn't communicate much and the container's capacity is close to full, then we migrate it 
  // We need an index container which monitors the total agents in each container agent, and warns any imbalance, forwarding references of the container agents to each other to balance the traffic.

  // Group messages by receiver id
  protected var mx = receivedMessages.toList.groupBy(_.receiverId)
  protected var messageBuffer: List[Message] = List()

  protected var unblockAgents: Set[Actor.AgentId] = Set()

  override def run() = org.coroutines.coroutine((() => while (true) 
    {
        meta.runtime.simulation.util.bench {
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
        }
        org.coroutines.yieldval(sendMessages.toList);
        sendMessages.clear()
    }))

  // Implement the reflection API  
  // override def gotoHandleMessage(new_ir: Int = 0) = this 

//   override def getInstructionPointer: Int = 0

//   override def setInstructionPointer(new_ir: Int) = {
//     if (new_ir!=0) {
//         throw new Exception(s"Invalid address pointer ${new_ir} for agent ${id}")
//     }
//     this
//   }
}