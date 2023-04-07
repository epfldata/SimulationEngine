package simulation.akka.core

import meta.runtime.{JsonSerializable, Message}
import akka.actor.NoSerializationVerificationNeeded
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes, JsonTypeName}
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import akka.actor.typed.ActorRef
import akka.actor.typed.receptionist.{ServiceKey}
import akka.actor.typed.receptionist.Receptionist.Listing


object WorkerSpec {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value= classOf[Stop], name = "Stop"),
        new JsonSubTypes.Type(value= classOf[ReceiveMessages], name = "ReceiveMessages"),
        new JsonSubTypes.Type(value= classOf[ReceiveAgentMap], name = "ReceiveAgentMap"),
        new JsonSubTypes.Type(value = classOf[SendTo], name = "SendTo"), 
        new JsonSubTypes.Type(value = classOf[ExpectedReceives], name = "ExpectedReceives")))
    sealed trait WorkerEvent
    final case class Prepare() extends WorkerEvent with NoSerializationVerificationNeeded
    final case class AgentsCompleted() extends WorkerEvent with NoSerializationVerificationNeeded
    @JsonTypeName("Stop")
    final case class Stop() extends WorkerEvent with JsonSerializable
    final case class RoundStart() extends WorkerEvent with NoSerializationVerificationNeeded
    // After receiving messages from other workers, do not immediately add to the mailbox of the agents, 
    // since current worker may have not finished and the agent can accidentally see and process (if not timed) future messages
    @JsonTypeName("ReceiveMessages")
    final case class ReceiveMessages(workerId: Int, messages: Map[java.lang.Long, List[Message]]) extends WorkerEvent with JsonSerializable
    @JsonTypeName("ReceiveAgentMap")
    final case class ReceiveAgentMap(workerId: Int, agentIds: Iterable[java.lang.Long], replyTo: ActorRef[ReceiveMessages]) extends WorkerEvent with JsonSerializable
    @JsonTypeName("SendTo")
    final case class SendTo(workerId: Int, proposeInterval: Int) extends WorkerEvent with JsonSerializable
    @JsonTypeName("ExpectedReceives")
    final case class ExpectedReceives(sendTo: ActorRef[SendTo], acceptedInterval: Int, availability: Int) extends WorkerEvent with JsonSerializable
    final case class ListingResponse(listing: Listing) extends WorkerEvent

    val WorkerStartServiceKey = ServiceKey[ExpectedReceives]("WorkerStart")
    val WorkerStopServiceKey = ServiceKey[Stop]("WorkerStop")
    val WorkerUpdateAgentMapServiceKey = ServiceKey[ReceiveAgentMap]("Update peer workers")
}