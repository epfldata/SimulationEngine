package meta.runtime

import java.util.UUID
import scala.collection.mutable.{ListBuffer, Map => MutMap}

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
  def getNextAgentId: AgentId = this.synchronized {
    lastAgentId = lastAgentId + 1
    lastAgentId
  }

  def reset: Unit = this.synchronized {
    lastAgentId = 0
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

  var proxyIds: ListBuffer[AgentId] = new ListBuffer[AgentId]()

  var deleted: Boolean = false

  // a variable denoting whether we can relax the consistency constraint and allow concurrent copies 
  var relaxConsistency: Boolean = false 

  /**
    * Contains the received messages from the previous step
    */
//  protected var receivedMessages: List[Message] = List()
  val receivedMessages: ListBuffer[Message] = new ListBuffer[Message]()

  /**
    * Contains the messages, which should be sent to other actors in the next step
    */
  val sendMessages: ListBuffer[Message] = new ListBuffer()

  /**
    * A map of listeners, which is required to register a listener for a response of a request message
    */
  val responseListeners
    : MutMap[String, Message => Unit] = MutMap()

  var _container: Container = null

  /**
    * Adds one message to the sendActions list, which will be collected and distributed at the end of the step
    *
    * @param message Action, which should be sent to a different Agent
    */
  proxyIds += id

  final def getProxyIds: List[AgentId] = proxyIds.toList 

  final def addProxyIds(ids: Seq[AgentId]): Unit = {
    proxyIds ++= ids
  }

  final def sendMessage(message: Message): Unit = {
    if (message.receiverId == this.id) {
      addReceiveMessages(List(message))
    } else {
      sendMessages.append(message)
    }
  }

  /**
    * Adds a list of messages to the agent
    *
    * @param messages Actions with receiver matching the agent from the previous step
    */
  def addReceiveMessages(messages: List[Message]): Actor = {
    this.receivedMessages.appendAll( messages.filter(
      x =>
        x.isInstanceOf[RequestMessage] || responseListeners
          .get(x.sessionId)
          .isEmpty))
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
    * This returns all messages, which are sent via sendMessage
    * @return the actor itself
    */
  def getSendMessages: ListBuffer[Message] = {
    sendMessages
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
    * This function removes all receivedMessages of type RequestMessage from the receivedMessages list. Process nonblocking messages first to ensure the causal relationship between the nonblocking messages and blocking messages (if a blocking message follows some non-blocking messages and arbitrary actions in between)
    * and returns them to the method caller
    * @return a list of receivedMessages of type RequestMessage
    */
  def popRequestMessages: List[RequestMessage] = {
    val rM = this.receivedMessages
      .filter(_.isInstanceOf[RequestMessage])
      .map(_.asInstanceOf[RequestMessage])
      .sortBy(x => x.blocking)  // non-blocking messages are processed first
    this.receivedMessages --=
      this.receivedMessages.filter(_.isInstanceOf[RequestMessage])
    rM.toList
  }

  /**
    * This function removes all receivedMessages of type ResponseMessage from the receivedMessages list
    * and returns them to the method caller
    * @return a list of receivedMessages of type ResponseMessage
    */
  def popResponseMessages: List[ResponseMessage] = {
    val rM = this.receivedMessages
      .filter(_.isInstanceOf[ResponseMessage])
      .map(_.asInstanceOf[ResponseMessage])
    this.receivedMessages --=
      this.receivedMessages.filter(_.isInstanceOf[ResponseMessage])
    rM.toList
  }

  /**
    * Stub, gets overriden by generated code
    * messages: a list of input messages
    * return: (a list of output messages, passed rounds)
    */
  def run(messages: List[Message]): (List[Message], Int) = {
    ???
  }

  // Get the code position of the handleMessage and go to that location. Process the code related to handle message, reset the instruction pointer, and return the agent
  // def handleNonblockingMessages(): Actor = ??? 
  def gotoHandleMessages(new_ir: Int = -1): Actor = ??? 
  
  def handleNonblockingMessage(m: RequestMessage): Unit = ??? 
}
