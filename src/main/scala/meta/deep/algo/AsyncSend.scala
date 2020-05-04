package meta.deep.algo

import meta.deep.IR.Predef._
import meta.deep.runtime.{Actor}

case class AsyncSend[R, T](actorFrom: OpenCode[Actor],
                           actorRef: OpenCode[Actor],
                           methodId: Int,
                           argss: List[List[OpenCode[_]]])
                          (implicit val R: CodeType[R], implicit val T: CodeType[T])
    extends Algo[R] {

  override def codegen(): Unit = {
    val methodIdC = Const(methodId)

    // Convert arguments to opencode, so hat the can be used as argument inside of OpenCode
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
        val requestMessage = meta.deep.runtime.RequestMessage(sender.id, receiver.id, $methodIdC, $convertedArgs);
        requestMessage.future = requestMessage.future.asInstanceOf[meta.deep.runtime.Future[T]]
        sender.sendMessage(requestMessage);
        sender.setMessageResponseHandler(requestMessage.sessionId, (response: meta.deep.runtime.Message) => {
          requestMessage.future = requestMessage.future.setValue((response.asInstanceOf[meta.deep.runtime.ResponseMessage]).arg).asInstanceOf[meta.deep.runtime.Future[T]]
         sender.async_messages = sender.async_messages + (requestMessage.future.id -> requestMessage.future)
        })
        ${AlgoInfo.returnValue} := Some(requestMessage.future)
        ()"""

    if (T <:< codeTypeOf[Unit]){
      Send[R](actorFrom, actorRef, methodId, argss, false).codegen()
    } else {
      AlgoInfo.stateGraph.append(
        AlgoInfo.EdgeInfo("Async with_reply f1",
          AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
          AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
          f1,
          sendInfo = (Send[R](actorFrom, actorRef, methodId, argss, false), true)
        ))

      AlgoInfo.nextPos()
    }
  }
}
