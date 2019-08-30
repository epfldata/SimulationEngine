package Simulation
import GLOBAL.{print, println}
import Owner.Owner
import Securities._
import bo.Main
import code._


class Person(
  val shared: Simulation,
  val active: Boolean,
  val male: Boolean = GLOBAL.rnd.nextBoolean(),
  var _happiness : Int = 0, // pursuit of it
  var log : List[String] = List()
) extends SimO(shared) {

  variables("happiness") = _happiness
  variables("salary") = 0.0

  private val distr = shared.distributions(this)
  // between 1 and 10
  val education = math.max(1, math.min(10, distr("edu").sample.round.toInt))
  private val (buyProbs, consumeProbs: Map[Commodity, Double]) = {
    def softmax(list: List[Double]): List[Double] = {
      val exp = list.map(math.exp)
      val sum = exp.sum
      exp.map(_ / sum)
    }
    def mapValuesSoftmax(containString: String): List[(Commodity, Double)] = {
      def getItemByParam(paramName: String) =
        Securities.all_commodities.find(c => paramName.toLowerCase().contains(c.name))
      val filtered = shared.constants("Person")
        .filter(t => t._1.contains(containString) && getItemByParam(t._1).isDefined)
        .map{case (name, value) => (getItemByParam(name).get, value)}
        .toList
      filtered.zip(softmax(filtered.map(_._2))).map(t => (t._1._1, t._2))
    }

    (mapValuesSoftmax("buy").toMap, mapValuesSoftmax("consume").toMap)
  }

  private val expected_enjoyment: Map[Commodity, Int] = Securities.all_commodities
    .filter(item => distr.contains("enjoy" + item.name.capitalize))
    .map(item => (item, distr("enjoy" + item.name.capitalize).sample.round.toInt))
    .toMap

  println("dist:")
  println(distr)
  println("ee:")
  println(expected_enjoyment)

  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val p = new Person(_shared, active, male, _happiness, log);
    copy_state_to(p);
    p
  }

  private def consume(consumable: Commodity, units: Int) {
    assert(available(consumable) >= units);
    variables("happiness") += units * expected_enjoyment(consumable)
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
    print("(Person@" + variables("happiness") + " " + variables("capital")/100 + ")  ");
  }

  private def getNextCommodity(probs: Map[Commodity, Double]): Commodity = {
    val prob = GLOBAL.rnd.nextDouble()
    var cumul = 0.0
    for ((commodity, value) <- probs.toList) {
      if (prob < cumul + value) {
        return commodity
      }
      cumul += value
    }
    probs.last._1
  }

  def getInputRow(): Map[String, Double] = {
    var tuples: List[(String, Double)] = List("male" -> (if (male) 1 else 0), "education" -> education.toDouble,
      "buyWheat" -> buyProbs(Securities.Wheat), "buyFlour" -> buyProbs(Securities.Flour),
      "buyBread" -> buyProbs(Securities.Bread), "buyLand" -> buyProbs(Securities.Land),
      "buyBeef" -> buyProbs(Securities.Beef), "buyOil" -> buyProbs(Securities.Oil), "buyFuel" -> buyProbs(Securities.Fuel),
      "consumeWheat" -> consumeProbs(Securities.Wheat), "consumeFlour" -> consumeProbs(Securities.Flour),
      "consumeBread" -> consumeProbs(Securities.Bread), "consumeBeef" -> consumeProbs(Securities.Beef),
      "consumeOil" -> consumeProbs(Securities.Oil), "consumeFuel" -> consumeProbs(Securities.Fuel),
      "enjoyWheat" -> expected_enjoyment(Securities.Wheat), "enjoyFlour" -> expected_enjoyment(Securities.Flour),
      "enjoyBread" -> expected_enjoyment(Securities.Bread), "enjoyBeef" -> expected_enjoyment(Securities.Beef),
      "enjoyOil" -> expected_enjoyment(Securities.Oil), "enjoyFuel" -> expected_enjoyment(Securities.Fuel))
      .map(t => if (t._2.isInstanceOf[Int]) (t._1, t._2.asInstanceOf[Int].toDouble) else (t._1, t._2.asInstanceOf[Double]))
    tuples ++= getOutputRow().toList
    tuples ++= Main.outputNames.zip(Main.outputFromState(shared)).toMap.toList
    tuples.toMap
  }

  def getOutputRow(): Map[String, Double] = Map("happiness" -> variables("happiness"), "capital" -> variables("capital"),
    "salary" -> variables("salary"))
}
