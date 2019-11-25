package Simulation

import GLOBAL.println
import Markets._
import Owner._
import Securities._
import Simulation.Factory.Factory
import ml_supplements.DatasetCreator.{Data, Statistics}
import breeze.stats.distributions.{Gaussian, RandBasis, ThreadLocalRandomGenerator}
import org.apache.commons.math3.random.MersenneTwister

import scala.collection.mutable.{Map => MutableMap}


class Simulation(val constants: Data, val variables: Data) {
  var recursionDepth: Int = 0
  def this() = this(MutableMap.empty[String, Map[String, Double]], MutableMap.empty[String, Map[String, Double]])

  var timer = 0;

  implicit val randBasis: RandBasis = new RandBasis(new ThreadLocalRandomGenerator(new MersenneTwister(19)))

  def distributions(sim: Sim): Map[String, Gaussian] = sim match {
    case person: Person =>
      val gender = if (person.male) "male" else "female"
      var distr: Map[String, Gaussian] = Map(
        ("edu", new Gaussian(constants("Person")(gender + "EduMu"), constants("Person")(gender + "EduSigma"))),
        ("bonusSal", new Gaussian(constants("Person")(gender + "BonusSalMu"), constants("Person")(gender + "BonusSalSigma"))),
        ("active", new Gaussian(constants("Person")(gender + "ActiveMu"), constants("Person")(gender + "ActiveSigma"))),
        ("buy", new Gaussian(constants("Person")("buyMu"), constants("Person")("buySigma"))),
        ("consume", new Gaussian(constants("Person")("consumeMu"), constants("Person")("consumeSigma"))),
        ("capital", new Gaussian(variables("Person")("capitalMu"), variables("Person")("capitalSigma"))),
        ("total_value_destroyed", new Gaussian(variables("Person")("total_value_destroyedMu"),
          variables("Person")("total_value_destroyedSigma"))),
        ("happiness", new Gaussian(variables("Person")("happinessMu"), variables("Person")("happinessSigma"))),
        ("salary", new Gaussian(variables("Person")("salaryMu"), variables("Person")("salarySigma")))
      )
      for (com <- Securities.all_commodities) {
        val capName = com.name.capitalize
        if (constants("Person").contains("enjoy" + capName + "Mu")) {
          distr += "enjoy" + capName -> new Gaussian(constants("Person")("enjoy" + capName + "Mu"),
            constants("Person")("enjoy" + capName + "Sigma"))
        }
      }
      distr

    case factory: Factory =>
      val factoryType = GLOBAL.getAgentTypeFromClass(factory.getClass.getName)
      Map(
        ("salary", new Gaussian(constants(factoryType)("salaryMu"),
          constants(factoryType)("salarySigma"))),
        ("iters", new Gaussian(constants(factoryType)("itersMu"),
          constants(factoryType)("itersSigma"))),
        ("tactics", new Gaussian(constants(factoryType)("tacticsMu"), constants(factoryType)("tacticsSigma"))),
        ("capital", new Gaussian(variables(factoryType)("capitalMu"),
          variables(factoryType)("capitalSigma"))),
        ("total_value_destroyed", new Gaussian(variables(factoryType)("total_value_destroyedMu"),
          variables(factoryType)("total_value_destroyedSigma")))
      )

    case _ => Map()
  }

  def getPopulationData(agents: Iterable[String]): Data = {
    def getAgentPopulationData(agentType: String): Map[String, Double] = {
      val filtered: List[SimO] = sims.filter(sim => agentType.equals(GLOBAL.getAgentTypeFromClass(sim.getClass.getName)))
      val individualVars: Map[String, List[Double]] = filtered.flatMap(_.variables.toSeq).groupBy(_._1).mapValues(_.map(_._2))
      val individualObs: Map[String, List[Double]] = filtered.flatMap(_.observables.toSeq).groupBy(_._1).mapValues(_.map(_._2()))
      val individualStates = individualVars ++ individualObs
      val populationMeans: Map[String, Double] = individualStates.mapValues(vars => vars.map(_ / vars.size).sum)
      val populationSDs: Map[String, Double] = individualStates.map {
        case (name, values) =>
          val mean = populationMeans(name)
          val variance = values.map(value => (value - mean) * (value - mean) / values.size).sum
          (name, math.sqrt(variance))
      }
      val consts: Map[String, Double] = constants(agentType)

      populationMeans.map(t => ("var_" + t._1 + "Mu", t._2)) ++
        populationSDs.map(t => ("var_" + t._1 + "Sigma", t._2)) ++
        consts.map(t => ("const_" + t._1, t._2))
    }

    MutableMap(agents.map(agentType => (agentType, getAgentPopulationData(agentType))).toSeq: _*)
  }

