package bo

import scala.collection.mutable.{Map => MutableMap}

import Simulation.Simulation

case class ChaosTest(f: Simulation => Seq[Double], var params: MutableMap[String, Map[String, Double]]) {
  def lyapunovExponent(paramName: String,
                       agentType: String,
                       iterations: Int = 1000,
                       step: Int = 1,
                       deltaFraction: Double = 20,
                       distance: (Double, Double) => Double = (a, b) => math.abs(a - b)): Double = {
    def getTimeSeries(s: Simulation) = {
      Main.initializeSimulation(s)
      for (_ <- 1 to iterations) yield {
        Main.callSimulation(s, step)
        f(s)
      }
    }

    val paramValue = params(agentType)(paramName)
    var s = new Simulation(params)
    val result0 = getTimeSeries(s)

    params(agentType) += paramName -> paramValue * (1 + deltaFraction)
    s = new Simulation(params)
    val result1 = getTimeSeries(s)

    val time = for (t <- 1 to iterations) yield t

    val distances = result0.zip(result1).map(t => t._1.zip(t._2).map(z => distance(z._1, z._2)).sum)

    distances.zip(time).map {
      case (d, t) if d > 0 =>
        math.log(d / math.abs(deltaFraction * paramValue)) / t / iterations
      case _ => 0
    }.sum
  }

}