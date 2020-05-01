package meta.deep.algo

import meta.deep.IR.Predef._
import meta.deep.runtime.{Actor, Future}

case class Send[R](actorFrom: OpenCode[Actor],
                   actorRef: OpenCode[Actor],
                   methodId: Int,
                   argss: List[List[OpenCode[_]]],
                   blocking: Boolean)(implicit val R: CodeType[R])
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

    if (blocking) {
      // 1. Send message and register response handler, which stores the response in a variable
      // 2. Increase timer, so that you wait for some time
      // 3. If message has not been received, jump to timer and wait again for the next step
      // 4. Asap message is received, set return value and reset responseMessage to null, so that it can be used the next time again
      val f1: OpenCode[Unit] =
        code"""
          val sender = $actorFrom;
          val receiver = $actorRef;
          val requestMessage = meta.deep.runtime.RequestMessage(sender.id, receiver.id, $methodIdC, $convertedArgs);
          sender.sendMessage(requestMessage);
          sender.setMessageResponseHandler(requestMessage.sessionId, (response: meta.deep.runtime.Message) => {
            ${AlgoInfo.responseMessage} := response.asInstanceOf[meta.deep.runtime.ResponseMessage]
          })
          ${AlgoInfo.returnValue} := null
          ()"""
      val f2: OpenCode[Unit] = code"""()"""
      val f3: OpenCode[Unit] = code"""()"""
      val f4: OpenCode[Unit] =
        code"""
         ${AlgoInfo.returnValue} := (${AlgoInfo.responseMessage}!).arg;
         ${AlgoInfo.responseMessage} := null;
         ()"""
      AlgoInfo.stateGraph.append(
        AlgoInfo.EdgeInfo("Send b f1",
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
                          f1,
                          sendInfo = (this, true)))
      AlgoInfo.nextPos()
      AlgoInfo.stateGraph.append(
        AlgoInfo.EdgeInfo("Send b f2",
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
                          f2,
                          waitEdge = true,
                          sendInfo = (this, false)))
      AlgoInfo.nextPos()
      AlgoInfo.stateGraph.append(
        AlgoInfo.EdgeInfo("Send b f3 result",
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
                          f3,
                          cond = code"(${AlgoInfo.responseMessage}!) != null",
                          sendInfo = (this, false)))
      AlgoInfo.stateGraph.append(
        AlgoInfo.EdgeInfo("Send b f3 no result",
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter - 1),
                          f3,
                          cond = code"(${AlgoInfo.responseMessage}!) == null",
                          sendInfo = (this, false)))
      AlgoInfo.nextPos()
      AlgoInfo.stateGraph.append(
        AlgoInfo.EdgeInfo("Send b f4",
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                          AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
                          f4,
                          sendInfo = (this, false)))
      AlgoInfo.nextPos()
    } else {
          val f1: OpenCode[Unit] =
          code"""
            val sender = $actorFrom;
            val receiver = $actorRef;
            val requestMessage = meta.deep.runtime.RequestMessage(sender.id, receiver.id, $methodIdC, $convertedArgs);
            sender.sendMessage(requestMessage);
            ${AlgoInfo.returnValue} := None
            ()"""
        AlgoInfo.stateGraph.append(
          AlgoInfo.EdgeInfo("Async wo_reply f1",
                            AlgoInfo.CodeNodePos(AlgoInfo.posCounter),
                            AlgoInfo.CodeNodePos(AlgoInfo.posCounter + 1),
                            f1,
                            sendInfo = (this, true)))
        AlgoInfo.nextPos()
    }
  }
}
