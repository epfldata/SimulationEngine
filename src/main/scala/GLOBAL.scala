import breeze.stats.distributions.Gaussian

import scala.util.Random
package GLOBAL {

class Dummy;

}

package object GLOBAL {
  var silent = false
  val rnd = new Random(19)

  def mapopt[A,B](l: List[A], f: A => Option[B]) : List[B] =
    l.flatMap((a: A) => f(a) match {
      case Some(b) => List(b)
      case None    => List()
    })
}

