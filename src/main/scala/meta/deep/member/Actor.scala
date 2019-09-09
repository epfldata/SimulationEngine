package meta.deep.member

import java.util.UUID

import meta.deep.member.Actor.AgentId

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

abstract class Message extends Serializable {
  val senderId: Actor.AgentId
  val receiverId: Actor.AgentId
  var sessionId: String = UUID.randomUUID().toString

  override def toString: String = {
    "Message: " + senderId + " -> " + receiverId + "(" + sessionId + ")"
  }
}

// General message
case class RequestMessage(override val senderId: Actor.AgentId,
                          override val receiverId: Actor.AgentId,
                          methodId: Int,
                          argss: List[List[Any]])
    extends Message {
  def reply(owner: Actor, returnValue: Any): Unit = {
    val msg = ResponseMessage(receiverId, senderId, returnValue)
    msg.sessionId = this.sessionId
    owner.sendMessage(msg)
  }
}

case class ResponseMessage(override val senderId: Actor.AgentId,
                           override val receiverId: Actor.AgentId,
                           arg: Any)
    extends Message

class Actor {
  var id: AgentId = Actor.getNextAgentId
  var timer: Int = 0
  var current_pos: Int = 0

  /**
    * Contains the received messages from the previous step
    */
  protected var receivedMessages: List[Message] = List()
  protected var sendMessages: List[Message] = List()
  protected var responseListeners
    : collection.mutable.Map[String, Message => Unit] = collection.mutable.Map()

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
  final def addReceiveMessages(messages: List[Message]): Actor = {
    this.receivedMessages = this.receivedMessages ::: messages.filter(
      x =>
        x.isInstanceOf[RequestMessage] || responseListeners
          .get(x.sessionId)
          .isEmpty)
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
    this
  }

  final def getSendMessages: List[Message] = {
    sendMessages
  }

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

  final def popRequestMessages: List[RequestMessage] = {
    val rM = this.receivedMessages
      .filter(_.isInstanceOf[RequestMessage])
      .map(_.asInstanceOf[RequestMessage])
    this.receivedMessages =
      this.receivedMessages.filterNot(_.isInstanceOf[RequestMessage])
    rM
  }

  final def popResponseMessages: List[ResponseMessage] = {
    val rM = this.receivedMessages
      .filter(_.isInstanceOf[ResponseMessage])
      .map(_.asInstanceOf[ResponseMessage])
    this.receivedMessages =
      this.receivedMessages.filterNot(_.isInstanceOf[ResponseMessage])
    rM
  }

  def run_until(until: Int): Actor = {
    while (timer <= until) {
      println(this.getClass.getSimpleName, timer, until, current_pos)
      val (a, b) = stepFunction
      current_pos = a
      timer = b
    }
    this
  }

  def stepFunction: (Int, Int) = (current_pos, timer + 1)
}
