package ml_supplements

import java.nio.file.{Files, Paths}

import Simulation.Simulation
import ml_supplements.DatasetCreator.Data
import breeze.linalg.{Axis, DenseMatrix, DenseVector, linspace, normalize}
import breeze.plot.{Figure, plot}

case class Viz(var constants: Data, var variables: Data) {

  val outputNames: Array[String] = new Simulation(constants, variables).getGlobalStat.keys.toArray

  def plotSimOverParam(paramName: String,
                       agentType: String,
                       bounds: (Double, Double),
                       numberOfPoints: Int = 100,
                       runSimTill: Int = 1000): Unit = {
    val csvFilePath = s"results/csv/plotsOver$paramName.csv"
    val (x: DenseVector[Double], ys: DenseMatrix[Double]) =
      if (Files.exists(Paths.get(csvFilePath))) {
        val matrix = CsvManager.readCsvFile(csvFilePath)._1
        (matrix(::, 0), matrix.delete(0, Axis._1))
      } else {
        val x = linspace(bounds._1, bounds._2, numberOfPoints)
        val ys = DenseMatrix(
          x.map(value => {
            println(s"running sim for param $value")
            constants(agentType) += paramName -> value
            val s = new Simulation(constants, variables)
            Main.initializeSimulation(s)
            Main.runSimulation(s, runSimTill)
            s.getGlobalStat.values.toSeq
          }).toArray:_*
        )
        CsvManager.writeCsvFile(DenseMatrix(x.data ++ ys.data), csvFilePath, "x" +: outputNames)
        (x, ys)
      }

    val figureSingle = Figure("Single Measures")
    val pSingle = figureSingle.subplot(0)
    for (i <- 0 until ys.cols) {
      val normalized = normalize(ys(::, i)).map(_ * 100)
      pSingle += plot(x, normalized, name = outputNames(i))
    }
    pSingle.xlabel = s"$paramName"
    pSingle.ylabel = "f"
    pSingle.setXAxisDecimalTickUnits()
    pSingle.legend = true
    val dpi = 720
    try {
      figureSingle.saveas(s"results/singlePlotsOver$paramName.png", dpi)
    } catch {
      case _: Exception => // ignore
    }

    val figureSum = Figure("Sum of Measures")
    val pSum = figureSum.subplot(0)
    val normalized: Seq[DenseVector[Double]] = for (i <- 0 until ys.cols) yield normalize(ys(::, i)).map(_ * 100)
    pSum += plot(x, normalized.foldLeft(DenseVector.zeros[Double](normalized.head.size))(_ + _))
    pSum.xlabel = s"$paramName"
    pSum.ylabel = "f"
    pSum.setXAxisDecimalTickUnits()
    figureSum.saveas(s"results/sumPlotOver$paramName.png", dpi)
  }

  def plotSimOverTime(bounds: (Int, Int),
                      numberOfPoints: Int = 1000): Unit = {
    val t = linspace(bounds._1, bounds._2, numberOfPoints)
    val step = (bounds._2 - bounds._1) / numberOfPoints
    assert(step > 0, "step size must be a positive non-zero integer!")
    val s = new Simulation(constants, variables)
    Main.initializeSimulation(s)
    val ys: DenseMatrix[Double] = DenseMatrix(
      t.map(_ => {
      Main.runSimulation(s, step)
      s.getGlobalStat.values.toSeq
      }).toArray:_*)

    val figure = Figure()
    val p = figure.subplot(0)
    for (i <- 0 until ys.cols) {
      p += plot(t, ys(::, i), name = outputNames(i))
    }
    p.xlabel = "time"
    p.ylabel = "f"
    figure.saveas("results/plotOverTime.png")
  }
}
