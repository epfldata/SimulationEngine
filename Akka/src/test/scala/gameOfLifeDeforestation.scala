package simulation.akka
package test

import simulation.akka.API._
import meta.runtime.Actor
import org.scalatest.FlatSpec

case object GoLQ1Timeseries extends SimulationTimeseries[Int, Int] {
  def mapper(x: Actor): Int = {
      x.asInstanceOf[generated.example.gameOfLife.Cell].alive
  }

  def reducer(x: Iterable[Int]): Int = x.sum

  def reduce(round: Int): Unit = {
    log.update(round, intermediateLog.getOrElse(round, List[Int]()).sum)
  }
}

case object GoLQ2Timeseries extends SimulationTimeseries[Option[Actor], Iterable[Actor]] {
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

class deforestationTest extends FlatSpec {
    import meta.deep.IR.Predef._

    val width: Int = 5
    val height: Int = 5
    val totalTurns: Int = 10

    "Full timeseries" should f"print the state of agents in every round" in {
        val agents = generated.example.gameOfLife.InitData(width, height)
        simulation.akka.API.OptimizationConfig.timeseries = Some(FullTimeseries)
        // default to time series
        Simulate(agents, totalTurns)
        FullTimeseries.log.foreach(x => println(x._2.map(i => i.asInstanceOf[generated.example.gameOfLife.Cell].alive)))
    }

    "Q1" should "return only the number of alive agents" in {
        // (logicalClock, Iterable(local_sims.filter(i => i._2.asInstanceOf.alive==1).size))
        val agents = generated.example.gameOfLife.InitData(width, height)
        simulation.akka.API.OptimizationConfig.timeseries = Some(GoLQ1Timeseries)
        API.Simulate(agents, totalTurns)
        GoLQ1Timeseries.log.foreach(x => println(x._2))
    }

    "Q2" should "return agents that are alive" in {
        val agents = generated.example.gameOfLife.InitData(width, height)
        simulation.akka.API.OptimizationConfig.timeseries = Some(GoLQ2Timeseries)
        API.Simulate(agents, totalTurns)
        GoLQ2Timeseries.log.foreach(x => println(x._2.map(i => i.asInstanceOf[generated.example.gameOfLife.Cell].alive)))
    }
}