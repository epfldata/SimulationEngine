import breeze.stats.distributions.Gaussian

import scala.util.Random
package GLOBAL {

class Dummy;

}

package object GLOBAL {
  var silent = false
  val rnd = new Random(19)

  var parameters: Map[String, Double] = Map()
  var distributions: Map[String, Gaussian] = Map()

  def mapopt[A,B](l: List[A], f: A => Option[B]) : List[B] =
    l.flatMap((a: A) => f(a) match {
      case Some(b) => List(b)
      case None    => List()
    })

  def initParams(args: Array[String]) = {
    parameters = Map(("foodUnitMu", args(1).toDouble), ("foodUnitSigma", args(2).toDouble))
    distributions = Map(
      ("foodUnit", new Gaussian(parameters("foodUnitMu"), parameters("foodUnitSigma")))
    )
  }
}

