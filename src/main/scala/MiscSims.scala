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

  def action = __do{}
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
    if(available(commodity) > 0)
      Some(1.05 * inventory_avg_cost.getOrElse(commodity, 0.0))
    else None
  }

  def action = __do {
      if(available(commodity) < desired_inventory) {
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
  ProductionLineSpec(1, List((Land, s.params("farmReq").toInt)), List(), (Wheat, s.params("farmProd").toInt), s.params("farmTime").toInt), s)

class Mill(s: Simulation) extends Factory(
  ProductionLineSpec(1, List(), List((Wheat, s.params("millCons").toInt)), (Flour, s.params("millProd").toInt), s.params("millTime").toInt), s)

class Bakery(s: Simulation) extends Factory(
  ProductionLineSpec(1, List(), List((Flour, s.params("bakeryCons").toInt)), (Bread, s.params("bakeryProd").toInt), s.params("bakeryTime").toInt), s)

class CattleFarm(s: Simulation) extends Factory(
  ProductionLineSpec(1, List((Land, s.params("cattleReq").toInt)), List(), (Beef, s.params("cattleProd").toInt), s.params("cattleTime").toInt), s)

class OilField(s: Simulation) extends Factory(
  ProductionLineSpec(1, List((Land, s.params("oilReq").toInt)), List(), (Oil, s.params("oilProd").toInt), s.params("oilTime").toInt), s)

class Refinery(s: Simulation) extends Factory(
  ProductionLineSpec(1, List(), List((Oil, s.params("refineryCons").toInt)), (Fuel, s.params("refineryProd").toInt), s.params("refineryTime").toInt), s)