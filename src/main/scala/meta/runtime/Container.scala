package meta.runtime
import scala.collection.mutable.ListBuffer

import Actor.AgentId
/**
 * A container agent holds a collection of agents. 
 * The internal messages among the agents are non-blocking. 
 */
class Container extends Actor {
  private var position: Int = 0

  // Use a list buffer to remove agents
  private var containedAgents: ListBuffer[Actor] = new ListBuffer()
  private var internalMessages: List[Message] = List()

  private val chattyAgents: ChattyAgents = new ChattyAgents(50)

  def initAddAgents(sims: ListBuffer[Actor]): Unit = {
    containedAgents ++= sims
    addProxyIds(sims.flatMap(x => x.getProxyIds).toList)

    sims.foreach(s => {
      s._env = s._env.union(proxyIds.toSet)
    })
  }
  
  // When add agents to a container, also update the proxyIds, and transfer the messages 
  def addAgents(sims: List[Actor]): Unit = {
    val simsMessages = sims.flatMap(_.getSendMessages)
    sendMessages = simsMessages ::: sendMessages
    containedAgents ++= sims.map(_.cleanSendMessage)
    addProxyIds(sims.flatMap(x => x.getProxyIds))

    sims.foreach(s => {
      s._env = s._env.union(proxyIds.toSet)
    })

    // println(s"Proxy ids of agent ${id} are ${proxyIds}")
  }

  // Root can propose to migrate an agent based on profiling data. 
  // None if the agent is hot or warmup not completed, and we prefer to keep it internal
  // Otherwise return the agent to the root, and remove it. Upon removal, we also reset the chattyAgent monitor to clear the proposed merged agents
  def migrateAgent(a: AgentId): Option[Actor] = {
    var silentAgents: List[AgentId] = List()
    // if no merge candidates, then stil in the warm-up phase
    val hotAgents = chattyAgents.getPairedMergeCandidates.flatten
    if (hotAgents.nonEmpty) {
      silentAgents = getProxyIds.filterNot(_ == id).diff(hotAgents)
    }
    // println(s"Silent agents of the turn: ${silentAgents}")

    silentAgents.contains(a) match {
      case true => removeAgent(a)
      case false => None
    }
  }

  def getAgents: List[Actor] = containedAgents.toList

  // If an agent communicates much more frequently with an external container, then offer it to that container; if an agent doesn't communicate much and the container's capacity is close to full, then we migrate it 
  // We need an index container which monitors the total agents in each container agent, and warns any imbalance, forwarding references of the container agents to each other to balance the traffic.

  // Group messages by receiver id
  private var mx = receivedMessages.groupBy(_.receiverId)
  private var messageBuffer: List[Message] = List()

  private var unblockAgents: Set[Actor.AgentId] = Set()

  // Remove an agent from the contained agents and drop its id from proxyId
  // Return the agent
  private def removeAgent(a: AgentId): Option[Actor] = {
    val idx = proxyIds.indexOf(a)
    if (idx == -1) {
      None
    } else {
      // Clear the ChattyAgent monitor after removing a silent agent
      // chattyAgents.clearMergeCandidates()
      proxyIds.remove(idx)
      Some(containedAgents.remove(idx))
    }
  }

  private val commands: List[() => Unit] = List(
    () => {
        mx = receivedMessages.groupBy(_.receiverId)
        receivedMessages = List()
        unblockAgents = proxyIds.toSet
        cleanSendMessage

        sendMessages = containedAgents.flatMap(a => {
          a.cleanSendMessage
          .run(a.getProxyIds.flatMap(id => mx.getOrElse(id, List())))
        }).toList

        // do {
        //   // get all the messages
        //   messageBuffer = (containedAgents.flatMap(_.getSendMessages)).toList
        //   // remove the sent messages from the agents 
        //   containedAgents = containedAgents
        //     .map(_.cleanSendMessage)
        //   // println(s"Looping inside container agent! Message buffer: ${messageBuffer}")
        //   // filter out the internal messages 
        //   internalMessages = messageBuffer.filter(x => proxyIds.contains(x.receiverId))
        //   // profile internal messages
        //   chattyAgents.recordMessage(internalMessages)
        //   // append the external messages to the outgoing mailbox  
        //   sendMessages = messageBuffer.diff(internalMessages) ::: sendMessages
        //   // update the unblockAgent indexes to selectively deliver the internal blocking messages
        //   unblockAgents = internalMessages.flatMap(x => List(x.receiverId)).toSet
        //   // update the messages to deliver for the selected unblocking agents
        //   mx = internalMessages.groupBy(_.receiverId)

        //   containedAgents = containedAgents.map(a => {
        //     if (unblockAgents.contains(a.id)) {
        //       val msgs: List[Message] = mx.getOrElse(a.id, List())
        //       val b = a.addReceiveMessages(msgs)
        //       // If the receiver agent was waiting for a blocking request, unblock it
        //       if (msgs.exists(x => (x.isInstanceOf[ResponseMessage] && x.blocking))) {  
        //         meta.Util.debug(s"Unblock agent ${a.id} to run")
        //         b.run()
        //       } else {
        //         meta.Util.debug(s"Unblock agent ${a.id} to handle messages")
        //         b
        //         // b.handleMessages()
        //       }
        //     }
        //     else {
        //       a
        //     }
        //   })

        // } while (internalMessages.nonEmpty)
    }
  )

  // Assume each wait in main follows a handleMessage
  override def run(msgs: List[Message]): List[Message] = {
    addReceiveMessages(msgs)
    commands(position)()
    sendMessages
  }

  // Implement the reflection API
  // override def gotoHandleMessage(new_ir: Int = 0) = this

  override def getInstructionPointer: Int = 0

  override def setInstructionPointer(new_ir: Int) = {
    if (new_ir!=0) {
        throw new Exception(s"Invalid address pointer ${new_ir} for agent ${id}")
    }
    this
  }
}