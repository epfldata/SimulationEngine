package ecosim

package code {

  abstract class Instruction

  /** An instruction that can be directly executed. */
  abstract class SimpleInstruction extends Instruction {
    def exec(pos: Int): Int
  }

  case class __wait[T](ticks: T) extends SimpleInstruction {
    def exec(pos: Int): Int = pos + 1
  }

  class __goto(_cond: => Boolean, _next_pos: Int) extends SimpleInstruction {
    val next_pos: Int = _next_pos

    def cond: Boolean = _cond

    def exec(pos: Int): Int = if (_cond) _next_pos else pos + 1
    override def toString: String = "__goto(?, " + _next_pos + ")"
  }
  object __goto {
    def apply(cond: => Boolean, next_pos: Int) = new __goto(cond, next_pos)
    def unapply(g: __goto): Some[(() => Boolean, Int)] =
      Some((g.cond _, g.next_pos))
  }

  /** This is not a case class but a class with a companion object because
    val parameters may not be call by name. Same for [[__goto]],
    [[__repeat]], and [[__dowhile]].
    */
  class __do(f: => Unit) extends SimpleInstruction {
    def exec(pos: Int): Int = { f; pos + 1 }
    override def toString = "__do{?}"
  }
  object __do { def apply(f: => Unit) = new __do(f) }

  /** An instruction that needs to be compiled down to SimpleInstructions
    for the program to be executable.
    */
  abstract class SugarInstruction extends Instruction

  case class __forever(block: Instruction*) extends SugarInstruction

  /** WARNING: compiling [[__repeat]] creates state that cannot be copied when
    copying a simulation.
    */
  class __repeat(_k: => Int, _block: Instruction*) extends SugarInstruction {
    val block: Seq[Instruction] = _block

    def k: Int = _k

    override def toString: String = "__repeat(?, " + block + ")"
  }
  object __repeat {
    def apply(k: => Int, _block: Instruction*) = new __repeat(k, _block: _*)
    def unapply(r: __repeat): Some[(() => Int, Seq[Instruction])] =
      Some((r.k _, r.block))
  }

  class __dowhile(_cond: => Boolean, _block: Instruction*)
      extends SugarInstruction {
    val block: Seq[Instruction] = _block

    def cond: Boolean = _cond

    override def toString: String = "__dowhile(" + block + ")(?)"
  }
  object __dowhile {
    def apply(_block: Instruction*)(_cond: => Boolean) =
      new __dowhile(_cond, _block: _*)
    def unapply(r: __dowhile): Some[(Seq[Instruction], () => Boolean)] =
      Some((r.block, r.cond _))
  }

  class __if(_cond: => Boolean, _block: Instruction*) extends SugarInstruction {
    val block: Seq[Instruction] = _block

    def cond: Boolean = _cond

    override def toString: String = "__if(?) {" + block + "}"
  }
  object __if {
    def apply(_cond: => Boolean)(_block: Instruction*) =
      new __if(_cond, _block: _*)
    def unapply(r: __if): Some[(() => Boolean, Seq[Instruction])] =
      Some((r.cond _, r.block))
  }

}
