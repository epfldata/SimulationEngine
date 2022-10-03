package simulation.akka.API

import com.typesafe.config.ConfigFactory
import meta.API.SimulationSnapshot
import meta.runtime.{Actor, Message}
import scala.concurrent.Await
import scala.concurrent.duration._
import akka.actor.typed.ActorSystem

import scala.collection.mutable.{Map}

class Log[T, R](mapper: Actor => T, reducer: Iterable[T] => R) {
  val log: Map[Int, R] = Map[Int, R]()
  // val reduced: Map[Int, Iterable[T]] = Map[Int, Iterable[T]]()
  val timeseries: Map[Int, Iterable[Any]] = Map[Int, Iterable[Any]]()

  def getMapper(): Actor => T = {
    mapper
  }

  def getReducer(): Iterable[T] => R = {
    reducer
  }

  def add[K](round: Int, partial_reduced: Iterable[K]): Unit = synchronized {
    timeseries.update(round, timeseries.getOrElse(round, List[K]())++partial_reduced)
  }
}