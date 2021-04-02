package meta.runtime

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
    // println(s"Proxy ids of agent ${id} are ${proxyIds}")
  }

  def getAgents: List[Actor] = containedAgents

  // If an agent communicates much more frequently with an external container, then offer it to that container; if an agent doesn't communicate much and the container's capacity is close to full, then we migrate it 
  // We need an index container which monitors the total agents in each container agent, and warns any imbalance, forwarding references of the container agents to each other to balance the traffic.

  // Group messages by receiver id 
  private var mx = receivedMessages.groupBy(_.receiverId)
  private var messageBuffer: List[Message] = List() 

  private var unblockAgents: Set[Actor.AgentId] = Set()

  private val commands: List[() => Unit] = List(
    () => {
        mx = receivedMessages.groupBy(_.receiverId)
        receivedMessages = List()
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
          // println(s"Looping inside container agent! Message buffer: ${messageBuffer}")
          // filter out the internal messages 
          internalMessages = messageBuffer.filter(x => proxyIds.contains(x.receiverId))
          // append the external messages to the outgoing mailbox  
          sendMessages = messageBuffer.diff(internalMessages) ::: sendMessages
          // update the unblockAgent indexes to selectively deliver the internal blocking messages
          unblockAgents = internalMessages.flatMap(x => List(x.receiverId)).toSet
          // update the messages to deliver for the selected unblocking agents
          mx = internalMessages.groupBy(_.receiverId)

          containedAgents = containedAgents.map(a => {
            if (unblockAgents.contains(a.id)) {
              val msgs: List[Message] = mx.getOrElse(a.id, List())
              
              // If the receiver agent was waiting for a blocking request, unblock it
              if (msgs.exists(x => (x.isInstanceOf[ResponseMessage] && x.blocking))) {  
                a.addReceiveMessages(msgs).run()
              } else {
                val currentPtr: Int = a.getInstructionPointer
                a.addReceiveMessages(msgs)
                  .gotoHandleMessage.run()
                  .setInstructionPointer(currentPtr)
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
    // meta.Util.debug(s"Agent ${id} received messages: ${receivedMessages}")
    commands(position)()
    // meta.Util.debug(s"Agent ${id} sent messages: ${sendMessages}")
    this
  }

  override def getSendMessages: List[Message] = {
    containedAgents.flatMap(_.getSendMessages) ::: sendMessages
  }

  // Implement the reflection API  
  override def gotoHandleMessage = this 

  override def getInstructionPointer: Int = 0

  override def setInstructionPointer(new_ir: Int) = {
    if (new_ir!=0) {
        throw new Exception(s"Invalid address pointer ${new_ir} for agent ${id}")
    }
    this 
  }
}