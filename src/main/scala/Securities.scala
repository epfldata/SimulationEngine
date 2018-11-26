package Securities {
import breeze.stats.distributions._


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


/** A simple model of price development: The security appreciates at the
    risk-free rate `r`, with volatility `stddev`.

    This is the model of securities used in the Black-Scholes model.

    @param r      risk-free rate of appreciation
    @param stddev volatility / standard deviation of the security
                  in one unit of time
*/
case class VanillaSecurity(r: Double, val stddev: Double
) extends Security {
  /** The price of the security `t` time steps from now.

      Does an approximation of Brownian motion.

      @param S0           current price of security
      @param current_time now
      @param goal_time    future time at which the price is to be estimated.
      @param resolution   The number of steps into which the time to
                          process (`goal_time - current_time`) is split up.
  */
  def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double = {
    def Nsample(mu: Double, sigma: Double) = (Gaussian(mu, sigma).sample(1))(0)

    assert(current_time <= goal_time);

    var S       = S0;
    val t       = goal_time - current_time; // look this far into the future
    val inc     = t / resolution;           // time step size
    val stddev2 = stddev * math.sqrt(inc);  // stddev in inc units of time

    assert(resolution >= 1);
    assert(t >= 0);

    for(_ <- 1 to resolution)
      S = Nsample(S, S * stddev2); // * math.exp(r * inc);

    S * math.exp(r * t)
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



/** There is only one reference security, so we can plot a payoff profile
    relative to it.
*/
abstract class SingleSecurityDerivative(
  val security : Security
) extends Security {

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

  def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double = {
    securities.map(sec => sec.sample_future_price(
      S0, current_time, goal_time, resolution)).sum
  }

  /** Not implemented. */
  def stddev = { assert(false); 0.0 }
}


case class Hedge(
  securities : List[SingleSecurityDerivative]
) extends Portfolio(securities) {

//  for(sec <- securities.tail) assert(securities.head.security == sec.security);

  def plot_payoff_profile(S_from: Double, S_to: Double, current_time : Double,
    n: Int = 1
  ) {
    val x = (for(k <- 1 to 100) yield {
      S_from + (S_to - S_from) * k / 100;
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


case class Short(
  override val security : Security
) extends SingleSecurityDerivative(security) {

  def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double = {
    val S = security.sample_future_price(
      S0, current_time, goal_time, resolution);
    -S
  }

  def stddev = security.stddev
}


case class Times(
  override val security : Security,
  n: Int
) extends SingleSecurityDerivative(security) {

  def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double = {
    val S = security.sample_future_price(
      S0, current_time, goal_time, resolution);
    S * n
  }

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

  /** Value at expiration time of the derivative as a function of the
      price `S` of the underlying security at expiration time.
  */
  protected def value_at_expiration_time(S: Double) : Double

  /** Uses protected abstract function `value_at_expiration_time`, which
      is specific to the derivative.

    @param S0            current price
    @param current_time  must not be greater than `due_date`.
    @param goal_time     may be greater or smaller than `due_date`.
    @param resolution    used for sampling future price of underlying
                         security.
  */
  def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double = {
    assert(current_time <= due_date);

    // the price of the security at expiration time
    val S = security.sample_future_price(
      S0, current_time, due_date, resolution);

    value_at_expiration_time(S) *
      math.exp(risk_free_rate * (goal_time - due_date))
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

  /** Compute the current price of the option using the Black-Scholes model.

      @param S0 security price
  */
  def BlackScholes(S0: Double, current_time : Double) = {
    val sec    = security.asInstanceOf[VanillaSecurity];
    val r      = sec.r;                   // risk-free interest rate
    val stddev = sec.stddev;              // standard deviation of the security
    val K      = strike_price;            // option strike price
    val T      = due_date - current_time; // time to expiration

    assert(T >= 0);

    def N(d: Double) = Gaussian(0, 1).probability(-1.0/0, d)

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


} // end package Securities




package object Securities {

  val Wheat       = Commodity("wheat")
  val Flour       = Commodity("flour")
  val Land        = Commodity("land")
  val MovieTicket = Commodity("ticket")
  val Beef        = Commodity("beef")
  val Burger      = Commodity("burger")

  val all_commodities = List(Wheat, Flour, Land, MovieTicket, Beef, Burger);


  def expectation(sample: () => Double, n: Int) : Double = {
    (for(_ <- 1 to n) yield sample()).sum / n
  }

  def compute_standard_deviation(
    mean: Double, sample: () => Double, n: Int) =
    math.sqrt((for(_ <- 1 to n) yield {
      val d = mean - sample();
      d*d
    }).sum) / (n-1)

  /** According to a book, a collar is something else, but they are WRONG. */
  def Collar(s: Security, K1: Double, K2: Double, T: Double, r: Double) =
    Hedge(List(
      Short(EuropeanCallOption(s, K2, T, r)),
            EuropeanCallOption(s, K1, T, r)
  ))


  object OptionTest {
    //import Securities._;
    val S0     = 100.0;
    val K      = 100.0;
    val T      = 2.0;
    val stddev = 0.05;
    val r      = 0.03;
    val now    = 0.1;

    val s = VanillaSecurity(r, stddev);

    val o = EuropeanCallOption(s, K, T, r);

    val c  = o.estimate_price(S0, now, 1000, 100);
    val bs = o.BlackScholes(  S0, now);

    o.plot_distribution(S0, now, 1000000);
    //o.plot_price(S0, now, T, 10000);
    o.plot_payoff_profile(80, 120, 2.0);

    Collar(s, 90, 110, T, r).plot_payoff_profile(80, 120, 2.0);

    val oo = EuropeanCallOption(o, 5.0, 1.5, r);
    oo.plot_payoff_profile(90, 120, 1.5, 10);
  }

} // end package object Securities




