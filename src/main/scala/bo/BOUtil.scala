package bo

import java.io.FileWriter

object BOUtil {

  /**
    * Calculates the error between f(x) and y
    *
    * @param Xs       a sequence of predictor inputs (each input can be a vector-like itself)
    * @param Ys       a sequence of target values (each target value can be a vector-like itself)
    * @param f        the function to be applied on predictor inputs
    * @param distance the distance measure for calculating the error
    * @return
    */
  def error(Xs: Seq[Array[Int]],
            Ys: Seq[Array[Double]],
            f: Seq[Int] => Seq[Double],
            distance: (Double, Double) => Double = (y1, y2) => math.abs(y2 - y1)): Double = {
    val numberOfXYPairs = Xs.size
    Xs.zip(Ys).map {
      case (xs, ys) =>
        val results = f(xs)
        results.zip(ys).map(t => distance(t._1, t._2)).sum / numberOfXYPairs
    }.sum
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
    * @param fileName the file where parameter values and input/output pairs made with it will be written
    * @param bounds   input(predictor) data will be randomly generated within the given bounds
    * @param f        the function to be applied to input(predictor) and result the output(target) values
    * @param numberOfXYPairs
    */
  def generateXYPairs(fileName: String,
                      bounds: Seq[(Int, Int)],
                      f: Seq[Int] => Seq[Double],
                      numberOfXYPairs: Int): Unit = {
    val fileWriter = new FileWriter(fileName)
    GLOBAL.parameters.foreach {
      case (param, value) =>
        fileWriter.write(s"$param: $value\t")
    }
    fileWriter.write("\n")
    for (_ <- 1 to numberOfXYPairs) {
      fileWriter.write("x:")
      var xs: List[Int] = Nil
      bounds.foreach {
        case (low, high) =>
          xs = (GLOBAL.rnd.nextInt(high - low + 1) + low) :: xs
          fileWriter.write(xs.head + " ")
      }
      fileWriter.write("\n")
      fileWriter.write("y:")
      f(xs).foreach(metric => fileWriter.write(metric + " "))
      fileWriter.write('\n')
    }
    fileWriter.close()
  }
}
