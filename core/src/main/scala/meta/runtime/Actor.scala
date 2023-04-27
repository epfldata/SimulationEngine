package meta.runtime

import scala.collection.mutable.{Buffer, Map => MutMap}

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
  def getNextAgentId(): AgentId = this.synchronized {
    lastAgentId = lastAgentId + 1
    lastAgentId
  }

  def reset(): Unit = this.synchronized {
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

  var time: Long = 0
  
  var deleted: Boolean = false

  var proposeInterval: Int = 1
  /**
    * Contains the received messages from the previous step
    */
  // val receivedMessages: ConcurrentLinkedQueue[Message] = new ConcurrentLinkedQueue[Message]()
  var receivedMessages: Buffer[Message] = Buffer[Message]()

  var receivedSerializedMessages: Buffer[Array[Byte]] = Buffer[Array[Byte]]()

  /**
    * Contains the messages, which should be sent to other actors in the next step
    */
  val sendMessages: MutMap[Long, Buffer[Message]] = MutMap[Long, Buffer[Message]]()
  
  val sendSerializedMessages: MutMap[Long, Buffer[Array[Byte]]] = MutMap[Long, Buffer[Array[Byte]]]()


  /**
    * A map of listeners, which is required to register a listener for a response of a request message
    */
  var responseListeners: Map[String, Message => Unit] = Map[String, Message => Unit]()

  var reachableAgents: Set[AgentId] = Set()

  var connectedAgents: Iterable[Actor] = List()

  var connectedAgentIds: Iterable[AgentId] = List()  

  final def sendMessage(receiver: Long, message: Message): Unit = {
    sendMessages.getOrElseUpdate(receiver, Buffer[Message]()) += message
  }

  final def sendSerializedMessage(receiver: Long, message: Array[Byte]): Unit = {
    sendSerializedMessages.getOrElseUpdate(receiver, Buffer[Array[Byte]]()) += message
  }

  final def receiveMessage(): Option[Message] = {
    val ans = receivedMessages.headOption
    if (ans.isDefined) {
      Some(receivedMessages.remove(0))
    } else {
      None
    }
  }

  final def receiveSerializedMessage(): Option[Array[Byte]] = {
    val ans = receivedSerializedMessages.headOption
    if (ans.isDefined) {
      Some(receivedSerializedMessages.remove(0))
    } else {
      None
    }
  }

  val scheduledRPCRequests: MutMap[Long, Buffer[RequestMessage]] = MutMap[Long, Buffer[RequestMessage]]()

  // Only applicable for Message type
  def messageListener(): Unit = {
    receivedMessages = receivedMessages.filter(msg => {
      msg match {
        case m: ResponseMessage => 
          if (responseListeners.get(m.sessionId).isDefined){
            responseListeners.get(m.sessionId).get(m)
            responseListeners = responseListeners - m.sessionId 
          }
          false
        case m: RequestMessage =>
          // send_time is 0-based
          val procTime = m.send_time + m.latency
          scheduledRPCRequests.getOrElseUpdate(procTime, Buffer[RequestMessage]()) += m
          false
        case m: Message =>
          true
      }
    })
  }

  /**
    * Sets a message response handler for a specific session id
    *
    * @param sessionId session of message you want to listen for a response
    * @param handler   function, which handles the message
    */
  final def setMessageResponseHandler(sessionId: String,
                                      handler: Message => Unit): Unit = {
    responseListeners = responseListeners + (sessionId -> handler)
  }

  /**
    * The API for interacting with the runtime, called at the beginning of a round
    * return the proposed interval
    */
  def run(): Int = {
    ???
  }

  /**
    * Since agents are mutable, clone creates a copy of the agent with current states, 
    * except for state variables defined in args
    *
    * @param args a list of variable names which have initial values in cloned agent
    * @return
    */
  def SimClone(args: Set[String] = Set()): Actor = {
    this
  }

  // In-place reset the user-defined attributes of a Sim. Runtime attributes, such as id and connectedAgents, are unaffected.
  def SimReset(args: Set[String] = Set()): Unit = {}
  
  def handleRPC(): Unit = {}
}