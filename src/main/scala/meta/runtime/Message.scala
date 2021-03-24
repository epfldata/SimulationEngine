package meta.runtime

import java.util.UUID


/**
  * This class is the supertype of the messages
  */
abstract class Message extends Serializable {

  /**
    * The sender of the message
    */
  val senderId: Actor.AgentId

  /**
    * The receiver of the message
    */
  val receiverId: Actor.AgentId

  /**
    * A unique id for the message-communication (request/response)
    */
  var sessionId: String = UUID.randomUUID().toString

  override def toString: String = {
    "Message: " + senderId + " -> " + receiverId + "(" + sessionId + ")"
  }
}


/**
  * This represents a message, which is used for sending something to another actor
  * @param senderId the id of the sender
  * @param receiverId the id of the receiver
  * @param methodId the id of the method which should be called
  * @param argss the arguments of the method
  */
case class RequestMessage(override val senderId: Actor.AgentId,
                          override val receiverId: Actor.AgentId,
                          methodId: Int,
                          argss: List[List[Any]])
    extends Message {

  /**
    * this functions simplified the replying to a method
    * @param owner the sender of the reply message
    * @param returnValue the return value/answer for the request message
    */
  def reply(owner: Actor, returnValue: Any): Unit = {
    val msg = ResponseMessage(receiverId, senderId, returnValue)
    msg.sessionId = this.sessionId
    owner.sendMessage(msg)
  }
}

/**
  * This class is used to answer to a received message.
  * @param senderId the id of the sender
  * @param receiverId the id of the receiver
  * @param arg the return value of the method/answer of the request message
  */
case class ResponseMessage(override val senderId: Actor.AgentId,
                           override val receiverId: Actor.AgentId,
                           arg: Any)
    extends Message