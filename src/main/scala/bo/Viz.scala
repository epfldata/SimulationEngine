package bo

import Simulation.Simulation
import breeze.linalg.{DenseMatrix, DenseVector, linspace, normalize}
import breeze.plot.{Figure, plot}

case class Viz(f: Simulation => Seq[Double], outputNames: Array[String], var params: Map[String, Double]) {

  def plotSimOverParam(param: String,
                       bounds: (Double, Double),
                       numberOfPoints: Int = 100,
                       runSimTill: Int = 1000): Unit = {
    val x: DenseVector[Double] = linspace(bounds._1, bounds._2, numberOfPoints)
    val ys: DenseMatrix[Double] = DenseMatrix(
      x.map(value => {
        println(s"running sim for param $value")
        params += param -> value
        val s = new Simulation(params)
        Main.initializeSimulation(s)
        Main.callSimulation(s, runSimTill)
        f(s)
      }).toArray:_*
    )

    val figureSingle = Figure("Single Measures")
    val pSingle = figureSingle.subplot(0)
    for (i <- 0 until ys.cols) {
      val normalized = normalize(ys(::, i)).map(_ * 100)
      pSingle += plot(x, normalized, name = outputNames(i))
    }
    pSingle.xlabel = s"$param"
    pSingle.ylabel = "f"
    pSingle.legend = true
    try {
      figureSingle.saveas(s"results/singlePlotsOver$param.png")
    } catch {
      case _: Exception => // ignore
    }

    val sumFigure = Figure("Sum of Measures")
    val pSum = sumFigure.subplot(0)
    val normalized: Seq[DenseVector[Double]] = for (i <- 0 until ys.cols) yield normalize(ys(::, i)).map(_ * 100)
    pSum += plot(x, normalized.foldLeft(DenseVector.zeros[Double](normalized.head.size))(_ + _))
    pSum.xlabel = s"$param"
    pSum.ylabel = "f"
    sumFigure.saveas(s"results/sumPlotOver$param.png")
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
