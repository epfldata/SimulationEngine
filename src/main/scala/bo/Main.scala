package bo

import java.io.{BufferedOutputStream, FileDescriptor, FileOutputStream, FileWriter, PrintStream}

import Securities.{Commodity, Land}
import Simulation.{Person, Simulation}
import _root_.Simulation.Factory.Factory
import _root_.Simulation.SimLib._
import breeze.linalg.DenseMatrix
import breeze.stats.distributions.Gaussian
import spray.json.{JsObject, JsonParser}

object Main {
  val numberPeople = 120
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
  private val outputNames = Array("CapitalAvg", "UnemploymentRate", "MillEmploymentRate", "FarmEmploymentRate",
    "WheatAvgPrice", "FlourAvgPrice", "BreadAvgPrice", "BeefAvgPrice", "OilAvgPrice", "FuelAvgPrice")

  def simFunction(params: Map[String, Double]): Seq[Double] = {
    val s = new Simulation(params)
    initializeSimulation(s)
    callSimulation(s, 300)
    outputFromState(s)
  }

  private def seqSimFunction(params: Map[String, Double], nIters: Int): Seq[(Int, Seq[Double])] = {
    val s = new Simulation(params)
    initializeSimulation(s)
    for (i <- 1 to nIters) yield {
      callSimulation(s, 1)
      (i, outputFromState(s))
    }
  }

  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.fromFile(args(1))
    val jsonString = source.mkString
    source.close()

    val jsonAst = JsonParser(jsonString)
    val params = jsonAst.asInstanceOf[JsObject].fields.mapValues(_.toString().toDouble)

    args(0) match {
      case "generate-csv" =>
        val nIters = args(2).toInt
        val trainsetSize = args(3).toInt
        val paramsList = sampleParams(trainsetSize)
        val results: List[(Map[String, Double], Seq[(Int, Seq[Double])])] =
          paramsList.zip(paramsList.map(params => seqSimFunction(params, nIters)))
        val paramNames: Array[String] = params.toArray.map(_._1)
        val x: DenseMatrix[Double] = DenseMatrix(results.map{case (map, _) =>
          val x: Array[Double] = paramNames.map(name => map(name))
          x.toList
        }:_*)
        val y: DenseMatrix[Double] = DenseMatrix(results.flatMap{case (_, seqOutputs: Seq[(Int, Seq[Double])]) =>
          seqOutputs.map{case (iter, outputs) => outputs.toList}
        }:_*)
        CsvManager.writeCsvFile(x, "target/scala-2.11/train_X.csv", paramNames)
        CsvManager.writeCsvFile(y, "target/scala-2.11/train_Y.csv", "timestep" +: outputNames)

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
    val male = GLOBAL.rnd.nextDouble() <= math.max(0, math.min(1, genderDistr.sample()))
    val people = for (x <- 1 to numberPeople) yield new Person(s, true, male)

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

  private def sampleParams(trainsetSize: Int): List[Map[String, Double]] = (for (_ <- 0 until trainsetSize) yield
    Map("genderMu" -> GLOBAL.rnd.nextDouble(),
      "genderSigma" -> GLOBAL.rnd.nextDouble(),
      "capitalMu" -> GLOBAL.rnd.nextDouble() * 100000,
      "capitalSigma" -> GLOBAL.rnd.nextDouble() * 10000,

      "buyWheat" -> GLOBAL.rnd.nextDouble(),
      "buyFlour" -> GLOBAL.rnd.nextDouble(),
      "buyBread" -> GLOBAL.rnd.nextDouble(),
      "buyLand" -> GLOBAL.rnd.nextDouble(),
      "buyBeef" -> GLOBAL.rnd.nextDouble(),
      "buyOil" -> GLOBAL.rnd.nextDouble(),
      "buyFuel" -> GLOBAL.rnd.nextDouble(),

      "consumeWheat" -> GLOBAL.rnd.nextDouble(),
      "consumeFlour" -> GLOBAL.rnd.nextDouble(),
      "consumeBread" -> GLOBAL.rnd.nextDouble(),
      "consumeBeef" -> GLOBAL.rnd.nextDouble(),
      "consumeOil" -> GLOBAL.rnd.nextDouble(),
      "consumeFuel" -> GLOBAL.rnd.nextDouble(),

      "buyMu" -> (GLOBAL.rnd.nextDouble() * 10.0 + 1),
      "buySigma" -> (GLOBAL.rnd.nextDouble() * 10 + 1),
      "consumeMu" -> (GLOBAL.rnd.nextDouble() * 10 + 1),
      "consumeSigma" -> (GLOBAL.rnd.nextDouble() * 10 + 1),

      "enjoyWheatMu" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyWheatSigma" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyFlourMu" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyFlourSigma" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyBreadMu" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyBreadSigma" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyBeefMu" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyBeefSigma" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyOilMu" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyOilSigma" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyFuelMu" -> GLOBAL.rnd.nextDouble() * 1000,
      "enjoyFuelSigma" -> GLOBAL.rnd.nextDouble() * 1000,

      "maleEduMu" -> GLOBAL.rnd.nextDouble() * 10,
      "maleEduSigma" -> GLOBAL.rnd.nextDouble() * 10,
      "femaleEduMu" -> GLOBAL.rnd.nextDouble() * 10,
      "femaleEduSigma" -> GLOBAL.rnd.nextDouble() * 10,

      "maleBonusSalMu" -> GLOBAL.rnd.nextDouble() * 1000,
      "maleBonusSalSigma" -> GLOBAL.rnd.nextDouble() * 1000,
      "femaleBonusSalMu" -> GLOBAL.rnd.nextDouble() * 1000,
      "femaleBonusSalSigma" -> GLOBAL.rnd.nextDouble() * 1000,

      "factorySalaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
      "factorySalarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
      "farmSalaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
      "farmSalarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
      "millSalaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
      "millSalarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),

      "factoryItersMu" -> GLOBAL.rnd.nextDouble() * 20,
      "factoryItersSigma" -> GLOBAL.rnd.nextDouble() * 20,
      "farmItersMu" -> GLOBAL.rnd.nextDouble() * 20,
      "farmItersSigma" -> GLOBAL.rnd.nextDouble() * 20,
      "millItersMu" -> GLOBAL.rnd.nextDouble() * 20,
      "millItersSigma" -> GLOBAL.rnd.nextDouble() * 20,

      "farmReq" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "farmProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "farmTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "millCons" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "millProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "millTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "bakeryCons" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "bakeryProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "bakeryTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "cattleReq" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "cattleProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "cattleTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "oilReq" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "oilProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "oilTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "refineryCons" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "refineryProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
      "refineryTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble
    )).toList
}
