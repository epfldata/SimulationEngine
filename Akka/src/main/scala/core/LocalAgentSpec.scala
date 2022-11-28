package simulation.akka.core

import meta.runtime.{JsonSerializable, Message}
import akka.actor.NoSerializationVerificationNeeded
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes, JsonTypeName}
import akka.actor.typed.{ActorRef}

/**
  * Local agents communicate only with the workers.
  */
object LocalAgentSpec {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[AddMessages], name = "AddMessages"),
        new JsonSubTypes.Type(value = classOf[MessagesAdded], name = "MessagesAdded")
    ))
    trait AgentEvent 
    @JsonTypeName("AddMessages")
    final case class AddMessages(replyTo: ActorRef[MessagesAdded]) extends AgentEvent with JsonSerializable
    @JsonTypeName("MessagesAdded")
    final case class MessagesAdded(proposeInterval: Int, indexedSentMessages: Map[Long, List[Message]]) extends AgentEvent with JsonSerializable
}
