package Simulation
import Markets._
import Owner._
import Securities._
import Commodities._
import Simulation.Factory.{Factory, ProductionLineSpec}
import generator.Generator
import landAdministrator.LandAdministrator
import landAdministrator.CadastralParcel
import cooperative.AgriculturalCooperative
import Securities.Commodities._

class Simulation {
  var timer = 0;

  //Give global statistics about the simulation (only co2 emitted at the moment)
  val observator = new Observator


  //Chose here the name of the canton you want to simulate. Also works with "Suisse" for all country (but currently causes java heap overflow)
  val canton = "Glaris"

  val market = collection.mutable.Map[Commodity, SellersMarket]();
  for (c <- Commodities.all_commodities) {
    market += (c -> new SellersMarket(c));
  };

  val prices = new Prices(market)

  var labour_market = collection.mutable.Stack[SimO](); // all Persons

  var chicago = collection.mutable.Map[Security, OrderBook]();

  /** TODO: We should have a registry of sims here, which can be looked up by
    * id. This eliminates the need for substitution when copying a simulation,
    * which is a mess.
    */
  var sims = List[SimO]()

  // Manage the lands
  val landAdministrator = new LandAdministrator(0, 0)
  // The Generator for the data
  val generator = new Generator

  /** This is not a constructor since we first need to create the Simulation to
    * hand it over to the sims, and then hand the sims to the Simulation (via
    * init).
    *
    * init() accepts the list of sims `_sims`, enters persons into the labor
    * market, and output the status of each sim.
    */
  def init(_sims: List[SimO]) {
    assert(timer == 0);
    println("INIT Simulation " + this);
    sims = _sims;

    //Create the people inside the canton (call before initFarms cause farm needs worker inside labourMarket)
    initPerson
    //Create parcels and farm. Assign parcels to each farm.
    initLandsAndFarms

    initMills

    println("Number of sims = " + sims.length)

    if (!GLOBAL.silent) {
      for (s <- sims) { s.stat; }
      println; println;
    }

    println("INIT Simulation complete " + this);

  }

  /** TODO: Object ids (owners) in logs don't get substituted yet. This will
    * become necessary when we want to compute supply by _other_ sellers.
    */
  def mycopy() = {
    val s2 = new Simulation;
    val old2new = collection.mutable.Map[SimO, SimO]();

    // this separation would not be needed if we had a central map from sim ids
    // to sims.
    for (s <- sims) {
      if (s.isInstanceOf[Person]) {
        val cp = s.mycopy(s2, old2new).asInstanceOf[SimO];
        old2new += (s -> cp);
      }
    }

    s2.sims = sims.map((s: SimO) => {
      old2new.getOrElse(
        s, {
          val cp = s.mycopy(s2, old2new).asInstanceOf[SimO];
          old2new += (s -> cp);
          cp
        }
      )
    });

    s2.labour_market =
      labour_market.map((x: SimO) => old2new(x.asInstanceOf[SimO]));

    for ((commodity, ma) <- market) {
      ma.copy_state_to(
        s2.market(commodity),
        (s: Seller) => old2new(s.asInstanceOf[SimO])
      );
    }

    assert(s2.chicago.isEmpty);
    for ((security, ob) <- chicago) {
      s2.chicago += (security -> ob.mycopy());
    }

    s2.timer = timer;

    (s2, old2new)
  }

  /** run the simulation. Must init() first! */
  def run_until(until: Int) {
    println("RESUME Simulation " + this);
    while (timer <= until) {
      if (!GLOBAL.silent) println("timer = " + timer);
      for (s <- sims) s.run_until(timer);
      if (!GLOBAL.silent) {
        //for (s <- sims) s.stat;
        //println(); println();
      }

      //update the global Prices
      prices.updateAllPrices

      timer += 1;
    }
    println("STOP Simulation " + this);
    
    observator.stats
    
  }
  def run(steps: Int) {
    run_until(timer + steps - 1);
  }

  /** To be used to start a nested simulation. Callable from the sims.
    *
    * Returns a mapping from the sims of the old simulation to those of the new.
    */
  def run_sim(it: Int): collection.mutable.Map[SimO, SimO] = {
    val (new_sim, old2new) = this.mycopy;

    // prevent recursive simulation. This is only safe it the simulation
    // runs for fewer than 1000 iterations!
    for (s <- new_sim.sims)
      if (s.isInstanceOf[Factory.Factory])
        s.asInstanceOf[Factory.Factory].prev_mgmt_action += 1000;

    new_sim.run(it);
    old2new
  }

  private def initLandsAndFarms {

    //Init generate parcels, and assign them to farms
    val allParcels = generator.generateParcels(canton)
    landAdministrator.cadastralParcels = allParcels._1 ::: allParcels._2
    //var farms = generator.assignParcelsToFarms(canton, allParcels._1, this)
    var farms = generator.assignParcelsToFarms(canton, allParcels._1, this).take(2)
    println(farms.length + " farms created: ")
    //assign land overlays to farms
    generator.createAndAssignLandOverlays(farms, landAdministrator)
    // init the farm (make a factory for each landOverlay)
    farms.foreach(_.init)

    val coop = new AgriculturalCooperative(farms, List(Wheat, Fertilizer), this)

    sims ++= List(coop)
    sims ++= farms
    println("Number of worker = " + CONSTANTS.workercounter)
  }

  private def initPerson {
    val people = generator.generatePeople(canton, this)
    println("Generating " + people.length + " people")
    sims ++= people
    labour_market.pushAll(people)
  }

  private def initMills {
    val mills = generator.generateMills(canton, this)
    println(mills.length + " Mills generated")
    sims ++= mills
  }
}
