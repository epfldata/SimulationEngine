package simulation.akka.core

import meta.runtime.{JsonSerializable, Message}
import akka.actor.NoSerializationVerificationNeeded
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes}
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import akka.actor.typed.ActorRef
import akka.actor.typed.receptionist.{ServiceKey}
 
object WorkerSpec {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value= classOf[Prepare], name = "Prepare"),
        new JsonSubTypes.Type(value= classOf[AgentsCompleted], name = "AgentsCompleted"),
        new JsonSubTypes.Type(value= classOf[Stop], name = "Stop"),
        new JsonSubTypes.Type(value= classOf[Start], name = "Start"),
        new JsonSubTypes.Type(value= classOf[ReceiveMessages], name = "ReceiveMessages"),
        new JsonSubTypes.Type(value= classOf[ReceiveAgentMap], name = "ReceiveAgentMap"),
        new JsonSubTypes.Type(value = classOf[SendTo], name = "SendTo"), 
        new JsonSubTypes.Type(value = classOf[ExpectedReceives], name = "ExpectedReceives")))
    sealed trait WorkerEvent
    final case class Prepare() extends WorkerEvent with NoSerializationVerificationNeeded
    final case class AgentsCompleted() extends WorkerEvent with NoSerializationVerificationNeeded
    final case class Stop() extends WorkerEvent with JsonSerializable
    final case class Start() extends WorkerEvent with NoSerializationVerificationNeeded
    final case class ReceiveMessages(workerId: Int, messages: Map[Long, List[Message]]) extends WorkerEvent with JsonSerializable
    final case class ReceiveAgentMap(workerId: Int, agentIds: Iterable[Long], replyTo: ActorRef[ReceiveMessages]) extends WorkerEvent with JsonSerializable
    final case class SendTo(workerId: Int, sendTo: Set[Int], agentTime: Int) extends WorkerEvent with JsonSerializable
    final case class ExpectedReceives(ids: Map[Int, Set[Int]], sendTo: ActorRef[SendTo]) extends WorkerEvent with JsonSerializable

    val WorkerStartServiceKey = ServiceKey[ExpectedReceives]("WorkerStart")
    val WorkerStopServiceKey = ServiceKey[Stop]("WorkerStop")
    val WorkerUpdateAgentMapServiceKey = ServiceKey[ReceiveAgentMap]("Update peer workers")
}