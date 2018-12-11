package Simulation.SimLib.OB_Sims
import code._
import Simulation._
import Markets._
import Securities._
import Commodities._
import Timeseries._


class MarketMaker(security: Security,
                  shared: Simulation
                 ) extends SimO(shared) with SimpleSim {

  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val n = new MarketMaker(security, _shared);
    copy_state_to(n);
    n
  }

  /** Not to be used. */
  override def price(dummy: Commodity) = None

  /** Computes the cost break-even point if we add another to_price many
      units to our inventory. The result is reported per unit to price.

      Example: Suppose we hold 1 item and previously paid 10 for it.
      Now we consider buying another 1, and the income from selling two
      on the market would currently be 22. Then valuation returns 12.
  */
  protected def valuation(ob: OrderBook, to_price: Int) : Option[Double] = {
    if(! inventory.contains(ob.security)) init_inv(ob.security);
    val units = inventory(ob.security) + to_price;
    val cost  = inventory(ob.security) * inventory_avg_cost(ob.security);

    ob.value_of(units) match {
      case None => {
        println("MarketMaker.valuation: couldn't establish a price at all!!");
        None
      }
      case Some(income) => {
        println("MarketMaker  " + units + " " + income + " " + cost);
        Some((income - cost) / to_price)
      }
    }
  }

  /** replaces its orders in every time tick. */
  def action = __do {
    val ob = shared.chicago(security);

    ob.cancel_sell_order(_.trader == this);
    ob.cancel_buy_order( _.trader == this);

    val vol = 2;
    val bid0 = valuation(ob, vol);
    val ask0 = valuation(ob, -vol);

    println("MarketMaker action: " + bid0 + " " + ask0);

    if((ask0 != None) && (bid0 != None)) {
      val bid1 = bid0.get;
      val ask1 = ask0.get;
      val center   = (bid1 + ask1)/2;
      val myspread = math.abs(ask1 - bid1) * 0.9; // 0.9 loses money
        // unless there is a lot of trading in place

      val my_bid_price = center - myspread/2;
      val my_ask_price = center + myspread/2;

      println("MarketMaker: [" + my_bid_price + "; " + my_ask_price + "]");

      ob.enter_sell_order(OB_Line(
        Some(my_ask_price), shared.timer, vol, Some(1), this));
      ob.enter_buy_order(OB_Line(
        Some(my_bid_price), shared.timer, vol, Some(1), this));
    }
  }
}


class RandomTrader(security: Security, shared: Simulation) extends
  SimO(shared) with SimpleSim {

  /* FIXME: substitute ob. */
  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val n = new TrendFollower(security, _shared);
    copy_state_to(n);
    n
  }

  override def price(dummy: Commodity) = None

  def action = __do {
    import breeze.stats.distributions._;

    val ob = shared.chicago(security);

    if(GLOBAL.rnd.nextInt(2) == 0) {
      val p0 = ob.bid_price;
      val a = new Gaussian(p0, math.sqrt(p0)).sample(1)(0);
      val p = math.max(0, a);
      ob.enter_buy_order( OB_Line(Some(p), shared.timer, 1, Some(1), this));
    }
    else {
      val p0 = ob.ask_price.get;
      val p = math.max(0, new Gaussian(p0, math.sqrt(p0)).sample(1)(0));
      ob.enter_sell_order(OB_Line(Some(p), shared.timer, 1, Some(1), this));
    }
  }
}


class TrendFollower(security: Security, shared: Simulation) extends
  SimO(shared) with SimpleSim {

  var l = List[(Double, Double)]();

  /* FIXME: substitute ob. */
  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val n = new TrendFollower(security, _shared);
    copy_state_to(n);
    n.l = l;
    n
  }

  override def price(dummy: Commodity) = None

  def action = __do {
    //import breeze_ext._
    import breeze.linalg._
    import breeze.stats.regression._

    val ob = shared.chicago(security);

    l = (ob.bid_price, ob.ask_price.getOrElse(1.0/0)) :: l;

    if((l.length >= 5) && (l.length % 3 == 0))
    {
    val to_watch = l.map((t: (Double, Double)) => (t._2 + t._1)/2.0);
    val tw2 = to_watch.take(5).reverse;

    val x : Array[Double] = (1 to (tw2.size)).toArray.map(_.toDouble);
    val y = tw2.toArray;


    val features: DenseMatrix[Double] = DenseMatrix.horzcat(
      DenseMatrix.ones[Double](x.length, 1),
      DenseVector[Double](x).toDenseMatrix.t
    )
    val w = DenseVector[Double](y);
    val r = leastSquares(features, w);
 
    val Array(a, b) = r.coefficients.toArray;
    val result = (b,a);

/*
    val xy = x.zip(y);
    val start = DenseVector[Double](y(0), 0.0);
    val result = linear_regression(xy, start);
*/

    println("TrendFollower regression: " + " " + result._1 + " " + result._2);
    if(result._1 > 0) {
      println("Prices will rise!");
      ob.enter_buy_order( OB_Line(None, shared.timer, 1, Some(1), this));
    }
    else {
      println("Prices will fall!");
      ob.enter_sell_order(OB_Line(None, shared.timer, 1, Some(1), this));
    }
    }
  }
}


