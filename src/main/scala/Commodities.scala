package commodities

import Securities.Security

object Commodities {
  val Wheat = Commodity("wheat")
  val Flour = Commodity("flour")
  val Land = Commodity("land")
  val MovieTicket = Commodity("ticket")
  val Beef = Commodity("beef")
  val Burger = Commodity("burger")

  val all_commodities = List(Wheat, Flour, Land, MovieTicket, Beef, Burger);

  // TODO: Just made source code compiling again, may not work correctly
  /**
    *
    * @param name of Commodity
    */
  case class Commodity(name: String) extends Security {

    /** Assumes price remains constant. */
    override def sample_future_price(
        S0: Double,
        current_time: Double,
        goal_time: Double,
        resolution: Int = 1
    ): Double = S0

    override def stddev = 0.0

    override def compute_time_series(S0: Double,
                                     current_time: Double,
                                     goal_time: Double,
                                     resolution: Int): Array[Double] = Array(S0)
  }
}
