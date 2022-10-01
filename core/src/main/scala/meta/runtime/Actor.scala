package meta.runtime

import java.util.UUID
import scala.collection.mutable.{Buffer, ListBuffer, Map => MutMap}
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.ArrayList

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

  var proposeInterval: Int = 1
  /**
    * Contains the received messages from the previous step
    */
  val receivedMessages: ConcurrentLinkedQueue[Message] = new ConcurrentLinkedQueue[Message]()

  /**
    * Contains the messages, which should be sent to other actors in the next step
    */
  val sendMessages: MutMap[Long, ListBuffer[Message]] = MutMap[Long, ListBuffer[Message]]()

  /**
    * A map of listeners, which is required to register a listener for a response of a request message
    */
  val responseListeners
    : MutMap[String, Message => Unit] = MutMap()

  var reachableAgents: Set[AgentId] = Set()

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

  final def sendMessage(receiver: Long, message: Message): Unit = {
    sendMessages.getOrElseUpdate(receiver, new ListBuffer[Message]()).append(message)
  }

  final def receiveMessage(): Option[Message] = {
    val ans = receivedMessages.poll()
    if (ans == null){
      return None
    } else {
      Some(ans)
    }
  }

  val receivedRPCRequests: ListBuffer[RequestMessage] = ListBuffer()

  def messageListener(): Unit = {
    val processedMessages = new ArrayList[Message]()
    receivedMessages.forEach(msg => {
      msg match {
        case m: ResponseMessage => 
          if (responseListeners.get(m.sessionId).isDefined){
            responseListeners.remove(m.sessionId).get(m)
            processedMessages.add(m)
          }
        case m: RequestMessage =>
          // send_time is 0-based
          if (m.latency + m.send_time <= time){
            receivedRPCRequests.append(m)
            processedMessages.add(m) 
          } 
        case _ =>
      }
    }) 
    receivedMessages.removeAll(processedMessages)
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
    * Stub, gets overriden by generated code
    * messages: a list of input messages
    * return: (a list of output messages, passed rounds)
    */
  def run(): Int = {
    ???
  }

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