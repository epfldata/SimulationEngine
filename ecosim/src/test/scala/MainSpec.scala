import ecosim.owner.BalanceSheet
import ecosim.securities.Commodities._
import ecosim.simulation.Simulation
import ecosim.simulation.factory.Factory
import ecosim.simulation.simlib.{Buyer, Source, Trader}
import org.scalatest._

class MainSpec extends FlatSpec {

  "The simple trading example" should "work" in {
    val simu = new Simulation

    val s = new Source(Wheat, 4, 1000 * 100, simu)
    val t = Trader(Wheat, 1, simu)
    val b = Buyer(Wheat, () => 1, simu)

    simu.init(List(s, t, b))
    simu.run(4)

    assert(s.balance_sheet == BalanceSheet(4000, 4000, 0, 0, 0, 0))
    assert(t.balance_sheet == BalanceSheet(50, 50, 0, 0, 0, 0))
    assert(b.balance_sheet == BalanceSheet(0, 0, 4050, -4050, 0, 0))
    assert(s.available(Wheat) == 0)
    assert(t.available(Wheat) == 0)
    assert(b.available(Wheat) == 4)
  }

  "Copying a simulation" should "work" in {
    import ecosim.simulation.MainExample._
    ecosim.global.silent = true
    s.run(1)
    val (s2, _) = s.mycopy()
    val f2 = s2.sims(2).asInstanceOf[Factory]
    /*
        s.run(7);
        s2.run(7);
     */
    /*
    s.run(10);
    s2.run(10);
    // for(x <- s2.sims) x.stat()
    // for(x <- s.sims) x.stat()
    assert(s.sims.zip(s2.sims).forall(t => (t._1.capital == t._2.capital) &&
                       (t._1.inventory_to_string == t._2.inventory_to_string)));
     */
    /*
    s.run(100);
    s2.run(100);
    assert(s.sims.zip(s2.sims).forall(t => (t._1.capital == t._2.capital) &&
                       (t._1.inventory_to_string == t._2.inventory_to_string)));
   */
  }
}
