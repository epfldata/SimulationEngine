package ecosim.code.multithreading.immutable

import ecosim.code.multithreading.{Accumulator, Effects, Executable, Precursor}
import ecosim.{code, global}

abstract class ThreadPrecursor[T: Numeric] extends Precursor[T, Thread[T]] {
  val prog: Vector[code.SimpleInstruction]

  def start(start_time: T): Thread[T] = new Thread[T](this, start_time)
}

class Thread[T: Numeric](
    tp: ThreadPrecursor[T],
    private var time: T,
    private var pos: Int = 0
) extends Executable[T] {

  /** Runs until at most time `until`. */
  def run_until(until: T): Option[(Thread[T], T)] = {
    val tp2 = tp; // TODO tp.mycopy()

    val (p, t, n) = code.exec[T](tp2.prog, pos, time, until)

    n match {
      case Some(nt) => Some((new Thread(tp2, t, p), nt))
      case None     => None
    }
  }

  private def lte(a: T, b: T) = !implicitly[Numeric[T]].lt(b, a)
}

/** @param time  current global time.
    Each executable's time will be `<= this.time`.
  */
class ParallelExecutor[T: Numeric, S <: Executable[T]](
    executables: List[S],
    time: T
) extends Executable[T] {

  /** Births and deaths are possible and the return type is different,
      otherwise like `code.execp()`.
    */
  def run_until(until: T): Option[(ParallelExecutor[T, S], T)] = {
    if (lt(until, time)) Some(this, time)
    // this is an optimization, not necessary for correctness.
    else {
      var t = time
      var l = executables

      while (lte(t, until) && l.nonEmpty) {
        val l2 = global.mapopt(l, (_: S).run_until(t))
        l = l2.map(_._1).asInstanceOf[List[S]]

        this match {
          case value: Effects[S] =>
            l = global.mapopt(l, (s: S) => value(s))
          case _ =>
        }

        if (l2.nonEmpty) t = l2.map(_._2).min
      }

      if (this.isInstanceOf[Accumulator[_]]) {
        val precursors =
          this.asInstanceOf[Accumulator[Precursor[T, S]]].collect()
        l = l ++ precursors.map(_.start(time))
      }

      if (l.nonEmpty)
        Some((new ParallelExecutor[T, S](l, t), t))
      else None
    }
  }

  private def lte(a: T, b: T) = !lt(b, a)

  private def lt(a: T, b: T) = implicitly[Numeric[T]].lt(a, b)
}
