package meta.API

import meta.runtime.{Actor, Message}

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

  def runAndEval[R, T](c: SimulationConfig)(eval: (List[Actor], List[Message]) => T)(implicit runner: SimsRunner[R]): List[T] = {
    val duration: Int = c.totalTurn
    var stepConf: SimulationConfig = c.copy(totalTurn=1)
    (1 to duration).map(_ => {
      val res: SimulationSnapshot = apply(stepConf)(runner)
      stepConf = stepConf.copy(actors = res.sims, messages=res.messages)
      eval(res.sims, res.messages)
    }).toList
  }

  def runAndEvalOpt[R, T](c: SimulationConfig)(eval: (List[Actor], List[Message]) => T)(implicit runner: SimsRecorder[R]): List[T] = {
    runner.runAndEval[T](c)(eval)
  }

  def runAndReduce[R, K, T](c: SimulationConfig)(mapper: Actor=>K, reducer: List[K]=>T)(implicit runner: SimsMapReduceRecorder[R]): List[T] = {
    runner.runAndEval[K, T](c)(mapper, reducer)
  }
}
