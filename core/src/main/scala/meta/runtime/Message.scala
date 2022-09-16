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
  * @param receiverId the id of the receiver
  * @param blocking indicates how the message is sent (whether sender waits for the reply) through A.B() or async_call()
  * @param methodInfo the name of the method which should be called
  * @param argss the arguments of the method
  */
case class RequestMessage(senderId: AgentId,
                          receiverId: AgentId,
                          oneside: Boolean,
                          methodInfo: String,
                          send_time: Int, 
                          latency: Int,
                          argss: List[List[Any]])
    extends Message {

  /**
    * this functions simplified the replying to a method
    * @param owner the sender of the reply message
    * @param returnValue the return value/answer for the request message
    */
  var sessionId: String = UUID.randomUUID().toString
}

/**
  * This class is used to answer to a received message.
  * @param receiverId the id of the receiver
  * @param arg the return value of the method/answer of the request message
  * @param blocking indicates whether the sender waits for the reply
  */
case class ResponseMessage(arg: Any, 
                           send_time: Int, 
                           latency: Int)
    extends Message {
      var sessionId: String = UUID.randomUUID().toString
    }