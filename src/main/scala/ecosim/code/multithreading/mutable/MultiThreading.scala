package ecosim.code.multithreading.mutable

import ecosim.code
import ecosim.code.multithreading._

abstract class ThreadPrecursor[T: Numeric] extends Precursor[T, Thread[T]] {
  val prog: Vector[code.SimpleInstruction]

  def start(start_time: T): Thread[T] =
    new Thread[T](this, start_time)
}

class ConcreteThreadPrecursor[T: Numeric](
    val prog: Vector[code.SimpleInstruction]
) extends ThreadPrecursor[T]

/** A thread is obtained from a [[ThreadPrecursor]] by starting it
  * (at `start_time`).
  */
class Thread[T: Numeric](
    tp: ThreadPrecursor[T],
    private val start_time: T
) extends Executable[T] {

  // BEGIN state
  private var pos: Int = 0
  private var time: T = start_time
  // END state

  /** Runs until at most time `until`. */
  def run_until(until: T): Option[(Thread[T], T)] = {
    val (p, t, n) = code.exec[T](tp.prog, pos, time, until)
    pos = p; time = t

    n match {
      case Some(nt) => Some((this, nt))
      case None     => None
    }
  }

  protected def copy_state_to(_to: Thread[T]) {
    _to.pos = pos
    _to.time = time
    // TODO: copy tp
  }
}

/** An aggregation of executables, with two main cases:
    `ParallelExecutor[T, Thread[T]]` is a multi-threaded process, and
    `ParallelExecutor[T, ParallelExecutor[T, Thread[T]]]` is a simulation with
    several multi-threaded sims.

    @param time  Current global time.
                 Each executable's time will be `<= this.time`.
  */
class ParallelExecutor[T: Numeric, S <: Executable[T]](
    val precursor: ParallelExecutorPrecursor[T, S],
    private[this] var time: T
) extends Executable[T] {

  private[this] var l: List[S] = precursor.precursors.map(_.start(time))

  /** Births and deaths are possible and the return type is different,
      otherwise like `code.execp()`.
    */
  def run_until(until: T): Option[(ParallelExecutor[T, S], T)] = {

    def lte(a: T, b: T) = !implicitly[Numeric[T]].lt(b, a)

    while (lte(time, until) && l.nonEmpty) {
      val l2 = ecosim.global.mapopt(l, (_: S).run_until(time))
      l = l2.map(_._1).asInstanceOf[List[S]]

      this match {
        case value: Effects[S] =>
          l = ecosim.global.mapopt(l, (s: S) => value(s))
        case _ =>
      }

      if (l2.nonEmpty) time = l2.map(_._2).min
    }

    if (this.isInstanceOf[Accumulator[_]]) {
      val precursors =
        this.asInstanceOf[Accumulator[Precursor[T, S]]].collect()
      l = l ++ precursors.map(_.start(time))
    }

    if (l.nonEmpty) Some((this, time))
    else None
  }

  protected def get_executables(): List[S] =
    this match {
      case value: Effects[S] => ecosim.global.mapopt(l, (s: S) => value(s))
      case _                 => l
    }
}

abstract class ParallelExecutorPrecursor[T: Numeric, S <: Executable[T]]
    extends Precursor[T, ParallelExecutor[T, S]] {
  def precursors: List[Precursor[T, S]]

  def start(start_time: T) =
    new ParallelExecutor[T, S](this, start_time)
}

class ConcreteParallelExecutorPrecursor[T: Numeric, S <: Executable[T]](
    val precursors: List[Precursor[T, S]]
) extends ParallelExecutorPrecursor[T, S]

/** A simulation executes several multi-threaded sims in parallel. */
class Simulation[T: Numeric](
    sim_precursors: List[ParallelExecutorPrecursor[T, Thread[T]]]
) extends ParallelExecutor[T, ParallelExecutor[T, Thread[T]]](
      new ConcreteParallelExecutorPrecursor(sim_precursors),
      implicitly[Numeric[T]].zero
    )
    with Accumulator[ParallelExecutorPrecursor[T, Thread[T]]] {

  def get_sims: List[ParallelExecutor[T, Thread[T]]] =
    get_executables()
}
