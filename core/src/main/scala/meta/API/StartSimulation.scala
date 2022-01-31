package meta.API

object StartSimulation {
  def apply[T](c: SimulationConfig)(implicit runner: SimsRunner[T]): SimulationSnapshot = {
      runner.run(c)
  }

  def benchAvg[T](c: SimulationConfig)(implicit runner: SimsRunner[T]): Double = {
    val totalTime = meta.runtime.simulation.util.bench {
      runner.run(c)
    }
    totalTime.toDouble/c.totalTurn
  }
}
