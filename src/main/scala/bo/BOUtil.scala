package bo

import java.io.FileWriter

object BOUtil {

  /**
    * Calculates the mean relative error between f(x) and y
    *
    * @param Xs       a sequence of predictor inputs (each input can be a vector-like itself)
    * @param Ys       a sequence of target values (each target value can be a vector-like itself)
    * @param f        the function to be applied on predictor inputs
    * @return
    */
  def meanRelativeError(Xs: Seq[Array[Int]],
                        Ys: Seq[Array[Double]],
                        f: Seq[Int] => Seq[Double]): Double = {
    val avgY = Ys.map(_.sum).sum / Ys.size
    Xs.zip(Ys).map {
      case (xs, ys) =>
        val results = f(xs)
        results.zip(ys).map(t => math.abs(t._1 - t._2)).sum
    }.sum / Ys.map(ys => math.abs(ys.sum - avgY)).sum
  }

  /**
    * Calculates the error between E(f(x)) and y where E stands for Expected Value
    *
    * @param Xs           a sequence of predictor inputs (each input can be a vector-like itself)
    * @param Ys           a sequence of target values (each target value can be a vector-like itself)
    * @param f            the function to be applied on predictor inputs and compared with y
    * @param distance     the distance measure for calculating the error
    * @param evIterations number of iterations for estimating the expected value of f(x)
    * @return
    */
  def errorOfEV(Xs: Seq[Array[Int]],
                Ys: Seq[Array[Double]],
                f: Seq[Int] => Seq[Double],
                distance: (Double, Double) => Double = (y1, y2) => math.abs(y2 - y1),
                evIterations: Int = 1000): Double = {
    val numberOfXYPairs = Xs.size
    Xs.zip(Ys).map {
      case (xs, ys) =>
        val samples = for (_ <- 1 to evIterations) yield f(xs)
        val results = samples
          .map(sample => sample.map(_ / evIterations))
          .reduce((s1, s2) => s1.zip(s2).map(t => t._1 + t._2))

        results.zip(ys).map(t => distance(t._1, t._2)).sum / numberOfXYPairs
    }.sum
  }

  /**
    * Generates input/output pairs as a data to begin optimization with
    *
    * @param fileName       the file where parameter values and input/output pairs made with it will be written
    * @param inputGenerator the random generator of the input(predictor) values
    * @param f              the function to be applied to input(params and predictor) and result the output(target) values
    * @param params         The params for the simulation
    * @param numberOfXYPairs
    */
  def generateXYPairs(fileName: String,
                      inputGenerator: () => Seq[Int],
                      f: Map[String, Double] => Seq[Int] => Seq[Double],
                      params: Map[String, Double],
                      numberOfXYPairs: Int): Unit = {
    val fileWriter = new FileWriter(fileName)
    params.foreach {
      case (param, value) =>
        fileWriter.write(s"$param: $value\t")
    }
    fileWriter.write("\n")
    for (i <- 1 to numberOfXYPairs) {
      println(s"Generating pair number $i")
      fileWriter.write("x:")
      val xs = inputGenerator()
      xs.foreach(x => fileWriter.write(x + " "))
      fileWriter.write("\n")
      fileWriter.write("y:")
      f(params)(xs).foreach(metric => fileWriter.write(metric + " "))
      fileWriter.write('\n')
    }
    fileWriter.close()
  }
}