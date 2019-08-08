package bo

import Simulation.Simulation
import breeze.linalg.{DenseVector, linspace, normalize}
import breeze.plot.{Figure, plot}

case class Viz(f: Simulation => Seq[Double], outputNames: Array[String], var params: Map[String, Double]) {

  def plotSimOverParam(param: String,
                       bounds: (Double, Double),
                       numberOfPoints: Int = 100,
                       runSimTill: Int = 1000): Unit = {
    val x = linspace(bounds._1, bounds._2, numberOfPoints)
    val ys: DenseVector[Seq[Double]] = x.map(value => {
      println(s"running sim for param $value")
      params += param -> value
      val s = new Simulation(params)
      Main.initializeSimulation(s)
      Main.callSimulation(s, runSimTill)
      f(s)
    }).toDenseVector

    val figure = Figure()
    val p = figure.subplot(0)
    for (i <- ys(1).indices) {
      val normalized = normalize(ys.map(seq => seq(i))).map(_ * 100)
      p += plot(x, normalized, name = outputNames(i))
    }
    p.xlabel = s"$param"
    p.ylabel = "f"
    p.legend = true
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
