package meta.runtime

import java.util.UUID
import Actor.AgentId
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes}

/**
  * This class is the supertype of the messages
  */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
  Array(
    new JsonSubTypes.Type(value = classOf[RequestMessage], name = "responseMessage"),
    new JsonSubTypes.Type(value = classOf[ResponseMessage], name = "requestMessage")))
abstract class Message extends CborSerializable {

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
  * @param blocking indicates how the message is sent (whether sender waits for the reply): through A.B() or asyncMessage(() => A.B())
  * @param methodInfo the name of the method which should be called
  * @param argss the arguments of the method
  */
case class RequestMessage(override val senderId: AgentId,
                          override val receiverId: AgentId,
                          blocking: Boolean,
                          methodInfo: Either[String, Int],
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
  * @param blocking indicates whether the sender waits for the reply
  */
case class ResponseMessage(override val senderId: AgentId,
                           override val receiverId: AgentId,
                           arg: Any, 
                           blocking: Boolean)
    extends Message