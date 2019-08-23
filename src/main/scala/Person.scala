package Simulation
import GLOBAL.{print, println}
import Securities._
import code._

class Person(
  val shared: Simulation,
  val active: Boolean,
  val male: Boolean = GLOBAL.rnd.nextBoolean(),
  var happiness : Int = 0, // pursuit of it
  var log : List[String] = List()
) extends SimO(shared) {

  private val distr = shared.distributions(this)
  // between 1 and 10
  val education = math.max(1, math.min(10, distr("edu").sample.round.toInt))
  val bonusSalary = distr("bonusSal").sample.round.toInt * education
  private val (buyProbs, consumeProbs) = {
    def softmax(list: List[Double]): List[Double] = {
      val exp = list.map(math.exp)
      val sum = exp.sum
      exp.map(_ / sum)
    }
    def mapValuesSoftmax(containString: String): List[(Commodity, Double)] = {
      def getItemByParam(paramName: String) =
        Securities.all_commodities.find(c => paramName.toLowerCase().contains(c.name))
      val filtered = shared.params
        .filter(t => t._1.contains(containString) && getItemByParam(t._1).isDefined)
        .map{case (name, value) => (getItemByParam(name).get, value)}
        .toList
      filtered.zip(softmax(filtered.map(_._2))).map(t => (t._1._1, t._2))
    }

    (mapValuesSoftmax("buy"), mapValuesSoftmax("consume"))
  }

  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val p = new Person(_shared, active, male, happiness, log);
    copy_state_to(p);
    p
  }

  private def expected_enjoyment(item: Commodity): Int =
    distr("enjoy" + item.name.capitalize).sample.round.toInt

  private def consume(consumable: Commodity, units: Int) {
    assert(available(consumable) >= units);
    happiness += units * expected_enjoyment(consumable)
    destroy(consumable, units);
    log = (units + "*" + consumable + "@" + shared.timer) :: log;
  }

  protected def algo = __forever(
    __do{
      if(active) {
        val buy = getNextCommodity(buyProbs)
        val buyUnits = math.max(1, distr("buy").sample().round.toInt)
        println("Food units: " + buyUnits)
        shared.market(buy).market_buy_order_now(shared.timer, this, buyUnits);

        val consumable = getNextCommodity(consumeProbs)
        val consumeUnits = math.min(available(consumable), distr("consume").sample.round.toInt)
        consume(consumable, consumeUnits)
      }
    },
    __wait(1)
  );

  override def stat {
    print("(Person@" + happiness + " " + capital/100 + ")  ");
  }

  private def getNextCommodity(probs: List[(Commodity, Double)]): Commodity = {
    val prob = GLOBAL.rnd.nextDouble()
    var cumul = 0.0
    for ((commodity, value) <- probs) {
      if (prob < cumul + value) {
        return commodity
      }
      cumul += value
    }
    probs.last._1
  }
}


