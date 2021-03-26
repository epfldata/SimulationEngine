package meta.runtime

/**
 * A container agent holds a collection of agents. 
 * The internal messages among the agents are non-blocking. 
 */ 
class Container extends Actor {
  var position: Int = 0 

  var containedAgents: List[Actor] = List()
  var internalMessages: List[Message] = List()

  // When add agents to a container, also update the proxyIds 
  def addAgents(as: List[Actor]): Unit = {
    containedAgents = as ::: containedAgents 
    proxyIds = as.flatMap(x => x.proxyIds) ::: proxyIds
  }

  // Group messages by receiver id 
  var mx = receivedMessages.groupBy(_.receiverId)
  var messageBuffer: List[Message] = List() 

  // proxyIds initialized after addAgents. Therefore init again inside run. An optimization (if large number of agents don't send internal messages), not required
  var unblockAgents: Set[Actor.AgentId] = Set()

  val commands: List[() => Unit] = List(
    () => {
        unblockAgents = proxyIds.toSet 
        cleanSendMessage

        containedAgents = containedAgents.map(a => {
        a.cleanSendMessage 
        .addReceiveMessages(mx.getOrElse(a.id, List()))
        .run()
        })

        do {
        // get all the messages  
        messageBuffer = containedAgents.flatMap(_.getSendMessages)
        // remove the sent messages from the agents 
        containedAgents = containedAgents.map(_.cleanSendMessage)
        // filter out the internal messages 
        internalMessages = messageBuffer.filter(x => proxyIds.contains(x.receiverId))
        // append the external messages to the outgoing mailbox  
        sendMessages = messageBuffer.diff(internalMessages) ::: sendMessages 
        // update the unblockAgent indexes to selectively deliver the internal blocking messages
        unblockAgents = internalMessages.flatMap(x => List(x.receiverId)).toSet  
        // update the messages to deliver for the selected unblocking agents
        mx = internalMessages.groupBy(_.receiverId)
        
        containedAgents = containedAgents.map(a => if (unblockAgents.contains(a.id)) {
            val currentPtr: Int = a.gotoHandleMessage
            val b: Actor = a.addReceiveMessages(mx.getOrElse(a.id, List())).run()
            b.setInstructionPointer(currentPtr)
            b 
        } else a)

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