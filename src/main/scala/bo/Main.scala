package bo

import java.io.{BufferedOutputStream, FileDescriptor, FileOutputStream, FileWriter, PrintStream}

import Securities.{Commodity, Land}
import Simulation.{Person, Simulation}
import _root_.Simulation.Factory.Factory
import _root_.Simulation.SimLib._
import breeze.stats.distributions.Gaussian
import spray.json.{JsObject, JsonParser}

object Main {
  val numberPeople = 1200
  val bufferSize = math.pow(2, 27).toInt // 128 MB
  val initLog = new BufferedOutputStream(new FileOutputStream("target/scala-2.11/initLog"), bufferSize)
  val runLog = new BufferedOutputStream(new FileOutputStream("target/scala-2.11/runLog"), bufferSize)

  def outputFromState(s: Simulation): Seq[Double] = {
    def avgPrice(commodity: Commodity) =
      s.market(commodity).sellers.map(s => s.price(commodity).getOrElse(0.0)).sum / s.market(commodity).sellers.size

    List(
      s.sims.map(_.capital.toDouble / 100).sum / s.sims.size,
      (numberPeople - s.sims.map{case f: Factory => f.numEmployees; case _ => 0}.sum).toDouble / numberPeople,
      s.sims.map{case m: Mill => m.numEmployees; case _ => 0}.sum.toDouble / numberPeople,
      s.sims.map{case f: Farm => f.numEmployees; case _ => 0}.sum.toDouble / numberPeople,
      avgPrice(Securities.Wheat),
      avgPrice(Securities.Flour),
      avgPrice(Securities.Bread),
      avgPrice(Securities.Beef),
      avgPrice(Securities.Oil),
      avgPrice(Securities.Fuel)
    )
  }
  val outputNames = Array("CapitalAvg", "UnemploymentRate", "MillEmploymentRate", "FarmEmploymentRate",
    "WheatAvgPrice", "FlourAvgPrice", "BreadAvgPrice", "BeefAvgPrice", "OilAvgPrice", "FuelAvgPrice")

  def simFunction(params: Map[String, Double]): Seq[Double] = {
    val s = new Simulation(params)
    initializeSimulation(s)
    callSimulation(s, 300)
    outputFromState(s)
  }

  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.fromFile(args(1))
    val jsonString = source.mkString
    source.close()

    val jsonAst = JsonParser(jsonString)
    val params = jsonAst.asInstanceOf[JsObject].fields.mapValues(_.toString().toDouble)

    args(0) match {
      case "generate-csv" =>
        args(2) match {
          case "separate" =>
            val trainsetSize = args(3).toInt
            DatasetCreator.separateDatasets(trainsetSize)

          case "simulation" =>
            val trainsetSize = args(3).toInt
            val nIters = args(4).toInt
            DatasetCreator.simulationDatasets(trainsetSize, nIters)
        }

      case "generate" =>
        val fileWriter = new FileWriter("target/scala-2.11/actuals")
        simFunction(params).foreach(observable => fileWriter.write(observable + "\n"))
        fileWriter.close()

      case "evaluate" =>
        val file = scala.io.Source.fromFile("target/scala-2.11/actuals")
        val actuals = file.getLines().toList.map(_.toDouble)
        file.close()
        println(Metrics.meanAbsoluteError(simFunction(params), actuals))

      case "lyapunov" =>
        println(ChaosTest(outputFromState, params).lyapunovExponent(args(2)))

      case "plot-time" =>
        val visualizer = Viz(outputFromState, outputNames, params)
        val from = if (args.length > 2) args(2).toInt else 0
        val to = if (args.length > 3) args(3).toInt else 300
        val points = if (args.length > 4) args(4).toInt else 300
        visualizer.plotSimOverTime((from, to), points)

      case "plot-param" =>
        val visualizer = Viz(outputFromState, outputNames, params)
        val from = if (args.length > 3) args(3).toDouble else 0
        val to = if (args.length > 4) args(4).toDouble else 100
        val simIters = if (args.length > 5) args(5).toInt else 300
        visualizer.plotSimOverParam(args(2), (from, to), runSimTill = simIters)
    }

    initLog.flush()
    runLog.flush()
  }

  def initializeSimulation(s: Simulation, mute: Boolean = true): Unit = {
    if (mute)
      Console.setOut(initLog)

    GLOBAL.silent = true
    GLOBAL.strongSilence = true
    val f = new Farm(s)
    val m = new Mill(s)
    val b = new Bakery(s)
    val cf = new CattleFarm(s)
    val o = new OilField(s)
    val r = new Refinery(s)
    val landlord = new Source(Land, 20, 100000 * 100, s)

    val genderDistr = Gaussian(s.params("genderMu"), s.params("genderSigma"))
    val people = for (x <- 1 to numberPeople) yield {
      val male = math.max(0, math.min(1, genderDistr.sample())) <= 0.5
      new Person(s, true, male)
    }

    s.init(List(
      landlord,
      f, m, b,
      cf,
      o, r
    ) ++ people.toList)

    val capitals = Gaussian(s.params("capitalMu"), s.params("capitalSigma")).sample(s.sims.length)
    s.sims.zip(capitals).foreach(t => t._1.capital = t._2.round.toInt)

    Console.out.flush()
    Console.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
  }

  def callSimulation(s: Simulation, iterations: Int, mute: Boolean = true): Unit = {
    if (mute)
      Console.setOut(runLog)

    s.run(iterations)

    Console.out.flush()
    Console.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
  }
}
