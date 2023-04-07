package simulation.akka.core

import akka.actor.NoSerializationVerificationNeeded
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes, JsonTypeName}
import meta.runtime.{JsonSerializable, Actor}
import akka.actor.typed.receptionist.{ServiceKey}
import akka.actor.typed.{ActorRef}

object LogControllerSpec {
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
    @JsonSubTypes(
    Array(
        new JsonSubTypes.Type(value = classOf[AggregateLog], name = "AggregateLog"),
        new JsonSubTypes.Type(value = classOf[ReduceLog], name = "ReduceLog"),
        new JsonSubTypes.Type(value = classOf[Stop], name = "Stop")))
    sealed trait LogControllerEvent
    @JsonTypeName("AggregateLog") 
    final case class AggregateLog(val wid: Int, val time: Int, val logPerWorker: Iterable[JsonSerializable]) extends LogControllerEvent with JsonSerializable
    @JsonTypeName("ReduceLog")
    final case class ReduceLog(val time: Int, val logPerRound: Map[Int, Iterable[JsonSerializable]]) extends LogControllerEvent with JsonSerializable
    @JsonTypeName("Stop")
    final case class Stop(val time: Int, val reply: ActorRef[DriverSpec.LogControllerFinished]) extends LogControllerEvent with JsonSerializable
    
    val LoggerAggregateServiceKey = ServiceKey[AggregateLog]("AggregateLog")
    val LoggerStopServiceKey = ServiceKey[Stop]("Stop")
}