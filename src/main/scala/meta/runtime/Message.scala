package meta.runtime

import java.util.UUID

import Actor.AgentId 

/**
  * This class is the supertype of the messages
  */
abstract class Message extends Serializable {

  /**
    * The sender of the message
    */
  val senderId: AgentId

  /**
    * The receiver of the message
    */
  val receiverId: AgentId

  /**
    * A unique id for the message-communication (request/response)
    */
  var sessionId: String = UUID.randomUUID().toString

  val blocking: Boolean
  
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
case class RequestMessage(override val senderId: AgentId,
                          override val receiverId: AgentId,
                          blocking: Boolean, 
                          methodId: Int,
                          argss: List[List[Any]])
    extends Message {

  /**
    * this functions simplified the replying to a method
    * @param owner the sender of the reply message
    * @param returnValue the return value/answer for the request message
    */
  def reply(owner: Actor, returnValue: Any): Unit = {
    val msg = ResponseMessage(receiverId, senderId, returnValue, blocking)
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
case class ResponseMessage(override val senderId: AgentId,
                           override val receiverId: AgentId,
                           arg: Any, 
                           blocking: Boolean)
    extends Message