class Plotter(security: Security, shared: Simulation) extends
  SimO(shared) with SimpleSim {
  import breeze.linalg._
  import breeze.plot._

  var l = List[(Double, Double)]();
  val f = Figure("Orderbook Plotter");

  /* FIXME: substitute ob. */
  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val n = new Plotter(security, _shared);
    copy_state_to(n);
    n.l = l;
    n
  }

  override def price(dummy: Commodity) = None

  def action = __do {
    val ob = shared.chicago(security);

    l = (ob.bid_price, ob.ask_price.getOrElse(1.0/0)) :: l;

    if(shared.timer % 5 == 4) {
      def prep(ll: List[Double]) = ll.map((x: Double) => 
          if(x != 1.0/0) x else 0.0
        ).toArray;

      val bids = prep(l.map(_._1))/*.take(100)*/.reverse;
      val asks = prep(l.map(_._2))/*.take(100)*/.reverse;
      val len = bids.length

      println("Plotting.");
      val p = f.subplot(0);
      assert(l.length == shared.timer + 1);
      val x = DenseVector((/*(shared.timer - len + 1)*/
                           0 to shared.timer).toArray).map(_.toDouble);
      val y = DenseVector(bids);
      val z = DenseVector(asks);
      p += plot(x, y);
      p += plot(x, z);
      p.xlabel = "time";
      p.ylabel = "price";
      f.refresh;  // f.saveas("ob_chart.png")
    }
  }
}



/** Arbitrage between orderbooks of a commodity and its option */
class Arbitrageur(
  the_option: EuropeanCallOption,
  shared: Simulation
) extends SimO(shared) with SimpleSim {

  /* FIXME: substitute ob. */
  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val n = new Arbitrageur(the_option, _shared);
    copy_state_to(n);
    n
  }

  override def price(dummy: Commodity) = None

  def action = __do {
    val ob_o = shared.chicago(the_option);
    val ob_c = shared.chicago(the_option.security);

    if((shared.timer % 5 == 4) && (ob_c.ask_price != None)) {
/*
      (ob_c.security, ob_o.security) match {
        case (s1, EuropeanCallOption(s2, _, _, _)) if (s1 == s2) => {

          OptionArbitrage(s1, s2)
        }
        case (s1, s2) if (s1 == s2) => {
          // determine which one of the two orderbooks has higher liquidity. 

          CrossExchangeArbitrage(s1, s2)
        }
        case (s1, ForeignCurrencyTraded(s2) if (s1 == s2) => {
          // determine which one of the two orderbooks has higher liquidity.

          CrossCurrencyArbitrage(s1, s2)

          // find the orderbook of the currency exchange for the two relevant
          // currencies.
          XXX
        }
        case (...) => {
          TransitivityArbitrage:
          currency1/currency2 and currency2/currency3 => currency1/currency3

        }
      }

*/
      // val EuropeanCallOption(s, _, _, _) = ob_o.security;
      // assert(s == ob_c.security);

      val current_price_c = ob_c.ask_price.get;
      val o_val = the_option.estimate_price(
        current_price_c, shared.timer, 100, 100);

      println("Arbitrageur: the value of the option is " + o_val);

      ob_o.cancel_buy_order( _.trader == this);
      ob_o.cancel_sell_order(_.trader == this);

      val margin = 50;

      ob_o.enter_buy_order(
        OB_Line(Some(o_val - margin), shared.timer, 1, None, this));
      ob_o.enter_sell_order(
        OB_Line(Some(o_val + margin), shared.timer, 1, None, this));
    }
  }
}



class OBExample {
  import Simulation._
  import Markets._
  import Owner._

  def chicago_builder() = {
    for(c <- List(Flour, Wheat)) { //Securities.all_commodities) {
      simu.chicago += (c -> new OrderBook(c));

      val current_price = 1100.0; // depends on commodity

      for(i <- -2 to 2; j <- 1 to 2) {
        val p = current_price + i * 100;
        val t = j * 20;
        val co = new EuropeanCallOption(c, p, t, 0.03);
        simu.chicago += (co -> new OrderBook(co));
        val po = new EuropeanPutOption(c, p, t, 0.03);
        simu.chicago += (po -> new OrderBook(po));
      }
    }
  }

  val b    = new Owner;
  val s    = new Owner;
  val simu = new Simulation;
  chicago_builder();
  val opt  = EuropeanCallOption(Flour, 1100, 20, 0.03);
  val ob_c = simu.chicago(Flour);
  val ob_o = simu.chicago(opt);

  def buy(ob: OrderBook, price: Double, units: Int = 1) {
    ob.enter_buy_order(
      OB_Line(Some(price), simu.timer, units, None, b));
  }
  def sell(ob: OrderBook, price: Double, units: Int = 1) {
    ob.enter_sell_order(
      OB_Line(Some(price), simu.timer, units, None, s));
  }

  val mm      = new   MarketMaker(Flour, simu);
  val rt      = new  RandomTrader(Flour, simu);
  val tf      = new TrendFollower(Flour, simu);
  val arb     = new   Arbitrageur(opt, simu);
  val plotter = new       Plotter(Flour, simu);
  simu.init(List(mm/*, rt, tf*/, arb, plotter));

  sell(ob_c, 1300, 1000);
  sell(ob_c, 1200);
  buy(ob_c, 1000);
  buy(ob_c, 900, 1000);
  sell(ob_o, 200, 1000);
  buy(ob_o, 50, 1000);
}


/*
import Simulation.SimLib.OB_Sims._
val obe = new OBExample;
import obe._;
simu.run(1); ob_c.show(); ob_o.show(); mm.stat;
*/



