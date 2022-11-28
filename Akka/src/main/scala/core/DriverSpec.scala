package simulation.akka.core

import akka.actor.NoSerializationVerificationNeeded
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes, JsonTypeName}
import meta.runtime.JsonSerializable

object DriverSpec {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[InitializeWorkers], name = "InitializeWorkers"),
        new JsonSubTypes.Type(value = classOf[RoundStart], name = "RoundStart"),
        new JsonSubTypes.Type(value = classOf[RoundEnd], name = "RoundEnd")))
    sealed trait DriverEvent

    @JsonTypeName("InitializeWorkers")
    final case class InitializeWorkers() extends DriverEvent with JsonSerializable
    
    @JsonTypeName("RoundStart")
    final case class RoundStart() extends DriverEvent with JsonSerializable
    
    @JsonTypeName("RoundEnd")
    final case class RoundEnd() extends DriverEvent with JsonSerializable
}