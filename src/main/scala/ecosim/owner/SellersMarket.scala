package ecosim

package owner {
  import ecosim.commodities.Commodities._
  import ecosim.timeseries.LogList

  case class SalesRecord(
      buyer: Owner,
      matching: List[(Owner, Int, Int)], // (seller, units, unit_price)
      num_ordered: Int,
      num_sold: Int,
      total_price: Int
  ) {
    def missed_sales(at_price: Double): Int = {
      if (total_price > num_sold * at_price)
        num_ordered // we would have made a cheaper offer
      else num_ordered - num_sold
      // even though we are expensive, we make the deal
      // because it's a market order
    }
  }

  class Seller extends Owner {
    var order_history: LogList[SalesRecord] = new LogList[SalesRecord]

    /** A partial sell, using the price from this[Seller].price.

      Note that this corresponds to partial_sell_to in Owner, not sell_to.
      Writes a SalesRecord to order_history.
      TODO: give all or nothing option.
      */
    def sell_to(time: Int, buyer: Owner, item: Commodity, units: Int): Int = {
      price(item) match {
        case (Some(p)) => {
          val units_sold = partial_sell_to(buyer, item, units, p)
          order_history.add(time,
                            SalesRecord(buyer,
                                        List(),
                                        units,
                                        units_sold,
                                        (units_sold * p).toInt))
          // create entry even if nothing is sold
          units_sold // TODO: also return price (unit or total?)
        }
        case None => 0
      }
    }

    // only one global price, not for speculators.
    // override to offer stuff.
    def price(item: Commodity): Option[Double] = None

    protected def copy_state_to(_to: Seller) {
      //println("Seller.copy_state_to: " + this);
      super.copy_state_to(_to)
      _to.order_history = order_history.copy()
    }
  }

} // package Owner

/*
class OwnerOnMarket extends Owner {
  // this assets calculation takes market prices into account, where available.
  def assets(market: collection.mutable.Map[Commodity, SellersMarket]) : Int = {
    var write_off = 0;
    math.max(0, capital) +
    (for((item, units) <- inventory) yield {
      val cost = units * inventory_avg_cost(item).toInt;
      market(item).ask_price() match {
        case Some(p) => {
          val market_value = (units * p).toInt
          write_off += cost - market_value;
          market_value
        }
        case None    => cost
      }
    }).sum
    // TODO: could also return math.max(0, write_off)
  }
}
 */
