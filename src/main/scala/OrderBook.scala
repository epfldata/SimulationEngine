package Markets;
import Owner._;
import Securities._;


/** An order; an entry line in an order book. Does not specify whether it's
    a buy or sell order, but `OB_Line` can be used for either.

    @param limit  If `limit == None`, it's a market order;
                  otherwise it's a limit order and `limit` is of the form
                  `Some(limit_price)`.
*/
case class OB_Line(limit: Option[Double], // Some(price) or None
                   time_inserted: Int,
                   units: Int,
                   expires: Option[Int],
                   trader: Owner) {
  /** For tabular display of an order book. */
  def format : String = {
      val exp = expires match {
        case Some(_) => "2016-10-08"
        case None    => "          "
      };
      "%10.2f %4d %3d ".format(limit.get, time_inserted, units) + exp + " " +
        trader.toString.reverse.take(20).reverse
  }
  def deduct_units(n: Int) =
    OB_Line(limit, time_inserted, units - n, expires, trader)

  def set_limit(price: Double) =
    OB_Line(Some(price), time_inserted, units, expires, trader)
}


/** There are no built-in market makers (implement them as sims).

    Note: A player may trade with himself.

    FIXME: market orders are not handled correctly (see enter_..._order).
*/
class OrderBook(
  val security: Security
) extends /* MarketSelling with MarketBuying with
*/ MarketMatchingUtilities[OB_Line] {

  var bid_orderbook = List[OB_Line]()
  var ask_orderbook = List[OB_Line]()

  def mycopy() = {
    val ob = new OrderBook(security);
    ob.bid_orderbook = bid_orderbook;
    ob.ask_orderbook = ask_orderbook;
    ob
  }

  private def ord = (line: OB_Line) => (line.limit.get, line.time_inserted)
  private def modf(line: OB_Line, n: Int) = line.deduct_units(n)

  /** FIXME: for market orders, it uses the current ask_price;
      if there are not enough units on
      offer at that price, it enters a limit order for the rest.
  */
  def enter_buy_order(o: OB_Line) {
    val price : Double = o.limit.getOrElse(ask_price.getOrElse(0));

    def action(t: OB_Line, n: Int) {
      t.trader.atomic_sell_to(o.trader, security, n, t.limit.get);
    }

    val (left_over, aob) = greedy_match_execute(ask_orderbook,
      (_:OB_Line).units, ((_:OB_Line).limit.get <= price),
      action, modf, o.units);

    ask_orderbook = aob;

    if(left_over > 0) {
      implicit object ReverseDoubleOrdering extends Ordering[Double] {
        def compare(x: Double, y: Double) = -1 * x.compareTo(y)
      }

      // largest limit price first; to break ties, smallest time first.
      bid_orderbook =
          (o.deduct_units(o.units - left_over).set_limit(price) ::
            bid_orderbook).sorted(Ordering[(Double, Int)].on[OB_Line](ord));
    }
  }

  /** cancel if cond is true */
  def cancel_buy_order(cond: OB_Line => Boolean) {
    bid_orderbook = bid_orderbook.filter(! cond(_))
  }

  /** FIXME: for market orders, it uses the bid_price;
      if there are not enough units
      bid for at that price, it enters a limit order for the rest.
  */
  def enter_sell_order(o: OB_Line) {
    val price = o.limit.getOrElse(bid_price);

    def action(t: OB_Line, n: Int) {
      o.trader.atomic_sell_to(t.trader, security, n, t.limit.get);
    }

    val (left_over, bob) = greedy_match_execute(bid_orderbook,
      (_:OB_Line).units, ((_:OB_Line).limit.get >= price),
      action, modf, o.units);

    bid_orderbook = bob;

    // smallest limit price first; to break ties, smallest time first.
    if(left_over > 0)
      ask_orderbook =
          (o.deduct_units(o.units - left_over).set_limit(price) ::
            ask_orderbook).sorted(Ordering[(Double, Int)].on[OB_Line](ord));
  }

  /** cancel if cond is true */
  def cancel_sell_order(cond: OB_Line => Boolean) {
    ask_orderbook = ask_orderbook.filter(! cond(_))
  }

  /** Deletes expired orders. */
  def purge(time: Int) {
    bid_orderbook = bid_orderbook.filter(_.expires match {
      case Some(exp_time) if (exp_time < time) => false
      case _ => true });
  }

  def bid_vol   = bid_orderbook.map(x => x.units).sum;
  def bid_vwap  = if(bid_orderbook.length > 0)
                    bid_orderbook.map(x => x.limit.get * x.units).sum / bid_vol
                  else 0.0;
  def bid_price = if(bid_orderbook.length > 0) bid_orderbook.head.limit.get
                  else 0;

  /** returns (#units unmatched, total price) */
  def bid_price(units: Int) =
    matching_price(bid_orderbook,
      (_:OB_Line).units, (_:OB_Line).limit.get, units)

  def ask_vol   = ask_orderbook.map(x => x.units).sum;
  def ask_vwap  = ask_orderbook.map(x => x.limit.get * x.units).sum / ask_vol
  def ask_price = if(ask_orderbook.length > 0) ask_orderbook.head.limit
                  else None

  /** returns (#units unmatched, total price) */
  def ask_price(units: Int) =
    matching_price(ask_orderbook, (_:OB_Line).units, (_:OB_Line).limit.get,
                   units)

  def spread = ask_price.getOrElse(1.0/0) - bid_price;

  /** Income from/cost of closing a position of size units.

      Units may be negative -- a short position is allowed and will have a
      negative value.
  */
  def value_of(units: Int) : Option[Double] = {
    val (left_over, p) =
      if(units < 0) ask_price(-units); // need to buy back
      else          bid_price( units); // need to sell off

    if(units == 0)
      Some(0.0)
    else if(math.abs(units) == left_over)
      None
    else
      Some(p * units / (math.abs(units) - left_over))
      // Note: units here is important for the sign.
  }

  def show(len: Int = 3) = {
    println("----------------------------------------------------");
    println("     Price Time   # Expires    Trader");
    println("(%9.2f)    (%3d)".format(ask_vwap, ask_vol));
    if(ask_orderbook.length > len) println("       ...");
    for(o <- ask_orderbook.take(len).reverse) println(o.format);
    println("  >                                                < " +
            "spread = " + spread);
    for(o <- bid_orderbook.take(len)) println(o.format);
    if(bid_orderbook.length > len) println("       ...");
    println("(%9.2f)    (%3d)".format(bid_vwap, bid_vol));
    println("----------------------------------------------------");
  }
}


