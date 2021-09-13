package Simulation
import SimLib._
import Securities.Commodities._
import farmpackage.Farm
import Simulation.Factory._


object MainExample {
  val s = new Simulation;

  val seedsSeller = new Source(WheatSeeds, 10000000,300, s);
  val seedsSeller1 = new Source(WheatSeeds, 100000,340, s);
  val feedStuffSeller = new Source(FeedStuff, 100000,100, s);

  s.init(List());

  //def main(argv: Array[String]) {
  //  if((argv.length != 1) || (argv(0).toInt < 1))
  //    println("Exactly one integer >0 argument needed!");
  //  else
  //    s.run(argv(0).toInt);
  //}

  /** For the moment change the number of iteration directly in the code, problem with previous implementation
   * @note This will be fixed to select number of turns directly from terminal
  */
  def main(argv: Array[String]) {
    //s.run(2);
    s.run(800);
  }
}

object TradingExample {
  import Owner._;

  val simu = new Simulation;

  val s = new Source(Wheat, 4, 1000*100, simu);
  val t = new Trader(Wheat, 1,           simu);
  val b = new  Buyer(Wheat, () => 1,     simu);

  simu.init(List(s, t, b));
  simu.run(4);
/* After 4 steps we have
(BalanceSheet(4000,4000,4000,0,0),ArrayBuffer(Wheat -> 0@0))
(BalanceSheet(50,50,50,0,0),ArrayBuffer(Wheat -> 0@1000))
(BalanceSheet(0,4050,0,-4050,0),ArrayBuffer(Wheat -> 4@1012))
*/
}



