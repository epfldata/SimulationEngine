package meta.deep.algo

import meta.deep.IR.Predef._
import meta.runtime.{Actor}

case class AsyncSend[R, T](actorFrom: OpenCode[Actor],
                           actorRef: OpenCode[Actor],
                           methodId: Int,
                           argss: List[List[OpenCode[_]]])
                          (implicit val R: CodeType[R], implicit val T: CodeType[T])
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
        val sender = $actorFrom;
        val receiver = $actorRef;
        val requestMessage = meta.runtime.RequestMessage(sender.id, receiver.id, false, Right($methodIdC), $convertedArgs);
        var future = meta.runtime.Future[$T](requestMessage.sessionId); 
        sender.sendMessage(requestMessage);
        sender.setMessageResponseHandler(requestMessage.sessionId, (response: meta.runtime.Message) => {
          future.setValue(response.asInstanceOf[meta.runtime.ResponseMessage].arg.asInstanceOf[$T])
        })
        ${AlgoInfo.returnValue} := future
        ()"""

      AlgoInfo.stateGraph.append(
        EdgeInfo("Async with_reply f1",
          CodeNodePos(AlgoInfo.posCounter),
          CodeNodePos(AlgoInfo.posCounter + 1),
          f1,
          sendInfo = (Send[R](actorFrom, actorRef, methodId, argss, false), true)
        ))

      AlgoInfo.nextPos()
  }
}
