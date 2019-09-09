import org.scalatest._

class OrderBookSpec extends FlatSpec {
  import ecosim.markets._
  import ecosim.owner._
  import ecosim.securities._
  import Commodities._

  "Time ordering" should "be respected for asks" in {
    val s = new Owner()
    val b = new Owner()
    val ob = new OrderBook(Flour)
    ob.enter_sell_order(OB_Line(Some(1000), 2, 6, None, s))
    ob.enter_sell_order(OB_Line(Some(2000), 0, 10, None, s))
    ob.enter_sell_order(OB_Line(Some(1000), 0, 4, None, s))
    ob.enter_sell_order(OB_Line(Some(1000), 1, 2, None, s))
    ob.enter_sell_order(OB_Line(Some(1000), 3, 8, None, s))
    assert(
      ob.ask_orderbook ==
        List(
          OB_Line(Some(1000.0), 0, 4, None, s),
          OB_Line(Some(1000.0), 1, 2, None, s),
          OB_Line(Some(1000.0), 2, 6, None, s),
          OB_Line(Some(1000.0), 3, 8, None, s),
          OB_Line(Some(2000.0), 0, 10, None, s)
        ))
    ob.enter_buy_order(OB_Line(Some(1100), 2, 5, None, b))
    assert(
      ob.ask_orderbook ==
        List(OB_Line(Some(1000.0), 1, 1, None, s),
             OB_Line(Some(1000.0), 2, 6, None, s),
             OB_Line(Some(1000.0), 3, 8, None, s),
             OB_Line(Some(2000.0), 0, 10, None, s)))
    assert(
      s.inventory_to_string ==
        "ArrayBuffer(Commodity(flour) -> -5@10)")
    assert(
      b.inventory_to_string ==
        "ArrayBuffer(Commodity(flour) -> 5@10)")
  }

  "Time ordering" should "be respected for bids" in {
    val s = new Owner()
    val b = new Owner()
    val ob = new OrderBook(Flour)
    ob.enter_buy_order(OB_Line(Some(1000), 1, 2, None, b))
    ob.enter_buy_order(OB_Line(Some(2000), 2, 12, None, b))
    ob.enter_buy_order(OB_Line(Some(1000), 2, 6, None, b))
    ob.enter_buy_order(OB_Line(Some(2000), 0, 10, None, b))
    ob.enter_buy_order(OB_Line(Some(1000), 0, 4, None, b))
    ob.enter_buy_order(OB_Line(Some(1000), 3, 8, None, b))
    assert(
      ob.bid_orderbook ==
        List(
          OB_Line(Some(2000.0), 0, 10, None, b),
          OB_Line(Some(2000.0), 2, 12, None, b),
          OB_Line(Some(1000.0), 0, 4, None, b),
          OB_Line(Some(1000.0), 1, 2, None, b),
          OB_Line(Some(1000.0), 2, 6, None, b),
          OB_Line(Some(1000.0), 3, 8, None, b)
        ))
    ob.enter_sell_order(OB_Line(Some(1100), 2, 11, None, s))
    assert(
      ob.bid_orderbook ==
        List(
          OB_Line(Some(2000.0), 2, 11, None, b),
          OB_Line(Some(1000.0), 0, 4, None, b),
          OB_Line(Some(1000.0), 1, 2, None, b),
          OB_Line(Some(1000.0), 2, 6, None, b),
          OB_Line(Some(1000.0), 3, 8, None, b)
        ))
  }
}
