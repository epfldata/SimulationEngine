package bo

import Simulation.Simulation
import breeze.linalg.linspace
import breeze.plot.{Figure, plot}

case class Viz(f: Simulation => Seq[Double], var params: Map[String, Double]) {

  def plotSimOverParam(param: String,
                       bounds: (Double, Double),
                       numberOfPoints: Int = 100,
                       runSimTill: Int = 1000): Unit = {
    val x = linspace(bounds._1, bounds._2, numberOfPoints)
    val y = x.map(value => {
      println(s"running sim for param $value")
      params += param -> value
      val s = new Simulation(params)
      Main.initializeSimulation(s)
      Main.callSimulation(s, runSimTill)
      f(s).head
    }).toDenseVector

    val figure = Figure()
    val p = figure.subplot(0)
    p += plot(x, y)
    p.xlabel = s"$param"
    p.ylabel = "f"
    figure.saveas(s"results/plotOver$param.png")
  }

  def plotSimOverTime(bounds: (Int, Int),
                      numberOfPoints: Int = 1000): Unit = {
    val t = linspace(bounds._1, bounds._2, numberOfPoints)
    val step = (bounds._2 - bounds._1) / numberOfPoints
    assert(step > 0, "step size must be a positive non-zero integer!")
    val s = new Simulation(params)
    Main.initializeSimulation(s)
    val y = t.map(_ => {
      Main.callSimulation(s, step)
      f(s).head
    }).toDenseVector

    val figure = Figure()
    val p = figure.subplot(0)
    p += plot(t, y)
    p.xlabel = "time"
    p.ylabel = "f"
    figure.saveas("results/plotOverTime.png")
  }
}
