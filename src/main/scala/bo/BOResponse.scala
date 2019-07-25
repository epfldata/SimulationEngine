package bo

import java.io.FileWriter

import scala.util.Random


object BOResponse {

  def numberOfXYPairs = 1

  def iterations = 1000

  def evIterations = 1000 //number of iterations for estimating the expected value of

  def error(Xs: Seq[Array[Int]], Ys: Seq[Array[Double]], f: Seq[Int] => Seq[Double]): Double = {
    Xs.zip(Ys).map {
      case (xs, ys) =>
        val results = f(xs)
        results.zip(ys).map(t => math.abs(t._2 - t._1)).sum / numberOfXYPairs
    }.sum
  }

  def errorOfEV(Xs: Seq[Array[Int]], Ys: Seq[Array[Double]], f: Seq[Int] => Seq[Double]): Double = {
    Xs.zip(Ys).map {
      case (xs, ys) =>
        val samples = for (_ <- 1 to evIterations) yield f(xs)
        val results = samples
          .map(sample => sample.map(_ / evIterations))
          .reduce((s1, s2) => s1.zip(s2).map(t => t._1 + t._2))

        results.zip(ys).map(t => math.abs(t._2 - t._1)).sum / numberOfXYPairs
    }.sum
  }

  def generateXYPairs(fileName: String, bounds: Seq[(Int, Int)], f: Seq[Int] => Seq[Double]): Unit = {
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
          xs = (Random.nextInt(high - low + 1) + low) :: xs
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
