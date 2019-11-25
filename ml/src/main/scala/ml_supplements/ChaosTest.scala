package ml_supplements

import Simulation.Simulation
import ml_supplements.DatasetCreator.{Data, Statistics}

case class ChaosTest(var params: Data, var variables: Data) {
  def lyapunovExponent(paramName: String,
                       agentType: String,
                       iterations: Int = 1000,
                       step: Int = 1,
                       deltaFraction: Double = 20,
                       distance: (Double, Double) => Double = (a, b) => math.abs(a - b)): Double = {
    def getTimeSeries(s: Simulation) = {
      Main.initializeSimulation(s)
      for (_ <- 1 to iterations) yield {
        Main.runSimulation(s, step)
        s.getGlobalStat
      }
    }

    val paramValue = params(agentType)(paramName)
    var s = new Simulation(params, variables)
    val result0: Seq[Statistics] = getTimeSeries(s)

    params(agentType) += paramName -> paramValue * (1 + deltaFraction)
    s = new Simulation(params, variables)
    val result1: Seq[Statistics] = getTimeSeries(s)

    val time = for (t <- 1 to iterations) yield t

    val distances = result0.zip(result1).map(t => {
      val merged: Map[String, Seq[Double]] = (t._1.toSeq ++ t._2.toSeq).groupBy(_._1).mapValues(_.map(_._2))
      merged.map{case (_, l) => distance(l.head, l.last)}.sum
    })

    distances.zip(time).map {
      case (d, t) if d > 0 =>
        math.log(d / math.abs(deltaFraction * paramValue)) / t / iterations
      case _ => 0
    }.sum
  }

}