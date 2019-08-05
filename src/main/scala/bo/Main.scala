package bo

import java.io.{FileDescriptor, FileOutputStream, PrintStream}

import Securities.{Flour, Land}
import Simulation.SimLib.{Buyer, Mill}
import Simulation.{Person, Simulation}
import _root_.Simulation.SimLib.{Farm, Source}
import breeze.stats.distributions.Gaussian
import spray.json.{JsObject, JsonParser}

object Main {
  val outputFromState: Simulation => Seq[Double] = s => List(s.sims.map(_.capital.toDouble / 100 / s.sims.size).sum)
  println()
  val numberPeople = 120

  val inputGenerator: () => Seq[Int] = () => List(GLOBAL.rnd.nextInt(100000000) + 50, GLOBAL.rnd.nextInt(100000))

  def metrics(params: Map[String, Double])(xs: Seq[Int]): Seq[Double] = {
    val s = new Simulation(params)
    initializeSimulation(s)
    val capitals = Gaussian(xs.head, xs.last).sample(s.sims.length)
    s.sims.zip(capitals).foreach(t => t._1.capital = t._2.round.toInt)
    callSimulation(s, 300)
    outputFromState(s)
  }

  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.fromFile(args(1))
    val jsonString = source.mkString
    source.close()

    val jsonAst = JsonParser(jsonString)
    val params = jsonAst.asInstanceOf[JsObject].fields.mapValues(_.toString().toDouble)

    def readPairs(ratio: Double, isTraining: Boolean = true) = {
      val file = scala.io.Source.fromFile("target/scala-2.11/xypairs")
      val lines = file.getLines().toList
      val Xs = lines.filter(_.startsWith("x:")).map(_.substring(2).split(' ').map(_.toInt))
      val Ys = lines.filter(_.startsWith("y:")).map(_.substring(2).split(' ').map(_.toDouble))
      val from = if (isTraining) 0 else (Xs.length * ratio).round.toInt
      val to = if (isTraining) (Xs.length * ratio).round.toInt else Xs.length
      (Xs.slice(from, to), Ys.slice(from, to))
    }

    args(0) match {
      case "generate" =>
        BOUtil.generateXYPairs("target/scala-2.11/xypairs", inputGenerator, metrics, params, 20)

      case "evaluate" =>
        val trainTestRatio = args(2).toDouble
        val pairs = readPairs(trainTestRatio)
        println(BOUtil.meanRelativeError(pairs._1, pairs._2, metrics(params)))

      case "test" =>
        val trainTestRatio = args(2).toDouble
        val pairs = readPairs(trainTestRatio, isTraining = false)
        println(BOUtil.meanRelativeError(pairs._1, pairs._2, metrics(params)))

      case "lyapunov" =>
        println(ChaosTest(outputFromState, params).lyapunovExponent(args(2)))

      case "plot-time" =>
        val visualizer = Viz(outputFromState, params)
        val from = if (args.length > 2) args(2).toInt else 0
        val to = if (args.length > 3) args(3).toInt else 300
        val points = if (args.length > 4) args(4).toInt else 300
        visualizer.plotSimOverTime((from, to), points)

      case "plot-param" =>
        val visualizer = Viz(outputFromState, params)
        val from = if (args.length > 3) args(3).toInt else 0
        val to = if (args.length > 4) args(4).toInt else 100
        val simIters = if (args.length > 5) args(5).toInt else 300
        visualizer.plotSimOverParam(args(2), (from, to), runSimTill = simIters)
    }
  }

  def initializeSimulation(s: Simulation, mute: Boolean = true): Unit = {
    if (mute)
      Console.setOut(new PrintStream(new FileOutputStream("target/scala-2.11/initLog")))

    GLOBAL.silent = true
    GLOBAL.strongSilence = true
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

    val people = for (x <- 1 to numberPeople) yield new Person(s, true)

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
