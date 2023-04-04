package simulation.akka.API

import meta.runtime.Actor
import scala.collection.mutable.Map

trait SimulationTimeseries[T, R] {
  val log: Map[Int, R] = Map[Int, R]()
  val intermediateLog: Map[Int, List[R]] = Map[Int, List[R]]()

  def mapper(x: Actor): T
  def reducer(x: Iterable[T]): R
  
  // worker proposes collected data from agents
  def add(round: Int, partial_reduced: R): Unit = synchronized {
    intermediateLog.update(round, partial_reduced :: intermediateLog.getOrElse(round, List[R]()))
  }

  // driver reduces collected data from workers
  def reduce(round: Int): Unit 
}

case object FullTimeseries extends SimulationTimeseries[Actor, Iterable[Actor]] {
  def mapper(x: Actor): Actor = x
  def reducer(x: Iterable[Actor]): Iterable[Actor] = x

  def reduce(round: Int): Unit = {
    log.update(round, intermediateLog.getOrElse(round, List[Iterable[Actor]]()).flatten)
  }
}