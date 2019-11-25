package ml_supplements

import ml_supplements.DatasetCreator.Statistics
import breeze.linalg.DenseMatrix
import breeze.stats._


object Metrics {
  def meanAbsoluteError(predicted: Statistics, actual: Statistics, scaled: Boolean = true): Double = {
    val merged: Seq[(Double, Double)] = predicted.toSeq.sortBy(_._1).map(_._2).zip(actual.toSeq.sortBy(_._1).map(_._2))
    merged.map(t => math.abs(t._1 - t._2) / merged.size).sum
  }

  def meanSquaredError(predicted: Statistics, actual: Statistics, scaled: Boolean = true): Double = {
    val merged: Seq[(Double, Double)] = predicted.toSeq.sortBy(_._1).map(_._2).zip(actual.toSeq.sortBy(_._1).map(_._2))
    merged.map(t => (t._1 - t._2) * (t._1 - t._2) / merged.size).sum
  }

  def standardize(matrix: DenseMatrix[Double], header: List[String], data: Statistics): Statistics = {
    header.zipWithIndex.map { case (stat, index) =>
      if (stddev(matrix(::, index)) > 0)
        (stat, (data(stat) - mean(matrix(::, index))) / stddev(matrix(::, index)))
      else
        (stat, 1.0)
    }.toMap
  }
}
