package meta.runtime

import scala.collection.mutable.{ListBuffer, Map => MutMap}

import Actor.AgentId
import spire.std.SeqEq
/**
 * A container agent holds a collection of agents. 
 * The internal messages among the agents are non-blocking. 
 */ 
class Container extends Actor {
  protected var position: Int = 0

  // Use a list buffer to remove agents
  val containedAgents: MutMap[AgentId, Actor] = MutMap[AgentId, Actor]()
  
  protected var internalMessages: List[Message] = List()

  // private val chattyAgents: ChattyAgents = new ChattyAgents(50)

  def initAddAgents(sims: Seq[Actor]): Unit = {
		containedAgents ++= sims.map(x => (x.id, x)).toMap

    addProxyIds(sims.flatMap(x => {
      x._container = this
      x.getProxyIds
    }))
  }

  def addAgents(sims: Seq[Actor]): Unit = {
    val simsMessages = sims.flatMap(_.getSendMessages)
    sendMessages = simsMessages.toList ::: sendMessages
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
  protected var mx = receivedMessages.groupBy(_.receiverId)
  protected var messageBuffer: List[Message] = List()

  protected var unblockAgents: Set[Actor.AgentId] = Set()

  // Remove an agent from the contained agents and drop its id from proxyId
  // Return the agent
  // private def removeAgent(a: AgentId): Option[Actor] = {
  //   val idx = proxyIds.indexOf(a)
  //   if (idx == -1) {
  //     None
  //   } else {
  //     // Clear the ChattyAgent monitor after removing a silent agent
  //     // chattyAgents.clearMergeCandidates()
  //     proxyIds.remove(idx)
  //     Some(containedAgents.remove(idx))
  //   }
  // }

  protected val commands: List[() => Unit] = List(
    () => {
        mx = receivedMessages.groupBy(_.receiverId)
        // println(f"Received messages for container ${mx}")
        receivedMessages = List()
        unblockAgents = proxyIds.toSet
        cleanSendMessage

        sendMessages = containedAgents.flatMap(a => {
          a._2.cleanSendMessage
          .run(a._2.getProxyIds.flatMap(id => mx.getOrElse(id, List())))
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
  override def run(msg: List[Message]): List[Message] = {    
    // println(s"Agent ${id} contains agents: ${containedAgents}")
    val start: Long = System.currentTimeMillis()
    addReceiveMessages(msg)
    commands(position)()
    val end: Long = System.currentTimeMillis()
    // println(f"Container runs: ${end - start} ms")
    // println(s"Container ${id} sent messages: ${sendMessages}")
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