package ecosim

package object global {
  import scala.util.Random

  val rnd: Random.type = util.Random
  var silent = false

  def mapopt[A, B](l: List[A], f: A => Option[B]): List[B] =
    l.flatMap((a: A) =>
      f(a) match {
        case Some(b) => List(b)
        case None    => List()
    })
}
