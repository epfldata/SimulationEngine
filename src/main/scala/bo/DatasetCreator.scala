package bo

import Simulation.{Person, Simulation}
import breeze.linalg.DenseMatrix

object DatasetCreator {

  private val outputHeader = List("happiness", "capital", "salary")
  private val inputHeader = List("male", "education", "bonusSalary", "buyWheat", "buyFlour", "buyBread",
    "buyLand", "buyBeef", "buyOil", "buyFuel", "consumeWheat", "consumeFlour", "consumeBread", "consumeBeef",
    "consumeOil", "consumeFuel", "enjoyWheat", "enjoyFlour", "enjoyBread", "enjoyBeef", "enjoyOil", "enjoyFuelMu") ++
    outputHeader ++ Main.outputNames

  def separateDatasets(trainsetSize: Int) = {
    val nIters = 100
    val paramsList = sampleParams(trainsetSize / nIters)
    val results: List[(List[(String, Double)], List[(String, Double)])] =
      paramsList.flatMap(params => separateSimFunction(params, nIters))
    val xHeader: Array[String] = results.head._1.map(_._1).toArray
    val yHeader: Array[String] = results.head._2.map(_._1).toArray
    val x: DenseMatrix[Double] = DenseMatrix(results.map{case (xList, _) => xList.map(_._2)}:_*)
    val y: DenseMatrix[Double] = DenseMatrix(results.map{case (_, yList) => yList.map(_._2)}:_*)
    CsvManager.writeCsvFile(x, "target/scala-2.11/train_sep_X.csv", xHeader)
    CsvManager.writeCsvFile(y, "target/scala-2.11/train_sep_Y.csv", yHeader)
  }

  def simulationDatasets(trainsetSize: Int, nIters: Int) = {
    val paramsList = sampleParams(trainsetSize)
    val results: List[(Map[String, Double], Seq[(Int, Seq[Double])])] =
      paramsList.zip(paramsList.map(params => seqSimFunction(params, nIters)))
    val paramNames: Array[String] = paramsList.head.toArray.map(_._1)
    val x: DenseMatrix[Double] = DenseMatrix(results.map{case (map, _) =>
      val x: Array[Double] = paramNames.map(name => map(name))
      x.toList
    }:_*)
    val y: DenseMatrix[Double] = DenseMatrix(results.flatMap{case (_, seqOutputs: Seq[(Int, Seq[Double])]) =>
      seqOutputs.map{case (iter, outputs) => outputs.toList}
    }:_*)
    CsvManager.writeCsvFile(x, "target/scala-2.11/train_sim_X.csv", paramNames)
    CsvManager.writeCsvFile(y, "target/scala-2.11/train_sim_Y.csv", "timestep" +: Main.outputNames)
  }

  private def seqSimFunction(params: Map[String, Double], nIters: Int): Seq[(Int, Seq[Double])] = {
    val s = new Simulation(params)
    Main.initializeSimulation(s)
    for (i <- 1 to nIters) yield {
      Main.callSimulation(s, 1)
      (i, Main.outputFromState(s))
    }
  }

  private def separateSimFunction(params: Map[String, Double], nIters: Int) = {
    val s = new Simulation(params)
    Main.initializeSimulation(s)
    (for (_ <- 1 to nIters) yield {
      val personInputRows: Map[Person, Map[String, Double]] = s.sims.map{case p: Person => (p, p.getInputRow())}.toMap
      Main.callSimulation(s, 1)
      val personOutputRows: Map[Person, Map[String, Double]] = personInputRows.map{case (p, _) => (p, p.getOutputRow())}
      personInputRows.map{case (person, map) => (map.toList.sortBy(_._1), personOutputRows(person).toList.sortBy(_._1))}.toList
    }).toList.flatten
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
