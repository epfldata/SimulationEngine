package Markets;
import Owner._;


trait MarketMatchingUtilities[L] {
  type mm_match_t = (Int,    L);
  // record:     (#units, seller)

  /** Returns (#units unmatched, matching).
      Assumes that the lines are sorted best-first.
      Used by SellersMarket.
  */
  protected def greedy_match(
    lines: List[L],
    get_units: L => Int,
    units: Int
  ) : (Int, List[mm_match_t])= {
    type state = (Int, List[mm_match_t]);
    val start_state = (units, List[mm_match_t]());
    lines.foldLeft(start_state)(
       (acc: state, line: L) => {
         val n = math.min(acc._1, get_units(line));
         if(n > 0) (acc._1 - n, (n, line) :: acc._2)
         else acc
       })
  }

  /** Returns (#units unmatched, price of matching). */
  protected def matching_price(
    lines: List[L],
    get_units: L => Int,
    get_price: L => Double,
    units: Int
  ) = {
    val (left_over, matching) = greedy_match(lines, get_units, units);

    (left_over, matching.map((t: mm_match_t) => t._1 * get_price(t._2)).sum)
  }

  /** Returns the pair (units left over, left over orderbook entries).
      By left-over stuff we mean things not consumed in the matching.
      Executes an action that supposedly changes/consumes some of the lines.
      Assumes that the lines are sorted best-first.
      Used by OrderBook.

      cond is not a filter but a condition for continuing (usually on price).
  */
  protected def greedy_match_execute(
        lines: List[L],
        get_units: L => Int,
        cond: L => Boolean,
        action: (L, Int) => Unit,
        modf: (L, Int) => L,
        units: Int
  ) : (Int, List[L]) = {
    lines match {
      case line :: l if cond(line) => {
        val n = math.min(units, get_units(line));
        action(line, n);

        if(get_units(line) > n) (0, modf(line, n)::l) // we are done
        else greedy_match_execute(l, get_units, cond, action, modf, units - n);
      }
      case l => (units, l)
    }
  }
}


trait MarketSelling {
  // returns (price for k items, k) where k <= units and k is available
  def ask_price(units: Int) : (Double, Int);
  def ask_price() : Option[Double];

  // #units currently offered on the market
  //def offered(): Int;

  def market_buy_order_now(time: Int, buyer: Owner, units: Int) : Int;
  def  limit_buy_order_now(time: Int, buyer: Owner, units: Int) : Int;
}