  def getGlobalStat: Statistics = Map(
    getVariableMu("capital"),
    getVariableMu("total_value_destroyed"),
    "unemploymentRate" -> (1.0 -
      sims.map { case f: Factory => f.numEmployees case _ => 0 }.sum.toDouble / sims.count(_.isInstanceOf[Person])),
    getVariableMu("happiness"),
    getObservableMu("valueProduced"),
    getObservableMu("goodwill")
  )

  private def getVariableMu(variableName: String): (String, Double) = {
    val filtered = sims.filter(sim => GLOBAL.isAgent(sim) && sim.variables.contains(variableName))
    (s"gl_$variableName" + "Mu", filtered.map(_.variables(variableName) / filtered.size).sum)
  }

  private def getObservableMu(observableName: String): (String, Double) = {
    val filtered = sims.filter(sim => GLOBAL.isAgent(sim) && sim.observables.contains(observableName))
    (s"gl_$observableName" + "Mu", filtered.map(_.observables(observableName)() / filtered.size).sum)
  }

  val market = collection.mutable.Map[Commodity, SellersMarket]();
  for (c <- all_commodities) {
    market += (c -> new SellersMarket(c));
  };

  var arbeitsmarkt = collection.mutable.Stack[SimO](); // all Persons

  /** TODO: We should have a registry of sims here, which can be looked up by
    *id. This eliminates the need for substitution when copying a simulation,
    * which is a mess.
    */
  var sims = List[SimO]()


  /** This is not a constructor since we first need to create the Simulation
    * to hand it over to the sims, and then hand
    * the sims to the Simulation (via init).
    * *
    * init() accepts the list of sims `_sims`,
    * enters Persons into the labor market,
    * and output the status of each sim.
    */
  def init(_sims: List[SimO]) {
    assert(timer == 0);
    println("INIT Simulation " + this);
    sims = _sims;
    for (s <- sims) if (s.isInstanceOf[Person]) arbeitsmarkt.push(s);

    if (!GLOBAL.silent) {
      for (s <- sims) {
        s.stat;
      }
      println;
      println;
    }

    println("INIT Simulation complete " + this);
  }

  /** TODO: Object ids (owners) in logs don't get substituted yet.
    * This will become necessary when we want to compute supply by _other_
    * sellers.
    */
  def mycopy() = {
    val s2 = new Simulation(constants, variables);
    s2.recursionDepth = recursionDepth + 1

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
      old2new.getOrElse(s, {
        val cp = s.mycopy(s2, old2new).asInstanceOf[SimO];
        old2new += (s -> cp);
        cp
      })
    });

    s2.arbeitsmarkt =
      arbeitsmarkt.map((x: SimO) => old2new(x.asInstanceOf[SimO]));

    for ((commodity, ma) <- market) {
      ma.copy_state_to(s2.market(commodity),
        (s: Seller) => old2new(s.asInstanceOf[SimO]));
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
        for (s <- sims) s.stat;
        println();
        println();
      }
      timer += 1;
    }
    println("STOP Simulation " + this);
  }

  def run(steps: Int) {
    run_until(timer + steps - 1);
  }

  /** To be used to start a nested simulation. Callable from the sims.
    * *
    * Returns a mapping from the sims of the old simulation to those of the
    * new.
    */
  def run_sim(it: Int): collection.mutable.Map[SimO, SimO] = {
    val (new_sim, old2new) = this.mycopy;

    new_sim.run(it);
    old2new
  }
}



