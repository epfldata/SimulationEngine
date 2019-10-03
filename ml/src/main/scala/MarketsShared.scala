package Markets;
import Owner._

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
}


trait MarketSelling {
  // returns (price for k items, k) where k <= units and k is available
  def ask_price(units: Int) : (Double, Int);

  def market_buy_order_now(time: Int, buyer: Owner, units: Int) : Int;
}


