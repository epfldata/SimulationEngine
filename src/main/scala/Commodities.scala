package Securities

package Commodities {

case class Commodity(name: String) extends Security {
  def compute_time_series(
    S0           : Double, // start_price
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Array[Double] = {
    (0 to 100).map(_ => S0).toArray
  }

  /** Assumes price remains constant. */
  override def sample_future_price(
    S0           : Double,
    current_time : Double,
    goal_time    : Double,
    resolution   : Int = 1
  ) : Double = S0

  def stddev = 0.0
}

} // end package Commodities



package object Commodities {
  val Wheat       = Commodity("wheat")
  val Flour       = Commodity("flour")
  val Land        = Commodity("land")
  val MovieTicket = Commodity("ticket")
  val Beef        = Commodity("beef")
  val Burger      = Commodity("burger")

  val all_commodities = List(Wheat, Flour, Land, MovieTicket, Beef, Burger);
}

