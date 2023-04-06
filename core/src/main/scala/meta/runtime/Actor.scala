package meta.runtime

import scala.collection.mutable.{Buffer, ListBuffer, Map => MutMap}

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

  var proxyIds: List[AgentId] = List(id)

  var time: Int = 0
  
  var deleted: Boolean = false

  var proposeInterval: Int = 1
  /**
    * Contains the received messages from the previous step
    */
  // val receivedMessages: ConcurrentLinkedQueue[Message] = new ConcurrentLinkedQueue[Message]()
  var receivedMessages: List[Message] = List[Message]()

  /**
    * Contains the messages, which should be sent to other actors in the next step
    */
    // Replace mutable with immutable ds, for Spark serialization
  var sendMessages: MutMap[Long, Buffer[Message]] = MutMap[Long, Buffer[Message]]()
  // var sendMessages: Map[Long, List[Message]] = Map[Long, List[Message]]()

  /**
    * A map of listeners, which is required to register a listener for a response of a request message
    */
  var responseListeners: Map[String, Message => Unit] = Map[String, Message => Unit]()

  var reachableAgents: Set[AgentId] = Set()

  var connectedAgents: Iterable[Actor] = List()

  var connectedAgentIds: Iterable[AgentId] = List()  

  /**
    * Adds one message to the sendActions list, which will be collected and distributed at the end of the step
    *
    * @param message Action, which should be sent to a different Agent
    */

  final def addProxyIds(ids: List[AgentId]): Unit = {
    proxyIds = proxyIds ::: ids
  }

  final def sendMessage(receiver: Long, message: Message): Unit = {
    // sendMessages = sendMessages + (receiver -> (message :: sendMessages.getOrElse(receiver, List[Message]()))) 
    sendMessages.getOrElseUpdate(receiver, new ListBuffer[Message]()).append(message)
  }

  final def receiveMessage(): Option[Message] = {
    // val ans = receivedMessages.poll()
    val ans = receivedMessages.headOption
    if (ans == None){
      return None
    } else {
      receivedMessages = receivedMessages.tail
      ans
    }
  }

  var scheduledRPCRequests: Map[Int, List[RequestMessage]] = Map[Int, List[RequestMessage]]()

  def messageListener(): Unit = {
    val totalMessages = receivedMessages.size

    Range(0, totalMessages).foreach(i => {
      val msg = receivedMessages.head
      msg match {
        case m: ResponseMessage => 
          if (responseListeners.get(m.sessionId).isDefined){
            responseListeners.get(m.sessionId).get(m)
            responseListeners = responseListeners - m.sessionId 
          }
          receivedMessages = receivedMessages.tail
        case m: RequestMessage =>
          // send_time is 0-based
          val procTime = m.send_time + m.latency
          val existing = scheduledRPCRequests.get(procTime)
          if (existing.isDefined) {
            scheduledRPCRequests = scheduledRPCRequests + (procTime -> (m :: existing.get))
          } else {
            scheduledRPCRequests = scheduledRPCRequests + (procTime -> List(m))
          }
          receivedMessages = receivedMessages.tail
        case m: Message =>
          // receivedMessages.add(m)
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
    * Stub, gets overriden by generated code
    * messages: a list of input messages
    * return: (a list of output messages, passed rounds)
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
  
  def runAndEval[K](mapper: Actor=>K): K = {
    run() 
    mapper(this)
  }
}