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
  ProductionLineSpec(1, List((Land, s.constants("Farm")("required").toInt)), List(),
    (Wheat, s.constants("Farm")("produced").toInt),
    s.constants("Farm")("time").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new Farm(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class Mill(s: Simulation) extends Factory(
  ProductionLineSpec(1, List(), List((Wheat, s.constants("Mill")("consumed").toInt)),
    (Flour, s.constants("Mill")("produced").toInt),
    s.constants("Mill")("time").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new Mill(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class Bakery(s: Simulation) extends Factory(
  ProductionLineSpec(1, List(), List((Flour, s.constants("Bakery")("consumed").toInt)),
    (Bread, s.constants("Bakery")("produced").toInt),
    s.constants("Bakery")("time").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new Bakery(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class CattleFarm(s: Simulation) extends Factory(
  ProductionLineSpec(1, List((Land, s.constants("CattleFarm")("required").toInt)), List(),
    (Beef, s.constants("CattleFarm")("produced").toInt),
    s.constants("CattleFarm")("time").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new CattleFarm(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class OilField(s: Simulation) extends Factory(
  ProductionLineSpec(1, List((Land, s.constants("OilField")("required").toInt)), List(),
    (Oil, s.constants("OilField")("produced").toInt),
    s.constants("OilField")("time").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new OilField(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}

class Refinery(s: Simulation) extends Factory(
  ProductionLineSpec(1, List(), List((Oil, s.constants("Refinery")("consumed").toInt)),
    (Fuel, s.constants("Refinery")("produced").toInt),
    s.constants("Refinery")("time").toInt), s) {

  override def mycopy(_shared: Simulation,
                      _substitution: collection.mutable.Map[SimO, SimO]): Factory = {
    val f = new Refinery(_shared);
    copy_state_to(f, _shared, _substitution);
    f
  }
}