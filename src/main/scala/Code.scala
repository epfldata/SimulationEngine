package code {


abstract class Instruction


/** An instruction that can be directly executed. */
abstract class SimpleInstruction extends Instruction {
  def exec(pos: Int) : Int
}

case class __wait[T](ticks: T) extends SimpleInstruction {
  def exec(pos: Int) : Int = pos + 1
}

class __goto(_cond: => Boolean, _next_pos: Int) extends SimpleInstruction {
  def cond = _cond
  val next_pos = _next_pos
  def exec(pos: Int) : Int = if(_cond) _next_pos else pos + 1
  override def toString = "__goto(?, " + _next_pos + ")"
}
object __goto {
  def apply(cond: => Boolean, next_pos: Int) = new __goto(cond, next_pos)
  def unapply(g: __goto) : Some[(() => Boolean, Int)] =
    Some((g.cond _, g.next_pos))
}

/** This is not a case class but a class with a companion object because
    val parameters may not be call by name. Same for [[__goto]],
    [[__repeat]], and [[__dowhile]].
*/
class __do(f : => Unit) extends SimpleInstruction {
  def exec(pos: Int) : Int = { f; pos + 1 }
  override def toString = "__do{?}"
}
object __do { def apply(f : => Unit) = new __do(f) }



/** An instruction that needs to be compiled down to SimpleInstructions
    for the program to be executable.
*/
abstract class SugarInstruction extends Instruction


case class __forever(block: Instruction*) extends SugarInstruction

/** WARNING: compiling [[__repeat]] creates state that cannot be copied when
    copying a simulation.
*/
class __repeat(_k: => Int, _block: Instruction*) extends SugarInstruction {
  def k = _k
  val block = _block
  override def toString = "__repeat(?, " + block + ")"
}
object __repeat {
  def apply(k: => Int, _block: Instruction*) = new __repeat(k, (_block: _*))
  def unapply(r: __repeat) : Some[(() => Int, Seq[Instruction])] =
    Some((r.k _, r.block))
}

class __dowhile(_cond: => Boolean,
                _block: Instruction*) extends SugarInstruction {
  def cond = _cond
  val block = _block
  override def toString = "__dowhile(" + block + ")(?)"
}
object __dowhile {
  def apply(_block: Instruction*)(_cond: => Boolean) =
    new __dowhile(_cond, (_block: _*))
  def unapply(r: __dowhile) : Some[(Seq[Instruction], () => Boolean)] =
    Some((r.block, r.cond _))
}

class __if(_cond: => Boolean, _block: Instruction*) extends SugarInstruction {
  def cond = _cond
  val block = _block
  override def toString = "__if(?) {" + block + "}"
}
object __if {
  def apply(_cond: => Boolean)(_block: Instruction*) =
    new __if(_cond, (_block: _*))
  def unapply(r: __if) : Some[(() => Boolean, Seq[Instruction])] =
    Some((r.cond _, r.block))
}


} // package code



