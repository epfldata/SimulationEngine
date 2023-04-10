package meta.runtime

import Actor.AgentId
import scala.math.BigInt

class Message {
  def toBinary(): Array[Byte] = ???
  def fromBinary(bytes: Array[Byte]): AnyRef = ???
}

case class BooleanMessage(value: Boolean) extends Message {
  override def toBinary(): Array[Byte] = {
    if (value) {
      Array(0)
    } else {
      Array(1)
    }
  }
}

object BooleanMessage {
  def fromBinary(bytes: Array[Byte]): Boolean = {
    bytes.head match {
      case 0 => true
      case 1 => false
    }
  }
}

case class IntMessage(value: Int) extends Message {
  override def toBinary(): Array[Byte] = {
    BigInt(value).toByteArray
  }
}

case object IntMessage {
  def fromBinary(bytes: Array[Byte]): Int = {
    BigInt(bytes).toInt
  }
}

case class DoubleMessage(value: Double) extends Message {
  override def toBinary(): Array[Byte] = {
      val l = java.lang.Double.doubleToLongBits(value)
      val a = Array.fill(8)(0.toByte)
      for (i <- 0 to 7) a(i) = ((l >> ((7 - i) * 8)) & 0xff).toByte
      a
  }
}

case object DoubleMessage {
  def fromBinary(bytes: Array[Byte]): Double = {
    var i = 0
    var res = 0.toLong
    for (i <- 0 to 7) {
        res +=  ((bytes(i) & 0xff).toLong << ((7 - i) * 8))
    }
    java.lang.Double.longBitsToDouble(res)
  }
}

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
case class ResponseMessage(arg: Any, sessionId: String)
    extends TimedMessage