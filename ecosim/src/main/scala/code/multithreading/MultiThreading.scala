package ecosim
package code.multithreading

trait Precursor[T, A] {
  def start(start_time: T): A
  // def mycopy() : Precursor
}

trait Effects[A] {
  def apply(a: A): Option[A]
}

trait StackedEffects[A] extends Effects[A] {
  type Effect = A => Option[A]
  private var effects = List[Effect]()

  def register_effect(eff: Effect) { effects = eff :: effects }

  def apply(a: A): Option[A] = a0(a, effects)

  @scala.annotation.tailrec
  private def a0(a: A, l: List[Effect]): Option[A] = l match {
    case f :: l2 =>
      f(a) match {
        case Some(a2) => a0(a2, l2)
        case None     => None
      }
    case List() => Some(a)
  }
}

protected[multithreading] trait Accumulator[A] {
  private var acc = List[A]()

  def add(a: A) { acc = a :: acc }

  protected[multithreading] def collect(): List[A] = {
    println(this + ": collecting " + acc)
    val l = acc
    acc = List()
    l
  }
}

/** A common interface for Threads and ParallelExecutors
    (multi-threaded processes).
  */
trait Executable[T] {
  def run_until(until: T): Option[(Executable[T], T)]
}
