package simulation.akka.API

import meta.runtime.Actor

case object GoLQ1Timeseries extends SimulationTimeseries[Option[Actor], Int] {
  def mapper(x: Actor): Option[Actor] = {
      if (x.asInstanceOf[generated.example.gameOfLife.Cell].alive==1) {
          Some(x)
      } else {
          None
      }
  }
  def reducer(x: Iterable[Option[Actor]]): Int = x.filter(i => i.isDefined).size

  def reduce(round: Int): Unit = {
    log.update(round, intermediateLog.getOrElse(round, List[Int]()).sum)
  }
}

case object GoLQ3Timeseries extends SimulationTimeseries[Option[Actor], Iterable[Actor]] {
  def mapper(x: Actor): Option[Actor] = {
    if (x.asInstanceOf[generated.example.gameOfLife.Cell].alive==1) {
        Some(x)
    } else {
        None
    }
  }
  def reducer(x: Iterable[Option[Actor]]): Iterable[Actor] = x.filter(i => i.isDefined).map(i => i.get)

  def reduce(round: Int): Unit = {
    log.update(round, intermediateLog.getOrElse(round, List[Iterable[Actor]]()).flatten)
  }
}