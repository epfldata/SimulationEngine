package meta.runtime

import spire.std.int

/**
 * A container agent holds a collection of agents. 
 * The internal messages among the agents are non-blocking. 
 */ 
class Container extends Actor {
  private var position: Int = 0 

  private var containedAgents: List[Actor] = List()
  private var internalMessages: List[Message] = List()

  // When add agents to a container, also update the proxyIds 
  // An RPC method that other containers can use to migrate their agents over 
  def addAgents(as: List[Actor]): Unit = {
    containedAgents = as ::: containedAgents 
    proxyIds = as.flatMap(x => x.proxyIds) ::: proxyIds
    println(s"Proxy ids of agent ${id} are ${proxyIds}")
  }

  // // Reply a request message corresponding to addAgents

  // If an agent communicates much more frequently with an external container, then offer it to that container; if an agent doesn't communicate much and the container's capacity is close to full, then we migrate it 
  // We need an index container which monitors the total agents in each container agent, and warns any imbalance, forwarding references of the container agents to each other to balance the traffic.

  // Group messages by receiver id 
  private var mx = receivedMessages.groupBy(_.receiverId)
  private var messageBuffer: List[Message] = List() 

  // proxyIds initialized after addAgents. Therefore init again inside run. An optimization (if large number of agents don't send internal messages), not required
  private var unblockAgents: Set[Actor.AgentId] = Set()

  private var internalReqAgents: Set[Actor.AgentId] = Set()

  private val commands: List[() => Unit] = List(
    () => {
        mx = receivedMessages.groupBy(_.receiverId)

        unblockAgents = proxyIds.toSet
        cleanSendMessage

        containedAgents = containedAgents.map(a => {
          a.cleanSendMessage
          .addReceiveMessages(a.proxyIds.flatMap(id => mx.getOrElse(id, List())))
          .run()
        })

        do {
          // get all the messages  
          messageBuffer = containedAgents.flatMap(_.getSendMessages)
          // remove the sent messages from the agents 
          containedAgents = containedAgents
            .map(_.cleanSendMessage)
          // filter out the internal messages 
          internalMessages = messageBuffer.filter(x => proxyIds.contains(x.receiverId))
          // Update the internal request agents
          internalReqAgents = internalMessages
            .filter(x => x.isInstanceOf[RequestMessage])
            .filter(x => x.asInstanceOf[RequestMessage].blocking)
            .map(x => x.senderId)
            .toSet.union(internalReqAgents)

          // append the external messages to the outgoing mailbox  
          sendMessages = messageBuffer.diff(internalMessages) ::: sendMessages 
          // update the unblockAgent indexes to selectively deliver the internal blocking messages
          unblockAgents = internalMessages.flatMap(x => List(x.receiverId)).toSet
          // update the messages to deliver for the selected unblocking agents
          mx = internalMessages.groupBy(_.receiverId)

          containedAgents = containedAgents.map(a => {
            if (unblockAgents.contains(a.id)) {
              val currentPtr: Int = a.gotoHandleMessage
              val b: Actor = a.addReceiveMessages(mx.getOrElse(a.id, List())).run()
              b.setInstructionPointer(currentPtr)
              // If the receiver agent was waiting for a blocking request, unblock it
              if (internalReqAgents.contains(a.id)){  
                internalReqAgents -= a.id 
                b.run()
              } else {
                b 
              }
            }
            else {
              a
            }
          })

        } while (internalMessages.nonEmpty)

    }
  )

  // Assume each wait in main follows a handleMessage 
  override def run(): Actor = {    
    commands(position)()
    this 
  }

  // Implement the reflection API  
  override def gotoHandleMessage: Int = 0 

  override def getInstructionPointer: Int = 0

  override def setInstructionPointer(new_ir: Int): Int = {
    if (new_ir!=0) {
        throw new Exception(s"Invalid address pointer ${new_ir} for agent ${id}")
    } else {
        0
    }
  }
}