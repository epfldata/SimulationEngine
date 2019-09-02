package bo

import java.io.{BufferedOutputStream, FileDescriptor, FileOutputStream, FileWriter, PrintStream}

import Securities.{Commodity, Land}
import Simulation.{Person, Simulation}
import _root_.Simulation.Factory.Factory
import _root_.Simulation.SimLib._
import bo.DatasetCreator.Data
import breeze.stats.distributions.Gaussian
import spray.json.JsonParser

import scala.collection.mutable.{Map => MutableMap}

object Main {
  val bufferSize = math.pow(2, 27).toInt // 128 MB
  val initLog = new BufferedOutputStream(new FileOutputStream("target/scala-2.11/initLog"), bufferSize)
  val runLog = new BufferedOutputStream(new FileOutputStream("target/scala-2.11/runLog"), bufferSize)

  def outputFromState(s: Simulation): Seq[Double] = {
    def avgPrice(commodity: Commodity) =
      s.market(commodity).sellers.map(s => s.price(commodity).getOrElse(0.0)).sum / s.market(commodity).sellers.size

    val numberPeople = s.constants("Person")("number")
    List(
      s.sims.map(_.variables("capital") / 100).sum / s.sims.size,
      s.sims.map(_.variables("total_value_destroyed") / s.sims.size).sum,
      (numberPeople - s.sims.map{case f: Factory => f.numEmployees; case _ => 0}.sum) / numberPeople,
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

  def simFunction(params: Data): Seq[Double] = {
    val s = new Simulation(params)
    initializeSimulation(s)
    runSimulation(s, 300)
    outputFromState(s)
  }

  def main(args: Array[String]): Unit = {
    val source = scala.io.Source.fromFile(args(1))
    val jsonString = source.mkString
    source.close()

    val jsonAst = JsonParser(jsonString)
    val immutableParams = jsonAst.asJsObject.fields.mapValues(_.asJsObject.fields.mapValues(_.toString.toDouble))
    val params = MutableMap(immutableParams.toSeq: _*)

    args(0) match {
      case "generate-csv" =>
        val size = args(2).toInt
        val nSteps = args(3).toInt
        val step = if(args(4).forall(_.isDigit)) args(4).toInt else 10
        var agents = if (args(4).forall(_.isDigit)) args.slice(5, args.length) else args.slice(4, args.length)
        agents = if (agents.length == 1 && agents.head.equals("all")) GLOBAL.allAgents else agents
        DatasetCreator.createDataset(size, agents, nSteps, step)

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
        println(ChaosTest(outputFromState, params).lyapunovExponent(args(2), args(3)))

      case "plot-time" =>
        val visualizer = Viz(outputFromState, outputNames, params)
        val from = if (args.length > 2) args(2).toInt else 0
        val to = if (args.length > 3) args(3).toInt else 300
        val points = if (args.length > 4) args(4).toInt else 300
        visualizer.plotSimOverTime((from, to), points)

      case "plot-param" =>
        val visualizer = Viz(outputFromState, outputNames, params)
        val from = if (args.length > 4) args(4).toDouble else 0
        val to = if (args.length > 5) args(5).toDouble else 100
        val simIters = if (args.length > 6) args(6).toInt else 300
        visualizer.plotSimOverParam(args(2), args(3), (from, to), runSimTill = simIters)
    }

    initLog.flush()
    runLog.flush()
  }

  def initializeSimulation(s: Simulation, mute: Boolean = true, randomized: Boolean = false): Unit = {
    if (mute)
      Console.setOut(initLog)

    GLOBAL.silent = true
    GLOBAL.strongSilence = true
    val landlord = new Source(Land, 20, 100000 * 100, s)

    val genderDistr = Gaussian(s.constants("Person")("genderMu"), s.constants("Person")("genderSigma"))
    val people = for (_ <- 1 to s.constants("Person")("number").toInt) yield {
      val male = math.max(0, math.min(1, genderDistr.sample())) <= 0.5
      new Person(s, true, male)
    }

    val farms = for (_ <- 1 to s.constants("Farm")("number").toInt) yield new Farm(s)
    val mills = for (_ <- 1 to s.constants("Mill")("number").toInt) yield new Mill(s)
    val bakeries = for (_ <- 1 to s.constants("Bakery")("number").toInt) yield new Bakery(s)
    val cattleFarms = for (_ <- 1 to s.constants("CattleFarm")("number").toInt) yield new CattleFarm(s)
    val oilFields = for (_ <- 1 to s.constants("OilField")("number").toInt) yield new OilField(s)
    val refineries = for (_ <- 1 to s.constants("Refinery")("number").toInt) yield new Refinery(s)

    val sims = farms ++ mills ++ bakeries ++ cattleFarms ++ oilFields ++ refineries ++ people

    s.init(List(
      landlord
    ) ++ sims.toList, randomized)

    val capitals = Gaussian(s.constants("Person")("capitalMu"), s.constants("Person")("capitalSigma")).sample(s.sims.length)
    s.sims.zip(capitals).foreach(t => t._1.variables("capital") = t._2.round.toInt)

    Console.out.flush()
    Console.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
  }

  def runSimulation(s: Simulation, iterations: Int, mute: Boolean = true): Unit = {
    if (mute)
      Console.setOut(runLog)

    s.run(iterations)

    Console.out.flush()
    Console.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)))
  }
}
