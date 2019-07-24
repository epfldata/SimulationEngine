package bo

import java.io.{FileDescriptor, FileOutputStream, PrintStream}

import Securities.{Flour, Land}
import Simulation.SimLib.{Buyer, Mill}
import Simulation.{Person, Simulation}
import _root_.Simulation.SimLib.{Farm, Source}

object Main {
  def main(args: Array[String]): Unit = {
    val s = new Simulation
    callSimulation(s)
    println(s.sims.map(_.capital / s.sims.size).sum)
  }

  def callSimulation(s: Simulation, mute: Boolean = true): Unit = {
    if (mute)
      Console.setOut(new PrintStream(new FileOutputStream("target/scala-2.11/runLog")))

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

    val people = for (x <- 1 to 12) yield new Person(s, false)

    s.init(List(
      landlord,
      //silo,
      // silo2, billa, freudensprung,
      f, m,
      // c, rf, mcd,
      mehlbuyer
    ) ++ people.toList)
    GLOBAL.silent = true
    s.run(1000)
    Console.out.flush()
    Console.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
  }
}
