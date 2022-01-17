package meta.deep.algo

import meta.deep.IR.Predef._

case class DoWhile(cond: OpenCode[Boolean], body: Algo[_]) extends Algo[Unit] {

  /**
    * 1. Save current position
    * 2. Execute body
    * 3. Jump to position saved in 1
    */
  override def codegen(): Unit = {

    val tmpPos = AlgoInfo.posCounter

    body.codegen()

    AlgoInfo.stateGraph.append(
      EdgeInfo("DoWhile true",
                        CodeNodePos(AlgoInfo.posCounter),
                        CodeNodePos(tmpPos),
                        code"()",
                        cond = code"$cond"))
    AlgoInfo.stateGraph.append(
      EdgeInfo("DoWhile false",
                        CodeNodePos(AlgoInfo.posCounter),
                        CodeNodePos(AlgoInfo.posCounter + 1),
                        code"()",
                        cond = code"!$cond"))
    AlgoInfo.nextPos()
  }

}
