package ecosim.simulation

import ecosim.global
import ecosim.code.{__do, __forever, __wait}
import ecosim.commodities.Commodities.{Burger, Commodity, Flour, MovieTicket}

class Person(
    val shared: Simulation,
    val active: Boolean,
    var happiness: Int = 0, // pursuit of it
    var log: List[String] = List()
) extends SimO(shared) {

  private val properties: Map[Commodity, Map[String, Int]] =
    Map((Flour -> Map("calories" -> 100)), (Burger -> Map("calories" -> 500)))
  private val foodstuffs = List(Flour, Burger)

  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]): Person = {
    val p = new Person(_shared, active, happiness, log)
    copy_state_to(p)
    p
  }

  override def stat {
    print("(Person@" + happiness + " " + capital / 100 + ")  ")
  }

  protected def algo = __forever(
    __do {
      if (active) {
        val food =
          if (global.rnd.nextInt(2) == 0) Flour
          else Burger

        happiness -= 100; // hunger

        // assert(market(food).is_at_time(shared.timer));
        shared.market(food).market_buy_order_now(shared.timer, this, 1)
        // needs to eat
        if (available(food) >= 1) consume(food, 1)
        shared
          .market(MovieTicket)
          .market_buy_order_now(shared.timer, this, 1)
        // wants entertainment
        if (available(MovieTicket) >= 1)
          consume(MovieTicket, 1)

        // shared.market("miete").market_buy_order_now(shared.timer, this, 1);
      }
    },
    __wait(1)
  )

  private def consume(consumable: Commodity, units: Int) {
    assert(available(consumable) >= units)
    happiness += units * expected_enjoyment(consumable)
    destroy(consumable, units)
    log = (units + "*" + consumable + "@" + shared.timer) :: log
  }

  // TODO: factor in bounded rationality: far-off rewards are to be discounted
  private def expected_enjoyment(item: Commodity): Int = {
    item match {
      case MovieTicket => 1
      case _ if properties(item).contains("calories") =>
        properties(item)("calories")
      case _ => 0
    }
  }
}
