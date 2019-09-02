package bo

import bo.DatasetCreator.Statistics

object Metrics {
  def meanAbsoluteError(predicted: Statistics, actual: Statistics, scaled: Boolean = true): Double = {
    val dist: (Double, Double) => Double = (a, b) => {
      if (scaled && math.abs(a + b) > 0) math.abs((a - b) / (a + b)) else math.abs(a - b)
    }
    meanError(predicted, actual, dist)
  }

  def meanSquaredError(predicted: Statistics, actual: Statistics, scaled: Boolean = true): Double = {
    val dist: (Double, Double) => Double = (a, b) => {
      if (scaled && math.abs(a + b) > 0) (a - b) * (a - b) / (a + b) / (a + b) else (a - b) * (a - b)
    }
    meanError(predicted, actual, dist)
  }

  private def meanError(y1: Statistics, y2: Statistics, dist: (Double, Double) => Double) = {
    val merged: Map[String, Seq[Double]] = (y1.toSeq ++ y2.toSeq).groupBy(_._1).mapValues(_.map(_._2))
    merged.map{case (_, l) => dist(l.head, l.last) / y1.size}.sum
  }
}
