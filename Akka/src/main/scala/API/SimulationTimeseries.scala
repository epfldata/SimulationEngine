package simulation.akka.API

import meta.runtime.{Actor, JsonSerializable}
import com.fasterxml.jackson.annotation.{JsonTypeInfo, JsonSubTypes, JsonTypeName}
import scala.collection.mutable.Map

@JsonTypeName("SimulationTimeseries")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
class SimulationTimeseries extends JsonSerializable{
  def mapper(x: JsonSerializable): JsonSerializable = x
  def reducer(x: Iterable[Iterable[JsonSerializable]]): Iterable[JsonSerializable] = x.flatten
}