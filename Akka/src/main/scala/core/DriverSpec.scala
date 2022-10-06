package simulation.akka.core

import akka.actor.NoSerializationVerificationNeeded
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes}
import meta.runtime.JsonSerializable

object DriverSpec {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[InitializeWorkers], name = "InitializeWorkers"),
        new JsonSubTypes.Type(value = classOf[RoundStart], name = "RoundStart"),
        new JsonSubTypes.Type(value = classOf[RoundEnd], name = "RoundEnd")))
    sealed trait DriverEvent
    final case class InitializeWorkers() extends DriverEvent with NoSerializationVerificationNeeded
    final case class RoundStart() extends DriverEvent with NoSerializationVerificationNeeded
    final case class RoundEnd() extends DriverEvent with NoSerializationVerificationNeeded
}