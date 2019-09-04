package bo

import breeze.linalg.DenseMatrix

import scala.collection.mutable.{Map => MutableMap}

object DatasetCreator {

  type Data = MutableMap[String, Map[String, Double]]
  type Statistics = Map[String, Double]

  def createDataset(nSamples: Int, agents: Iterable[String], nSteps: Int, stepSize: Int = 10)  {
    val constantSamples = sampleConstants(nSamples / nSteps)
    val data: Seq[(Data, Data, Statistics)] = constantSamples.flatMap(Main.simFunction(_, nSteps, stepSize, agents))
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

  private def sampleConstants(nConstants: Int): List[Data] = (for (_ <- 0 until nConstants) yield
    MutableMap("Person" ->
      Map("number" -> (GLOBAL.rnd.nextInt(100) + 100).toDouble,
        "genderMu" -> GLOBAL.rnd.nextDouble(),
        "genderSigma" -> GLOBAL.rnd.nextDouble(),
        "capitalMu" -> GLOBAL.rnd.nextDouble() * 10000,
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

        "buyMu" -> (GLOBAL.rnd.nextDouble() * 10 + 1),
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
          "number" -> (1 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "required" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "Mill" ->
        Map(
          "number" -> (1 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "consumed" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "Bakery" ->
        Map(
          "number" -> (1 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "consumed" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "CattleFarm" ->
        Map(
          "number" -> (1 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "required" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "OilField" ->
        Map(
          "number" -> (1 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "required" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "Refinery" ->
        Map(
          "number" -> (1 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "consumed" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        )
    )).toList

}
