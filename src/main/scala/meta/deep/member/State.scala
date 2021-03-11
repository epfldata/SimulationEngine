package meta.deep.member

import meta.deep.IR
import meta.deep.IR.Predef._

/**
  * Init states of class variables
  *
  * @param sym  of the variable
  * @param init code to init the variable
  * @param tpe  type of the variable
  * @tparam A codetype of the variable
  */
case class State[A](sym: IR.MtdSymbol, tpe: CodeType[A], init: OpenCode[A]) {
  override def toString =
    s"""var ${sym.asMethodSymbol.owner.name}.${sym.asMethodSymbol.name}"""
}
