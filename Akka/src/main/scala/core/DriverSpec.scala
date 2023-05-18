package simulation.akka.core

import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes, JsonTypeName}
import meta.runtime.JsonSerializable
import akka.actor.typed.receptionist.{ServiceKey}

object DriverSpec {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[InitializeWorkers], name = "InitializeWorkers"),
        new JsonSubTypes.Type(value = classOf[RoundStart], name = "RoundStart"),
        new JsonSubTypes.Type(value = classOf[RoundEnd], name = "RoundEnd"),
        new JsonSubTypes.Type(value = classOf[LogControllerFinished], name = "LogControllerFinished"),
        new JsonSubTypes.Type(value = classOf[InterruptDriver], name = "InterruptVector")))
    sealed trait DriverEvent

    @JsonTypeName("InitializeWorkers")
    final case class InitializeWorkers() extends DriverEvent with JsonSerializable
    
    @JsonTypeName("RoundStart")
    final case class RoundStart() extends DriverEvent with JsonSerializable
    
    @JsonTypeName("RoundEnd")
    final case class RoundEnd() extends DriverEvent with JsonSerializable

    @JsonTypeName("LogControllerFinished")
    final case class LogControllerFinished() extends DriverEvent with JsonSerializable

    @JsonTypeName("InterruptVector")
    final case class InterruptDriver(v: Vector[Int]) extends DriverEvent with JsonSerializable

    val InterruptDriverServiceKey = ServiceKey[InterruptDriver]("InterruptDriver")
}