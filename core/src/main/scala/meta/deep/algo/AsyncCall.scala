package meta.deep.algo

import meta.deep.IR.Predef._
import meta.runtime.{Actor}

case class AsyncCall[R, T](actorFrom: OpenCode[Actor],
                           actorRef: OpenCode[Actor],
                           methodSym: String,
                           latency: OpenCode[Int],
                           argss: List[List[OpenCode[_]]])
                          (implicit val R: CodeType[R], implicit val T: CodeType[T])
    extends Algo[R] {

  override def codegen(): Unit = {
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
        val requestMessage = meta.runtime.RequestMessage(sender.id, Some(java.util.UUID.randomUUID().toString), ${Const(methodSym)}, $convertedArgs);
        requestMessage.send_time = sender.time;
        requestMessage.latency = $latency;
        var future = meta.runtime.Future[$T](requestMessage.sessionId.get); 
        sender.sendMessage(receiver.id, requestMessage);
        sender.setMessageResponseHandler(requestMessage.sessionId.get, (response: meta.runtime.Message) => {
          future.setValue(response.asInstanceOf[meta.runtime.ResponseMessage].value.asInstanceOf[$T])
        })
        ${AlgoInfo.returnValue} := future
        ()"""

      AlgoInfo.stateGraph.append(
        EdgeInfo("Async with_reply f1",
          CodeNodePos(AlgoInfo.posCounter),
          CodeNodePos(AlgoInfo.posCounter + 1),
          f1,
          sendInfo = (Send[R](actorFrom, actorRef, methodSym, latency, argss), true)
        ))

      AlgoInfo.nextPos()
  }
}
