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

  var proxyIds: List[AgentId] = List(id)

  var time: Int = 0
  
  var deleted: Boolean = false

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

  var connectedAgents: List[Actor] = List()

  // todo: add a logger to redirect warning to stderr
  def setConnectedAgents(agents: List[Actor]): Unit = {
    if (agents.size > agents.toSet.size){
      println(f"Duplicated agents found in ${id} connection!")
    }
    connectedAgents = agents
  }
  

  /**
    * Adds one message to the sendActions list, which will be collected and distributed at the end of the step
    *
    * @param message Action, which should be sent to a different Agent
    */

  final def addProxyIds(ids: List[AgentId]): Unit = {
    proxyIds = proxyIds ::: ids
  }

  final def sendMessage(message: Message): Unit = {
    if (message.receiverId == this.id) {
      addReceiveMessages(List(message))
    } else {
      sendMessages.append(message)
    }
  }

  final def receiveMessage(): Option[Message] = {
    if (receivedMessages.isDefinedAt(0)){
      Some(receivedMessages.remove(0))
    } else {
      None
    }
  }

  /**
    * Adds a list of messages to the agent
    *
    * @param messages Actions with receiver matching the agent from the previous step
    */
  def addReceiveMessages(messages: List[Message]): Actor = {
    for (m <- messages) {
      m match {
        case m: RequestMessage => this.receivedMessages.append(m)
        case m: ResponseMessage => if (time < m.send_time + m.latency) {
          this.receivedMessages.append(m)
        } else if (responseListeners.get(m.sessionId).isDefined) {
          val handler = responseListeners.remove(m.sessionId).get
          handler(m)
        } else {
          this.receivedMessages.append(m)
        }
      }
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
    * This function removes all receivedMessages of type RequestMessage from the receivedMessages list that should be processed. 
    * Remove the blocking attribute, which is not necessary since rpcs are non-blocking
    * agents can process messages that arrived earlier, but not later
    * and returns them to the method caller
    * @return a list of receivedMessages of type RequestMessage
    */
  def popRPCRequests: List[RequestMessage] = {
    val rM = this.receivedMessages
      .filter(m => (m.isInstanceOf[RequestMessage] && m.rpc && (time >=m.latency + m.send_time)))
      .map(_.asInstanceOf[RequestMessage])
    this.receivedMessages --= rM
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

  def SimClone(args: Set[String] = Set()): Actor = {
    this
  }

  // In-place reset the user-defined attributes of a Sim. Runtime attributes, such as id and connectedAgents, are unaffected.
  def SimReset(args: Set[String] = Set()): Unit = {}

  // Get the code position of the handleMessage and go to that location. Process the code related to handle message, reset the instruction pointer, and return the agent
  // def handleNonblockingMessages(): Actor = ??? 
  def gotoHandleMessages(new_ir: Int = -1): Actor = ??? 
  
  def handleNonblockingMessage(m: RequestMessage): Unit = ??? 

  def runAndEval[K](messages: List[Message], mapper: Actor=>K): ((List[Message], Int), K) = {
    (run(messages), mapper(this))
  }
}