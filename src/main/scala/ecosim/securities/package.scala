package ecosim

package object securities {
  def expectation(sample: () => Double, n: Int): Double = {
    (for (_ <- 1 to n) yield sample()).sum / n
  }

  def compute_standard_deviation(
      mean: Double,
      sample: () => Double,
      n: Int
  ): Double =
    math.sqrt((for (_ <- 1 to n) yield {
      val d = mean - sample()
      d * d
    }).sum) / (n - 1)

  /** geometric Brownian Motion. For a security in the Black-Scholes Model,
      this needs to be scaled by (multiplied with) the starting price of the
      security. This approximates continuous Brownian Motion for dt time.

    @param mu -- risk-free rate
  @param sigma -- volatility
  @param dt -- time to run.
    */
  def geoBM(
      mu: Double,
      sigma: Double,
      dt: Double
  ): Double = {
    math.exp(
      (mu - sigma * sigma / 2) * dt
        + sigma * Nsample(0, math.sqrt(dt)))
  }

  def Nsample(mu: Double, sigma: Double): Double =
    (breeze.stats.distributions.Gaussian(mu, sigma).sample(1))(0)

  /** given the risk free rate, and a value S, get compounded value after
      time delta_t.

      @param delta_t      is in years
    */
  def compound_interest(risk_free_rate: Double,
                        S: Double,
                        delta_t: Double): Double =
    S * math.exp(risk_free_rate * delta_t)
  // argument of sample is # of samples to take; produces a vector

  def plot_distribution(
      label: String,
      xlabel: String,
      sample: () => Int,
      num_samples: Int
  ) {
    val v = for (_ <- 1 to num_samples) yield sample()
    val m = v.groupBy((x: Int) => x).mapValues(_.length)

    val x0 = (m.min._1 to m.max._1).toArray
    val x = x0.map(_.toDouble)
    val y = x0.map((v: Int) => m.getOrElse(v, 0).toDouble)

    plot("Distribution Plot for " + label, xlabel, "#", x, List(y))
  }

  def plot(name: String,
           xl: String,
           yl: String,
           _x: Array[Double],
           _ys: List[Array[Double]]): Unit = {
    import breeze.linalg._
    import breeze.plot._

    val x = DenseVector(_x)
    val ys = _ys.map(DenseVector(_))

    val f = Figure(name)
    val p = f.subplot(0)
    p.xlabel = xl
    p.ylabel = yl
    for (y <- ys) p += breeze.plot.plot(x, y)
    f.refresh
  }
}
