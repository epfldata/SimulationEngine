package meta.deep.algo

import meta.deep.IR.Predef._
import meta.runtime.{Actor}

case class Interrupt[R](actor: OpenCode[Actor],
                         delay: OpenCode[Double],
                         methodId: Int,
                         argss: List[List[OpenCode[_]]])
                          (implicit val R: CodeType[R])
  extends Algo[R] {

  override def codegen(): Unit = {
    val methodIdC = Const(methodId)

    // Convert arguments to opencode, so that can be used as argument inside of OpenCode
    val initCodeO: OpenCode[List[List[Any]]] = code"Nil"
    val convertedArgs: OpenCode[List[List[Any]]] =
      argss.foldRight(initCodeO)((x, y) => {
        val initCode: OpenCode[List[Any]] = code"Nil"
        val z: OpenCode[List[Any]] =
          x.foldRight(initCode)((a, b) => code"$a::$b")
        code"$z::$y"
      })

    val f1: OpenCode[Unit] =
      code"""
        val sender = $actor;
        val receiver = $actor;
        val interruptRequest = meta.runtime.RequestMessage(sender.id, receiver.id, $methodIdC, $convertedArgs);
        // register the interrupt
        val interrupts = sender.interrupts.getOrElse($delay, List());
        sender.interrupts($delay) = interrupts ::: List(interruptRequest)
        ${AlgoInfo.returnValue} := null
        ()"""

    AlgoInfo.stateGraph.append(
      EdgeInfo("Interrupt",
        CodeNodePos(AlgoInfo.posCounter),
        CodeNodePos(AlgoInfo.posCounter + 1),
        f1
      ))

    AlgoInfo.nextPos()
  }
}

