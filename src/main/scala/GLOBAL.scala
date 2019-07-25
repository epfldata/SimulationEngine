package GLOBAL {

class Dummy;

}

package object GLOBAL {
  var silent = false
  val rnd = util.Random

  val parameters = collection.mutable.Map[String, Double]()

  def mapopt[A,B](l: List[A], f: A => Option[B]) : List[B] =
    l.flatMap((a: A) => f(a) match {
      case Some(b) => List(b)
      case None    => List()
    })
}