package object code {


/** Turns SugarInstructions into SimpleInstructions.
*/
def compile(p: Instruction) : Vector[SimpleInstruction] = {
  /* Shifts all mentioned absolute code positions by offset. */
  def shift(v : Vector[SimpleInstruction], offset: Int) =
    v.map(_ match {
      case __goto(c, new_pos) => __goto(c(), new_pos + offset)
      case other @ _ => other
    })

  /* Compiles a vector of instructions.
     Recursively maintains instruction offsets and makes sure absolute
     goto positions are correct.
  */
  def compilev(block: Vector[Instruction]) : Vector[SimpleInstruction] = {
    var offset = 0;
    def f(_instr: Instruction) : Vector[SimpleInstruction] = {
      val x2 = shift(compile(_instr), offset);
      offset += x2.length;
      x2
    };
    block.map(i => f(i)).flatten
  }

  p match {
    case __if(cond, block) => {
      val v = compilev(block.toVector);
      Vector(__goto((! cond()), v.length + 1)) ++ shift(v, 1)
    }
    case __dowhile(block, cond) => {
      compilev(block.toVector) ++ Vector(__goto(cond(), 0))
    }
    case __forever(block @ _*) => compile(__dowhile(block: _*)(true))
    case __repeat(k, block) => {
      println(
        "WARNING! Compiling away __repeat creates state that we can't copy!");

      var i = 0; // There it is, that state!
      compile(__dowhile(block: _*)({
        i += 1;
        if(i < k()) true
        else { i=0; false }
      }))
    }
    case a @ _ if a.isInstanceOf[SimpleInstruction] =>
      Vector(a.asInstanceOf[SimpleInstruction])
  }
}


/** Executes a program. Works with floating-point time type.

    @param program   The program. A position `_pos` is addressed as
                     `program(_pos)`.

    @param _pos      The program counter: the position of the next instruction
                     in the program to be executed.

    @param _time     The time at the start of the execution.

    @param end_time  Execution must be interrupted as late as possible, but
                     no later than the end time.

    @return A triple `(pos, time, nt)`.
            If `pos < program.length`, then the third
            element `nt` of the triple is the
            time `Some(next_goal_time)`
            we'll reach when executing the next instruction
            `program(pos)` (and this instruction is a
            [[__wait]]), or else `None` if there is no next instruction
            (`pos == program.length`, that is, the program terminates).

    @example One can chain calls to exec: If until1 < until2,
    {{{
       val (p2, t2, _) = exec(prog, p1, t1, until1);
       exec(prog, p2, t2, until2)
    }}}
    will have the same result and effects as
    {{{
       exec(prog, p1, t1, until2)
    }}}
*/
def exec[T : Numeric](
  program: Vector[SimpleInstruction], _pos: Int, _time: T, end_time: T
) : (Int, T, Option[T]) = {
  var pos = _pos;
  var time = _time;

  def lte(a: T, b: T) = (! implicitly[Numeric[T]].lt(b, a));

  def next_time = {
    program(pos) match {
      case __wait(ti) => {
        //assert(ti.isInstanceOf[T]); // Frickin erasure
        implicitly[Numeric[T]].plus(time, ti.asInstanceOf[T])
      }
      case _ => time
    }
  }

  while((pos < program.length) && lte(next_time, end_time)) {
    time = next_time;
    pos = program(pos).exec(pos);
  }

  val nt = if(pos < program.length) {
             assert(program(pos).isInstanceOf[__wait[_]]);
             Some(next_time)
           }
           else None; // program terminates

  (pos, time, nt)
}


/** Parallel execution of multiple sims.

    @param sims       A collection of environments. An environment `s` must
                      be able to provide the first three
                      arguments of the function [[exec]], say as
                      `s.program`, `s.pos`, and `s.time`.

    @param exec_f     A function that takes an environment `s` and an end
                      time `t` and runs `exec(s.program, s.pos, s.time, t)`,
                      updates its internal state (`pos` and `time`), and
                      returns `nt` as described in the documentation for
                      [[exec]].

    @param start_time In the interval from `start_time` to `end_time`, the
                      sims are executed in parallel, respecting time order.
                      Any sim whose time in its environment is
                      initially lower than the start time is
                      first brought up to start time. This preprocessing is
                      done sequentially, not in parallel, in the sequence order
                      of the environments as stored in `sims`.

    @param end_time   See `start_time`.

    @example {{{
       class MySim(name: String, var time : Int = 0) {
         var pos  = 0
         val prog = compile(__forever(__do{ print(name) },
                                      __wait(1)))
       }

       def execf(s: MySim, until: Int) = {
         val (p, t, n) = code.exec(s.prog, s.pos, s.time, until);
         s.pos = p; s.time = t;
         n
       }

       val s1 = new MySim("a", 1);
       val s2 = new MySim("b");
       execp[MySim, Int](Vector(s1, s2), execf, 3, 6)
    }}}
    will print
    {{{
       aaabbbbababab
    }}}
    The function `execp`
    first brings `s1` up to time 3, which needs three iterations of the 
    loop in prog from time 1 (including 1), then it brings up `s2` from time 0
    (four iterations, 0, 1, 2, 3), and then there are three parallel iterations
    from step 4 to 6.

    Calls to `execp` can be composed. If this is done, the return value of the
    first call should be used as the start time of the second:
    {{{
      val Some(t) = execp[MySim, Int](Vector(s1, s2), execf, 3, 6);
      execp[MySim, Int](Vector(s1, s2), execf, t, 10)
    }}}
    will do the same as
    {{{
      execp[MySim, Int](Vector(s1, s2), execf, 3, 10)
    }}}
    no matter what the sims do.
*/
def execp[S, T: Numeric](
  sims       : Seq[S],
  exec_f     : (S, T) => Option[T], // returns next_goal_time
  start_time : T,
  end_time   : T
) = {
  def lte(a: T, b: T) = (! implicitly[Numeric[T]].lt(b, a));

  var highwater = start_time;

  while(lte(highwater, end_time))
  {
    // pick the smallest next time.
    highwater = sims.map(s => exec_f(s, highwater).get).min;
  }

  Some(highwater)
}


} // end package object code



