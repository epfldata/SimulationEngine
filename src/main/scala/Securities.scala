package Securities {

  /** Mann ohne Eigenschaften. */
  abstract class Security {
    /** The price of the security `t` time steps from now.

      @param S0            current price of security
    @param current_time  now
    @param goal_time     (future) time at which the price is to be estimated.
    @param resolution    The number of steps into which the time to
                           process (`goal_time - current_time`) is split up.
      */
    def sample_future_price(
                             S0           : Double,
                             current_time : Double,
                             goal_time    : Double,
                             resolution   : Int = 1
                           ) : Double

    def stddev : Double


    /** Current price */
    def sample_price(
                      S0           : Double,
                      current_time : Double,
                      resolution   : Int = 1
                    ) : Double =
      sample_future_price(S0, current_time, current_time, resolution)

    protected def plot(name: String, xl: String, yl: String,
                       _x: Array[Double], _ys: List[Array[Double]]
                      ) = {
      import breeze.linalg._
      import breeze.plot._

      val x = DenseVector(_x);
      val ys = _ys.map(DenseVector(_));

      val f = Figure(name);
      val p = f.subplot(0);
      p.xlabel = xl;
      p.ylabel = yl;
      for(y <- ys) p += breeze.plot.plot(x, y);
      f.refresh;
    }

    def plot_distribution(S0: Double, current_time : Double, n: Int) {
      val v = (for(_ <- 1 to n) yield (sample_price(S0, current_time).toInt));
      val m = v.groupBy((x: Int) => x).mapValues(_.length);

      val x0 = (m.min._1 to m.max._1).toArray;
      val x  = x0.map(_.toDouble);
      val y  = x0.map((v: Int) => m.getOrElse(v, 0).toDouble);

      plot("Distribution Plot for " + this + ", starting at price " + S0 +
        ", at time " + current_time, "price", "#", x, List(y));
    }

    def plot_price(S0: Double, current_time : Double, until_time: Double,
                   samples_per_tick : Int = 1000
                  ) = {
      val inc = (until_time - current_time) / 100;

      val xy = (for(k <- 1 to 100) yield {
        val p = expectation(() => sample_price(S0, current_time + k * inc, k),
          samples_per_tick);

        (current_time + k * inc ,p)
      }).toArray;

      val x = xy.map(_._1);
      val y = xy.map(_._2);

      plot("Price Forecast for " + this + ", starting at price " + S0,
        "time", "price", x, List(y));
    }
  }

  case class Commodity(name: String) extends Security {
    /** Assumes price remains constant. */
    def sample_future_price(
                             S0           : Double,
                             current_time : Double,
                             goal_time    : Double,
                             resolution   : Int = 1
                           ) : Double = S0

    def stddev = 0.0
  }
} // end package Securities




package object Securities {

  val Wheat       = Commodity("wheat")
  val Flour       = Commodity("flour")
  val Bread       = Commodity("bread")
  val Land        = Commodity("land")
  val Beef        = Commodity("beef")
  val Oil         = Commodity("oil")
  val Fuel        = Commodity("fuel")

  val all_commodities = List(Wheat, Flour, Bread, Land, Beef, Oil, Fuel)


  def expectation(sample: () => Double, n: Int) : Double = {
    (for(_ <- 1 to n) yield sample()).sum / n
  }
} // end package object Securities



