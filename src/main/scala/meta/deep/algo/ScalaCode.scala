package meta.deep.algo

import meta.deep.IR.Predef._

case class ScalaCode[A: CodeType](cde: OpenCode[A]) extends Algo[A] {

  /**
    * Runs the code provided and saves the result as return value.
    */
  override def codegen(): Unit = {
    val met: OpenCode[Unit] = code"""${AlgoInfo.returnValue} := $cde; ()"""
    AlgoInfo.stateGraph.append(
      AlgoInfo.EdgeInfo("ScalaCode met",
                        AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                        AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
                        met))
    AlgoInfo.nextPos()
  }
}
