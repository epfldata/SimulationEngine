package ml_supplements

import breeze.linalg.DenseMatrix

import scala.collection.mutable.{Map => MutableMap}

object DatasetCreator {

  type Data = MutableMap[String, Map[String, Double]]
  type Statistics = Map[String, Double]

  def createDataset(nSamples: Int, agents: Iterable[String], nSteps: Int, stepSize: Int = 10) {
    val parameterSamples = sampleParams(nSamples / nSteps)
    val data: Seq[(Data, Data, Statistics, Statistics)] = parameterSamples.flatMap {
      case (constants, variables) => Main.simFunction(constants, variables, nSteps, stepSize, agents)
    }
    agents.foreach(agent => {
      val in_data: Seq[Seq[(String, Double)]] = data.map(_._1).map(_ (agent).toSeq.sortBy(_._1))
      val header: Array[String] = in_data.head.map(_._1).toArray

      val x: DenseMatrix[Double] = DenseMatrix(in_data.map(_.map(_._2)): _*)

      val out_data: Seq[Seq[(String, Double)]] = data.map(_._2).map(_ (agent).toSeq.sortBy(_._1))
      val y: DenseMatrix[Double] = DenseMatrix(out_data.map(_.map(_._2)): _*)

      CsvManager.writeCsvFile(x, s"target/data/${agent}_x.csv", header)
      CsvManager.writeCsvFile(y, s"target/data/${agent}_y.csv", header)
    })


    val inStat: Seq[Seq[(String, Double)]] = data.map(_._3.toSeq.sortBy(_._1))
    val inStatMatrix: DenseMatrix[Double] = DenseMatrix(inStat.map(_.map(_._2)): _*)

    val outStat: Seq[Seq[(String, Double)]] = data.map(_._4.toSeq.sortBy(_._1))
    val outStatMatrix: DenseMatrix[Double] = DenseMatrix(outStat.map(_.map(_._2)): _*)

    val stat_header: Array[String] = inStat.head.map(_._1).toArray
    CsvManager.writeCsvFile(inStatMatrix, "target/data/global_stat_input.csv", stat_header)
    CsvManager.writeCsvFile(outStatMatrix, "target/data/global_stat_output.csv", stat_header)
  }

  /**
    *
    * @param nParams number of samples to be generated
    * @return a list of tuples, each tuple is one sample,
    *         the first element is constants' data, the second element is variables' data
    */
  private def sampleParams(nParams: Int): List[(Data, Data)] = (for (_ <- 0 until nParams) yield
    (MutableMap(
      "Person" ->
        Map("number" -> (GLOBAL.rnd.nextInt(900) + 100).toDouble,
          "genderMu" -> GLOBAL.rnd.nextDouble(),
          "genderSigma" -> GLOBAL.rnd.nextDouble(),

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
          "femaleBonusSalSigma" -> GLOBAL.rnd.nextDouble() * 1000,

          "maleActiveMu" -> GLOBAL.rnd.nextDouble(),
          "maleActiveSigma" -> GLOBAL.rnd.nextDouble(),
          "femaleActiveMu" -> GLOBAL.rnd.nextDouble(),
          "femaleActiveSigma" -> GLOBAL.rnd.nextDouble()
        ),
      "Farm" ->
        Map(
          "number" -> (2 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "tacticsMu" -> GLOBAL.rnd.nextDouble(),
          "tacticsSigma" -> GLOBAL.rnd.nextDouble(),
          "required" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "Mill" ->
        Map(
          "number" -> (2 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "tacticsMu" -> GLOBAL.rnd.nextDouble(),
          "tacticsSigma" -> GLOBAL.rnd.nextDouble(),
          "consumed" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "Bakery" ->
        Map(
          "number" -> (2 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "tacticsMu" -> GLOBAL.rnd.nextDouble(),
          "tacticsSigma" -> GLOBAL.rnd.nextDouble(),
          "consumed" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "CattleFarm" ->
        Map(
          "number" -> (2 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "tacticsMu" -> GLOBAL.rnd.nextDouble(),
          "tacticsSigma" -> GLOBAL.rnd.nextDouble(),
          "required" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "OilField" ->
        Map(
          "number" -> (2 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "tacticsMu" -> GLOBAL.rnd.nextDouble(),
          "tacticsSigma" -> GLOBAL.rnd.nextDouble(),
          "required" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "Refinery" ->
        Map(
          "number" -> (2 + GLOBAL.rnd.nextInt(5)).toDouble,
          "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
          "itersMu" -> GLOBAL.rnd.nextDouble() * 20,
          "itersSigma" -> GLOBAL.rnd.nextDouble() * 20,
          "tacticsMu" -> GLOBAL.rnd.nextDouble(),
          "tacticsSigma" -> GLOBAL.rnd.nextDouble(),
          "consumed" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "produced" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble,
          "time" -> (GLOBAL.rnd.nextInt(19) + 1).toDouble
        ),
      "Landlord" ->
        Map(
          "units" -> (GLOBAL.rnd.nextInt(999) + 1).toDouble,
          "price" -> (GLOBAL.rnd.nextDouble() * 9900000 + 100000.0)
        )
    ),
      MutableMap(
        "Person" ->
          Map(
            "capitalMu" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "capitalSigma" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "total_value_destroyedMu" -> GLOBAL.rnd.nextInt(100).toDouble,
            "total_value_destroyedSigma" -> GLOBAL.rnd.nextInt(100).toDouble,
            "happinessMu" -> GLOBAL.rnd.nextInt(100).toDouble,
            "happinessSigma" -> GLOBAL.rnd.nextInt(100).toDouble,
            "salaryMu" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0),
            "salarySigma" -> (GLOBAL.rnd.nextDouble() * 999000 + 10000.0)
          ),
        "Farm" ->
          Map(
            "capitalMu" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "capitalSigma" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "total_value_destroyedMu" -> GLOBAL.rnd.nextInt(100).toDouble,
            "total_value_destroyedSigma" -> GLOBAL.rnd.nextInt(100).toDouble
          ),
        "Mill" ->
          Map(
            "capitalMu" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "capitalSigma" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "total_value_destroyedMu" -> GLOBAL.rnd.nextInt(100).toDouble,
            "total_value_destroyedSigma" -> GLOBAL.rnd.nextInt(100).toDouble
          ),
        "Bakery" ->
          Map(
            "capitalMu" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "capitalSigma" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "total_value_destroyedMu" -> GLOBAL.rnd.nextInt(100).toDouble,
            "total_value_destroyedSigma" -> GLOBAL.rnd.nextInt(100).toDouble
          ),
        "CattleFarm" ->
          Map(
            "capitalMu" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "capitalSigma" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "total_value_destroyedMu" -> GLOBAL.rnd.nextInt(100).toDouble,
            "total_value_destroyedSigma" -> GLOBAL.rnd.nextInt(100).toDouble
          ),
        "OilField" ->
          Map(
            "capitalMu" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "capitalSigma" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "total_value_destroyedMu" -> GLOBAL.rnd.nextInt(100).toDouble,
            "total_value_destroyedSigma" -> GLOBAL.rnd.nextInt(100).toDouble
          ),
        "Refinery" ->
          Map(
            "capitalMu" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "capitalSigma" -> GLOBAL.rnd.nextInt(10000).toDouble,
            "total_value_destroyedMu" -> GLOBAL.rnd.nextInt(100).toDouble,
            "total_value_destroyedSigma" -> GLOBAL.rnd.nextInt(100).toDouble
          )
      )
    )).toList

}
