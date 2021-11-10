package meta.API

import scala.collection.mutable.StringBuilder

object StartSimulation {
  def apply[T](c: SimulationConfig)(implicit runner: SimsRunner[T]): SimulationSnapshot = {
      runner.run(c)
  }

  def bench[T](c: SimulationConfig)(w: StringBuilder)(implicit runner: SimsRunner[T]): Long = {
    println(f"Start simulation benchmark! ${c}")
    val totalTime = meta.runtime.simulation.util.bench {
      runner.run(c)
    }
    w ++= c.toString
    w ++= f" TotalTime:${totalTime}; TimePerIteration:${totalTime.toDouble/c.totalTurn}\n"
    totalTime
  }
}
