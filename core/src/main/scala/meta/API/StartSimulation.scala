package meta.API

/** Entry point for starting a simulation */
object StartSimulation {
  def apply[T](c: SimulationConfig)(implicit runner: SimsRunner[T]): SimulationSnapshot = {
      runner.run(c)
  }

  def benchAvg[T](c: SimulationConfig)(implicit runner: SimsRunner[T]): Double = {
    val totalTime = meta.runtime.simulation.util.bench {
      apply(c)(runner)
    }
    totalTime.toDouble/c.totalTurn
  }

  def record[T](c: SimulationConfig)(implicit runner: SimsRunner[T]): List[SimulationSnapshot] = {
    val duration: Int = c.totalTurn
    var stepConf: SimulationConfig = c.copy(totalTurn=1)
    (1 to duration).map(_ => {
      val res: SimulationSnapshot = apply(stepConf)(runner)
      stepConf = stepConf.copy(actors = res.sims, messages=res.messages)
      SimulationSnapshot(res.sims.map(x => x.SimClone()), List())
    }).toList
  }
}
