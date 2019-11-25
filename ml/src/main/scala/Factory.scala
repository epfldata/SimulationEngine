package Simulation.Factory
import GLOBAL.println
import Owner._
import Securities._
import Simulation._
import code._

case class ProductionLineSpec(employees_needed: Int,
                              required: List[(Commodity, Int)],
                              consumed: List[(Commodity, Int)],
                              produced: (Commodity, Int),
                              time_to_complete: Int) {

  def theoretical_max_productivity(): Double =
    produced._2.toDouble / time_to_complete
}



// does not do its own buying
case class ProductionLine(
  val pls: ProductionLineSpec,
  var o: Factory,
  val employees: List[(Person, Int)],
  val start_time: Int
) extends Sim {

  // state of the machine (this plus env)
  var goodwill = 0.0
  var lost_runs_cost = 0.0 // cost from zero-efficiency production runs
  var valueProduced = 0.0
  private var rpt = 0
  private var frac = 1.0
  // fraction of theoretical capacity currently achieved
  private var costs_consumables = 0.0 // of current production run

  init(start_time);

  def salary_cost(): Int = employees.map(_._2).sum

  def mycopy(_o: Factory) = {
    val p = this.copy();
    p.o = _o;
    this.copy_state_to(p);
    //println("ProductionLine.mycopy(" + _o + "): pl.get_time = " + p.get_time);
    p
  }

  protected def algo = __forever(
    __do { // start of production run
      costs_consumables = 0;
      //print("buying consumables: " + o + " " + this + ". ");
      frac = 1.0;
      for(x <- pls.consumed) {
        val n = math.min(o.available(x._1), x._2); // requested and available
        costs_consumables += o.destroy(x._1, n);
        frac = math.min(frac, n.toDouble / x._2);
      }
      goodwill = costs_consumables;
      if((frac < 1.0) && (! GLOBAL.silent))
        println(o + " " + " starts low-efficiency run.");

      rpt = 0;
    },
    __dowhile(
      __wait(1),
      __do{
        //print("paying salaries. ");
        // salaries are paid globally (by the Factory)
        goodwill += salary_cost()
        rpt += 1;
      }
    )({ rpt < pls.time_to_complete }),
    __do{
      //print("production complete! ");
      val units_produced = (pls.produced._2  * frac).toInt;
      val Personnel_costs = salary_cost() * pls.time_to_complete;
      val total_cost : Double = costs_consumables + Personnel_costs;
      val unit_cost = total_cost / units_produced;

      if(units_produced > 0) {
        o.make(pls.produced._1, units_produced, unit_cost)
        valueProduced = units_produced * unit_cost

        if(! GLOBAL.silent)
        println(o + " produces " + units_produced + "x " +
          pls.produced._1 + " at efficiency " + frac +
          " and " + (unit_cost/100).toInt + "/unit.");
      }
      else {
        lost_runs_cost += total_cost;

        if(! GLOBAL.silent)
        println(o + " had a production line with zero efficiency.");
      }
//      log = (get_time, frac) :: log;
    }
  )
}



// TODO: different capabilities and salaries per employee
case class HR(private val shared: Simulation,
              private val o: Owner,
              salary: Int = 20000 * 100, // 20kEUR
              employees: collection.mutable.Stack[(Person, Int)] =
                         collection.mutable.Stack[(Person, Int)]()
) {
  def pay_workers() { for((employee, salary) <- employees) o.transfer_money_to(employee, salary); }

  protected def hire_one(): Option[(Person, Int)] = {
    if(shared.arbeitsmarkt.nonEmpty) {
      val employee = shared.arbeitsmarkt.pop.asInstanceOf[Person]
      val bonusSalary = shared.distributions(employee)("bonusSal").sample.round.toInt * employee.education
      employees.push((employee, salary + bonusSalary))
      employee.variables("salary") = salary + bonusSalary
      Some(employees.top)
    } else {
      None
    }
  }
  protected def fire_one() {
    val employee = employees.pop._1
    employee.variables("salary") = 0
    shared.arbeitsmarkt.push(employee)
  }

  def hire(n: Int): List[(Person, Int)] = {
    (for(_ <- 1 to n) yield hire_one()).flatten.toList
  }
  def fire(n: Int) { for(i <- 1 to n) fire_one(); }
}


