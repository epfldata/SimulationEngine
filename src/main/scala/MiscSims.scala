package Simulation.SimLib

import Securities._
import Simulation._
import _root_.Simulation.Factory._
import code._

class Source(commodity: Commodity, units: Int, p: Int,
             shared: Simulation) extends
  SimO(shared) with SimpleSim {
  {
    shared.market(commodity).add_seller(this);
    make(commodity, units, 0); // at no cost
  }

  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val n = new Source(commodity, units, p, _shared);
    copy_state_to(n);
    n
  }

  def action = __do {}

  override def price(dummy: Commodity) = Some(p)
}


case class Trader(commodity: Commodity,
                  desired_inventory: Int,
                  shared: Simulation) extends
  SimO(shared) with SimpleSim {
  {
    shared.market(commodity).add_seller(this);
  }

  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val n = Trader(commodity, desired_inventory, _shared);
    copy_state_to(n);
    n
  }

  override def price(dummy: Commodity) = {
    if (available(commodity) > 0)
      Some(1.05 * inventory_avg_cost.getOrElse(commodity, 0.0))
    else None
  }

  def action = __do {
    if (available(commodity) < desired_inventory) {
      val missing = shared.market(commodity).
        market_buy_order_now(shared.timer, this, 1);
    }
  }
}


// A regular buyer for a sandbox simulation
case class Buyer(commodity: Commodity,
                 units_per_tick: () => Int,
                 shared: Simulation) extends SimO(shared) with SimpleSim {

  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]) = {
    val n = Buyer(commodity, units_per_tick, _shared)
    copy_state_to(n);
    n
  }

  def action = __do {
    shared.market(commodity).
      market_buy_order_now(shared.timer, this, units_per_tick());
  }
}


class Farm(s: Simulation) extends Factory(
  ProductionLineSpec(1, List((Land, s.constants("Farm")("farmReq").toInt)), List(),
    (Wheat, s.constants("Farm")("farmProd").toInt),
    s.constants("Farm")("farmTime").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new Farm(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class Mill(s: Simulation) extends Factory(
  ProductionLineSpec(1, List(), List((Wheat, s.constants("Mill")("millCons").toInt)),
    (Flour, s.constants("Mill")("millProd").toInt),
    s.constants("Mill")("millTime").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new Mill(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class Bakery(s: Simulation) extends Factory(
  ProductionLineSpec(1, List(), List((Flour, s.constants("Bakery")("bakeryCons").toInt)),
    (Bread, s.constants("Bakery")("bakeryProd").toInt),
    s.constants("Bakery")("bakeryTime").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new Bakery(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class CattleFarm(s: Simulation) extends Factory(
  ProductionLineSpec(1, List((Land, s.constants("CattleFarm")("cattleReq").toInt)), List(),
    (Beef, s.constants("CattleFarm")("cattleProd").toInt),
    s.constants("CattleFarm")("cattleTime").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new CattleFarm(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class OilField(s: Simulation) extends Factory(
  ProductionLineSpec(1, List((Land, s.constants("OilField")("oilfieldReq").toInt)), List(),
    (Oil, s.constants("OilField")("oilfieldProd").toInt),
    s.constants("OilField")("oilfieldTime").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new OilField(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class Refinery(s: Simulation) extends Factory(
  ProductionLineSpec(1, List(), List((Oil, s.constants("Refinery")("refineryCons").toInt)),
    (Fuel, s.constants("Refinery")("refineryProd").toInt),
    s.constants("Refinery")("refineryTime").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new Refinery(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}