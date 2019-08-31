package bo

import Simulation.Simulation
import breeze.linalg.DenseMatrix

import scala.collection.mutable.{Map => MutableMap}

object DatasetCreator {

  type Data = MutableMap[String, Map[String, Double]]
  type Statistics = Map[String, Double]

  def createDataset(size: Int, agents: Iterable[String], nSteps: Int, step: Int = 10)  {
    val constantSamples = sampleConstants(size / nSteps)
    val data: Seq[(Data, Data, Statistics)] = constantSamples.flatMap(simFunction(_, nSteps, step, agents))
    agents.foreach(agent => {
      val in_data: Seq[Seq[(String, Double)]] = data.map(_._1).map(_(agent).toSeq.sortBy(_._1))
      val header: Array[String] = in_data.head.map(_._1).toArray

      val x: DenseMatrix[Double] = DenseMatrix(in_data.map(_.map(_._2)): _*)

      val out_data: Seq[Seq[(String, Double)]] = data.map(_._2).map(_(agent).toSeq.sortBy(_._1))
      val y: DenseMatrix[Double] = DenseMatrix(out_data.map(_.map(_._2)): _*)

      CsvManager.writeCsvFile(x, s"target/scala-2.11/${agent}_x.csv", header)
      CsvManager.writeCsvFile(y, s"target/scala-2.11/${agent}_y.csv", header)
    })


    val stat_data: Seq[Seq[(String, Double)]] = data.map(_._3.toSeq.sortBy(_._1))
    val stat_header: Array[String] = stat_data.head.map(_._1).toArray
    val stats: DenseMatrix[Double] = DenseMatrix(stat_data.map(_.map(_._2)): _*)
    CsvManager.writeCsvFile(stats, "target/scala-2.11/global_stats.csv", stat_header)
  }

  /**
    *
    * @return   a sequence of triples indexed over time, where the first element of the triple is input data,
    *           the second in the output data and the third is the global statistics
    */
  private def simFunction(constants: Data,
                          nSteps: Int, step: Int, agents: Iterable[String]): Seq[(Data, Data, Statistics)] = {
    val s = new Simulation(constants)
    Main.initializeSimulation(s, randomized = true)
    var data_out = s.getPopulationData(agents)
    for (_ <- 1 to nSteps) yield {
      val data_in = data_out
      Main.runSimulation(s, step)
      data_out = s.getPopulationData(agents)
      (data_in, s.getPopulationData(agents), s.getGlobalStat)
    }
  }


  private def sampleConstants(trainsetSize: Int): List[Data] = (for (_ <- 0 until trainsetSize) yield
    MutableMap("Person" ->
      Map("number" -> (GLOBAL.rnd.nextInt(20) + 100).toDouble,
        "genderMu" -> GLOBAL.rnd.nextDouble(),
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
        "femaleBonusSalSigma" -> GLOBAL.rnd.nextDouble() * 1000
      ),
      "Farm" ->
        Map(
          "number" -> 1.0,
          "farmSalaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "farmSalarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "farmItersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "farmItersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "farmReq" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "farmProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "farmTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble
        ),
      "Mill" ->
        Map(
          "number" -> 1.0,
          "millSalaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "millSalarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "millItersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "millItersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "millCons" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "millProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "millTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble
        ),
      "Bakery" ->
        Map(
          "number" -> 1.0,
          "bakerySalaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "bakerySalarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "bakeryItersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "bakeryItersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "bakeryCons" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "bakeryProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "bakeryTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble
        ),
      "CattleFarm" ->
        Map(
          "number" -> 1.0,
          "cattlefarmSalaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "cattlefarmSalarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "cattlefarmItersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "cattlefarmItersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "cattlefarmReq" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "cattlefarmProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "cattlefarmTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble
        ),
      "OilField" ->
        Map(
          "number" -> 1.0,
          "oilfieldSalaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "oilfieldSalarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "oilfieldItersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "oilfieldItersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "oilfieldReq" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "oilfieldProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "oilfieldTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble
        ),
      "Refinery" ->
        Map(
          "number" -> 1.0,
          "refinerySalaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "refinerySalarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "refineryItersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "refineryItersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "refineryCons" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "refineryProd" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble,
          "refineryTime" -> (GLOBAL.rnd.nextInt(9) + 1).toDouble
        )
    )).toList

}