abstract class Factory(protected val pls: ProductionLineSpec,
                       val shared: Simulation
) extends SimO(shared) {
  private val distr = shared.distributions(this)

  variables("capital") = distr("capital").sample
  variables("total_value_destroyed") = distr("total_value_destroyed").sample

  observables += "employees" -> (() => numEmployees.toDouble)
  observables += "goodwill" -> (() => pl.map(_.goodwill).sum)
  observables += "valueProduced" -> (() => pl.map(_.valueProduced).sum)

  var pl : List[ProductionLine] = List()
  private var zombie_cost2 : Double = 0.0 // cost from canceled prod. runs
  protected var hr : HR = HR(shared, this, math.abs(distr("salary").sample.round.toInt))
  protected var goal_num_pl = 0;

  private var nestedSimIters = math.max(0, math.min(20, distr("iters").sample.round.toInt))
  private var tacticsProbability = distr("tactics").sample


  // constructor
  {
    shared.market(pls.produced._1).add_seller(this);
    goal_num_pl = 1; // have one production line
  }

  protected def copy_state_to(_to: Factory,
      _shared: Simulation,
      old2new: collection.mutable.Map[SimO, SimO]) {

    //println("Factory.copy_state_to: " + this);
    super.copy_state_to(_to);

    _to.pl = pl.map(_.mycopy(_to));
    _to.zombie_cost2 = zombie_cost2;
    _to.hr = new HR(_shared, _to, hr.salary,
       hr.employees.map{case (employee, salary) => (old2new(employee).asInstanceOf[Person], salary)}
    )
    _to.goal_num_pl = goal_num_pl;
    _to.nestedSimIters = nestedSimIters
    _to.tacticsProbability = tacticsProbability
  }

  /** Returns whether everything was sucessfully bought. */
  protected def bulk_buy_missing(_l: List[(Commodity, Int)],
                                 multiplier: Int) : Boolean = {
    val l = _l.map(t => {
      // DANGER: if we have shorted his position, this amount is
      // not sufficient.
      val amount = math.max(0, t._2 * multiplier - available(t._1));
      (t._1, amount)
    });

    def successfully_bought(line: (Commodity, Int)) =
       (shared.market(line._1).
          market_buy_order_now(shared.timer, this, line._2) == 0);
       // nothing missing

    l.forall(successfully_bought)
  }


  protected def add_production_line() : Boolean = {
    var success = true;

    if(shared.arbeitsmarkt.length >= pls.employees_needed)
    {
      // buy only what we require. We may still have it from
      // previous production reductions.
      success = bulk_buy_missing(pls.required, pl.length + 1);
      if(success) {
        val employees = hr.hire(pls.employees_needed);
        pl = new ProductionLine(pls, this, employees, shared.timer) :: pl;
      }
    }
    else success = false;
    success
  }

  // We don't sell required items (land, etc.) but only fire people.
  protected def remove_production_line() {
    if(pl.nonEmpty) {
      hr.fire(pl.head.employees.length);
      zombie_cost2 += pl.head.goodwill;
      pl = pl.tail;
    }
  }

  protected def goodwill: Int = pl.map(_.goodwill).sum.toInt

  def numEmployees: Int = pl.map(_.pls.employees_needed).sum

  override def stat {
    val zombie_cost = pl.map(_.lost_runs_cost).sum.toInt;

    println((
      (assets + goodwill + liabilities)/100,
      ((assets + goodwill)/100, (assets/100, goodwill/100),
       liabilities/100),
      zombie_cost.toInt/100,
      zombie_cost2.toInt/100,
      inventory_to_string(),
      pl.length
    ))
  }

  protected def tactics() = {
    import Timeseries._;


    def historic_demand : Timeseries[Int] = sum_grp[SalesRecord](
      shared.market(pls.produced._1).order_history.toTimeseries, _.num_ordered);
    if (historic_demand.to < shared.timer - 1) {
      shared.market(pls.produced._1).market_buy_order_now(shared.timer, this, 0)
    }
    val past_demand: Timeseries[Int] = historic_demand.end_at(shared.timer - 1);

    val demand_fc : Double =
      super_forecast(past_demand).apply(shared.timer - 1);

    val suitable_num_pl =
      math.ceil(demand_fc / pls.theoretical_max_productivity).toInt;

    if(! GLOBAL.silent)
      println(this + " demand_fc = " + demand_fc + ", best_pl = " +
        suitable_num_pl + ", currently = " + pl.length);

    goal_num_pl = suitable_num_pl;
    if(suitable_num_pl >= 1) {
      println(this + ": First nested simulation starts.");
      val old2new1 = shared.run_sim(nestedSimIters);
      val future_self1 = old2new1(this).asInstanceOf[Factory];
      future_self1.stat;
      println(this + ": First nested simulation ends.");

      println(this + ": Second nested simulation starts.");
      //run simulation to see whether this is better.
      goal_num_pl -= 1;
      val old2new2 = shared.run_sim(nestedSimIters);
      val future_self2 = old2new2(this).asInstanceOf[Factory];
      future_self2.stat;
      println(this + ": Second nested simulation ends.");

      def valuation(f: Factory) = f.assets + f.goodwill + f.liabilities;

      if(valuation(future_self1) < valuation(future_self2)) {
        println("ONE LESS WOULD BE BETTER")
      }
      else {
        goal_num_pl += 1
        println(goal_num_pl + " IS A GOOD DECISION!")
      }
    }
  }


  // This is the cost-based price of product on stock
  override def price(dummy: Commodity) : Option[Double] = {
    if(available(pls.produced._1) > 0)
      Some(1.0 * inventory_avg_cost.getOrElse(pls.produced._1, 0.0))
    else None
  }


  override protected def algo = __forever(
    __do {
      if(shared.recursionDepth == 0 && shared.timer > 0 && GLOBAL.rnd.nextDouble() <= tacticsProbability) {
        tactics(); // changes goal_num_pl
      }

      for(i <- (pl.length + 1) to goal_num_pl)
        add_production_line();
      for(i <- (goal_num_pl + 1) to pl.length)
        remove_production_line();

      // TODO: buy more to get better prices?
      //println("Factory.algo: this=" + this);
      val still_missing = bulk_buy_missing(pls.consumed, pl.length);
    },
    __wait(1),
    __do {
      assert(hr.employees.length == pl.length * pls.employees_needed);
      hr.pay_workers();
    }
  )

  override def run_until(until: Int) : Option[Int] = {
    // this ordering is important, so that bulk buying
    // happens before consumption.
    val nxt1 = super.run_until(until).get;
    if (pl.isEmpty) {
      None
    } else {
      val nxt2 = pl.map(_.run_until(until).get).min;
      Some(math.min(nxt1, nxt2)) // compute a meaningful next time
    }
  }
}


