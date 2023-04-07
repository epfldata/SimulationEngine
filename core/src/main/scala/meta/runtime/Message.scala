package meta.runtime

import Actor.AgentId
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes, JsonTypeName}

@JsonTypeName("Message")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
  Array(
    new JsonSubTypes.Type(value = classOf[DoubleArrayMessage], name = "doubleArrayMessage"),
    new JsonSubTypes.Type(value = classOf[TimedMessage], name = "timedMessage")))
class Message extends JsonSerializable {
  var value: Double = 0
}

@JsonTypeName("doubleArrayMessage")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
class DoubleArrayMessage(size: Int) extends Message {
  var doubleArrayValue: Array[Double] = new Array[Double](size)
}

@JsonTypeName("timedMessage")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes(
  Array(
    new JsonSubTypes.Type(value = classOf[RequestMessage], name = "requestMessage"),
    new JsonSubTypes.Type(value = classOf[ResponseMessage], name = "responseMessage")))
class TimedMessage extends Message {
  var send_time: Int = 0
  var latency: Int = 1  // the allowed delay of the message
}

/**
  * This represents a message, which is used for sending something to another actor
  * @param senderId the id of the sender
  * @param sessionId If not None, then the sender expects a reply
  * @param methodId the id of the method that should be called. Label local RPC methods with 1 to n.
  * @param send_time the timestamp of the send message
  * @param argss the arguments of the method
  */
@JsonTypeName("requestMessage")
case class RequestMessage(senderId: AgentId,
                          sessionId: Option[String],
                          methodInfo: String,
                          argss: List[List[Any]])
    extends TimedMessage

/**
  * This class is used to answer to a received message.
  * @param arg the return value of the method/answer of the request message
  * @param sessionId the same as that of the request
  */
@JsonTypeName("responseMessage")
case class ResponseMessage(arg: Any, sessionId: String)
    extends TimedMessage