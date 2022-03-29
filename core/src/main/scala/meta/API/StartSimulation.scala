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

  def runAndEval[T, K](c: SimulationConfig)(eval: List[meta.runtime.Actor] => K)(implicit runner: SimsRunner[T]): List[K] = {
    val duration: Int = c.totalTurn
    var stepConf: SimulationConfig = c.copy(totalTurn=1)
    (1 to duration).map(_ => {
      val res: SimulationSnapshot = apply(stepConf)(runner)
      stepConf = stepConf.copy(actors = res.sims, messages=res.messages)
      eval(res.sims)
    }).toList
  }
}
