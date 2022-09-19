package meta.runtime

import java.util.UUID
import Actor.AgentId
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes}

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
  Array(
    new JsonSubTypes.Type(value = classOf[RequestMessage], name = "requestMessage"),
    new JsonSubTypes.Type(value = classOf[ResponseMessage], name = "responseMessage")))
class Message extends JsonSerializable {
  var value: Int = 0
}

/**
  * This represents a message, which is used for sending something to another actor
  * @param senderId the id of the sender
  * @param sessionId If not None, then the sender expects a reply
  * @param methodId the id of the method that should be called. Label local RPC methods with 1 to n.
  * @param send_time the timestamp of the send message
  * @param latency the expected latency of the message
  * @param argss the arguments of the method
  */
case class RequestMessage(senderId: AgentId,
                          sessionId: Option[String],
                          methodInfo: String,
                          send_time: Int, 
                          latency: Int,
                          argss: List[List[Any]])
    extends Message

/**
  * This class is used to answer to a received message.
  * @param arg the return value of the method/answer of the request message
  * @param send_time the timestamp of the send message
  * @param latency the expected latency of the message
  */
case class ResponseMessage(arg: Any, 
                           send_time: Int, 
                           latency: Int)
    extends Message {
      var sessionId: String = UUID.randomUUID().toString
    }