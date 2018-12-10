package code.multithreading


trait Precursor[T, A] {
  def start(start_time: T) : A
  // def mycopy() : Precursor
}


trait Effects[A] {
  def apply(a: A) : Option[A]
}


trait StackedEffects[A] extends Effects[A] {
  type Effect = A => Option[A]
  private var effects = List[Effect]()

  def register_effect(eff: Effect) { effects = eff :: effects }

  private def a0(a: A, l: List[Effect]) : Option[A] = l match {
    case f :: l2 => f(a) match {
      case Some(a2) => a0(a2, l2)
      case None     => None
    }
    case List() => Some(a)
  }
  def apply(a: A) = a0(a, effects)
}


protected[multithreading]
trait Accumulator[A] {
  private var acc = List[A]()

  def add(a: A) { acc = a :: acc }

  protected[multithreading]
  def collect() : List[A] = {
    println(this + ": collecting " + acc);
    val l = acc;
    acc = List();
    l
  }
}


/** A common interface for Threads and ParallelExecutors
    (multi-threaded processes).
*/
trait Executable[T] {
  def run_until(until: T) : Option[(Executable[T], T)]
}


package immutable {


abstract class ThreadPrecursor[T: Numeric] extends Precursor[T, Thread[T]] {
  val prog: Vector[code.SimpleInstruction]

  def start(start_time: T) : Thread[T] = new Thread[T](this, start_time)
}


class Thread[T: Numeric](
  tp : ThreadPrecursor[T],
  private var time       : T,
  private var pos        : Int = 0
) extends Executable[T] {

  private def lte(a: T, b: T) = (! implicitly[Numeric[T]].lt(b, a));

  /** Runs until at most time `until`. */
  def run_until(until: T) : Option[(Thread[T], T)] = {
    val tp2 = tp; // TODO tp.mycopy()

    val (p, t, n) = code.exec[T](tp2.prog, pos, time, until);

    n match {
      case Some(nt) => Some((new Thread(tp2, t, p), nt))
      case None     => None
    }
  }
}


/** @param time  current global time.
    Each executable's time will be `<= this.time`.
*/
class ParallelExecutor[T: Numeric, S <: Executable[T]](
  executables: List[S],
  time: T
) extends Executable[T] {

  private def lt(a: T, b: T)  = implicitly[Numeric[T]].lt(a, b);
  private def lte(a: T, b: T) = (! lt(b, a));

  /** Births and deaths are possible and the return type is different,
      otherwise like `code.execp()`.
  */
  def run_until(until: T) : Option[(ParallelExecutor[T,S], T)] = {
    if(lt(until, time)) Some(this, time)
      // this is an optimization, not necessary for correctness.
    else {
      var t = time;
      var l = executables;

      while(lte(t, until) && (! l.isEmpty))
      {
        val l2 = GLOBAL.mapopt(l, (_: S).run_until(t));
        l = l2.map(_._1).asInstanceOf[List[S]];

        if(this.isInstanceOf[Effects[S]])
          l = GLOBAL.mapopt(l, (s: S) => this.asInstanceOf[Effects[S]](s))

        if(! l2.isEmpty) t = l2.map(_._2).min;
      }

      if(this.isInstanceOf[Accumulator[_]]) {
        val precursors =
          this.asInstanceOf[Accumulator[Precursor[T, S]]].collect();
        l = l ++ precursors.map(_.start(time));
      }

      if(! l.isEmpty)
        Some((new ParallelExecutor[T, S](l, t), t))
      else None
    }
  }
}


} // end package immutable


package mutable {


abstract class ThreadPrecursor[T: Numeric] extends Precursor[T, Thread[T]] {
  val prog: Vector[code.SimpleInstruction]

  def start(start_time: T) : Thread[T] = new Thread[T](this, start_time)
}


class ConcreteThreadPrecursor[T: Numeric](
  val prog: Vector[code.SimpleInstruction]
) extends ThreadPrecursor[T]


/** A thread is obtained from a [[ThreadPrecursor]] by starting it
    (at `start_time`).
*/
class Thread[T : Numeric](
  tp : ThreadPrecursor[T],
  private val start_time : T
) extends Executable[T] {

  // BEGIN state
  private var pos   : Int = 0
  private var time  : T   = start_time
  // END state

  protected def copy_state_to(_to: Thread[T]) {
    _to.pos        = pos
    _to.time       = time;
    // TODO: copy tp
  }

  /** Runs until at most time `until`. */
  def run_until(until: T) : Option[(Thread[T], T)] = {
    val (p, t, n) = code.exec[T](tp.prog, pos, time, until);
    pos = p; time = t;

    n match {
      case Some(nt) => Some((this, nt))
      case None     => None
    }
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
  val precursor : ParallelExecutorPrecursor[T, S],
  private[this] var time : T
) extends Executable[T] {

  private[this] var l : List[S] = precursor.precursors.map(_.start(time))

  /** Births and deaths are possible and the return type is different,
      otherwise like `code.execp()`.
  */
  def run_until(until: T) : Option[(ParallelExecutor[T, S], T)] = {

    def lte(a: T, b: T) = (! implicitly[Numeric[T]].lt(b, a));

    while(lte(time, until) && (! l.isEmpty))
    {
      val l2 = GLOBAL.mapopt(l, (_: S).run_until(time));
      l = l2.map(_._1).asInstanceOf[List[S]];

      if(this.isInstanceOf[Effects[S]])
        l = GLOBAL.mapopt(l, (s: S) => this.asInstanceOf[Effects[S]](s))

      if(! l2.isEmpty) time = l2.map(_._2).min;
    }

    if(this.isInstanceOf[Accumulator[_]]) {
      val precursors =
        this.asInstanceOf[Accumulator[Precursor[T, S]]].collect();
      l = l ++ precursors.map(_.start(time));
    }

    if(! l.isEmpty) Some((this, time))
    else None
  }

  protected def get_executables() =
    if(this.isInstanceOf[Effects[S]])
      GLOBAL.mapopt(l, (s: S) => this.asInstanceOf[Effects[S]](s))
    else l
}


abstract class ParallelExecutorPrecursor[T: Numeric, S <: Executable[T]]
extends Precursor[T, ParallelExecutor[T, S]]  {
  def precursors : List[Precursor[T, S]]

  def start(start_time: T) = new ParallelExecutor[T, S](this, start_time)
}


class ConcreteParallelExecutorPrecursor[T: Numeric, S <: Executable[T]](
  val precursors : List[Precursor[T, S]]
) extends ParallelExecutorPrecursor[T, S]


/** A simulation executes several multi-threaded sims in parallel. */
class Simulation[T: Numeric](
  sim_precursors: List[ParallelExecutorPrecursor[T, Thread[T]]]
) extends ParallelExecutor[T, ParallelExecutor[T, Thread[T]]](
  new ConcreteParallelExecutorPrecursor(sim_precursors),
  implicitly[Numeric[T]].zero
) with Accumulator[ParallelExecutorPrecursor[T, Thread[T]]] {

  def get_sims : List[ParallelExecutor[T, Thread[T]]] = get_executables
}


} // end package mutable


