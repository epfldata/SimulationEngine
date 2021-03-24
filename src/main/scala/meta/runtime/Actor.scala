package meta.runtime

import java.util.UUID
import org.slf4j.LoggerFactory

import scala.collection.mutable.{ListBuffer, Map}

/**
  * This object handles the unique id generation of an actor
  * as long as all ids are generated on a single instance
  */
object Actor {
  type AgentId = Long
  var lastAgentId: AgentId = 0
  
  /**
    * Generates a new id for an agent and returns it
    *
    * @return id for an agent
    */
  def getNextAgentId: AgentId = {
    lastAgentId = lastAgentId + 1
    lastAgentId
  }
}

/**
 * A container agent holds a collection of agents 
 */ 
class Container extends Actor {
  var containedAgents: List[Actor] = List()

  var internalMessages: List[Message] = List()

  // When add agents to a container, also update the proxyIds 
  def addAgents(as: List[Actor]): Unit = {
    containedAgents = as ::: containedAgents 
    proxyIds = as.flatMap(x => x.proxyIds) ::: proxyIds
  }

  var mx = receivedMessages.groupBy(_.receiverId)
  var messageBuffer: List[Message] = List() 

  // proxyIds initialized after addAgents. Therefore init again inside run
  var unblockAgents: Set[Actor.AgentId] = Set()

  // Assume each wait in main follows a handleMessage 
  override def run(): Actor = {
    unblockAgents = proxyIds.toSet 
    cleanSendMessage
    do {
      containedAgents = containedAgents.map(a => {
        if (unblockAgents.contains(a.id)) {
          a.cleanSendMessage 
         .addReceiveMessages(mx.getOrElse(a.id, List()))
         .run()
        } else {
          a 
        }
      })

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
      // println(s"Unblock agents: ${unblockAgents} + Messages: ${internalMessages}")
      mx = internalMessages.groupBy(_.receiverId)
    } while (internalMessages.nonEmpty)

    this 
  }
}

/**
  * This class represents the main class of the generated classes
  * It contains the logic for message handling and defines the
  * functions for a step-wise simulation
  */
class Actor extends Serializable {
  import Actor.AgentId
  var id: AgentId = Actor.getNextAgentId
  var proxyIds: List[Actor.AgentId] = List(id)
  var deleted: Boolean = false
  
//  val logger = LoggerFactory.getLogger(this.getClass.getName())

  /**
    * Contains the received messages from the previous step
    */
//  protected var receivedMessages: List[Message] = List()
  var receivedMessages: List[Message] = List()

  /**
    * Contains the messages, which should be sent to other actors in the next step
    */
  var sendMessages: List[Message] = List()

  /**
    * A map of listeners, which is required to register a listener for a response of a request message
    */
  var responseListeners
    : Map[String, Message => Unit] = Map()

  var interrupts: Map[Double, List[Message]] = Map()

  /**
    * Adds one message to the sendActions list, which will be collected and distributed at the end of the step
    *
    * @param message Action, which should be sent to a different Agent
    */
  final def sendMessage(message: Message): Unit = {
    if (message.receiverId == this.id) {
      addReceiveMessages(List(message))
    } else {
      sendMessages = message :: sendMessages
    }
  }

  /**
    * Adds a list of messages to the agent
    *
    * @param messages Actions with receiver matching the agent from the previous step
    */
  def addReceiveMessages(messages: List[Message]): Actor = {
    // println(s"Add receive messages for ${id}: ${messages}")
    this.receivedMessages = this.receivedMessages ::: messages.filter(
      x =>
        x.isInstanceOf[RequestMessage] || responseListeners
          .get(x.sessionId)
          .isEmpty)
    // Only invoke handler callback If the agent is not a container agent
    if (proxyIds.size == 1) {
      messages
      .filter(
        x =>
          responseListeners.get(x.sessionId).isDefined && x
            .isInstanceOf[ResponseMessage])
      .foreach(x => {
        val handler = responseListeners(x.sessionId)
        responseListeners.remove(x.sessionId)
        handler(x)
      })
    }
    this 
  }

  /**
    * Add registered interrupts to receivedMessages if time is up
    * @param time
    * @return
    */
  final def addInterrupts(time: Double): Actor = {
    val registeredInterrupts: Option[List[Message]] = interrupts.remove(time)
    if (registeredInterrupts.isDefined){
      receivedMessages = receivedMessages ::: registeredInterrupts.get
    }
    this
  }

  /**
    * This returns all messages, which are sent via sendMessage
    * @return the actor itself
    */
  final def getSendMessages: List[Message] = {
    sendMessages
  }

  /**
    * This resets sendMessages, so that getSendMessages is empty again
    * @return the actor itself
    */
  final def cleanSendMessage: Actor = {
    sendMessages = List()
    this
  }

  /**
    * Sets a message response handler for a specific session id
    *
    * @param sessionId session of message you want to listen for a response
    * @param handler   function, which handles the message
    */
  final def setMessageResponseHandler(sessionId: String,
                                      handler: Message => Unit): Unit = {
    responseListeners += (sessionId -> handler)
  }

  /**
    * This function removes all receivedMessages of type RequestMessage from the receivedMessages list
    * and returns them to the method caller
    * @return a list of receivedMessages of type RequestMessage
    */
  final def popRequestMessages: List[RequestMessage] = {
    val rM = this.receivedMessages
      .filter(_.isInstanceOf[RequestMessage])
      .map(_.asInstanceOf[RequestMessage])
    this.receivedMessages =
      this.receivedMessages.filterNot(_.isInstanceOf[RequestMessage])
    rM
  }

  /**
    * This function removes all receivedMessages of type ResponseMessage from the receivedMessages list
    * and returns them to the method caller
    * @return a list of receivedMessages of type ResponseMessage
    */
  final def popResponseMessages: List[ResponseMessage] = {
    val rM = this.receivedMessages
      .filter(_.isInstanceOf[ResponseMessage])
      .map(_.asInstanceOf[ResponseMessage])
    this.receivedMessages =
      this.receivedMessages.filterNot(_.isInstanceOf[ResponseMessage])
    rM
  }

  /**
    * Stub, gets overriden by generated code 
    */
  def run(): Actor = {
    this
  }
}
