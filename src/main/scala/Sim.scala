package Simulation
import code._
import Owner._


trait Sim {
  type T = Int // time type
  private val zero : T = 0

  // BEGIN state
  private var current_pos  : Int = 0
  private var current_time : T   = zero
  // END state

  protected def algo   : Instruction
  private   var algo_c : Vector[SimpleInstruction] = compile(algo)

  /** Call from the constructor in inheriting classes. */
  protected def init(start_time: T) {
    current_pos    = 0;
    current_time   = start_time;
  }

  protected def copy_state_to(_to: Sim) {
    _to.current_pos    = current_pos
    _to.current_time   = current_time;
    _to.algo_c = compile(_to.algo);
  }

  /** Runs until at most time `until`. */
  def run_until(until: T) : Option[T] = {
    val (a, b, next_goal_time) =
      exec[T](algo_c, current_pos, current_time, until);

    current_pos = a;
    current_time = b;
    next_goal_time
  }
}


object Sim {

  def execp(sims: Seq[Sim], start_time: Int, end_time: Int) =
    code.execp[Sim, Int](sims, (s: Sim, t: Int) => s.run_until(t),
                         start_time, end_time)

} // end object Sim.


trait SimpleSim extends Sim {
  def action : Instruction
  override def algo = __forever(action, __wait(1))
}


abstract class SimO(
  shared: Simulation,
  start_time: Int = 0
) extends Seller with Sim {
  init(start_time);

  protected def copy_state_to(_to: SimO) = {
    super[Seller].copy_state_to(_to);
    super[Sim].copy_state_to(_to);
  }

  def mycopy(_shared: Simulation,
             _substitution: collection.mutable.Map[SimO, SimO]): SimO
}


