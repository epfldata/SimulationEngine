package Securities


/** an investor currently "belongs" to just one security.
*/
abstract class Investor {
  /** returns 1 (buy), -1 (sell), or 0 (do nothing) */
  def act(current_price: Double): Double
}


/** The investor's valuation is not changed in act(), it is currently set
    from the outside.
*/
case class ValueInvestor(
  initial_val: Double,
  eagerness:    Double // to do arbitrage between the current price
                       // and one's own valuation
) extends Investor {
  private val rnd = scala.util.Random
  var valuation = initial_val // the own valuation

  def act(current_price: Double): Double = {
    // decide whether to trade on the perception that the current price
    // is off the true value.
    val laziness = (1.0 / eagerness).toInt;
    val now = (rnd.nextInt % laziness) == 0;
    if(now) math.signum(valuation - current_price) else 0
  }
}

case class TechnicalTrader(laziness: Int, sensitivity: Double
) extends Investor {
  private var rrobin = 0;
  private val mem = new Array[Double](10); // buffer for the prices of the
                                           // last 10 time ticks.

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
  val num_vplayers = 10;
  val num_tplayers = 22;

  // todo: currently not used.
  val delay_p = 0.4; // probability that, after a fundamental event, the
                     // value investor takes
                     // at least another tick until realizing that the event
                     // took place.

  def compute_time_series(
    S0           : Double, // start_price
    current_time : Double, // ignored
    goal_time    : Double, // ignored
    resolution   : Int = 1
  ) : Array[Double] = {
    assert(resolution >= 1);


    // initialization
    val vplayers = (for(p <- 1 to num_vplayers) yield {
      val init_val  = Nsample(S0, S0 * 0.05);
      val eagerness = Nsample(0.1, 0.01);
      new ValueInvestor(init_val, eagerness)
    }).toList

    val players: List[Investor] = vplayers ++ (1 to num_tplayers).map(_ => {
      new TechnicalTrader(Nsample(4, 1).toInt, Nsample(0.1, 0.05))
    }).toList;

    val S = new Array[Double](resolution + 1); // time series
    S(0) = S0;


    // moving forward in time
    for(i <- 1 to resolution) {
      if(i == fu_event_time) {
        for (p <- vplayers) {
          // update the player's valuation of the security
          p.valuation = (p.valuation + fu_event_magnitude) * Nsample(1, 0.1);
        }
      }

      // very simple market mechanics -- price moves if demand and supply
      // are imbalanced.
      var m = 0.0; // matchmaking balance
      for (p <- players) { m += p.act(S(i-1)); }
      S(i) = S(i-1) * (1 + m / 200);
    }

    S
  }
}


object Goo {
  def main(args: Array[String]) {
    val s = FundamentalsSecurity();
    s.plot_chart(100.0, 1, 2, 2000)
  }
}


