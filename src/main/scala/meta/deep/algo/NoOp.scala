package meta.deep.algo

import meta.deep.IR.Predef._

case class NoOp[A: CodeType]() extends Algo[A] {

  /**
    * Just do nothing, it will not return anything in comparison to an empty scala code, which will return an empty unit
    */
  override def codegen(): Unit = {}
}
