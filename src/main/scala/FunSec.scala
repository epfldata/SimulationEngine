package Securities

object Glob {
  val rnd = scala.util.Random
}
import Glob._


/** an investor currently "belongs" to just one security.
*/
abstract class Investor {
  /** returns 1 (buy), -1 (sell), or 0 (do nothing) */
  def act(current_price: Double): Double
}


case class ValueInvestor(
  initial_val: Double,
  eagerness:   Double // to do arbitrage between the current price
                      // and one's own valuation
) extends Investor {
  private var valuation = initial_val // the own valuation
  private var event_queue = List[(Int, Double)]()

  def act(current_price: Double): Double = {
    // process the event queue
    for(t <- event_queue) if(t._1 == 0) valuation += t._2;
    event_queue = event_queue.map(t => (t._1 - 1, t._2)).filter(t => t._1 >= 0);

    // decide whether to trade on the perception that the current price
    // is off the true value.
    val laziness = (1.0 / eagerness).toInt;
    val now = rnd.nextInt(laziness) == 0;
    if(now) math.signum(valuation - current_price) else 0
  }

  /** notify the value investor of a fundamental event.
      update the player's valuation of the security.
  */
  def event(magnitude: Double) {
    val my_mag = magnitude * (1 + 0.1 * rnd.nextGaussian());

    // set time delay for how many time ticks from now the valuation change
    // is to be performed.
    val when   = rnd.nextInt(50);
    println(when, my_mag);
    event_queue = (when, my_mag) :: event_queue;
  }
}

case class TechnicalTrader(
  laziness: Int,
  sensitivity: Double,
  init_price: Double
) extends Investor {
  private var rrobin = 0;
  private val mem = new Array[Double](10); // buffer for the prices of the
                                           // last 10 time ticks.
  for(i <- 0 to 9) mem(i) = init_price;

  println("new TechnicalTrader("+laziness+", "+sensitivity+", "+init_price+")");

  // This is written for readability, not efficiency.
  def act(current_price: Double) = {
    val avg = mem.sum/10;
    var r = 0;

    if(rrobin % laziness == 0) {
      if(current_price > (1 + sensitivity) * avg) r =  1;
      if(current_price < (1 - sensitivity) * avg) r = -1;
    }

    mem(rrobin % 10) = current_price;
    rrobin += 1;

    r
  }
}

case class RandomTrader() extends Investor {
  def act(current_price: Double) = rnd.nextInt(3) - 1
}


/**
  Note: this Security does NOT behave like the VanillaSecurity in the absence
  of fundamental events. The effect of the risk-free-rate has to be explicitly
  forced in the form of fundamental events (the company increasing its
  value over time and reporting it at discrete times -- e.g. by releasing
  quarterly sales/asset numbers).
*/
case class FundamentalsSecurity() extends Security {
  val stddev = 0.0 // dummy

  // hardwiring exactly one fundamental event
  val fu_event_time = 500; // time tick in 1 .. resolution
  val fu_event_magnitude = 20.0;
  val num_vplayers = 0;
  val num_tplayers = 0; //22;
  val num_rplayers = 1; //5;

  def compute_time_series(
    S0           : Double, // start_price
    current_time : Double, // ignored
    goal_time    : Double, // ignored
    resolution   : Int = 1
  ) : Array[Double] = {
    assert(resolution >= 1);


    // initialization
    val vplayers = (1 to num_vplayers).map(_ => {
      val init_val  = S0 + S0 * 0.05 * rnd.nextGaussian();
      val eagerness = 0.1 + 0.01 * rnd.nextGaussian();
      new ValueInvestor(init_val, eagerness)
    }).toList

    val players: List[Investor] =
      vplayers ++
      (1 to num_tplayers).map(_ => {
        new TechnicalTrader(1 + rnd.nextInt(5),
                            0.1 + rnd.nextGaussian() * 0.05, S0)
      }).toList ++
      (1 to num_rplayers).map(_ => new RandomTrader()).toList;

    val S = new Array[Double](resolution + 1); // time series
    S(0) = S0;


    // moving forward in time
    for(i <- 1 to resolution) {
      if(i == fu_event_time) {
        for (p <- vplayers) {
          // update the player's valuation of the security
          p.event(fu_event_magnitude);
        }
      }

      // very simple market mechanics -- price moves if demand and supply
      // are imbalanced.
      var m = 0.0; // matchmaking balance
      for (p <- players) { m += p.act(S(i-1)); }
      S(i) = S(i-1) * (1.0 + m / 200.0);
    }

    S
  }
}


object FunRun {
  def main(args: Array[String]) {
    val s = FundamentalsSecurity();
    s.plot_chart(100.0, 1, 2, 2000)
  }
}


