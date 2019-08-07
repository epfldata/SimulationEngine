package bo

object Metrics {
  def meanAbsoluteError(predicted: Seq[Double], actual: Seq[Double], scaled: Boolean = true): Double = {
    val dist: (Double, Double) => Double = (a, b) => {
      if (scaled && math.abs(a + b) > 0) math.abs((a - b) / (a + b)) else math.abs(a - b)
    }
    meanError(predicted, actual, dist)
  }

  def meanSquaredError(predicted: Seq[Double], actual: Seq[Double], scaled: Boolean = true): Double = {
    val dist: (Double, Double) => Double = (a, b) => {
      if (scaled && math.abs(a + b) > 0) (a - b) * (a - b) / (a + b) / (a + b) else (a - b) * (a - b)
    }
    meanError(predicted, actual, dist)
  }

  private def meanError(y1: Seq[Double], y2: Seq[Double], dist: (Double, Double) => Double) = {
    y1.zip(y2).map(t => dist(t._1, t._2) / y1.length).sum
  }
}
