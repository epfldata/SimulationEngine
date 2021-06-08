package meta.deep.algo

import meta.deep.IR.Predef._
import meta.runtime.Actor

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
          val requestMessage = meta.runtime.RequestMessage(sender.id, receiver.id, true, Right($methodIdC), $convertedArgs);
          sender.sendMessage(requestMessage);
          sender.setMessageResponseHandler(requestMessage.sessionId, (response: meta.runtime.Message) => {
            ${AlgoInfo.responseMessage} := response.asInstanceOf[meta.runtime.ResponseMessage]
          })
          ${AlgoInfo.returnValue} := null
          ()"""

      val f2: OpenCode[Unit] = code"""()"""

      val f3: OpenCode[Unit] = code"""()"""

      // add 1 to the min turn calculation so that responses are received in the next turn
      // Check for any waitMessage, in addition to waiting

      // lazy val f4: OpenCode[Unit] =
      //   code"""meta.runtime.SimRuntime.labelVals("turn").append(1);
      //         ()"""

      val f4: OpenCode[Unit] = code"""()"""

      val f5: OpenCode[Unit] =
        code"""
         ${AlgoInfo.returnValue} := (${AlgoInfo.responseMessage}!).arg;
         ${AlgoInfo.responseMessage} := null;
         ()"""

      AlgoInfo.stateGraph.append(
        EdgeInfo("Send b f1",
                          CodeNodePos(AlgoInfo.posCounter),
                          CodeNodePos(AlgoInfo.posCounter + 1),
                          f1,
                          sendInfo = (this, true)))
      AlgoInfo.nextPos()
      AlgoInfo.stateGraph.append(
        EdgeInfo("Send b f2",
                          CodeNodePos(AlgoInfo.posCounter),
                          CodeNodePos(AlgoInfo.posCounter + 1),
                          f2,
                          waitEdge = true,
                          sendInfo = (this, false)))
      // while waiting for reply, if received a wait message, wait instead
      // val waitPos = AlgoInfo.posCounter
      // AlgoInfo.nextPos()
      // val p1 = Variable[WaitMessage]
      // val fwait = WaitLabel(code"$p1.label", code"$p1.value")
      // Foreach(code"$actorFrom.popWaitMessage", p1, fwait).codegen()
      AlgoInfo.nextPos()
      AlgoInfo.stateGraph.append(
        EdgeInfo("Send b f3 get response",
                          CodeNodePos(AlgoInfo.posCounter),
                          CodeNodePos(AlgoInfo.posCounter + 1),
                          f3,
                          cond = code"(${AlgoInfo.responseMessage}!) != null",
                          sendInfo = (this, false)))
      AlgoInfo.stateGraph.append(
        EdgeInfo("Send b f4 no response",
                          CodeNodePos(AlgoInfo.posCounter),
                          CodeNodePos(AlgoInfo.posCounter - 1),
                          f4,
                          cond = code"(${AlgoInfo.responseMessage}!) == null",
                          sendInfo = (this, false)))
      AlgoInfo.nextPos()

      AlgoInfo.stateGraph.append(
        EdgeInfo("Send b f5",
                          CodeNodePos(AlgoInfo.posCounter),
                          CodeNodePos(AlgoInfo.posCounter + 1),
                          f5,
                          sendInfo = (this, false)))
      AlgoInfo.nextPos()
    } else {
          val f1: OpenCode[Unit] =
          code"""
            val sender = $actorFrom;
            val receiver = $actorRef;
            val requestMessage = meta.runtime.RequestMessage(sender.id, receiver.id, false, Right($methodIdC), $convertedArgs);
            sender.sendMessage(requestMessage);
            ${AlgoInfo.returnValue} := None
            ()"""
        AlgoInfo.stateGraph.append(
          EdgeInfo("Async wo_reply f1",
                            CodeNodePos(AlgoInfo.posCounter),
                            CodeNodePos(AlgoInfo.posCounter + 1),
                            f1,
                            sendInfo = (this, true)))
        AlgoInfo.nextPos()
    }
  }
}
