package meta.deep.algo

import meta.deep.IR.Predef._

/**
  * Increases timer by 1
  */
case class Wait() extends Algo[Unit] {

  override def codegen(): Unit = {
    AlgoInfo.stateGraph.append(
      EdgeInfo("wait",
                        CodeNodePos(AlgoInfo.posCounter),
                        CodeNodePos(AlgoInfo.posCounter + 1),
                        code"()",
                        waitEdge = true))
    AlgoInfo.nextPos()
  }
}
