package meta.runtime.simulation

import meta.runtime.Actor.AgentId
import meta.runtime.{Actor, Message, RequestMessage, ResponseMessage}
import org.apache.spark.broadcast.Broadcast

import scala.collection.mutable.ListBuffer

object SparkSims {
  def cleanSendMessage(sim: Actor): Actor = {
    sim.sendMessages = List()
    sim
  }

  def run_until(sim: Actor, step: Int): Actor = {
    sim.run_until(step)
  }

  def updateTime(sim: Actor, updatedStep: Int): Actor = {
    sim.currentTurn = updatedStep
    sim
  }

  def addReceiveMessages(sim: Actor, messages: Broadcast[scala.collection.Map[AgentId, List[Message]]]): Actor = {
    sim.receivedMessages = sim.receivedMessages ::: messages.value.getOrElse(sim.id, List()).filter(
      x =>
        x.isInstanceOf[RequestMessage] || sim.responseListeners
          .get(x.sessionId)
          .isEmpty)
    messages.value.getOrElse(sim.id, List())
      .filter(
        x =>
          sim.responseListeners.get(x.sessionId).isDefined && x
            .isInstanceOf[ResponseMessage])
      .foreach(x => {
        val handler = sim.responseListeners(x.sessionId)
        sim.responseListeners.remove(x.sessionId)
        handler(x)
      })
    sim
  }

  def getSendMessages(sim: Actor): List[Message] = {
    sim.sendMessages
  }

  def checkInterrupts(sim: Actor, time: Double): Actor = {
    val registeredInterrupts: Option[List[Message]] = sim.interrupts.remove(time)
    if (registeredInterrupts.isDefined){
      sim.receivedMessages = sim.receivedMessages ::: registeredInterrupts.get.toList
    }
    sim
  }
}
