package object Securities {

  def expectation(sample: () => Double, n: Int) : Double = {
    (for(_ <- 1 to n) yield sample()).sum / n
  }


  def compute_standard_deviation(
    mean: Double, sample: () => Double, n: Int
  ) =
    math.sqrt((for(_ <- 1 to n) yield {
      val d = mean - sample();
      d*d
    }).sum) / (n-1)


  /** geometric Brownian Motion. For a security in the Black-Scholes Model,
      this needs to be scaled by (multiplied with) the starting price of the
      security. This approximates continuous Brownian Motion for dt time.

    @param mu -- risk-free rate
    @param sigma -- volatility
    @param dt -- time to run.
  */
  def geoBM(
     mu: Double, sigma: Double, dt: Double
  ) : Double = {
     math.exp((mu - sigma*sigma / 2) * dt
               + sigma * Nsample(0, math.sqrt(dt)))
  }


  /** given the risk free rate, and a value S, get compounded value after
      time delta_t.

      @param delta_t      is in years
  */
  def compound_interest(risk_free_rate: Double, S: Double, delta_t: Double) =
    S * math.exp(risk_free_rate * delta_t)


  def Nsample(mu: Double, sigma: Double) : Double =
    (breeze.stats.distributions.Gaussian(mu, sigma).sample(1))(0)
     // argument of sample is # of samples to take; produces a vector


  def plot(name: String, xl: String, yl: String,
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


  def plot_distribution(
    label: String, xlabel: String, sample: () => Int, num_samples: Int
  ) {
    val v = for(_ <- 1 to num_samples) yield sample();
    val m = v.groupBy((x: Int) => x).mapValues(_.length);

    val x0 = (m.min._1 to m.max._1).toArray;
    val x  = x0.map(_.toDouble);
    val y  = x0.map((v: Int) => m.getOrElse(v, 0).toDouble);

    plot("Distribution Plot for " + label, xlabel, "#", x, List(y));
  }

} // end package object Securities



package Securities {


abstract class Security {
  /** computes the sequence of actual prices at resolution

      @param S0           current price of security
      @param current_time now
      @param goal_time    future time at which the price is to be estimated.
      @param resolution   The number of steps into which the time to
                          process (`goal_time - current_time`) is split up.
  */
  def compute_time_series(
    S0           : Double, // start_price
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Array[Double]

  /** The price of the security at future point.
      The parameters are as for compute_time_series().
  */
  def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double = {
    assert(current_time <= goal_time);
    (compute_time_series(S0, current_time, goal_time, resolution))(resolution)
  }

  /** volatility */
  def stddev : Double

  def plot_chart(
    S0: Double, current_time : Double, until_time: Double, resolution: Int
  ) {
    val inc = (until_time - current_time) / resolution;
    val S = compute_time_series(S0, current_time, until_time, resolution);
    val x = (for (k <- 0 to resolution) yield {
       current_time + k * inc
    }).toArray;

    plot("Price Chart for" + this, "time", "price", x, List(S))
  }
}


/** A simple model of price development: The security appreciates at the
    risk-free rate `r`, with volatility `stddev`.

    This is the model of securities used in the Black-Scholes model.

    @param r      risk-free rate of appreciation
    @param stddev volatility / standard deviation of the security
                  in one unit of time
*/
case class VanillaSecurity(r: Double, val stddev: Double
) extends Security {
  /** computes the sequence of actual prices at resolution
      Does an approximation of Brownian motion.

      @param S0           current price of security
      @param current_time now
      @param goal_time    future time at which the price is to be estimated.
      @param resolution   The number of steps into which the time to
                          process (`goal_time - current_time`) is split up.
  */
  def compute_time_series(
    S0           : Double, // start_price
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Array[Double] = {
    assert(current_time <= goal_time);
    assert(resolution >= 1);

    val dt = goal_time - current_time; // look this far into the future
    val S  = new Array[Double](resolution + 1);
    S(0) = S0;

    for(i <- 1 to resolution)
      S(i) = S(i-1) * geoBM(r, stddev, dt/resolution)

    S
  }

  // this is redundant but more efficient than super.sample_future_price
  override def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double = {
    assert(current_time <= goal_time);
    S0 * geoBM(r, stddev, goal_time - current_time)
  }
}


case class FundamentalsSecurity0(
  r: Double,
  stddev: Double,
  frequency: Integer,
  amplitude: Double
) extends Security {

  private def fundamental_value_change(): Double = {
    val rnd = scala.util.Random

    // do we have an event now?
    val now = (rnd.nextInt % frequency) == 0;
    val magnitude = (rnd.nextDouble * 2 - 1) * amplitude;

    if(now) magnitude else 0.0
  }

  /** computes the sequence of actual prices at resolution */
  def compute_time_series(
    S0           : Double, // start_price
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Array[Double] = {
    assert(current_time <= goal_time);
    assert(resolution >= 1);

    val dt = goal_time - current_time; // look this far into the future
    val S  = new Array[Double](resolution + 1);
    S(0) = S0;

    for(i <- 1 to resolution)
      S(i) = S(i-1) * geoBM(r, stddev, dt/resolution)
        + fundamental_value_change();

    S
  }
}


/** There is only one reference security, so we can plot a payoff profile
    relative to it.
*/
abstract class SingleSecurityDerivative(
  val security : Security
) extends Security {

  /** current price. for futures and options this is nontrivial. */
  def sample_price(
    S0           : Double,
    current_time : Double,
    resolution   : Int = 1
  ) : Double =
      sample_future_price(S0, current_time, current_time, resolution)

  /** Estimate the current price of the security using sampling. */
  def estimate_price(
    S0: Double,
    current_time: Double,
    num_it: Int = 1000,
    resolution: Int = 100
  ) =
    expectation(() => sample_price(S0, current_time, resolution), num_it)

  def estimate_stddev(
    S0: Double,
    current_time : Double,
    num_it: Int = 1000,
    resolution: Int = 100
  ) =
    compute_standard_deviation(
      estimate_price(S0, current_time, num_it, resolution),
      () => sample_price(S0, current_time, resolution), num_it)

  def plot_price(
    S0: Double, current_time : Double, until_time: Double, resolution: Int,
    samples_per_tick : Int = 1000
  ) = {
    val inc = (until_time - current_time) / resolution;

    val xy = (for(k <- 0 to resolution) yield {
      val p = expectation(() => sample_price(S0, current_time + k * inc, k),
              samples_per_tick);

      (current_time + k * inc ,p)
    }).toArray;

    val x = xy.map(_._1);
    val S = xy.map(_._2);

    plot("Price Forecast for " + this, "time", "price", x, List(S));
  }

  /** Note: The price of buying the derivative is NOT taken into account.

      This is based on simulation. Try a current_time earlier than the
      execution date and a small n to see that the textbook
      curves are, in the limit, what simulation gives us.
  */
  def plot_payoff_profile(S_from: Double, S_to: Double, current_time : Double,
    n: Int = 1
  ) {
    val resolution = 100;
    val v = (for(k <- 1 to resolution) yield {
      val S0 = S_from + (S_to - S_from) * k / resolution;
      (S0, expectation(() => sample_price(S0, current_time), n))
    }).toArray;
    val x = v.map(_._1);
    val y = v.map(_._2);

    plot("Payoff Profile for " + this + " at time " + current_time,
         "price of security",
         "price of derivative", x, List(y));
  }
}


class Portfolio(
  securities : List[Security]
) extends Security {

  // TODO chart: this only requires summing up
  def compute_time_series(
    S0           : Double, // start_price
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Array[Double] = {
    assert(false);
    new Array[Double](0)
  }

  override def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double =
    securities.map(sec => sec.sample_future_price(
      S0, current_time, goal_time, resolution)).sum

  /** Not implemented. */
  def stddev = { assert(false); 0.0 }
}


/*
case class Hedge(
  securities : List[SingleSecurityDerivative]
) extends Portfolio(securities) {

//  for(sec <- securities.tail) assert(securities.head.security == sec.security);

  /** TODO:
      This should be also supported for combined derivatives on the same single
      security, e.g. a Times security or a combination of multiple options of
      the same stock.
  */
  def plot_payoff_profile(S_from: Double, S_to: Double, current_time : Double,
    n: Int = 1
  ) {
    val resolution = 100;
    val x = (for(k <- 1 to resolution) yield {
                S_from + (S_to - S_from) * k / resolution;
             }).toArray;

/*
    val ys = securities.map((sec: Security) =>
               x.map((S0: Double) =>
                 expectation(() => sec.sample_price(S0, current_time), n)));
*/

    val y = x.map((S0: Double) =>
                expectation(() => sample_price(S0, current_time), n));

    plot("Payoff Profile for " + this + " at time " + current_time,
         "price of security",
         "price of derivative", x, List(y) /*ys*/);
  }
}
*/


case class Short(
  override val security : Security
) extends SingleSecurityDerivative(security) {

  def compute_time_series(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Array[Double] =
    security.compute_time_series(
       S0, current_time, goal_time, resolution).map(x => - x)

  override def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double =
    - security.sample_future_price(S0, current_time, goal_time, resolution)

  def stddev = security.stddev
}


/** Multiple copies of a SingleSecurityDerivative */
case class Times(
  override val security : Security,
  n: Int
) extends SingleSecurityDerivative(security) {

  // TODO chart
  def compute_time_series(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Array[Double] =
    security.compute_time_series(
      S0, current_time, goal_time, resolution).map(x => n * x)

  override def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double =
    n * security.sample_future_price(
      S0, current_time, goal_time, resolution);

  /** Is that correct? */
  def stddev = security.stddev * math.sqrt(n)
}


/** A common superclass for futures and options -- a derivative of a single
    security with a strike price.

    Contributes a function `sample_future_price`, which computes a future
    price based on `sample_future_price` on the underlying security.

    Requires
    subclasses to implement a (protected) function `value_at_expiration_time`.
*/
abstract class FutOpt(
  override val security : Security,
  strike_price          : Double,
  due_date              : Double,
  risk_free_rate        : Double
) extends SingleSecurityDerivative(security) {

  // TODO chart: we could do all kinds of things here, but the interesting
  // prices to be charted here are at which the derivative is TRADED.
  // We only know this by running a market.
  def compute_time_series(
    S0           : Double, // start_price
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Array[Double] = {
    assert(false);
    new Array[Double](0)
  }

  /** Value at expiration time of the derivative as a function of the
      price `S` of the underlying security at expiration time.
  */
  protected def value_at_expiration_time(S: Double) : Double

  /** Uses protected abstract function `value_at_expiration_time`, which
      is specific to the derivative.

    @param S0            current price of the underlying security
    @param current_time  the current time at which S0 is the prict.
                         must not be greater than `due_date`.
    @param goal_time     the time at which the price is to be queries.
                         This is in general not the expiration date.
                         may be greater or smaller than `due_date`.
    @param resolution    used for sampling future price of underlying
                         security.
  */
  override def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double = {
    assert(current_time <= due_date);

    // the price of the underlying security at expiration time
    val sec_S = security.sample_future_price(
      S0, current_time, due_date, resolution);

    // the value of the derivative at expiration time
    val drv_S = value_at_expiration_time(sec_S);

    // what is the current value of being able to sell the derivative at the
    // expiration time at price drv_S?
    drv_S * math.exp(risk_free_rate * (goal_time - due_date))
    // same as:
    // compound_interest(risk_free_rate, drv_S, goal_time - due_date)
  }

  /** Not implemented. */
  def stddev = { assert(false); 0.0 }
}


case class EuropeanCallOption(
  override val security : Security,
  strike_price          : Double,
  due_date              : Double,
  risk_free_rate        : Double
) extends FutOpt(security, strike_price, due_date, risk_free_rate) {

  protected def value_at_expiration_time(S: Double) =
    math.max(0, S - strike_price)

  /** Compute the current price of the option using the Black-Scholes formula.
      Must only be used for VanillaSecurities.

      @param S0 security price
  */
  def BlackScholes(S0: Double, current_time : Double) = {
    val sec    = security.asInstanceOf[VanillaSecurity];
    val r      = sec.r;                   // risk-free interest rate
    val stddev = sec.stddev;              // standard deviation of the security
    val K      = strike_price;            // option strike price
    val T      = due_date - current_time; // time to expiration

    assert(T >= 0);

    def N(d: Double) =
      breeze.stats.distributions.Gaussian(0, 1).probability(-1.0/0, d)

    val nrm = stddev * math.sqrt(T);
    val d = (math.log(S0 / K) + (r + 0.5 * stddev * stddev) * T) / nrm;

    S0 * N(d) - K * math.exp(- r * T) * N(d - nrm)
  }
}


case class EuropeanPutOption(
  override val security : Security,
  strike_price          : Double,
  due_date              : Double,
  risk_free_rate        : Double
) extends FutOpt(security, strike_price, due_date, risk_free_rate) {

  protected def value_at_expiration_time(S: Double) =
    math.max(0, strike_price - S)
}


case class Future(
  override val security : Security,
  strike_price          : Double,
  due_date              : Double,
  risk_free_rate        : Double
) extends FutOpt(security, strike_price, due_date, risk_free_rate) {

  protected def value_at_expiration_time(S: Double) = S - strike_price

  def price(S0: Double, current_time: Double) =
    (S0 * math.exp(risk_free_rate * (due_date - current_time)) -
      strike_price) * math.exp(- risk_free_rate * (due_date - current_time))
}


/*
import Securities._
val S0     = 100.0;
  val K      = 100.0;
  val T      = 2.0;
  val stddev = 0.05;
  val r      = 0.03;
  val now    = 0.1;
  val s = VanillaSecurity(r, stddev);
  s.plot_chart(S0, 1.0, 2.0, 100)
*/


object OptionTest {
  val S0     = 100.0;
  val K      = 100.0;
  val T      = 2.0;
  val stddev = 0.05;
  val r      = 0.03;
  val now    = 0.1;

  def main(args: Array[String]) {
    val s = VanillaSecurity(r, stddev);
    //val s = FundamentalsSecurity0(r, stddev, 50, 30.0);
    val o = EuropeanCallOption(s, K, T, r);

    // Two ways of computing the current price of an option with execution
    // date T. The first is by simulation, the second by the closed-form
    // solution given by the Black-Scholes formula.
    val c  = o.estimate_price(S0, now, 100000, 100); // better make it 100000 it
    // val bs = o.BlackScholes(  S0, now); // only for European options
                                           // on VanillaSecurities

    println("PRICE ESTIMATE: " + c);
    // println("bs="+bs);

    // Distribution of current prices, calculated from many simulations...
    def f() = o.sample_price(S0, now).toInt;
    plot_distribution(o.toString, "price", f, 1000000);
    /*
      o.plot_payoff_profile(80, 120, 2.0);
      //o.plot_payoff_profile(80, 120, 1.5, 1000);

      // Option price forecast as we move closer to the execution date, assuming
      // that, at any point, the security price is exactly the strike price.
      // (at the execution date, the price will be zero).
      // This simulation is slower.
      o.plot_price(S0, now, T, 100, 10000);
    */
  }
} // end OptionTest

} // end package Securities



