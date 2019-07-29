package bo

import java.io.{FileDescriptor, FileOutputStream, PrintStream}

import Securities.{Flour, Land}
import Simulation.SimLib.{Buyer, Mill}
import Simulation.{Person, Simulation}
import _root_.Simulation.SimLib.{Farm, Source}

object Main {
  val outputFromState: Simulation => Seq[Double] = s => List(s.sims.map(_.capital.toDouble / 100 / s.sims.size).sum)

  val numberOfSims = 120
  val bounds: Seq[(Int, Int)] = for(_ <- 1 to numberOfSims) yield (0, 100)

  def metrics(params: Map[String, Double])(xs: Seq[Int]): Seq[Double] = {
    val s = new Simulation(params)
    initializeSimulation(s)
    s.sims.zip(xs).foreach(t => t._1.capital = t._2)
    callSimulation(s, 1000)
    outputFromState(s)
  }

  def main(args: Array[String]): Unit = {
    val params = Map(
      ("foodUnitsMu", args(1).toDouble), ("foodUnitsSigma", args(2).toDouble),
      ("movieUnitsMu", args(3).toDouble), ("movieUnitsSigma", args(4).toDouble)
    )
    args(0) match {
      case "generate" =>
        BOUtil.generateXYPairs("target/scala-2.11/xypairs", bounds, metrics, params, 1)
      case "evaluate" =>
        val file = scala.io.Source.fromFile("target/scala-2.11/xypairs")
        val lines = file.getLines().toList
        val Xs = lines.filter(_.startsWith("x:")).map(_.substring(2).split(' ').map(_.toInt))
        val Ys = lines.filter(_.startsWith("y:")).map(_.substring(2).split(' ').map(_.toDouble))
        println(BOUtil.error(Xs, Ys, metrics(params)))

      case "lyapunov" =>
        println(ChaosTest(outputFromState, params).lyapunovExponent("movieUnitsMu"))

      case "plot" =>
        val visulizer = Viz(outputFromState, params)
        visulizer.plotSimOverTime((0, 1000))
        visulizer.plotSimOverParam("foodUnitsMu", (0, 100), runSimTill = 100)
    }
  }

  def initializeSimulation(s: Simulation, mute: Boolean = true): Unit = {
    if (mute)
      Console.setOut(new PrintStream(new FileOutputStream("target/scala-2.11/initLog")))

    GLOBAL.silent = true
    val f = new Farm(s)
    val m = new Mill(s)
    //val c   = new Cinema(s);
    //val rf  = new CattleFarm(s);
    //val mcd = new McDonalds(s);
    val landlord = new Source(Land, 20, 100000 * 100, s)
    //val freudensprung = new Source(Beef,   100,  26000*100, s);
    //val silo          = new Source(Wheat, 1000,   6668*100, s);
    //val silo2         = new Trader(Whear, 100, s);
    //val billa         = new Trader(Flour, 50, s);
    val mehlbuyer = Buyer(Flour, () => 40, s)

    val people = for (x <- 1 to numberOfSims) yield new Person(s, true)

    s.init(List(
      landlord,
      //silo,
      // silo2, billa, freudensprung,
      f, m,
      // c, rf, mcd,
      mehlbuyer
    ) ++ people.toList)

    Console.out.flush()
    Console.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
  }

  def callSimulation(s: Simulation, iterations: Int, mute: Boolean = true): Unit = {
    if (mute)
      Console.setOut(new PrintStream(new FileOutputStream("target/scala-2.11/runLog")))

    s.run(iterations)

    Console.out.flush()
    Console.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
  }
}
