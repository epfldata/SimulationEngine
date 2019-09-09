package ecosim.markets

import ecosim.owner.{Owner, _}
import ecosim.securities.Commodities._
import ecosim.timeseries.LogList

/**
  Not all sellers registered here may be selling at a time.
   One can buy as much of the inventory as the seller has.
   Each seller has one price for all its ware.
   Orders are fulfilled immediately,
     partially if not enough ware is on the market.
   Orders may be fulfilled using inventories of multiple sellers.
   Fulfillment is at best prices.
  */
class SellersMarket(commodity: Commodity)
    extends MarketSelling
    with MarketMatchingUtilities[Seller] {
  var sellers: List[Seller] = List[Seller]()
  var order_history = new LogList[SalesRecord]

  def copy_state_to(other: SellersMarket, old2new: Seller => Seller) {
    other.order_history = order_history.copy()
    other.sellers = sellers.map(old2new(_))
  }

  def add_seller(s: Seller) { sellers = s :: sellers; }

  def ask_price(): Option[Double] = {
    val (p, l) = ask_price(1)
    if (l == 0) None else Some(p)
  }

  def ask_price(units: Int): (Double, Int) = {
    val (left_over, l) = best_match(units, null)
    (compute_price(l), units - left_over)
  }

  /** execute immediately, partial fulfillment possible. */
  def market_buy_order_now(time: Int, buyer: Owner, units: Int): Int = {
    //println("SellersMarket.market_buy_order_now " + this);
    val (left_over, l) = best_match(units, buyer)
    //println("Buying " + units + " on market: " + l + " " + left_over);
    val p = compute_price(l); // can't reorder this line and the next
    for ((u, s) <- l) { s.sell_to(time, buyer, commodity, u) }
    val sold = units - left_over

    order_history.add(time, SalesRecord(buyer, List(), units, sold, p.toInt))
    left_over
  }

  /** returns (#unmatched, List[(#matched with this seller, seller)]).  */
  private def best_match(units: Int,
                         exclude: Owner): (Int, List[(Int, Seller)]) = {
    //println("best_match sellers: " + sellers);

    // lowest price first
    val asks = sellers
      .filter((s: Seller) => s.price(commodity).isDefined && (s != exclude))
      .sorted(Ordering.by[Seller, Double]((s: Seller) =>
        s.price(commodity).get))

    greedy_match(asks, ((s: Seller) => s.available(commodity)), units)
  }

  private def compute_price(l: List[(Int, Seller)]) =
    l.map((t: (Int, Seller)) =>
        t._2.price(commodity).getOrElse(1.0 / 0) * t._1)
      .sum

  /** not implemented */
  def limit_buy_order_now(time: Int, buyer: Owner, units: Int): Int = {
    assert(false)
    0
  }
}
