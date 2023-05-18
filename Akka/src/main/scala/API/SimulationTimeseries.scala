package simulation.akka.API

import meta.runtime.Actor
import scala.collection.mutable.Map

abstract class SimulationTimeseries {
  def mapper(x: Serializable): Serializable
  def reducer(x: Iterable[Iterable[Serializable]]): Iterable[Serializable]
}

case object FullTimeseries extends SimulationTimeseries {
  // a sequential worker applies the mapper to each agent
  override def mapper(x: Serializable): Serializable = {
    x
  }

  // workers each send an Iterable[Serializable] to the log controller. 
  // Log controller collects Iterable[Iterable[Serializable]] and 
  // applies the reducer method to reduce the intermediate data 
  override def reducer(x: Iterable[Iterable[Serializable]]): Iterable[Serializable] = {
    x.flatten
  }
}