package meta.deep.algo

import meta.deep.IR.Predef._

case class IfThenElse[A](cond: OpenCode[Boolean],
                         ifBody: Algo[A],
                         elseBody: Algo[A])(implicit val A: CodeType[A])
    extends Algo[A] {

  /**
    * 1. Check if condition is fulfilled,
    * 2. If yes, run if part and jump then to end of else part
    * 3. If not jump to else start and run the code
    */
  override def codegen(): Unit = {
    //Append for met1 before calling met2
    val tmpPosMet1 = AlgoInfo.posCounter
    AlgoInfo.nextPos()

    ifBody.codegen()

    //Append for metInner before calling met3
    val tmpPosMetInner = AlgoInfo.posCounter
    AlgoInfo.nextPos()

    elseBody.codegen()

    AlgoInfo.stateGraph.append(
      AlgoInfo.EdgeInfo("IfThenElse cond valid",
                        AlgoInfo.CodeNodePos(tmpPosMet1),
                        AlgoInfo.CodeNodePos(tmpPosMet1 + 1),
                        code"()",
                        cond = code"$cond"))
    AlgoInfo.stateGraph.append(
      AlgoInfo.EdgeInfo("IfThenElse cond invalid",
                        AlgoInfo.CodeNodePos(tmpPosMet1),
                        AlgoInfo.CodeNodePos(tmpPosMetInner + 1),
                        code"()",
                        cond = code"!$cond"))

    AlgoInfo.stateGraph.append(
      AlgoInfo.EdgeInfo("IfThenElse jump end else",
                        AlgoInfo.CodeNodePos(tmpPosMetInner),
                        AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                        code"()"))

  }

}
